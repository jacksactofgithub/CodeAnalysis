package lmooc.modulize.bean.run;

import java.util.Iterator;
import java.util.List;

public class ClassResult {

	private String className;
	private List<CaseResult> results;
	
	public ClassResult(String className , List<CaseResult> results){
		this.className = className;
		this.results = results;
	}
	
	public String getClassName(){
		return className;
	}
	
	public Iterator<CaseResult> getResults(){
		return results.iterator();
	}
	
	public String getPassName(){
		String result = "";
		
		Iterator<CaseResult> caseIt = results.iterator();
		CaseResult temp;
		while( caseIt.hasNext() ){
			temp = caseIt.next();
			if(temp.isSucceed()){
				result = result + temp.getMethodName() + " " ;
			}
		}
		
		return result;
	}
	
	public String getFailName(){
		String result = "";
		
		Iterator<CaseResult> caseIt = results.iterator();
		CaseResult temp;
		while( caseIt.hasNext() ){
			temp = caseIt.next();
			if(!temp.isSucceed()){
				result = result + temp.getMethodName() + " " ;
			}
		}
		
		return result;
	}
	
}
