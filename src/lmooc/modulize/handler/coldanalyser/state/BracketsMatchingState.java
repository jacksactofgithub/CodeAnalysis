package lmooc.modulize.handler.coldanalyser.state;

import lmooc.modulize.handler.coldanalyser.JavaSentence;
import lmooc.modulize.handler.coldanalyser.Source;

public class BracketsMatchingState extends AbstractState {

	private int angleBracketCount;
	private int middleBracketCount;
	
	private String type;
	
	public BracketsMatchingState(Source source, StateCallBack lexer, JavaSentence sentence ,
			int angleBracketCount , int middleBracketCount) {
		super(source, lexer, sentence);
		// TODO Auto-generated constructor stub
		this.angleBracketCount = angleBracketCount;
		this.middleBracketCount = middleBracketCount;
		type = sentence.getTypeName();
	}

	// public BracketsMatchingState(Source source , StateCallBack lexer ,
	// JavaSentence sentence , int count1) {
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public void getSpace(String currentWord) {
		// TODO Auto-generated method stub
		type = type + currentWord + " ";
	}

	@Override
	public void getSemicolon(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getQuotes(char forechar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNoteEnd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getLeftBracket(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getRightBracket(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getLeftBrace(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getRightBrace(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getLeftAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		type = type + currentWord + "<";
		this.angleBracketCount++;
	}

	@Override
	public void getRightAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		type = type + currentWord + ">";
		this.angleBracketCount--;
		checkMatch();
	}

	@Override
	public void getComma(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getEqual(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getEnter(String currentWord) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getLogicOperation(String currentWord) {
		// TODO Auto-generated method stub

	}

	private void checkMatch(){
		if((angleBracketCount==0)&&(middleBracketCount==0)){
			sentence.setTypeName(type);
			this.lexer.endState();
		}
	}

	@Override
	public void getLeftMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		type = type + currentWord + "[";
		middleBracketCount++;
	}

	@Override
	public void getRightMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		type = type + currentWord + "]";
		middleBracketCount--;
		checkMatch();
	}
	
}
