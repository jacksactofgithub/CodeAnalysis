package lmooc.modulize.model.loganalyser;

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
 * 分析log文件
 * log分为2大类，FileState和Run
 * FileState分为Timer事件和Active_Editor事件
 * 使用方法analyse分析log后，将分析生成的2类对象分别存储在
 * states和runs2个List中
 * @author Ray Liu
 *
 */
public class LogAnalyser {

	private static final String SPLIT_TAG = " ";
	
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
		
		//记录当前log的所有FileState，并按时间排序
		List<FileState> states = new ArrayList<FileState>();
		//记录运行信息
		List<RunResult> runs = new ArrayList<RunResult>();
		
//		for(int i=0 , length = log.size(); i<length ; ++i){
//			analyseOne(log.get(i) , states , runs);
//		}
		
		while(log.hasNext()){
			analyseOne(log.next() , states , runs);
		}
		
		return new LogBean(states , runs);
	}
	
	/**
	 * 解析单条log信息，若以FileState开头，则加入states，若以Run开头，则加入RunResult
	 * @param log
	 * @param states
	 * @param runs
	 * @throws JSONException 
	 */
	private void analyseOne(String log , List<FileState> states , List<RunResult> runs) throws JSONException{
		String start = log.split(" " , 2)[0];
		
		if(start.equals("FileState")){
			addOneFS(log , states);
		}
		else if(start.equals("Run")){
			addOneRun(log , runs);
		}
		
	}
	
	/**
	 * 增加一条FileState记录，将log解析为FileState对象
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
	 * 增加一条RunResult记录，将log解析为RunResult对象
	 * @param log
	 * @param run
	 * @throws JSONException 
	 */
	private void addOneRun(String log , List<RunResult> run) throws JSONException{
		String[] logInfo = log.split(" " , 3);
		String tempDate = logInfo[1];
		String report = logInfo[2];
		Date date = DateParser.string2Date(tempDate);	//精确到分的时间
		
		JSONObject runJson = new JSONObject(report);
		String proName = runJson.getString("proName");
		int totalNum = runJson.getInt("totalNum");
		int successNum = runJson.getInt("successNum");
		
		List<ClassResult> classes = new ArrayList<ClassResult>();
		//参看run的json的结构，classArray为“classes”所对应的运行类的列表
		//目前仅有1个
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
	 * 生成一个对应的ClassResult对象
	 * @param info
	 * @return
	 * @throws JSONException 
	 */
	private ClassResult initClass(Object info) throws JSONException{
		JSONObject caseJson = (JSONObject) info;
		String className = caseJson.getString("className");
		//ClassResult内含有一组CaseResult，显示每个测试用例的运行情况
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
		//默认值，若测试用例通过则没有failureMessage
		String failureMessage = null;
		boolean pass = true;
		if(success.equals("0")){
			failureMessage = caseObj.getString("failureMessage");
			pass = false;
		}
		else if(!success.equals("1")){	//既不为0页不为1，则是无效数据返回null
			return null;
		}
		
		return new CaseResult(pass , methodName , failureMessage);
	}
	
}
