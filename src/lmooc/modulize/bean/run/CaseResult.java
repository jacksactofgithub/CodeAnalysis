package lmooc.modulize.bean.run;

public class CaseResult {

	private boolean success;
	private String methodName;
	private String failureMessage;
	
	public CaseResult(boolean success , String methodName , String failureMessage){
		this.success = success;
		this.methodName = methodName;
		this.failureMessage = failureMessage;
	}
	
	public boolean isSucceed(){
		return success;
	}
	
	public String getMethodName(){
		return methodName;
	}
	
	public String getFailureMessage(){
		return failureMessage;
	}
	
//	public JSONObject output(){
//		JSONObject obj = new JSONObject();
//		obj.put(key, value);
//	}
	
	
}
