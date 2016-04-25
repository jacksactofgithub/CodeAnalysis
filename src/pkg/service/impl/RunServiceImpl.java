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
import pkg.dao.CommonTestDAO;
import pkg.dao.RunDAO;
import pkg.dao.TestDAO;
import pkg.dao.TestNameDAO;
import pkg.entity.CommonTest;
import pkg.entity.Run;
import pkg.entity.Test;
import pkg.service.ExamService;
import pkg.service.RunService;
import util.cache.Cache;
import util.cache.LRUCache;

@Service
public class RunServiceImpl implements RunService{

	@Autowired
	private RunDAO runDAO;
	@Autowired
	private TestDAO testDAO;
	@Autowired
	private CommonTestDAO commonDAO;
	@Autowired
	private TestNameDAO testNameDAO;
	@Autowired
	private ExamService examService;
	// key: examId;proName;classMemId
	private static Cache<String, Map<Integer, Run>> runCache = new LRUCache<String, Map<Integer, Run>>(20,
				5 * 60 * 1000);
	private static final String seperator = ";";
	
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
		int classMemberId = examService.getClassMemberId(stuID, exam);
//		List<Run> runs = runDAO.queryRuns(classMemberId, proName , exam);
//		
//		Run originRun = generateStartRun(classMemberId, proName, exam);
//		runs.add(0, originRun);
		String key = exam+seperator+proName+seperator+classMemberId;
		if(!runCache.containsKey(key)){
			cacheRunMap(classMemberId, proName, exam);
		}
		Map<Integer , Run> runMap = runCache.get(key);
		
		return getRunJSON(runMap , proName , classMemberId , exam);
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
	
	public JSONObject getRunJSON(Map<Integer , Run> timeMap , String proName , int stuID , int exam) throws JSONException{
		List<String> testNames = findCommonTestCases(proName , exam);
		JSONObject runJSON = new JSONObject();
		
		runJSON.put("stuid",stuID);
		runJSON.put("problemName", proName);
		runJSON.put("caseNum", testNames.size());
		
		JSONArray caseNameArray = new JSONArray();
		for(String name:testNames){
			caseNameArray.put(name);
		}
		runJSON.put("caseName", caseNameArray);
		
		JSONArray resultArray = new JSONArray();
		
		int count = 0;
		while(true){
			if(!timeMap.containsKey(count)){
				break;
			}else{
				JSONObject json = formRun(timeMap.get(count) , count , testNames);
				resultArray.put(json);
				count++;
			}
		}
		
		runJSON.put("result", resultArray);
		
		return runJSON;
	}
	
	private void cacheRunMap(int classMemId , String proName , int exam_id){
		String key = exam_id+seperator+proName+seperator+classMemId;
		List<Run> runs = runDAO.queryRuns(classMemId, proName , exam_id);
		
		Run originRun = generateStartRun(classMemId, proName, exam_id);
		runs.add(0, originRun);
		
		Map<Integer , Run> runMap = mapTime(runs.iterator());
		runCache.put(key, runMap);
		
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
		while((run.hasNext())||((count <=120))){
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
	
	private int calInterval(int timeOne , int timeTwo){
		int result = timeOne - timeTwo;
		if(timeOne < timeTwo){
			result = 0 - result;
		}
		return result;
	}
	
	private JSONObject formRun(Run run , int count , List<String> testNames) throws JSONException{
		JSONObject json = new JSONObject();
		JSONArray successArray = new JSONArray();
		json.put("time", count);
		
		if(run.getRun_second() == 0){
			json.put("passNo", successArray);
			return json;
		}
		
		List<Test> tests = testDAO.queryTests(run);
		
		
		for(Test t:tests){
			String name = t.getTest_Name();
//			System.out.println(t.getSubject().getRun_id());
			if((testNames.contains(name))&&(t.getTest_Result() == 1)){
				
				successArray.put(testNames.indexOf(name));
			}
		}
		
		json.put("passNo", successArray);
		
		return json;
	}

	@Override
	public List<String> findCommonTestCases(String proName , int exam) {
		// TODO Auto-generated method stub
		CommonTest common = commonDAO.queryCommonTest(exam, proName);
		List<String> names = testNameDAO.queryStringNames(common);
		
		return names;
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
		
//		System.out.print(stuID + ":\t");
//		for(int i=0 ; i<common.size() ; ++i){
//			System.out.print(common.get(i)+"\t");
//		}
//		System.out.println();
		
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

	@Override
	public void saveCommonTests(int examID, String proName) {
		// TODO Auto-generated method stub
		List<Integer> stuList = runDAO.queryStudentID(proName , examID);
		
		List<String> result = new ArrayList<String>();
		
		if(stuList.size() >= 1){
			int stuID = stuList.get(0);
			result = findOneStudentCommon(stuID , proName , examID);
			
			for(int i=1 ; i<stuList.size() ;++i){
				stuID = stuList.get(i);
				List<String> stuCommon = findOneStudentCommon(stuID , proName , examID);
				if(stuCommon.size() == 0){
					continue;
				}
				findCommon(result , stuCommon);
			}
			
		}
		
		CommonTest common = commonDAO.addCommonTest(examID, proName);
		for(int i=0 ; i<result.size() ; i++){
			testNameDAO.addTestName(common, result.get(i));
		}
		
	}
	
}
