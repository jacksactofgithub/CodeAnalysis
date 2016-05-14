package lmooc.modulize.handler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.filestate.StateType;
import lmooc.modulize.handler.coldanalyser.Lexer;
import lmooc.modulize.handler.coldanalyser.Source;
import lmooc.modulize.io.Reader;

/**
 * 
 * @author Ray Liu
 *
 */
public class FileStateHandler {
	
	private String stuID;
	private ZipFile zip;
	
	private Reader reader = new Reader();
	
	private static Map<String , Long> startMap = new HashMap<String , Long>();

	public FileStateHandler(String stuID , ZipFile zip) {
		this.stuID = stuID;
		this.zip = zip;
	}
	
	public static long getStartTime(int studentID , String proName){
		String mapIdentifier = proName + " " + studentID;
		if(startMap.containsKey(mapIdentifier)){
			long time = startMap.get(mapIdentifier);
			return time;
		}
		
		return -1;
	}
	
	public static void clearMap(){
		startMap.clear();
	}

	public Iterator<CodeStamp> hanleFileStates(Iterator<FileState> states ) {
		List<CodeStamp> stampList = new ArrayList<CodeStamp>();
		
		String currentPath = "";
		while(states.hasNext()){
			FileState state = states.next();
			
			StateType type = state.getType();
			long startTime;
			switch (type) {
			case Active_Editor:
				String fileName = getFileName(state.getFilePath());
				String mapIdentifier = fileName+" " + stuID;
				if(startMap.containsKey(mapIdentifier)){
					startTime = startMap.get(mapIdentifier);
					
				}else{
					startMap.put(mapIdentifier, state.getMillisecond());
					startTime = state.getMillisecond();
				}
				currentPath = state.getFilePath();
				CodeStamp stamp = handleOne(currentPath , startTime , state.getMillisecond());
				stampList.add(stamp);
				break;
			case Timer:
				if(currentPath.equals("")){
					break;
				}
				startTime = startMap.get(getFileName(currentPath)+" "+stuID);
				stamp = handleOne(currentPath , startTime , state.getMillisecond());
				stampList.add(stamp);
				break;
			default:
				break;
			}
			
		}

		return stampList.iterator();
	}

	/*
	 */
	private String getFileName(String path) {
		String proName = path.split("/")[0];
		return proName;
	}

	/*
	 */
	private CodeStamp handleOne(String filePath,long startTime , long currentTime) {

		Source source = getSource(filePath, currentTime);
		Lexer lexer = new Lexer();
		lexer.segment(source);
		
		int lineCount = source.getLoc();
		int noteCount = source.getLon();
		int methodCount = source.getMethodCount();
		int varyCount = source.getTotalVaryCount();
		int maxCyc = source.getMaxCyc();

		return new CodeStamp(currentTime , currentTime - startTime, lineCount, noteCount, methodCount, varyCount, maxCyc, filePath);
	}

	/**
	 * 
	 * @param fileName
	 * @param toSecond
	 * @return
	 */
	private Source getSource(String fileName, long currentTime) {
		Iterator<String> it = reader.readJava(currentTime, fileName,zip);
		return new Source(it, fileName);
	}

}

class MyKeyComparator implements Comparator<Long> {

	public int compare(Long o1, Long o2) {
		// TODO Auto-generated method stub
		return (int) (o1 - o2);
	}

}
