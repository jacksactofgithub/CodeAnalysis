package lmooc.modulize.model.coldanalyser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JavaSentence {
	
	private static final String[] LIMITATIONS = {
		"public",
		"protected",
		"private",
		"static",
		"final",
		"synchronized",
		"native",
		"abstract",
		"volatile",
		"transient",
		"strictfp"
	};
	
	public static final String NEW = "new";
	public static final String CLASS = "class";
	public static final String RETURN = "return";
	public static final String IMPORT = "import";
	
	private List<String> limitations;
	
	private String typeName;		//for vary:type , for method:return type
	
	private List<String> names;		//multiple values for vary
	
	private List<String> follows;		//parameters , only for method
	
	public static boolean isLimitation(String tag){
		
		for(int i=0 , length=LIMITATIONS.length ; i<length ; ++i){
			if(tag.equals(LIMITATIONS[i])){
				return true;
			}
		}
		return false;
	}
	
	public JavaSentence(){
		limitations = new ArrayList<String>();
		names = new ArrayList<String>();
		follows = new ArrayList<String>();
	}
	
	public void addLimitation(String limit){
		limitations.add(limit);
	}
	
	public Iterator<String> getLimitations(){
		return limitations.iterator();
	}
	
	public void addName(String name){
		names.add(name);
	}
	
	public Iterator<String> getNames(){
		return names.iterator();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void addFollow(String follow){
		follows.add(follow);
	}
	
}
