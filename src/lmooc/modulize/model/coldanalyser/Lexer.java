package lmooc.modulize.model.coldanalyser;

import java.util.Iterator;
import java.util.Stack;

import lmooc.modulize.io.FileReader;
import lmooc.modulize.model.coldanalyser.state.AbstractState;
import lmooc.modulize.model.coldanalyser.state.StateCallBack;

public class Lexer implements StateCallBack{
	
	private AbstractState currentState;
	
	private Stack<AbstractState> states;
	
	private Source source;
	
	public Lexer(){
		states = new Stack<AbstractState>();
		
	}
	
	/**
	 * 对外接口，将source分词
	 * @param source
	 */
	public void segment(Source source){
		this.source = source;
		currentState = AbstractState.initState(source , this);
		
		Iterator<String> code = source.getSourceCode();
		String buffer = "";
		char forechar,currentChar;		//forechar is the char before current
		while(code.hasNext()){
			String current = code.next();
			char[] chars = current.toCharArray();
			
			int length = chars.length;
			if(length == 0){
				currentChar = '\n';
				continue;
			}
			currentChar = ' ';
			boolean getEqual = false;
			for(int i=0 ; i<chars.length ; ++i){
				forechar = currentChar;
				currentChar = chars[i];
				
				if((getEqual)||(currentChar == '=')){
					if(currentChar == '='){
						if(!getEqual){		//第一个等号
							getEqual = true;
						}
						else{
							getEqual = false;
						}
						continue;
					}
					else{
						currentState.getEqual(buffer);
						if(!isSpecial(forechar, currentChar, "")){
							buffer = "";
						}
						else{
							buffer = "";
						}
						getEqual = false;
					}
				}
				
				if(!isSpecial(forechar , currentChar , buffer)){
					buffer = buffer + currentChar;
				}
				else{
					buffer = "";
				}
			}
			isSpecial(currentChar , '\n' , buffer);
			buffer = "";
			
		}
		
	}
	
	private boolean isSpecial(char forechar , char current , String currentWord){
		if((forechar == '|')&&(current == '|')){
			currentState.getLogicOperation(currentWord);
			return true;
		}
		if((forechar == '&')&&(current == '&')){
			currentState.getLogicOperation(currentWord);
			return true;
		}
		if((current == ' ')||(current == '\t')){
			currentState.getSpace(currentWord);
			return true;
		}
		if(current == ';'){
			currentState.getSemicolon(currentWord);
			return true;
		}
		if(current == '"'){
			currentState.getQuotes(forechar);
			return true;
		}
		if((forechar == '/')&&(current == '/')){
			currentState.getLineNote();
			return true;
		}
		if((forechar == '/')&&(current == '*')){
			currentState.getNoteStart();
			return true;
		}
		if((forechar == '*')&&(current == '/')){
			currentState.getNoteEnd();
			return true;
		}
		if(current == '('){
			currentState.getLeftBracket(currentWord);
			return true;
		}
		if(current == ')'){
			currentState.getRightBracket(currentWord);
			return true;
		}
		if(current == '{'){
			currentState.getLeftBrace(currentWord);
			return true;
		}
		if(current == '}'){
			currentState.getRightBrace(currentWord);
			return true;
		}
		if(current == ','){
			currentState.getComma(currentWord);
			return true;
		}
		if(current == '\n'){
			currentState.getEnter(currentWord);
			return true;
		}
		if(current == '<'){
			currentState.getLeftAngleBracket(currentWord);
			return true;
		}
		if(current == '>'){
			currentState.getRightAngleBracket(currentWord);
			return true;
		}
		if(current == '['){
			currentState.getLeftMiddleBracket(currentWord);
			return true;
		}
		if(current == ']'){
			currentState.getRightMiddleBracket(currentWord);
			return true;
		}
		if(current == '@'){
			currentState.getLineNote();
			return true;
		}
		
		return false;
	}
	
//	/**
//	 * 对单条String进行分词操作
//	 * @param source
//	 * @param tag
//	 */
//	private void analyseOne(String tag){
//		char[] chars = tag.toCharArray();
//		
//	}

	public void startState(AbstractState newState) {
		// TODO Auto-generated method stub
		states.push(currentState);
		currentState = newState;
	}

	public void endState(JavaSentence sentence) {
		// TODO Auto-generated method stub
		
		if(sentence!=null){
			source.addSentence(sentence);
		}
		
		if(!states.isEmpty()){
			currentState = states.pop();
		}
	}

	public void switchState(AbstractState toState) {
		// TODO Auto-generated method stub
		toState.stateSwitch(currentState);
		currentState = toState;
	}

	public void endState() {
		// TODO Auto-generated method stub
		if(!states.isEmpty()){
			currentState = states.pop();
		}
	}
	
	public static void main(String[] args){
		FileReader reader = new FileReader();
		Iterator<String> txt = reader.read("D:/test.txt");
		
		Source source = new Source(txt , "test");
		Lexer lexer = new Lexer();
		lexer.segment(source);
		source.printVars();
		
		System.out.println(source.getLoc());
		System.out.println(source.getMethodCount());
		System.out.println(source.getMaxCyc());
		System.out.println(source.getTotalVaryCount());
		
	}
	
}
