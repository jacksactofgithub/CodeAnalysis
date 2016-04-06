package pkg.service;

import java.util.Iterator;
import java.util.List;

import lmooc.modulize.bean.RunStamp;

public interface RunService {

	public int saveRunStamp(Iterator<RunStamp> stamps , int stuID);
	
	public Iterator<RunStamp> getRunstamp(int stuID , String proName);
	
	public List<String> findCommonTestCases(String proName);
}
