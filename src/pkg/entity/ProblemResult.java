package pkg.entity;

import java.util.Iterator;
import java.util.List;

/**
 * 辅助类，对一次考试的一道题进行统计运行情况
 * 含有运行时间和总运行人数以及通过人数
 * @author Ray Liu
 *
 */
public class ProblemResult {

	private int timeSecond;
	private List<TestResult> tests;
	
	public ProblemResult(int timeSecond , List<TestResult> tests){
		this.timeSecond = timeSecond;
		this.tests = tests;
	}
	
	public int getTimeSecond(){
		return timeSecond;
	}
	
	public Iterator<TestResult> getTests(){
		return tests.iterator();
	}
	
}
