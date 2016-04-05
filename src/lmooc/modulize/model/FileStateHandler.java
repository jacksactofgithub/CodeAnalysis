package lmooc.modulize.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.filestate.StateType;
import lmooc.modulize.io.Reader;
import lmooc.modulize.model.coldanalyser.Lexer;
import lmooc.modulize.model.coldanalyser.Source;

/**
 * 管理一个学生的所有File State
 * 
 * @author Ray Liu
 *
 */
public class FileStateHandler {

	private String stuID;
	private String examID;

	public FileStateHandler(String stuID, String examID) {
		this.stuID = stuID;
		this.examID = examID;
	}

	public Iterator<CodeStamp> hanleFileStates(Iterator<FileState> states) {
		List<CodeStamp> stampList = new ArrayList<CodeStamp>();
		
		Map<String , Long> startMap = new HashMap<String , Long>();
		String currentPath = "";
		while(states.hasNext()){
			FileState state = states.next();
			
			StateType type = state.getType();
			long startTime;
			switch (type) {
			case Active_Editor:
				String fileName = getFileName(state.getFilePath());
				if(startMap.containsKey(fileName)){
					startTime = startMap.get(fileName);
					
				}else{
					startMap.put(fileName, state.getMillisecond());
					startTime = state.getMillisecond();
				}
				currentPath = state.getFilePath();
				CodeStamp stamp = handleOne(currentPath , startTime , state.getMillisecond());
				stampList.add(stamp);
				break;
			case Timer:
				startTime = startMap.get(getFileName(currentPath));
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
	 * 判断tag 是否是path所对应的项目
	 */
	private String getFileName(String path) {
		// 可能有文件格式问题，需后续调整
		// 即是stamp.getSourceName()可能为Triangle/src/Triangle.java 而 subjectName
		// 可能为Triangle
		String proName = path.split("/")[0];
		return proName;
	}

	/*
	 * 生成一个CodeStamp对象，存储截至toSecond时间的分析参数
	 */
	private CodeStamp handleOne(String filePath,long startTime , long currentTime) {

		Source source = getSource(filePath, currentTime);
		Lexer lexer = new Lexer();
		lexer.segment(source);
		
		source.printVars();
		
		int lineCount = source.getLoc();
		int noteCount = source.getLon();
		int methodCount = source.getMethodCount();
		int varyCount = source.getTotalVaryCount();
		int maxCyc = source.getMaxCyc();

		return new CodeStamp( currentTime - startTime, lineCount, noteCount, methodCount, varyCount, maxCyc, filePath);
	}

	/**
	 * 获取到Source ， toSecond是截屏时间的millisecond
	 * 
	 * @param fileName
	 * @param toSecond
	 * @return
	 */
	private Source getSource(String fileName, long currentTime) {
		Reader reader = new Reader();
		Iterator<String> it = reader.readJava(currentTime, fileName, examID, stuID);
		return new Source(it, fileName);
	}

}

class MyKeyComparator implements Comparator<Long> {

	public int compare(Long o1, Long o2) {
		// TODO Auto-generated method stub
		return (int) (o1 - o2);
	}

}
