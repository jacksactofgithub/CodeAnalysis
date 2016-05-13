package pkg.service;

import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import lmooc.modulize.bean.RunStamp;

public interface RunService {

	public int saveRunStamp(Iterator<RunStamp> stamps , int stuID , int examID);
	
	public JSONObject getRuns(int stuID , String proName , int exam) throws JSONException;
	
	public JSONObject getRunsByClassMemId(int classMemId , String proName , int exam) throws JSONException;
	
	/**
	 * @param proName
	 * @return
	 */
	public List<String> findCommonTestCases(String proName , int exam);
	
	public JSONObject getAvgPassResult(String proName , int exam) throws JSONException;
	
	public void saveCommonTests(int examID , String proName);
	
}
