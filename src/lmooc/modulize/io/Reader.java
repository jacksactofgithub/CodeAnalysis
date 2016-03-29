package lmooc.modulize.io;

import java.util.Iterator;

public class Reader {

	private String prePath; // 源目录
	private String tagFolder; // 存放代码的文件夹
	private String logPrefix;		//log文件的前缀
	private static final String CFG_PATH = "./reader.cfg";

	private ReadStrategy myReader;

	public Reader() {
		initReader();
	}

	public Iterator<String> readLog(String examID, String studentNum) {
		String path = prePath + "/" + examID + "/" + studentNum;
		String fileName = logPrefix + "_" + examID + "_" + studentNum + ".txt";
		return myReader.read(path + "/" + fileName);
	}

	public Iterator<String> readJava(long timeStamp, String fName, String examID, String stuID) {
		String javaName = fName.replaceAll("/", "\\.");
		String path = prePath + "/" + examID + "/" + stuID + "/" + tagFolder + "/"
		+ timeStamp + "/" + javaName;
		return myReader.read(path);
	}

	/**
	 * 通过cfg文件判断是从本地还是web读取数据，以及路径
	 */
	private void initReader() {
		FileReader fReader = new FileReader();
		// 读取cfg信息
		Iterator<String> info = fReader.read(CFG_PATH);

		String cfg = info.next();
		String dirCfg = info.next();

		String[] cfgInfo = cfg.split(" ", 2);
		if (cfgInfo[0].equals("local")) {
			myReader = fReader;
		} else if (cfgInfo[1].equals("url")) {
			myReader = new URLReader();
		} else {
			System.out.println("error configuration");
			System.exit(1);
		}
		prePath = cfgInfo[1];
		
		String[] dirCfgInfo = dirCfg.split(" " , 2);
		tagFolder = dirCfgInfo[0];
		logPrefix = dirCfgInfo[1];
		
	}

}
