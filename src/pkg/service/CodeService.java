package pkg.service;

import java.util.Iterator;

import lmooc.modulize.bean.CodeStamp;
import pkg.entity.Attendence;

public interface CodeService {

	public int saveStamps(Iterator<CodeStamp> stamps , int stuID , int exam);
	
	public Iterator<Attendence> getStamps(int stuID , String proName);
	
}
