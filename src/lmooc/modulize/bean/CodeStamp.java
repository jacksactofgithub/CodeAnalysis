package lmooc.modulize.bean;

/**
 * 一个时间戳下所对应的代码的各个参数的数据统计
 * 
 * @author Ray Liu
 *
 */
public class CodeStamp {

	private long timestamp;
	private long relativeTime;
	private int lineCount;
	private int noteCount;
	private int methodCount;
	private int varyCount;
	private int maxCyc; // 最大圈复杂度
	private String sourceName; // 对应的源文件的名称

	public CodeStamp(long timestamp , long relativeTime , int lineCount, int noteCount, int methodCount,
			int varyCount, int maxCyc,String sourceName) {

		this.timestamp = timestamp;
		this.relativeTime = relativeTime;
		this.lineCount = lineCount;
		this.noteCount = noteCount;
		this.methodCount = methodCount;
		this.varyCount = varyCount;
		this.maxCyc = maxCyc;
		this.sourceName = sourceName;

	}
	
	public long getTimestamp(){
		return timestamp;
	}

	public long getRelativeTime() {
		return relativeTime;
	}
	
	public int getLineCount() {
		return lineCount;
	}

	public int getNoteCount() {
		return noteCount;
	}

	public int getMethodCount() {
		return methodCount;
	}

	public int getVaryCount() {
		return varyCount;
	}

	public int getMaxCyc(){
		return maxCyc;
	}
	
	public String getSourceName() {
		return sourceName;
	}
}
