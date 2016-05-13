package pkg.entity;

import java.util.Iterator;
import java.util.List;

/**
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
