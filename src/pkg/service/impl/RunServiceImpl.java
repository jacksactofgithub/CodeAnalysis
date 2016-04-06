package pkg.service.impl;

import java.util.Iterator;
import java.util.List;
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
import pkg.service.RunService;

@Service
public class RunServiceImpl implements RunService{

	@Autowired
	private RunDAO runDAO;
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public int saveRunStamp(Iterator<RunStamp> stamps, int stuID) {
		// TODO Auto-generated method stub
		
		while(stamps.hasNext()){
			RunStamp stamp = stamps.next();
			long millisecond = stamp.getMillisecond();
			String proName = stamp.getClassName();
			long startsecond = FileStateHandler.getStartTime(stuID, proName);
			
			Run run = runDAO.addRun(proName, stuID , (int) ((millisecond-startsecond)/1000));
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
	public Iterator<RunStamp> getRunstamp(int stuID, String proName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public JSONArray getRunJSON(Iterator<Run> run){
		
		JSONArray runArray = new JSONArray();
		
		int count =0;		//第几分钟
		Run former = null;
		int interval = 0;
		while(run.hasNext()){
			JSONObject json;
			try {
				Run temp = run.next();
				
				if(former == null){
					json = formRun(temp , count);
					runArray.put(json);
					former = temp;
					count++;
					interval = (count*60) - former.getRun_second();
					continue;
				}
				
				int currentInterval = calInterval((count*60) , temp.getRun_second());
				if(currentInterval < interval){
					interval = currentInterval;
					former = temp;
				}else{
					json = formRun(temp , count);
					runArray.put(json);
					count++;
					interval = (count*60) - former.getRun_second();
				}
				if(!run.hasNext()){
					json = formRun(temp, count);
					runArray.put(json);
				}
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return runArray;
	}
	
	private int calInterval(int timeOne , int timeTwo){
		int result = timeOne - timeTwo;
		if(timeOne < timeTwo){
			result = 0 - result;
		}
		return result;
	}
	
	private JSONObject formRun(Run run , int count) throws JSONException{
		JSONObject json = new JSONObject();
		
//		json.put("time", stamp.getMillisecond());
//		
//		JSONArray successArray = new JSONArray();
//		
//		Iterator<Entry<String , Boolean>> tests = stamp.getTests();
//		
//		while(tests.hasNext()){
//			Entry<String , Boolean> entry = tests.next();
//			if(entry.getValue()){
//				successArray.put(entry.getKey());
//			}
//		}
//		
//		json.put("success", successArray);
		
		return json;
	}

	@Override
	public List<String> findCommonTestCases(String proName) {
		// TODO Auto-generated method stub
		return null;
	}

}
