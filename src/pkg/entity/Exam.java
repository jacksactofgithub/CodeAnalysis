package pkg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exam")
public class Exam {

	@Id
	private int exam_id;
	private String exam_name;
	
	public int getExam_Id(){
		return exam_id;
	}
	
	public String getExam_Name(){
		return exam_name;
	}
	
}
