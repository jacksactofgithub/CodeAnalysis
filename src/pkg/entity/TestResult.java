package pkg.entity;

/**
 * @author Ray Liu
 *
 */
public class TestResult {

	private String testName;
	private int totalNum;
	private int successNum;
	
	public TestResult(String testName , int totalNum , int successNum){
		this.testName = testName;
		this.totalNum = totalNum;
		this.successNum = successNum;
	}
	
	public String getTestName(){
		return testName;
	}
	
	public int getTotalNum(){
		return totalNum;
	}
	
	public int getSuccessNum(){
		return successNum;
	}
	
}
