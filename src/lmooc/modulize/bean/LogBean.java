package lmooc.modulize.bean;

import java.util.Iterator;
import java.util.List;

import lmooc.modulize.bean.filestate.FileState;
import lmooc.modulize.bean.run.RunResult;

public class LogBean {

	private List<FileState> states;
	private List<RunResult> runs;
	
	public LogBean(List<FileState> states , List<RunResult> runs){
		this.states = states;
		this.runs = runs;
	}
	
	public Iterator<FileState> getFileStates(){
		return states.iterator();
	}
	
	public Iterator<RunResult> getRuns(){
		return runs.iterator();
	}
	
}
