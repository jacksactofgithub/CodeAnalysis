package lmooc.modulize.model.coldanalyser.state;

import lmooc.modulize.model.coldanalyser.JavaSentence;
import lmooc.modulize.model.coldanalyser.JavaWord;
import lmooc.modulize.model.coldanalyser.Source;

/**
 * basic state of lexer
 * 
 * @author Ray Liu
 *
 */
public class NormalState extends AbstractState{

	public NormalState(Source source, StateCallBack lexer) {
		super(source, lexer);
		// TODO Auto-generated constructor stub
	}

	private boolean enable = true;
	
	@Override
	public void getSpace(String currentWord) {
		// TODO Auto-generated method stub
		handleWord(currentWord);
	}

	@Override
	public void getSemicolon(String currentWord) {
		// TODO Auto-generated method stub
		source.locPlus();
		this.flush();
	}

	@Override
	public void getQuotes(char forechar) {
		// TODO Auto-generated method stub
		lexer.startState(new QuoteState(source , lexer));
	}

	@Override
	public void getNoteEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftBracket(String currentWord) {
		// TODO Auto-generated method stub
		enable = false;
	}

	@Override
	public void getRightBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftBrace(String currentWord) {
		// TODO Auto-generated method stub
		source.getLeftBrace();
		this.flush();
	}

	@Override
	public void getRightBrace(String currentWord) {
		// TODO Auto-generated method stub
		source.getRightBrace();
		this.flush();
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
		handleWord(currentWord);
	}
	
	private void flush(){
		enable = true;
		sentence = new JavaSentence();
	}

	@Override
	public void getLogicOperation(String currentWord) {
		// TODO Auto-generated method stub
		source.plusCyclomaticCpl();
	}
	
	private void handleWord(String currentWord){
		if(enable && (!currentWord.equals(""))){
			enable = false;
			if(JavaSentence.isLimitation(currentWord)){		//是修饰词，加入修饰语句
				sentence.addLimitation(currentWord);
				enable = true;		//允许继续读取，直到读取到TypeName，但这种方法会忽略构造方法
//				lexer.startState(new PreformState(source , lexer , sentence));
			}
			else if(currentWord.equals(JavaSentence.RETURN)){
				lexer.startState(new ReturnState(source , lexer));
				flush();
			}
//			else if(currentWord == JavaSentence.NEW){
//				foreword = currentWord;
//			}
			else if(currentWord.equals(JavaSentence.CLASS)){
				
			}
			else if(currentWord.equals(JavaSentence.IMPORT)){
				
			}
			else if(JavaWord.isJavaWord(currentWord)){
				source.plusCyclomaticCpl();
			}
			else{
				sentence.setTypeName(currentWord);
				lexer.startState(new PreformState(source , lexer , sentence));
				
				matchBrackets();
				flush();
			}
		}
	}
	
	private void matchBrackets(){
		int angleBracketCount = 0;
		int middleBracketCount = 0;
		
		String tempType = sentence.getTypeName();
		char[] chars = tempType.toCharArray();
		
		for(int i=0 ; i<chars.length ; ++i){
			char c = chars[i];
			if(c == '<'){
				angleBracketCount++;
			}
			else if(c == '>'){
				angleBracketCount--;
			}
			else if(c == '['){
				middleBracketCount++;
			}
			else if(c == ']'){
				middleBracketCount--;
			}
		}
		
		if((angleBracketCount==0) && (middleBracketCount==0)){
			return ;
		}
		
		lexer.startState(new BracketsMatchingState(source, lexer, sentence, angleBracketCount, middleBracketCount));

	}

	@Override
	public void getLeftAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		handleWord(currentWord+"<");
	}

	@Override
	public void getRightAngleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getLeftMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		handleWord(currentWord+"[");
	}

	@Override
	public void getRightMiddleBracket(String currentWord) {
		// TODO Auto-generated method stub
		
	}

}
