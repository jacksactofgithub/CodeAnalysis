package lmooc.modulize.entity;

public class Attendence {

	private String attendID;
	private String studentID;
	private String subjectID;
	private int second;
	private int lineCount;
	private int noteCount;
	private int methodCount;
	private int varCount;
	private int maxCyclomaticCpl;
	
	public Attendence(String attendID,String studentID,String subjectID,int second ,
			int lineCount , int noteCount , int methodCount , int varCount , int maxCyclomaticCpl){
		this.attendID = attendID;
		this.studentID = studentID;
		this.subjectID = subjectID;
		this.second = second;
		this.lineCount = lineCount;
		this.noteCount = noteCount;
		this.methodCount = methodCount;
		this.varCount = varCount;
		this.maxCyclomaticCpl = maxCyclomaticCpl;
	}
	
	public String getAttendID(){
		return attendID;
	}
	
	public String getStudentID(){
		return studentID;
	}
	
	public String getSubjectID(){
		return subjectID;
	}
	
	public int getSecond(){
		return second;
	}
	
	public int getLineCount(){
		return lineCount;
	}
	
	public int getNoteCount(){
		return noteCount;
	}
	
	public int getMethodCount(){
		return methodCount;
	}
	
	public int getVarCount(){
		return varCount;
	}
	
	public int getMaxCyclomaticCpl(){
		return maxCyclomaticCpl;
	}
	
}
