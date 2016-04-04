package pkg.dao;

import java.util.List;

import pkg.entity.Run;
import pkg.entity.Test;

public interface TestDAO {

	public int addTest(Run run_id , String testName , boolean testResult);
	
	public List<Test> queryTests(int run_id);
	
}
