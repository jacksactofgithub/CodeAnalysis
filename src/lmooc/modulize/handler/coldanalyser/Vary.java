package lmooc.modulize.handler.coldanalyser;

/**
 * @author Ray Liu
 *
 */
public class Vary {

	private String type;
	private String name;
	
	public Vary(String type , String name){
		this.type = type;
		this.name = name;
	}
	
	public String getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
}
