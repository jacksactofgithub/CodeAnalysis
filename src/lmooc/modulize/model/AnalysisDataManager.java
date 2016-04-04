package lmooc.modulize.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.bean.LogBean;
import lmooc.modulize.bean.RunStamp;
import lmooc.modulize.bean.SubjectResult;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.run.RunResult;
import lmooc.modulize.io.Reader;
import lmooc.modulize.model.loganalyser.LogAnalyser;

/**
 * a class used for storing template data once a student`s code has been
 * analysed, it will be stored in static area.
 * 
 * @author Ray Liu
 *
 */
public class AnalysisDataManager {

	// private static final int MAP_SIZE = 300;

	private static Map<String, SubjectResult> RESULT_CACHE = new HashMap<String, SubjectResult>();

	private static Reader reader = new Reader();

	public static SubjectResult getResult(String examID, String studentNum, String problemName) {
		String key = examID + "/" + studentNum + "/" + problemName;
//		if (!RESULT_CACHE.containsKey(key)) {
//			SubjectResult result = loadResult(examID, studentNum, problemName);
//			RESULT_CACHE.put(key, result);
//			return result;
//		}
		return RESULT_CACHE.get(key);
	}

	private static SubjectResult loadResult(String examID, String studentNum, String problemName) {

		Iterator<String> logs = reader.readLog(examID, studentNum);
		LogAnalyser analyser = new LogAnalyser();
		LogBean logBean;
		try {
			logBean = analyser.analyse(logs);
			Iterator<CodeStamp> codes = codeOutput(logBean.getFileStates(),examID , studentNum , problemName);
			Iterator<RunStamp> runs = runOutput(logBean.getRuns(), problemName);
			
			// JSONObject run = runOutput(logBean.getRuns());
			
			return new SubjectResult(codes, runs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private static Iterator<CodeStamp> codeOutput(Iterator<FileState> states, String examID, String stuID,
			String problemName) {
		FileStateHandler handler = new FileStateHandler(stuID, examID);
		
//		return handler.hanleFileStates(states, problemName);
		return null;
	}

	private static Iterator<RunStamp> runOutput(Iterator<RunResult> runs, String problemName) {

		List<RunStamp> stampList = new LinkedList<RunStamp>();
		while(runs.hasNext()){
			RunResult result = runs.next();
			if(result.getProName().equals(problemName)){
				RunStamp stamp = result.toStamp();
				stampList.add(stamp);
			}
		}
		
		return stampList.iterator();
	}
}
