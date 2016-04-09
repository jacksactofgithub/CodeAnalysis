package pkg.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipFile;

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
		List<Integer> clazzMemberIds = reader.getStudentIds(examID+"");
		
		for(int i=0 ; i<clazzMemberIds.size() ; ++i){
			int memberId = clazzMemberIds.get(i);
			storeOne(examID , memberId , memberId);
		}
		
		//最后释放Map中的存储空间
		FileStateHandler.clearMap();
	}

	@Override
	public void storeOne(int examID, int classMemberId , int studentId) {
		// TODO Auto-generated method stub
		SubjectResult result = loadResult(examID+"", classMemberId+"");
		
		codeService.saveStamps(result.getCodeStamps() , studentId, examID);
		runService.saveRunStamp(result.getRunStamps(), studentId , examID);
	}

	private SubjectResult loadResult(String examID, String studentNum) {
		File backupFile = reader.getLatestBackup(examID, studentNum);
		
		try {
			ZipFile backupZip = new ZipFile(backupFile);
			
			Iterator<String> logs = reader.readLog(examID, studentNum , backupZip);
			LogAnalyser analyser = new LogAnalyser();
			LogBean logBean;
			try {
				logBean = analyser.analyse(logs);
				Iterator<CodeStamp> codes = codeOutput(logBean.getFileStates(),examID , studentNum , backupZip);
				Iterator<RunStamp> runs = runOutput(logBean.getRuns());
				
				// JSONObject run = runOutput(logBean.getRuns());
				
				return new SubjectResult(codes, runs);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private Iterator<CodeStamp> codeOutput(Iterator<FileState> states, String examID, String stuID , ZipFile backupZip) {
		FileStateHandler handler = new FileStateHandler(stuID , backupZip);
		
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
