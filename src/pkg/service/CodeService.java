package pkg.service;

import java.util.Iterator;

import org.json.JSONArray;

import lmooc.modulize.bean.CodeStamp;

public interface CodeService {

	public int saveStamps(Iterator<CodeStamp> stamps , int stuID , int exam);
	
	public JSONArray getCodeRecord(int stuID , String proName , int exam);
	
	public JSONArray getAvgCodeRecord(String proName , int examID);
	
	public String getStuCode(int stu_id,int time,int exam_id,String problem_name);
	
}
