package lmooc.modulize.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipFile;

public class Reader {

	private String prePath; // 源目录
//	private String tagFolder; // 存放代码的文件夹
	private String logPrefix;		//log文件的前缀
	private static final String CFG_PATH = "../reader.cfg";
	
	private ZipReader zipReader = new ZipReader();

//	private ReadStrategy myReader;

	public Reader() {
		initReader();
	}

	public Iterator<String> readLog(String examID, String studentNum , ZipFile zip) {
//		String path = prePath + "/" + examID + "/" + studentNum;
		String fileName = logPrefix + "_" + examID + "_" + studentNum + ".txt";
		
		
//		return myReader.read(path + "/" + fileName);
		return zipReader.readZipFile(zip, fileName);
	}

	public Iterator<String> readJava(long timeStamp, String fName,ZipFile zip) {
		String javaName = fName.replaceAll("/", "#");
		String path = timeStamp + "/" + javaName;
		
		return zipReader.readZipFile(zip, path);
	}
	
	public List<Integer> getStudentIds(String examId){
		
		File baseFile = new File(prePath+"/"+examId);
		File[] stuFile = baseFile.listFiles();
		
		List<Integer> stuIds = new ArrayList<Integer>(stuFile.length);
		
		for(int i=0 ; i<stuFile.length ; ++i){
			int stuNum = Integer.parseInt(stuFile[i].getName());
			stuIds.add(stuNum);
		}
		
		return stuIds;
	}
	
	public File getLatestBackup(String examID , String stuID){
		File parent = new File(prePath + "/" + examID + "/" + stuID);
		File[] children = parent.listFiles();
		
		if(children == null){
			return null;
		}
		
		return children[children.length-1];
	}

	/**
	 * 通过cfg文件判断是从本地还是web读取数据，以及路径
	 */
	private void initReader() {
		FileReader fReader = new FileReader();
		// 读取cfg信息
		String cfgPath = this.getClass().getResource("/").getPath();
		Iterator<String> info = fReader.read(cfgPath + CFG_PATH);

		String cfg = info.next();
		String dirCfg = info.next();

		String[] cfgInfo = cfg.split(" ", 2);
		if (cfgInfo[0].equals("local")) {
//			myReader = fReader;
		} else if (cfgInfo[1].equals("url")) {
//			myReader = new URLReader();
		} else {
			System.out.println("error configuration");
			System.exit(1);
		}
		prePath = cfgInfo[1];
		
		String[] dirCfgInfo = dirCfg.split(" " , 2);
//		tagFolder = dirCfgInfo[0];
		logPrefix = dirCfgInfo[1];
		
	}

}
