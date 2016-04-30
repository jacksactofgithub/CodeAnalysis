package lmooc.modulize.handler.coldanalyser.state;

import lmooc.modulize.handler.coldanalyser.Source;

public class ReturnState extends AbstractState{

	public ReturnState(Source source, StateCallBack lexer) {
		super(source, lexer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void getSpace(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getSemicolon(String currentWord) {
		// TODO Auto-generated method stub
		source.locPlus();
		lexer.endState();
	}

	@Override
	public void getQuotes(char forechar) {
		// TODO Auto-generated method stub
		lexer.startState(new QuoteState(source, lexer));
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

}
