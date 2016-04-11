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
		
		if(testResult){
			test.setTest_result(1);
		}else{
			test.setTest_result(0);
		}
		
		return dbopt.save(test);
	}

	@Override
	public List<Test> queryTests(Run run) {
		// TODO Auto-generated method stub
		String hql = "from Test as test where test.run=?";
		
		@SuppressWarnings("unchecked")
		List<Test> list = dbopt.findList(hql, run);
		
		return list;
	}

}
