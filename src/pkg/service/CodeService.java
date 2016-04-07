package pkg.service;

import java.util.Iterator;

import org.json.JSONArray;

import lmooc.modulize.bean.CodeStamp;

public interface CodeService {

	public int saveStamps(Iterator<CodeStamp> stamps , int stuID , int exam);
	
	public JSONArray getCodeRecord(int stuID , String proName , int exam);
	
}
