package lmooc.modulize.model.coldanalyser;

import java.util.ArrayList;
import java.util.List;

public class SourceMethod {
	
	private String name;
	private String parameters;
	
	private int cyclomaticCpl ;		//圈复杂度
	private List<Vary> varyList;	//方法的变量列表

	private int loc;
	private int lon;
	
	public SourceMethod(String name , String parameters){
		this.name = name ; 
		this.parameters = parameters;
		loc = 0 ;
		lon = 0 ;
		cyclomaticCpl = 0;
		varyList = new ArrayList<Vary>();
	}
	
	public String getMethodIndentifier(){
		return name+parameters;
	}
	
	public void locPlus(){
		loc++;
	}
	
	public void lonPlus(){
		lon++;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}
	
	public void plusCyclomaticCpl(){
		this.cyclomaticCpl++;
	}
	
	public int getCyclomaticCpl(){
		return cyclomaticCpl;
	}
	
	public void addVary(String type , String name){
		Vary vary = new Vary(type , name);
		varyList.add(vary);
	}
	
	public int getVaryCount(){
		return varyList.size();
	}
	
	public void printVars(){
		for(int i=0 ; i<varyList.size() ; ++i){
			System.out.print(varyList.get(i).getName() + "\t");
		}
	}
	
}
