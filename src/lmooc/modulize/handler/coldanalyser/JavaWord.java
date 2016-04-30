package lmooc.modulize.handler.coldanalyser;

public class JavaWord {
	
	private static final String[] WORDS = {
		"if",
		"else",
		"for",
		"while",
		"case",
		"default",
		"catch",
		"do"
	};
	
	private String name;
//	private String visibility;
//	private String type;			
	private String follow;
	
//	private String className;
//	private String methodKey;		//methodIdentifiyer

	public static boolean isJavaWord(String word){
		
		for(int i=0 , length=WORDS.length ; i<length ; ++i){
			if(word.equals(WORDS[i])){
				return true;
			}
		}
		
		return false;
	}
	
	public JavaWord(String name , String follow){
		this.name = name ;
		this.follow = follow;
	}

	public String getFollow() {
		return follow;
	}

	public void setFollow(String follow) {
		this.follow = follow;
	}
	
	public String getName(){
		return name;
	}
	
}
