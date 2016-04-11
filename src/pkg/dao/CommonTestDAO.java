package pkg.dao;

import java.util.List;

import pkg.entity.CommonTest;

public interface CommonTestDAO {

	public CommonTest addCommonTest(int examId , String proName);
	
	public CommonTest queryCommonTest(int examId , String proName);
	
	public List<String> queryCommonTestNames(int examId , String proName);
	
}
