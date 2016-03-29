package lmooc.modulize.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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

	public Iterator<CodeStamp> hanleFileStates(Iterator<FileState> states, String subjectName) {
		List<CodeStamp> stampList = new ArrayList<CodeStamp>();

		long fromSecond = 0;
		boolean workOn = false; // 上一次是否在该项目进行编码
		String fileName = ""; // 当前项目的文件名
		while (states.hasNext()) {
			FileState state = states.next();

			if (!workOn) {
				StateType type = state.getType();
				switch (type) {
				case Active_Editor: // 切换编辑框
					if (judgeProName(state.getFilePath(), subjectName)) {
						fileName = state.getFilePath();
						CodeStamp stamp = handleOne(fileName, fromSecond, state.getMillisecond());
						stampList.add(stamp);
						workOn = true;
					}
					break;
				case Timer:
					break;
				default:
				}
			} else { // 在当前项目中
				StateType type = state.getType();
				switch (type) {
				case Active_Editor:
					if (judgeProName(state.getFilePath(), subjectName)) {
						CodeStamp stamp = handleOne(fileName, fromSecond, state.getMillisecond());
						stampList.add(stamp);
					}
					else{
						workOn = false;
					}
					break;
				case Timer:
					CodeStamp stamp = handleOne(fileName, fromSecond, state.getMillisecond());
					stampList.add(stamp);
					break;
				default:
				}
			}

			fromSecond = state.getMillisecond();
		}

		return stampList.iterator();
	}

	/*
	 * 判断tag 是否是path所对应的项目
	 */
	private boolean judgeProName(String path, String tag) {
		// 可能有文件格式问题，需后续调整
		// 即是stamp.getSourceName()可能为Triangle/src/Triangle.java 而 subjectName
		// 可能为Triangle
		String proName = path.split("/")[0];
		if (proName.equals(tag)) {
			return true;
		}
		return false;
	}

	/*
	 * 生成一个CodeStamp对象，存储截至toSecond时间的分析参数
	 */
	private CodeStamp handleOne(String fileName, long fromSecond, long toSecond) {

		Source source = getSource(fileName, toSecond);
		Lexer lexer = new Lexer();
		lexer.segment(source);
		
		source.printVars();
		
		int lineCount = source.getLoc();
		int noteCount = source.getLon();
		int methodCount = source.getMethodCount();
		int varyCount = source.getTotalVaryCount();
		int maxCyc = source.getMaxCyc();

		return new CodeStamp(fromSecond, toSecond, lineCount, noteCount, methodCount, varyCount, maxCyc, fileName);
	}

	/**
	 * 获取到Source ， toSecond是截屏时间的millisecond
	 * 
	 * @param fileName
	 * @param toSecond
	 * @return
	 */
	private Source getSource(String fileName, long toSecond) {
		Reader reader = new Reader();
		Iterator<String> it = reader.readJava(toSecond, fileName, examID, stuID);
		return new Source(it, fileName);
	}

}

class MyKeyComparator implements Comparator<Long> {

	public int compare(Long o1, Long o2) {
		// TODO Auto-generated method stub
		return (int) (o1 - o2);
	}

}
