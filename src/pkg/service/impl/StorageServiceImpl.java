package pkg.service.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lmooc.modulize.bean.CodeStamp;
import lmooc.modulize.bean.LogBean;
import lmooc.modulize.bean.RunStamp;
import lmooc.modulize.bean.SubjectResult;
import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.run.RunResult;
import lmooc.modulize.io.Reader;
import lmooc.modulize.model.FileStateHandler;
import lmooc.modulize.model.loganalyser.LogAnalyser;
import pkg.service.CodeService;
import pkg.service.RunService;
import pkg.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	CodeService codeService;
	@Autowired
	RunService runService;
	
	private Reader reader = new Reader();
	
	@Override
	public void startStore(int examID, int tea_id) {
		// TODO Auto-generated method stub
		
		
		//最后释放Map中的存储空间
		FileStateHandler.clearMap();
	}

	@Override
	public void storeOne(int examID, int stuID) {
		// TODO Auto-generated method stub
		SubjectResult result = loadResult(examID+"", stuID+"");
		
		codeService.saveStamps(result.getCodeStamps() , stuID, examID);
		runService.saveRunStamp(result.getRunStamps(), stuID);
	}

	private SubjectResult loadResult(String examID, String studentNum) {

		Iterator<String> logs = reader.readLog(examID, studentNum);
		LogAnalyser analyser = new LogAnalyser();
		LogBean logBean;
		try {
			logBean = analyser.analyse(logs);
			Iterator<CodeStamp> codes = codeOutput(logBean.getFileStates(),examID , studentNum);
			Iterator<RunStamp> runs = runOutput(logBean.getRuns());
			
			// JSONObject run = runOutput(logBean.getRuns());
			
			return new SubjectResult(codes, runs);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	private Iterator<CodeStamp> codeOutput(Iterator<FileState> states, String examID, String stuID) {
		FileStateHandler handler = new FileStateHandler(stuID, examID);
		
		return handler.hanleFileStates(states);
	}

	private Iterator<RunStamp> runOutput(Iterator<RunResult> runs) {

		List<RunStamp> stampList = new LinkedList<RunStamp>();
		while(runs.hasNext()){
			RunResult result = runs.next();
			RunStamp stamp = result.toStamp();
			stampList.add(stamp);
		}
		
		return stampList.iterator();
	}
	
}
