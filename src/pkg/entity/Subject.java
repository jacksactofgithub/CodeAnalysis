package pkg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="subject")
public class Subject {

	@Id
	private int subject_id;
	private String subject_name;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="exam_id")
	private Exam exam;
	
	public int getSubject_Id(){
		return subject_id;
	}
	
	public String getSubject_Name(){
		return subject_name;
	}
	
	public Exam getExam(){
		return exam;
	}
	
}
