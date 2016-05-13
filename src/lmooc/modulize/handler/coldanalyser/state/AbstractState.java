package lmooc.modulize.handler.coldanalyser.state;

import lmooc.modulize.handler.coldanalyser.JavaSentence;
import lmooc.modulize.handler.coldanalyser.Source;

/**
 * abstract state 
 * help Lexer to segment the special words(JavaSentence , JavaWord)
 * of source code
 * control the operations of different input
 * get source data from Source and store formed data
 * in Source.
 * 
 * @author Ray Liu
 *
 */
public abstract class AbstractState {

	protected Source source;
	protected StateCallBack lexer;
	protected JavaSentence sentence;
	
	protected int braceCount =0; 
	protected int bracketCount =0; 
	
	public static AbstractState initState(Source source , StateCallBack lexer){
		return new NormalState(source , lexer);
	}
	
	public AbstractState(Source source , StateCallBack lexer){
		this.source = source;
		this.lexer = lexer;
		this.sentence = new JavaSentence();
	}
	
	public AbstractState(Source source , StateCallBack lexer , JavaSentence sentence){
		this.source = source;
		this.lexer = lexer;
		this.sentence = sentence;
	}
	
	/*
	 */
	public abstract void getSpace(String currentWord);
	
	/*
	 */
	public abstract void getSemicolon(String currentWord);
	
	/*
	 */
	public abstract void getQuotes(char forechar);
	
	public void getLineNote() {
		// TODO Auto-generated method stub
		lexer.startState(new NoteState(source, lexer, NoteState.LINE_NOTE));
	}

	public void getNoteStart() {
		// TODO Auto-generated method stub
		lexer.startState(new NoteState(source , lexer , NoteState.BLOCK_NOTE));
	}
	
	/*
	 */
	public abstract void getNoteEnd();
	
	/*
	 */
	public abstract void getLeftBracket(String currentWord);
	
	/*
	 */
	public abstract void getRightBracket(String currentWord);
	
	/*
	 */
	public abstract void getLeftBrace(String currentWord);
	
	/*
	 */
	public abstract void getRightBrace(String currentWord);
	
	/*
	 */
	public abstract void getLeftAngleBracket(String currentWord);
	
	/*
	 */
	public abstract void getRightAngleBracket(String currentWord);
	
	public abstract void getLeftMiddleBracket(String currentWord);
	
	public abstract void getRightMiddleBracket(String currentWord);
	
	/*
	 */
	public abstract void getComma(String currentWord);
	
	/*
	 */
	public abstract void getEqual(String currentWord);
	
	/*
	 */
	public abstract void getEnter(String currentWord);
	
	public abstract void getLogicOperation(String currentWord);
	
	public void stateSwitch(AbstractState state){
		this.braceCount = state.braceCount;
		this.bracketCount = state.bracketCount;
		this.sentence = state.sentence;
	}
	
}
