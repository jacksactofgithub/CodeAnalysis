package lmooc.modulize.bean;

import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubjectResult {

	private Iterator<CodeStamp> code ;
	private Iterator<RunStamp> run ;
	
	public SubjectResult(Iterator<CodeStamp> code , Iterator<RunStamp> run){
		this.code = code;
		this.run = run;
	}
	
	public JSONArray getCodeJSON() {
		
		JSONArray array = new JSONArray();
		
		while(code.hasNext()){
			JSONObject json;
			try {
				json = formCode(code.next());
				array.put(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return array;
	}
	
	private JSONObject formCode(CodeStamp stamp) throws JSONException{
		
		JSONObject json = new JSONObject();
		json.put("timestamp", stamp.getToSecond());
		json.put("source", stamp.getSourceName());
		json.put("lineCount", stamp.getLineCount());
		json.put("noteCount" , stamp.getNoteCount());
		json.put("methodCount", stamp.getMethodCount());
		json.put("varyCount", stamp.getVaryCount());
		json.put("maxCyclomaticCpl", stamp.getMaxCyc());
		
		return json;
	}
	
	public JSONArray getRunJSON(){
		
		JSONArray runArray = new JSONArray();
		
		while(run.hasNext()){
			JSONObject json;
			try {
				json = formRun(run.next());
				runArray.put(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return runArray;
	}
	
	private JSONObject formRun(RunStamp stamp) throws JSONException{
		JSONObject json = new JSONObject();
		
		json.put("time", stamp.getMillisecond());
		
		JSONArray successArray = new JSONArray();
		
		Iterator<Entry<String , Boolean>> tests = stamp.getTests();
		
		while(tests.hasNext()){
			Entry<String , Boolean> entry = tests.next();
			if(entry.getValue()){
				successArray.put(entry.getKey());
			}
		}
		
		json.put("success", successArray);
		
		return json;
	}
	
	
}
