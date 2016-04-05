package pkg.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pkg.dao.DBOperation;

@Controller
public class Test {

	@Autowired
	DBOperation dbopt;
	
	public boolean isNull(){
		return (dbopt == null);
	}
	
}
