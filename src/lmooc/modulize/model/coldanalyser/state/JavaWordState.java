//package lmooc.modulize.model.coldanalyser.state;
//
//import lmooc.modulize.model.coldanalyser.JavaWord;
//import lmooc.modulize.model.coldanalyser.Lexer;
//import lmooc.modulize.model.coldanalyser.Source;
//
//public class JavaWordState extends AbstractState{
//
//	private String word;
//	private String follow;
//	
//	public JavaWordState(Source source, Lexer lexer , String word) {
//		super(source, lexer);
//		// TODO Auto-generated constructor stub
//		this.word = word;
//		follow = "";
//	}
//
//	@Override
//	public void getSpace(String currentWord) {
//		// TODO Auto-generated method stub
//		if(bracketCount == 0){
//			word = word + " " + currentWord;
//		}
//		follow = follow + currentWord + " ";
//	}
//
//	@Override
//	public void getSemicolon(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getQuotes() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getNoteEnd() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getLeftBracket(String currentWord) {
//		// TODO Auto-generated method stub
//		if(bracketCount == 0){		//第一个括号
//			word = word + " " + currentWord;
//			follow = "(" ;
//		}
//		bracketCount++;
//		follow = follow + currentWord + "(";
//		
//	}
//
//	@Override
//	public void getRightBracket(String currentWord) {
//		// TODO Auto-generated method stub
//		bracketCount--;
//		follow = follow + currentWord + ")";
//		if(bracketCount == 0){		//最后一个括号
//			JavaWord jWord = new JavaWord(word , follow);
//			source.addWords(jWord);
//			lexer.endState();
//		}
//	}
//
//	@Override
//	public void getLeftBrace(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getRightBrace(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getComma(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getEqual(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getEnter(String currentWord) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void getLogicOperation() {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
