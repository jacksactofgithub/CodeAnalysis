package pkg.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="code")
public class Code implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int attend_id;
	private int student_id;
	private int exam_id;
	private String pro_name;
	private int second;
	private int line_count;
	private int note_count;
	private int method_count;
	private int var_count;
	private int max_cyclomaticcpl;
	private long timestamp;
	private String file_name;
	
	public Code(int attendID,int student_id,int exam_id , String pro_name,int second ,
			int lineCount , int noteCount , int methodCount ,
			int varCount , int maxCyclomaticCpl , long timestamp , String file_name){
		this.attend_id = attendID;
		this.exam_id = exam_id;
		this.setStudent_id(student_id);
		this.setPro_name(pro_name);
		this.setSecond(second);
		this.setLine_count(lineCount);
		this.setNote_count(noteCount);
		this.setMethod_count(methodCount);
		this.setVar_count(varCount);
		this.setMax_cyclomaticcpl(maxCyclomaticCpl);
		this.setTimestamp(timestamp);
		this.file_name = file_name;
	}
	
	public Code(){
		
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getLine_count() {
		return line_count;
	}

	public void setLine_count(int line_count) {
		this.line_count = line_count;
	}

	public int getNote_count() {
		return note_count;
	}

	public void setNote_count(int note_count) {
		this.note_count = note_count;
	}

	public int getMethod_count() {
		return method_count;
	}

	public void setMethod_count(int method_count) {
		this.method_count = method_count;
	}

	public int getVar_count() {
		return var_count;
	}

	public void setVar_count(int var_count) {
		this.var_count = var_count;
	}

	public int getMax_cyclomaticcpl() {
		return max_cyclomaticcpl;
	}

	public void setMax_cyclomaticcpl(int max_cyclomaticcpl) {
		this.max_cyclomaticcpl = max_cyclomaticcpl;
	}

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	

	
}
