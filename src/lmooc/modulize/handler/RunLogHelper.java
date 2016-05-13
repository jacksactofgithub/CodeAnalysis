package lmooc.modulize.handler;

import java.io.File;
import java.util.Iterator;

import lmooc.modulize.io.FileWriter;
import lmooc.modulize.io.Reader;

/**
 * @author Ray Liu
 *
 */
public class RunLogHelper {

	private Reader reader = new Reader();
	private FileWriter writer = new FileWriter();
	
	public void fillInRunLog(int examId , int classMemberId){
		
		Iterator<String> runLogs = reader.getJunitResults(examId, classMemberId);
		
		String folderPath = reader.getFolderPath(examId, classMemberId);
		String fileName = "monitor_"+examId+"_"+classMemberId+"_run.txt";
		String path = folderPath + File.separator + fileName;
		writer.writeToFlie( path , runLogs);
		
	}
	
}
