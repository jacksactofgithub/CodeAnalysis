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
@Table(name="test")
public class Test {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int test_id;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="run_id")
	private Run run;
	private String test_name;
	private boolean test_result;
	
	public void setRun(Run run){
		this.run = run;
	}
	
	public void setTest_name(String testName){
		this.test_name = testName;
	}
	
	public void setTest_result(boolean testResult){
		this.test_result = testResult;
	}
	
	public int getTest_Id(){
		return test_id;
	}
	
	public String getTest_Name(){
		return test_name;
	}
	
	public boolean getTest_Result(){
		return test_result;
	}
	
	public Run getSubject(){
		return run;
	}
	
}
