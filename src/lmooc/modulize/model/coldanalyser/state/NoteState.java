package lmooc.modulize.model.coldanalyser.state;

import lmooc.modulize.model.coldanalyser.Source;

public class NoteState extends AbstractState{

	public static final int LINE_NOTE = 1;
	public static final int BLOCK_NOTE = 2;
	
	private int type;
	
	public NoteState(Source source, StateCallBack lexer ,int type) {
		super(source, lexer);
		// TODO Auto-generated constructor stub
		this.type = type;
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
		
	}

	@Override
	public void getLineNote() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNoteStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getNoteEnd() {
		// TODO Auto-generated method stub
		source.lonPlus();
		if(type == BLOCK_NOTE){
			lexer.endState();
		}
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
		source.lonPlus();
		if(type == LINE_NOTE){
			lexer.endState();
		}
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
