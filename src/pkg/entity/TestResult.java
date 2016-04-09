package pkg.entity;

/**
 * 辅助类，对一次考试的一道题进行统计运行情况
 * 含有运行时间和总运行人数以及通过人数
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
