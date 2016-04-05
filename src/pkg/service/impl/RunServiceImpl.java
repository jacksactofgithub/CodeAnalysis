package pkg.service.impl;

import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmooc.modulize.bean.RunStamp;
import lmooc.modulize.model.FileStateHandler;
import pkg.dao.RunDAO;
import pkg.dao.TestDAO;
import pkg.entity.Run;
import pkg.service.RunService;

@Service
public class RunServiceImpl implements RunService{

	@Autowired
	private RunDAO runDAO;
	@Autowired
	private TestDAO testDAO;
	
	@Override
	public int saveRunStamp(Iterator<RunStamp> stamps, int stuID) {
		// TODO Auto-generated method stub
		
		while(stamps.hasNext()){
			RunStamp stamp = stamps.next();
			long millisecond = stamp.getMillisecond();
			String proName = stamp.getClassName();
			long startsecond = FileStateHandler.getStartTime(stuID, proName);
			System.out.println(millisecond + "  " + startsecond);
			
			Run run = runDAO.addRun(proName, stuID , (int) ((millisecond-startsecond)/1000));
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
