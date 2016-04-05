package lmooc.modulize.storage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.bean.LogBean;
import lmooc.modulize.bean.RunStamp;
import lmooc.modulize.bean.SubjectResult;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.run.RunResult;
import lmooc.modulize.io.Reader;
import lmooc.modulize.model.FileStateHandler;
import lmooc.modulize.model.loganalyser.LogAnalyser;
import pkg.service.CodeService;
import pkg.service.RunService;
import pkg.service.impl.CodeServiceImpl;
import pkg.service.impl.RunServiceImpl;

public class StorageManager {

	private static Reader reader = new Reader();
	
	private static CodeService codeService = new CodeServiceImpl();
	private static RunService runService = new RunServiceImpl();
	
	/**
	 * 将参加某一场考试的所有的学生的考试分析数据进行存储
	 * @param examID
	 */
	public static void startStore(int examID , int tea_id){
//		Iterator<Integer> stuList = 
	}
	
	/**
	 * 对参加一次考试的某一个学生的数据进行存储
	 * @param examID
	 * @param stu_id
	 */
	public static void storeOne(int examID , int stuID){
		SubjectResult result = loadResult(examID+"", stuID+"");
		
		codeService.saveStamps(result.getCodeStamps() , stuID, examID);
		runService.saveRunStamp(result.getRunStamps(), stuID);
		
	}
	
	
	private static SubjectResult loadResult(String examID, String studentNum) {

		Iterator<String> logs = reader.readLog(examID, studentNum);
		LogAnalyser analyser = new LogAnalyser();
		LogBean logBean;
		try {
			logBean = analyser.analyse(logs);
			Iterator<CodeStamp> codes = codeOutput(logBean.getFileStates(),examID , studentNum);
			Iterator<RunStamp> runs = runOutput(logBean.getRuns());
			
			// JSONObject run = runOutput(logBean.getRuns());
			
			return new SubjectResult(codes, runs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private static Iterator<CodeStamp> codeOutput(Iterator<FileState> states, String examID, String stuID) {
		FileStateHandler handler = new FileStateHandler(stuID, examID);
		
		return handler.hanleFileStates(states);
	}

	private static Iterator<RunStamp> runOutput(Iterator<RunResult> runs) {

		List<RunStamp> stampList = new LinkedList<RunStamp>();
		while(runs.hasNext()){
			RunResult result = runs.next();
			RunStamp stamp = result.toStamp();
			stampList.add(stamp);
		}
		
		return stampList.iterator();
	}
	
}
