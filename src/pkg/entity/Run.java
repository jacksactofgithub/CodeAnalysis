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
	private String pro_name;
	private int student_id;
	
	public void setPro_name(String pro_name){
		this.pro_name = pro_name;
	}
	
	public void setStudent_id(int student_id){
		this.student_id = student_id;
	}
	
	public String getPro_name(){
		return pro_name;
	}
	
	public int getStudent(){
		return student_id;
	}
	
}
