package pkg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="run")
public class Run {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int run_id;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="subject_id")
	private Subject subject;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="student_id")
	private Student student;
	
	public Subject getSubject(){
		return subject;
	}
	
	public Student getStudent(){
		return student;
	}
	
}
