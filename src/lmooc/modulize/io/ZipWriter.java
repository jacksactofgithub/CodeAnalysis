package lmooc.modulize.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipWriter {

	public boolean appendToZip(File file , String fileName , Iterator<String> content){
		
		try {
			ZipOutputStream zOut = new ZipOutputStream((new FileOutputStream(file)));
			try {
				zOut.putNextEntry(new ZipEntry(fileName));
				
				while(content.hasNext()){
					zOut.write(content.next().getBytes("utf-8"));
					zOut.write("\r\n".getBytes());
				}
				
				zOut.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
//	public static void main(String[] args){
//		File zip = new File("D:/testzip.zip");
//		
//		ZipWriter writer = new ZipWriter();
//		
//		List<String> list = new ArrayList<String>();
//		
//		list.add("test1");
//		list.add("test2");
//		list.add("test3");
//		
//		writer.appendToZip(zip, "test.txt", list.iterator());
//		
//	}
	
}
