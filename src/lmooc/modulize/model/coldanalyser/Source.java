package lmooc.modulize.model.coldanalyser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Source {

	private Iterator<String> sourceCode;
	private String sourceName;
	
	private List<SourceMethod> methods;
	private List<Vary> varyList;		//vary belong to class
	
	private List<JavaSentence> sentences;
//	private List<JavaWord> words;
	
	private int loc;		//line of count
	private int lon;		//line of notes
	
	private SourceMethod currentMethod;
	private int braceCount;
	
	public Source(Iterator<String> sourceCode , String sourceName){
		this.sourceCode = sourceCode;
		methods = new ArrayList<SourceMethod>();
		sentences = new ArrayList<JavaSentence>();
//		words = new ArrayList<JavaWord>();
		loc = 0;
		lon = 0;
		currentMethod = null;
		braceCount = 0;
		this.sourceName = sourceName;
		varyList = new ArrayList<Vary>();
	}
	
	public Iterator<String> getSourceCode(){
		return sourceCode;
	}
	
//	public void setMethods(String key , String methodName){
////		methods.pu
//	}
//	
//	public void addWords(JavaWord word){
//		
//	}
	
	public void addSentence(JavaSentence sentence){
		this.sentences.add(sentence);
	}
	
	/**
	 * 增加一个loc，若该行在一个方法中，则该方法的loc也增加
	 * @param methodID
	 */
	public void locPlus(){
		loc++;
//		SourceMethod method = findMethod(methodID);
//		if(method!=null){
//			method.locPlus();
//		}
//		
		if(currentMethod!=null){
			currentMethod.locPlus();
		}
		
	}
	
	public void lonPlus(){
		lon++;
//		SourceMethod method = findMethod(methodID);
//		if(method!=null){
//			method.lonPlus();
//		}
		if(currentMethod!=null){
			currentMethod.lonPlus();
		}
		
	}
	
	public int getLoc(){
		return loc;
	}
	
	public int getLon(){
		return lon;
	}
	
	public void plusCyclomaticCpl(){
		if(currentMethod!=null){
			currentMethod.plusCyclomaticCpl();
		}
	}
	
	/**
	 * 以下三个方法用于计量当前代码所属方法
	 * @param method
	 */
	public void startMethod(SourceMethod method){
		currentMethod = method;
	}
	
	public void getLeftBrace(){
		if(currentMethod!=null){
			braceCount++;
		}
	}
	
	public void getRightBrace(){
		if(currentMethod!=null){
			braceCount--;
		}
		if(braceCount == 0){
			currentMethod = null;
		}
	}
	
	public void addMethod(SourceMethod method){
		methods.add(method);
	}
	
	public void addVary(String type , String name){
		Vary vary = new Vary(type , name);
		if(currentMethod == null){
			varyList.add(vary);
			return ;
		}
		currentMethod.addVary(type, name);
	}
	
	public String getSourceName(){
		return sourceName;
	}
	
	public int getTotalVaryCount(){
		int vary = varyList.size();
		for(int i=0 , length = methods.size() ; i<length ; ++i){
			vary += methods.get(i).getVaryCount();
		}
		return vary;
	}
	
	public int getMethodCount(){
		return methods.size();
	}
	
	public int getMaxCyc(){
		
		Iterator<SourceMethod> methodIt = methods.iterator();
		
		int max = 0;
		while(methodIt.hasNext()){
			int temp = methodIt.next().getCyclomaticCpl();
			if(max < temp){
				max = temp;
			}
		}
		
		return max;
	}
	
	public void printVars(){
		System.out.println("vars of :" + sourceName);
		for(int i=0 ; i<varyList.size() ; ++i){
			System.out.print(varyList.get(i).getName() + "\t");
		}
		System.out.println();
		
		for(int i=0 ;i<methods.size() ; ++i){
			SourceMethod method = methods.get(i);
			System.out.print("methodName:" + method.getMethodIndentifier() + "\t");
			method.printVars();
			System.out.println();
		}
		
	}
	
//	private SourceMethod findMethod(String methodID){
//		SourceMethod method = null;
//		for(int i=0 , length=methods.size() ; i < length ; ++i){
//			method = methods.get(i);
//			if(method.getMethodIndentifier().equals(methodID)){
//				return method;
//			}
//		}
//		
//		return null;
//	}
//	
}
