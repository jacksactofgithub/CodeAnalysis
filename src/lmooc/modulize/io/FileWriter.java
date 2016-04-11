package lmooc.modulize.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class FileWriter {

	public boolean writeToFlie(String path , Iterator<String> content){
		
		try {
			java.io.FileWriter writer = new java.io.FileWriter(path);
			
			while(content.hasNext()){
				writer.append(content.next());
				writer.append("\r\n");
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
}
