package pkg.dao;

import java.util.List;

import pkg.entity.Test;

public interface TestDAO {

	public int addTest(int run_id , String testName , boolean testResult);
	
	public List<Test> queryTests(int run_id);
	
}
