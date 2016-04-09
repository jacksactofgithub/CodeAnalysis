package lmooc.modulize.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader {

	public Iterator<String> readZipFile(ZipFile zip , String path){
		List<String> result = new ArrayList<String>();
		ZipEntry entry = zip.getEntry(path);
		
		if(entry == null){
			return result.iterator();
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)));
			
			String line;
			while((line = reader.readLine())!=null){
				result.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
				}
			}
		}
		
		
		return result.iterator();
	}
	
}
