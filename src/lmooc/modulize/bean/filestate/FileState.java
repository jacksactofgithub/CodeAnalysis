package lmooc.modulize.bean.filestate;

public class FileState {

	private long millisecond;
	private StateType type;
	//if StateType is Timer,filePath is null
	private String filePath;
	
	public FileState(long millisecond , StateType type , String filePath){
		this.millisecond = millisecond;
		this.type = type;
		this.filePath = filePath;
	}
	
	public long getMillisecond(){
		return millisecond;
	}
	
	public StateType getType(){
		return type;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
}
