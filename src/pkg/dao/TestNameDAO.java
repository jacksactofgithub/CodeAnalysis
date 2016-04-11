package pkg.dao;

import java.util.List;

import pkg.entity.CommonTest;
import pkg.entity.TestName;

public interface TestNameDAO {

	public void addTestName(CommonTest common , String testName);
	
	public List<TestName> queryTestNames(CommonTest common);
	
	public List<String> queryStringNames(CommonTest common);
}
