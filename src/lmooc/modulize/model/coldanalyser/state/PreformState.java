package lmooc.modulize.model.coldanalyser.state;

import java.util.Iterator;

import lmooc.modulize.model.coldanalyser.JavaSentence;
import lmooc.modulize.model.coldanalyser.Source;
import lmooc.modulize.model.coldanalyser.SourceMethod;

public class PreformState extends AbstractState{
	
	private String follow = "";
	private String currentName = "";
	private boolean isMethod = false;
	private boolean inFWHandle = false;		//	正在处理first word
	private boolean isVary = false;
	private String firstWord = null;
	
	
//	public PreformState(Source source, StateCallBack lexer) {
//		super(source, lexer);
//		// TODO Auto-generated constructor stub
//	}
	
	public PreformState(Source source, StateCallBack lexer , JavaSentence sentence) {
		super(source, lexer , sentence);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void getSpace(String currentWord) {
		// TODO Auto-generated method stub
		if((firstWord == null) &&(!currentWord.equals(""))){
			firstWord = currentWord;
			sentence.addName(firstWord);
			inFWHandle = true;
			return ;
		}
		if(inFWHandle){
			if(!currentWord.equals("")){
				isMethod = false;
				isVary = true;
				inFWHandle = false;
				currentName = firstWord;
			}
		}
		
		if(isVary){	
			if((follow.equals(""))&&(!currentWord.equals(""))){		//first word without brackets must be vary
				sentence.addName(currentWord);
				currentName = currentWord;
				follow = " ";
			}
			else{
				follow = follow + currentWord + " ";
			}
		}
		else if(isMethod){
			follow = follow + currentWord + " ";
		}
	}

	@Override
	public void getSemicolon(String currentWord) {
		// TODO Auto-generated method stub
		if(follow.equals("")&&(!currentWord.equals(""))){
			if(!sentence.getTypeName().equals("")){
				sentence.addName(currentWord);
				currentName = currentWord;
				follow = "";
			}
		}
		else{
			follow += currentWord;
		}
		sentence.addFollow(follow);
//		source.addVary( sentence.getTypeName(), currentName);
		follow = "";
		source.locPlus();
		if(sentence.getTypeName() == null){
			lexer.endState();
		}
		else{
			addVarys();
			lexer.endState(sentence);
		}
	}
	
	private void addVarys(){
		Iterator<String> it = sentence.getNames();
		while(it.hasNext()){
			source.addVary(sentence.getTypeName(), it.next());
		}
		
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
		if((firstWord == null)&&(!currentWord.equals(""))){
			firstWord = currentWord;
			sentence.addName(currentWord);
			isMethod = true;
			isVary = false;
			follow = "(";
			bracketCount++;
			return ;
		}
		if(inFWHandle){
			isMethod = true;
			isVary = false;
//			follow = "(";
			bracketCount++;
		}
		
		if(isVary){
			follow = follow + currentWord+"(";
		}
		else if(isMethod){
			bracketCount++;
			follow = follow + currentWord + "(";
		}
		
	}

	@Override
	public void getRightBracket(String currentWord) {
		// TODO Auto-generated method stub
		if(isVary){
			follow = follow + currentWord + ")";
		}
		else if(isMethod){
			follow = follow + currentWord + ")";
			bracketCount--;
			if(bracketCount == 0){
				sentence.addFollow(follow);

				String name = sentence.getNames().next();
				if(name.contains(".")){
					lexer.endState();
					return ;
				}
				SourceMethod method = new SourceMethod(name, follow);
				source.addMethod(method);
				source.startMethod(method);
				lexer.endState(sentence);
			}
		}
	}

	@Override
	public void getLeftBrace(String currentWord) {
		// TODO Auto-generated method stub
		source.getLeftBrace();
		if(isVary || isMethod){
			
		}else{
			lexer.endState();
		}
	}

	@Override
	public void getRightBrace(String currentWord) {
		// TODO Auto-generated method stub
		source.getRightBrace();
	}

	@Override
	public void getComma(String currentWord) {
		// TODO Auto-generated method stub
		if(isVary){
			follow = follow + currentWord;
			sentence.addFollow(follow);
//			source.addVary(sentence.getTypeName(), currentName);
			follow = "";
			currentName = "";
		}
		if(isMethod){
			follow = follow + currentWord + ",";
		}
	}

	@Override
	public void getEqual(String currentWord) {
		// TODO Auto-generated method stub
		if(inFWHandle){
			isVary = true;
			isMethod = false;
			inFWHandle = false;
			follow = "=";
			return ;
		}
		if((currentName.equals(""))&&(currentWord.equals(""))){
//			currentName = currentWord;
//			sentence.addName(currentWord);
//			follow = "=";
			firstWord = "";
			currentName = "";
			sentence.setTypeName("");
//			sentence.setTypeName(null);
//			lexer.endState();
		}
		else{
			follow = follow + currentWord + "=";
		}
		
	}

	@Override
	public void getEnter(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLogicOperation(String currentWord) {
		// TODO Auto-generated method stub
		source.plusCyclomaticCpl();
	}

	@Override
	public void getLeftAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		follow = follow + currentWord + "<";
	}

	@Override
	public void getRightAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		follow = follow + currentWord + ">";
	}

	@Override
	public void getLeftMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		if(firstWord == null){
			lexer.startState(new BracketsMatchingState(source, lexer, sentence, 0, 1));
		}
		follow = follow + currentWord + "[";
	}

	@Override
	public void getRightMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		follow = follow + currentWord + "]";
	}
	
}
