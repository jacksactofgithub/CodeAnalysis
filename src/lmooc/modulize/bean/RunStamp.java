package lmooc.modulize.bean;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RunStamp {

	private long millisecond;
	private String className;
	private Map<String , Boolean> tests;
	
	public RunStamp(long millisecond , String className , Map<String , Boolean> tests){
		this.millisecond = millisecond;
		this.className = className;
		this.tests = tests;
	}
	
	public long getMillisecond(){
		return millisecond;
	}
	
	public String getClassName(){
		return className;
	}
	
	public Iterator<Entry<String , Boolean>> getTests(){
		return tests.entrySet().iterator();
	}
	
}
