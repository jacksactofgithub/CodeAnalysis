package lmooc.modulize.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import util.CompressFileUtil;
import util.DateParser;

public class Reader {

	private String prePath;  
	// private String tagFolder;  
	private String logPrefix;  

	private String runPrePath;
	private String jsonPath = "mooctest/junitResult.json";

	private static final String CFG_PATH = ".." + File.separator + "reader.cfg";

	private ZipReader zipReader = new ZipReader();

	private ReadStrategy myReader;

	public Reader() {
		initReader();
	}
	
	public String getFolderPath(int examId , int stuId){
		return prePath + File.separator + examId + File.separator + stuId;
	}

	public Iterator<String> readLog(String examID, String studentNum, ZipFile zip) {
		String fileName = logPrefix + "_" + examID + "_" + studentNum + ".txt";

		return zipReader.readZipFile(zip, fileName);
	}

	public Iterator<String> readRunLog(String examID, String studentNum, ZipFile zip) {
		String fileName = prePath + File.separator + examID + File.separator + studentNum 
				+ File.separator + logPrefix
				+ "_" + examID + "_" + studentNum + "_run.txt";

		return myReader.read(fileName);
	}
	
	public Iterator<String> readJava(long timestamp , String fileName , int classMemberId , int exam_id){
		File backup = getLatestBackup(exam_id+"", classMemberId+"");
		try {
			ZipFile zip = new ZipFile(backup);
			
			return readJava(timestamp , fileName , zip);
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Iterator<String> readJava(long timeStamp, String fName, ZipFile zip) {
		String javaName = fName.replaceAll("/", "#");
		String path = timeStamp + "/" + javaName;

		return zipReader.readZipFile(zip, path);
	}

	public List<Integer> getStudentIds(String examId) {

		File baseFile = new File(prePath + File.separator + examId);
		File[] stuFile = baseFile.listFiles();

		List<Integer> stuIds = new ArrayList<Integer>(stuFile.length);

		for (int i = 0; i < stuFile.length; ++i) {
			int stuNum = Integer.parseInt(stuFile[i].getName());
			stuIds.add(stuNum);
		}

		return stuIds;
	}

	public File getLatestBackup(String examID, String stuID) {
		File parent = new File(prePath + File.separator + examID + File.separator + stuID);
		File[] children = parent.listFiles(new ZipFileFilter());

		if (children == null) {
			return null;
		}

		return children[children.length - 1];
	}

	public Iterator<String> getJunitResults(int examId, int stuId) {
		File parent = new File(runPrePath + File.separator + examId + File.separator + stuId);
		File[] children = parent.listFiles(new ZipFileFilter());

		List<String> junitResults = new ArrayList<String>();

		for (File file : children) {
			junitResults.addAll(getOneAnswerJunits(file));
		}

		return junitResults.iterator();
	}

	private List<String> getOneAnswerJunits(File answer) {
		String answerName = answer.getName();
		String noSuffix = answerName.substring(0, answerName.lastIndexOf("."));

		List<String> junitResults = new ArrayList<String>();

		String parentFilePath = answer.getParentFile().getAbsolutePath() + File.separator + noSuffix;
		// unzip file
		try {
			CompressFileUtil.unzip(answer.getAbsolutePath(), parentFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return junitResults;
		}
		// long timestamp = getTimestamp(answerName);
		File parentFile = new File(parentFilePath);
		File[] resultFiles = parentFile.listFiles(new ZipFileFilter());

		for (File file : resultFiles) {
			String result = getOneResult(file);
			if (result != null) {
				junitResults.add(result);
			}
		}

		return junitResults;
	}

	private String getOneResult(File result) {
		String fileName = result.getName();
		long timestamp = getTimestamp(fileName);

		try {
			ZipFile zip = new ZipFile(result);

			Iterator<String> junitResult = zipReader.readZipFile(zip, jsonPath);

			if (junitResult.hasNext()) {
				String log = "Run\t" + DateParser.stamp2String(timestamp) + "\t" + junitResult.next();
				return log;
			}

		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private long getTimestamp(String fileName) {
		int index = fileName.lastIndexOf(".");
		int formerIndex = "result".length();

		String time = fileName.substring(formerIndex, index);

		return Long.parseLong(time);
	}

	/**
	 */
	private void initReader() {
		FileReader fReader = new FileReader();
		String cfgPath = this.getClass().getResource("/").getPath();
		Iterator<String> info = fReader.read(cfgPath + CFG_PATH);

		String cfg = info.next();
		String dirCfg = info.next();
		String runCfg = info.next();

		String[] cfgInfo = cfg.split(" ", 2);
		if (cfgInfo[0].equals("local")) {
			 myReader = fReader;
		} else if (cfgInfo[1].equals("url")) {
			// myReader = new URLReader();
		} else {
			System.out.println("error configuration");
			System.exit(1);
		}
		prePath = cfgInfo[1];

		String[] dirCfgInfo = dirCfg.split(" ", 2);
		// tagFolder = dirCfgInfo[0];
		logPrefix = dirCfgInfo[1];

		String[] runInfo = runCfg.split(" ", 2);
		runPrePath = runInfo[1];
	}

	public static void main(String[] args) {
		Reader reader = new Reader();

		// File file = new
		// File("D:/data/university/mooctest-runs/382/12262/415_1460170001977_Answer.zip");
		// List<String> list = reader.getOneAnswerJunits(file);

		Iterator<String> list = reader.getJunitResults(382, 12262);

		while (list.hasNext()) {
			System.out.println(list.next());
		}

	}

}

class ZipFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		// TODO Auto-generated method stub
		String fileName = pathname.getName();
		if (fileName.contains(".zip")) {
			return true;
		}

		return false;
	}

}
