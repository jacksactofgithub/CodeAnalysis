package pkg.service.impl;

import java.util.Iterator;
import java.util.Map.Entry;

import lmooc.modulize.bean.RunStamp;
import pkg.dao.RunDAO;
import pkg.dao.TestDAO;
import pkg.dao.impl.RunDAOImpl;
import pkg.dao.impl.TestDAOImpl;
import pkg.entity.Run;
import pkg.service.RunService;

public class RunServiceImpl implements RunService{

	private RunDAO runDAO = new RunDAOImpl();
	private TestDAO testDAO = new TestDAOImpl();
	
	@Override
	public int saveRunStamp(Iterator<RunStamp> stamps, int stuID) {
		// TODO Auto-generated method stub
		
		while(stamps.hasNext()){
			RunStamp stamp = stamps.next();
			Run run = runDAO.addRun(stamp.getClassName(), stuID);
			addTests(run , stamp.getTests());
		}
		
		return 0;
	}
	
	private void addTests(Run run , Iterator<Entry<String , Boolean>> tests){
		while(tests.hasNext()){
			Entry<String , Boolean> entry = tests.next();
			String testName = entry.getKey();
			boolean result = entry.getValue();
			testDAO.addTest(run, testName, result);
		}
	}
	

	@Override
	public Iterator<RunStamp> getRunstamp(int stuID, String proName) {
		// TODO Auto-generated method stub
		return null;
	}

}
