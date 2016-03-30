package pkg.service;

import java.util.Iterator;

import lmooc.modulize.bean.RunStamp;

public interface RunService {

	public int saveRunStamp(Iterator<RunStamp> stamps , int studentID , int subjectID);
	
	public Iterator<RunStamp> getRunstamp(int studentID , int subjectID);
	
}
