package pkg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmooc.modulize.bean.RunStamp;
import lmooc.modulize.model.FileStateHandler;
import pkg.dao.RunDAO;
import pkg.dao.TestDAO;
import pkg.entity.Run;
import pkg.entity.Test;
import pkg.service.RunService;

@Service
public class RunServiceImpl implements RunService{

	@Autowired
	private RunDAO runDAO;
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public int saveRunStamp(Iterator<RunStamp> stamps, int stuID , int examID) {
		// TODO Auto-generated method stub
		
		while(stamps.hasNext()){
			RunStamp stamp = stamps.next();
			long millisecond = stamp.getMillisecond();
			String proName = stamp.getClassName();
			long startsecond = FileStateHandler.getStartTime(stuID, proName);
			
			Run run = runDAO.addRun(proName, stuID , (int) ((millisecond-startsecond)/1000) , examID , millisecond);
			addTests(run , stamp.getTests());
		}
		
		return 0;
	}
	
	private void addTests(Run run , Iterator<Entry<String , Boolean>> tests){
		while(tests.hasNext()){
			Entry<String , Boolean> entry = tests.next();
			String testName = entry.getKey();
			boolean result = entry.getValue();
			testDAO.addTest(run, testName, result);
		}
	}
	

	@Override
	public JSONObject getRuns(int stuID, String proName , int exam) throws JSONException {
		// TODO Auto-generated method stub
		
		List<Run> runs = runDAO.queryRuns(stuID, proName , exam);
		
		Run originRun = generateStartRun(stuID, proName, exam);
		runs.add(0, originRun);
		
		return getRunJSON(runs.iterator() , proName , stuID , exam);
	}
	
	private Run generateStartRun(int stuId , String proName , int exam){
		Run run = new Run();
		run.setPro_name(proName);
		run.setExam_id(exam);
		run.setRun_second(0);
		run.setStudent_id(stuId);
		run.setTimestamp(0);
		
		return run;
	}
	
	public JSONObject getRunJSON(Iterator<Run> run , String proName , int stuID , int exam) throws JSONException{
		List<String> testNames = findCommonTestCases(proName , exam);
		JSONObject runJSON = new JSONObject();
		
		runJSON.put("stuid",stuID);
		runJSON.put("problemName", proName);
		runJSON.put("caseNumber", testNames.size());
		
		JSONArray caseNameArray = new JSONArray();
		for(String name:testNames){
			caseNameArray.put(name);
		}
		runJSON.put("caseName", caseNameArray);
//		runJSON.put("result", formResultArray(run, proName, testNames));
		
		Map<Integer , Run> timeMap = mapTime(run);
		
		Iterator<Entry<Integer , Run>> entryIt = timeMap.entrySet().iterator();
		
		JSONArray resultArray = new JSONArray();
		
		while(entryIt.hasNext()){
			Entry<Integer , Run> entry = entryIt.next();
			JSONObject json = formRun(entry.getValue(), entry.getKey(), testNames);
			resultArray.put(json);
		}
		
		runJSON.put("result", resultArray);
		
		return runJSON;
	}
	
	private Map<Integer , Run> mapTime(Iterator<Run> run){
		Map<Integer , Run> map = new HashMap<Integer , Run>();
		if((run == null)||(!run.hasNext())){
			return map;
		}
		
		int count =0;		//第几分钟
		Run former = null;
		Run current = null;
		int interval = 0;
		while((run.hasNext())||((count*60)<current.getRun_second())){
			if(former == null){
				Run temp = run.next();
				map.put(count, temp);
				former = temp;
				if(run.hasNext()){
					current = run.next();
				}else{
					break;
				}
				count++;
				interval = (count*60);
				continue;
			}
			
			if(former.getRun_second() == current.getRun_second()){
				former = current;
				if(run.hasNext()){
					current = run.next();
				}else{
					interval = 0;
				}
			}
			
			int currentInterval = calInterval((count*60) , current.getRun_second());
			if(currentInterval < interval){
				interval = currentInterval;
				former = current;
				if(run.hasNext()){
					current = run.next();
				}else{
					interval = 0;
				}
			}else{
				map.put(count, former);
				count++;
				interval = (count*60) - former.getRun_second();
			}
			
			if((!run.hasNext()) &&((count*60 > current.getRun_second()))){	//保证时间够
				map.put(count, current);
			}
				
		}
		
		return map;
	}
	
//	private JSONArray formResultArray(Iterator<Run> run , String proName , List<String> testNames){
//		
//		JSONArray runArray = new JSONArray();
//		
//		int count =0;		//第几分钟
//		Run former = null;
//		Run current = null;
//		int interval = 0;
//		while((run.hasNext())||((count*60)<current.getRun_second())){
//			JSONObject json;
//			try {
//				
//				if(former == null){
//					Run temp = run.next();
//					json = formRun(temp , count , testNames);
//					runArray.put(json);
//					former = temp;
//					if(run.hasNext()){
//						current = run.next();
//					}else{
//						current = temp;
//						interval = 0;
//					}
//					count++;
//					interval = (count*60);
//					continue;
//				}
//				
//				int currentInterval = calInterval((count*60) , current.getRun_second());
//				if(currentInterval < interval){
//					interval = currentInterval;
//					former = current;
//					if(run.hasNext()){
//						current = run.next();
//					}else{
//						interval = 0;
//					}
//				}else{
//					json = formRun(current , count ,testNames);
//					runArray.put(json);
//					count++;
//					interval = (count*60) - former.getRun_second();
//				}
//				
//				if((!run.hasNext()) &&((count*60 > current.getRun_second()))){	//保证时间够
//					json = formRun(current, count , testNames);
//					runArray.put(json);
//				}
//				
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		return runArray;
//		
//	}
	
	private int calInterval(int timeOne , int timeTwo){
		int result = timeOne - timeTwo;
		if(timeOne < timeTwo){
			result = 0 - result;
		}
		return result;
	}
	
	private JSONObject formRun(Run run , int count , List<String> testNames) throws JSONException{
		JSONObject json = new JSONObject();
		
		List<Test> tests = testDAO.queryTests(run);
		json.put("time", count);
		
		JSONArray successArray = new JSONArray();
		
		for(Test t:tests){
			String name = t.getTest_Name();
			if((testNames.contains(name))&&(t.getTest_Result() == true)){
				successArray.put(name);
			}
		}
		
		json.put("passNo", successArray);
		
		return json;
	}

	@Override
	public List<String> findCommonTestCases(String proName , int exam) {
		// TODO Auto-generated method stub
		
		List<Integer> stuList = runDAO.queryStudentID(proName , exam);
		
		List<String> result = null;
		
		if(stuList.size() >= 1){
			int stuID = stuList.get(0);
			result = findOneStudentCommon(stuID , proName , exam);
			
			for(int i=1 ; i<stuList.size() ;++i){
				stuID = stuList.get(i);
				findCommon(result , findOneStudentCommon(stuID , proName , exam));
			}
			
		}
		
		return result;
	}
	
	/**
	 * 找到一个学生的所有公共测试，即将该学生每个时间点的测试用例进行统计
	 * @param stu_id
	 * @param proName 
	 * @return
	 */
	private List<String> findOneStudentCommon(int stuID , String proName , int exam){
		List<Run> runs = runDAO.queryRuns(stuID, proName , exam);
		List<String> common = new LinkedList<String>();
		
		List<Test> temp = testDAO.queryTests(runs.get(0));
		List<String> strTemp = transList(temp);
		common.addAll(strTemp);
		
		for(int i=1 ; i<runs.size() ; ++i){
			temp = testDAO.queryTests(runs.get(i));	//查找该次run下的所有test
			strTemp = transList(temp);
			findCommon(common , strTemp);
		}
		
		return common;
	}
	
	/**
	 * 将Test类型的list转为string类型的，string是test的名称
	 * @param from
	 * @return
	 */
	private List<String> transList(List<Test> from){
		List<String> result = new ArrayList<String>(from.size());
		
		for(int i=0;i<from.size();i++){
			result.add(from.get(i).getTest_Name());
		}
		
		return result;
	}

	/**
	 * 找到两个list中的所有相同元素，将相同元素存入common中
	 * @param common
	 * @param tag
	 */
	private void findCommon(List<String> common , List<String> tag){
		Iterator<String> it = common.iterator();
		
		while(it.hasNext()){
			String temp = it.next();
			if(!tag.contains(temp)){
				it.remove();
			}
		}
		
	}

	@Override
	public JSONObject getAvgPassResult(String proName, int exam) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
