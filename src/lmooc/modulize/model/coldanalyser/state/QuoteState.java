package lmooc.modulize.model.coldanalyser.state;

import lmooc.modulize.model.coldanalyser.Source;

public class QuoteState extends AbstractState{

	public QuoteState(Source source, StateCallBack lexer) {
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
		
	}

	@Override
	public void getQuotes(char forechar) {
		// TODO Auto-generated method stub
		if(forechar == '\\'){
			return ;
		}
		lexer.endState();
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
