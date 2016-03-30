package pkg.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	@Id
	private int student_id;
	private String student_name;
	
	public int getStudent_Id(){
		return student_id;
	}
	
	public String getStudent_Name(){
		return student_name;
	}
	
}
