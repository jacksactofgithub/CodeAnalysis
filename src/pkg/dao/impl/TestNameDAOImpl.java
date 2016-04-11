package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.DBOperation;
import pkg.dao.TestNameDAO;
import pkg.entity.CommonTest;
import pkg.entity.TestName;

@Repository
public class TestNameDAOImpl implements TestNameDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public void addTestName(CommonTest common, String name) {
		// TODO Auto-generated method stub
		TestName testName = new TestName();
		testName.setTest_name(name);
		testName.setCommon(common);
		
		dbopt.save(testName);
	}

	@Override
	public List<TestName> queryTestNames(CommonTest common) {
		// TODO Auto-generated method stub
		String hql = "from TestName as tn where tn.common=?";
		
		@SuppressWarnings("unchecked")
		List<TestName> testNames = dbopt.findList(hql, common);
		
		return testNames;
	}

	@Override
	public List<String> queryStringNames(CommonTest common) {
		// TODO Auto-generated method stub
		String hql = "select tn.test_name from TestName as tn where tn.common=?";
		
		@SuppressWarnings("unchecked")
		List<String> names = dbopt.findList(hql, common);
		
		return names;
	}

}
