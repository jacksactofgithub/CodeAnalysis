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
@Table(name="test_name")
public class TestName {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int name_id;
	@ManyToOne
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@JoinColumn(name="common_id")
	private CommonTest common;
	private String test_name;
	
	
	public CommonTest getCommon(){
		return common;
	}
	public void setCommon(CommonTest common){
		this.common = common;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	
}
