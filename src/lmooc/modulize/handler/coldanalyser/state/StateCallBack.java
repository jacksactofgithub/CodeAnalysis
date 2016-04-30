package lmooc.modulize.handler.coldanalyser.state;

import lmooc.modulize.handler.coldanalyser.JavaSentence;

public interface StateCallBack {

	/**
	 * start a new state with new properties
	 * save current state
	 * @param newState
	 */
	public void startState(AbstractState newState);
	
	/**
	 * end current state ,
	 * return a JavaSentence instance if exits
	 * @param sentence
	 */
	public void endState(JavaSentence sentence);
	
	/**
	 * simply end current state,
	 * adding no JavaSentence
	 */
	public void endState();
	
	/**
	 * switch to another state with current properties
	 * will not save current state
	 * @param toState
	 */
	public void switchState(AbstractState toState);
	
}
