package pkg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="run")
public class Run {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int run_id;
	private int subject_id;
	private int student_id;
	
	public int getSubject(){
		return subject_id;
	}
	
	public int getStudent(){
		return student_id;
	}
	
}
