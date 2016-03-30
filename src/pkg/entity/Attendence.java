package pkg.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendence")
public class Attendence implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attend_id;
	private String student_id;
	private String subject_id;
	private int second;
	private int line_count;
	private int note_count;
	private int method_count;
	private int var_count;
	private int max_cyclomaticcpl;
	
	public Attendence(int attendID,String studentID,String subjectID,int second ,
			int lineCount , int noteCount , int methodCount , int varCount , int maxCyclomaticCpl){
		this.attend_id = attendID;
		this.student_id = studentID;
		this.subject_id = subjectID;
		this.second = second;
		this.line_count = lineCount;
		this.note_count = noteCount;
		this.method_count = methodCount;
		this.var_count = varCount;
		this.max_cyclomaticcpl = maxCyclomaticCpl;
	}
	
	public int getAttendID(){
		return attend_id;
	}
	
	public String getStudentID(){
		return student_id;
	}
	
	public String getSubjectID(){
		return subject_id;
	}
	
	public int getSecond(){
		return second;
	}
	
	public int getLineCount(){
		return line_count;
	}
	
	public int getNoteCount(){
		return note_count;
	}
	
	public int getMethodCount(){
		return method_count;
	}
	
	public int getVarCount(){
		return var_count;
	}
	
	public int getMaxCyclomaticCpl(){
		return max_cyclomaticcpl;
	}
	
}
