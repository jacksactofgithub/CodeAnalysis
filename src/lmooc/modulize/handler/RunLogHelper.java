package lmooc.modulize.handler;

import java.io.File;
import java.util.Iterator;

import lmooc.modulize.io.FileWriter;
import lmooc.modulize.io.Reader;

/**
 * 辅助工具，从提交的代码中获取Run的log，写入monitor中
 * @author Ray Liu
 *
 */
public class RunLogHelper {

	private Reader reader = new Reader();
//	private ZipWriter writer = new ZipWriter();
	private FileWriter writer = new FileWriter();
	
	public void fillInRunLog(int examId , int classMemberId){
		
		Iterator<String> runLogs = reader.getJunitResults(examId, classMemberId);
		
//		File backup = reader.getLatestBackup(examId+"", classMemberId+"");
		String folderPath = reader.getFolderPath(examId, classMemberId);
		String fileName = "monitor_"+examId+"_"+classMemberId+"_run.txt";
		String path = folderPath + File.separator + fileName;
		writer.writeToFlie( path , runLogs);
		
	}
	
}
