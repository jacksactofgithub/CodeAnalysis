package lmooc.modulize.model.coldanalyser.state;

import lmooc.modulize.model.coldanalyser.JavaSentence;
import lmooc.modulize.model.coldanalyser.Source;

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
	
	protected int braceCount =0;   //大括号数目
	protected int bracketCount =0; //圆括号
	
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
	 * 获取到空格符
	 */
	public abstract void getSpace(String currentWord);
	
	/*
	 * 获取到分号
	 */
	public abstract void getSemicolon(String currentWord);
	
	/*
	 * 获取到引号
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
	 * 获取到 注释结束符号
	 */
	public abstract void getNoteEnd();
	
	/*
	 * 获取到左圆括号
	 */
	public abstract void getLeftBracket(String currentWord);
	
	/*
	 * 获取到右圆括号
	 */
	public abstract void getRightBracket(String currentWord);
	
	/*
	 * 获取到左大括号
	 */
	public abstract void getLeftBrace(String currentWord);
	
	/*
	 * 获取到右大括号
	 */
	public abstract void getRightBrace(String currentWord);
	
	/*
	 * 获取到左尖括号
	 */
	public abstract void getLeftAngleBracket(String currentWord);
	
	/*
	 * 获取到右尖括号
	 */
	public abstract void getRightAngleBracket(String currentWord);
	
	public abstract void getLeftMiddleBracket(String currentWord);
	
	public abstract void getRightMiddleBracket(String currentWord);
	
	/*
	 * 获取到逗号
	 */
	public abstract void getComma(String currentWord);
	
	/*
	 * 获取到等号
	 */
	public abstract void getEqual(String currentWord);
	
	/*
	 * 获取到换行
	 */
	public abstract void getEnter(String currentWord);
	
	public abstract void getLogicOperation(String currentWord);
	
	public void stateSwitch(AbstractState state){
		this.braceCount = state.braceCount;
		this.bracketCount = state.bracketCount;
		this.sentence = state.sentence;
	}
	
}
