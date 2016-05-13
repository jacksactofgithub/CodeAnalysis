package pkg.service;

import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;

import lmooc.modulize.bean.CodeStamp;

public interface CodeService {

	public int saveStamps(Iterator<CodeStamp> stamps , int stuID , int exam);
	
	public JSONArray getCodeRecord(int stuID , String proName , int exam , String fileName);
	
	public JSONArray getCodeRecordByClassMemId(int classMemId , String proName , int exam , String fileName);
	
	public JSONArray getAvgCodeRecord(String proName , int examID);
	
	public String getStuCode(int stu_id,int time,int exam_id,String problem_name , String fileName);
	
	public String getStuCodeByClassMemId(int classMemId , int time , int exam_id , String problem_name , String fileName);
	
	public List<String> getAllProNames(int examId);
	
	public List<String> getStuFileNamesByStuId(int stu_id , int exam_id , String problem_name);
	
	public List<String> getStuFileNamesByClassMemId(int classMemId , int exam_id , String problem_name);
	
}
