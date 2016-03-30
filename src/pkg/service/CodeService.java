package pkg.service;

import java.util.Iterator;

import lmooc.modulize.bean.CodeStamp;

public interface CodeService {

	public int saveStamps(Iterator<CodeStamp> stamps , int studentID , int exam);
	
	public Iterator<CodeStamp> getStamps(int studentID , int subjectID);
	
}
