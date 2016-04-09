package lmooc.modulize.model.coldanalyser.state;

import lmooc.modulize.model.coldanalyser.Source;

public class JavaWordState extends AbstractState{

	private String word;
	private String follow;
	private int leftBracketCount;
	
	public JavaWordState(Source source, StateCallBack lexer , String word , int leftBracketCount) {
		super(source, lexer);
		// TODO Auto-generated constructor stub
		this.word = word;
		follow = "";
		this.leftBracketCount = leftBracketCount;
	}

	@Override
	public void getSpace(String currentWord) {
		// TODO Auto-generated method stub
		if(bracketCount == 0){
			word = word + " " + currentWord;
		}
		follow = follow + currentWord + " ";
	}

	@Override
	public void getSemicolon(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNoteEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftBracket(String currentWord) {
		// TODO Auto-generated method stub
		leftBracketCount++;
	}


	@Override
	public void getLeftBrace(String currentWord) {
		// TODO Auto-generated method stub
		source.getLeftBrace();
		lexer.endState();
	}

	@Override
	public void getRightBrace(String currentWord) {
		// TODO Auto-generated method stub
		
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
		if(word.equals("else")||(word.equals("default"))){
			lexer.endState();
		}
	}

	@Override
	public void getQuotes(char forechar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRightAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRightMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLogicOperation(String currentWord) {
		// TODO Auto-generated method stub
		source.plusCyclomaticCpl();
	}

	@Override
	public void getRightBracket(String currentWord) {
		// TODO Auto-generated method stub
		leftBracketCount--;
		if(leftBracketCount == 0){
			lexer.endState();
		}
	}

}
