package lmooc.modulize.handler.loganalyser;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lmooc.modulize.bean.LogBean;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.filestate.StateType;
import lmooc.modulize.bean.run.CaseResult;
import lmooc.modulize.bean.run.ClassResult;
import lmooc.modulize.bean.run.RunResult;
import util.DateParser;

/**
 * @author Ray Liu
 *
 */
public class LogAnalyser {

	private static final String SPLIT_TAG = "\t";
	
//	
//	public LogAnalyser(){
//		states = new ArrayList<FileState>();
//		runs = new ArrayList<RunResult>();
//	}
//	
	/**
	 * 
	 * @param log
	 * @throws JSONException 
	 */
	public LogBean analyse(Iterator<String> log) throws JSONException{
		
		List<FileState> states = new ArrayList<FileState>();
		List<RunResult> runs = new ArrayList<RunResult>();
		
		while(log.hasNext()){
			analyseOne(log.next() , states , runs);
		}
		
		return new LogBean(states , runs);
	}
	
	/**
	 * @param log
	 * @param states
	 * @param runs
	 * @throws JSONException 
	 */
	private void analyseOne(String log , List<FileState> states , List<RunResult> runs) throws JSONException{
		String start = log.split(SPLIT_TAG , 2)[0];
		
		if(start.equals("FileState")){
			addOneFS(log , states);
		}
		else if(start.equals("Process")){
			addOneRun(log , runs);
		}
		
	}
	
	/**
	 * @param log
	 * @param states
	 */
	private void addOneFS(String log , List<FileState> states){
		String[] infos = log.split(SPLIT_TAG);
		FileState fs;
		switch (infos.length){
		case 4: fs = new FileState(Long.parseLong(infos[2]) , StateType.Timer , null);break;
		case 5: fs = new FileState(Long.parseLong(infos[2]) , StateType.Active_Editor , infos[4]);break;
			default:return ;
		}
		states.add(fs);
	}
	
	/**
	 * @param log
	 * @param run
	 * @throws JSONException 
	 */
	private void addOneRun(String log , List<RunResult> run) throws JSONException{
		String[] logInfo = log.split(SPLIT_TAG , 3);
		String tempDate = logInfo[1];
		String report = logInfo[2];
		
		String last = report.substring(report.lastIndexOf(" ")+1);
		if((last.equals("Run"))||(last.equals("Submit"))){
			report = report.substring(0, report.lastIndexOf(" "));
		}
		
		Date date = DateParser.string2Date(tempDate); 
		
		JSONObject runJson = new JSONObject(report);
		String proName = runJson.getString("proName");
		int totalNum = runJson.getInt("totalNum");
		int successNum = runJson.getInt("successNum");
		
		List<ClassResult> classes = new ArrayList<ClassResult>();
		JSONArray classArray = runJson.getJSONArray("classes");
		ClassResult classResult ;
		for(int i=0,length=classArray.length();i<length ; i++){
			classResult = initClass(classArray.get(i));
			if(classResult!=null){
				classes.add(classResult);
			}
		}
		
		RunResult result = new RunResult(date , totalNum , successNum , proName , classes);
		run.add(result);
	}
	
	/**
	 */
	private ClassResult initClass(Object info) throws JSONException{
		JSONObject caseJson = (JSONObject) info;
		String className = caseJson.getString("className");
		List<CaseResult> resultList = new ArrayList<CaseResult>();
		
		JSONArray cases = caseJson.getJSONArray("testCases");
		CaseResult caseResult;		
		for(int i=0,length=cases.length();i<length ; ++i){
			caseResult = initCase(cases.getJSONObject(i));
			if(caseResult!=null){
				resultList.add(caseResult);
			}
		}
		
		return new ClassResult(className , resultList);
	}
	
	private CaseResult initCase(JSONObject caseObj) throws JSONException{
		String success = caseObj.getString("success");
		String methodName = caseObj.getString("methodName");
		String failureMessage = null;
		boolean pass = true;
		if(success.equals("0")){
			pass = false;
		}
		else if(!success.equals("1")){ 
			return null;
		}
		
		@SuppressWarnings("unchecked")
		Iterator<String> keys = caseObj.keys();
		
		while(keys.hasNext()){
			String key = keys.next();
			if(key.equals("failureMessage")){
				failureMessage = caseObj.getString(key);
			}
		}
		
		return new CaseResult(pass , methodName , failureMessage);
	}
	
}
