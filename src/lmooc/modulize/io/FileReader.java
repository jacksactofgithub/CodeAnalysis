package lmooc.modulize.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReader implements ReadStrategy{

	public Iterator<String> read(String path) {
		// TODO Auto-generated method stub
		File file = new File(path);
		List<String> result = new ArrayList<String>();
		try {
//			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			String s;
			while( (s=reader.readLine())!=null ){
				result.add(s);
			}
			
			reader.close();
			return result.iterator();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
