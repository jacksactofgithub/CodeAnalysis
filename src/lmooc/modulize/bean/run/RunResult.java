package lmooc.modulize.bean.run;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import lmooc.modulize.bean.RunStamp;

public class RunResult {

//	private String stuID;
//	private String title;
	private Date time;
	private int totalNum;
	private int successNum;
	private String proName;
	private List<ClassResult> classes;
	
	// private static List<RunResult> list = new ArrayList<RunResult>();

	public RunResult( Date time, int totalNum, int successNum, String proName,
			List<ClassResult> classes) {
		this.time = time;
		this.totalNum = totalNum;
		this.successNum = successNum;
		this.proName = proName;
		this.classes = classes;
	}

	public Date getDate() {
		return time;
	}

//	public String getStuID() {
//		return stuID;
//	}
//
//	public String getTitle() {
//		return title;
//	}

	public int getTotalNum() {
		return totalNum;
	}

	public int getSuccessNum() {
		return successNum;
	}

	public String getProName() {
		return proName;
	}

	public Iterator<ClassResult> getClasses() {
		return classes.iterator();
	}
	
	public RunStamp toStamp(){
		
		long millisecond = time.getTime();
		String className = proName;
		Map<String , Boolean> resultMap = new HashMap<String , Boolean>();
		
		for(int i=0 ; i<classes.size() ; ++i){
			ClassResult cResult = classes.get(i);
			Iterator<CaseResult> cases = cResult.getResults();
			
			while(cases.hasNext()){
				CaseResult oCase = cases.next();
				resultMap.put(oCase.getMethodName(), oCase.isSucceed());
			}
			
		}
		
		return new RunStamp(millisecond , className , resultMap);
	}

	public JSONObject output(int time) throws JSONException {
		JSONObject json = new JSONObject();
		
		String passInfo = "";
		String failInfo = "";
		
		Iterator<ClassResult> classIt = classes.iterator();
		while( classIt.hasNext() ){
			ClassResult temp = classIt.next();
			passInfo = passInfo + temp.getPassName();
			failInfo = failInfo + temp.getFailName();
		}

		json.put("runNo", time);
		json.put("passNo", passInfo);
		json.put("failNo", failInfo);
		
		return json;
	}

}
