package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.DBOperation;
import pkg.dao.TestDAO;
import pkg.entity.Run;
import pkg.entity.Test;

@Repository
public class TestDAOImpl implements TestDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public int addTest(Run run, String testName, boolean testResult) {
		// TODO Auto-generated method stub
		
		Test test = new Test();
		test.setRun(run);
		test.setTest_name(testName);
		test.setTest_result(testResult);
		
		return dbopt.save(test);
	}

	@Override
	public List<Test> queryTests(int run_id) {
		// TODO Auto-generated method stub
		String hql = "from test as test where test.run_id=?";
		
		@SuppressWarnings("unchecked")
		List<Test> list = dbopt.findList(hql, run_id);
		
		return list;
	}

}
