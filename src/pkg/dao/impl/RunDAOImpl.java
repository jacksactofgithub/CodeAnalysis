package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.DBOperation;
import pkg.dao.RunDAO;
import pkg.entity.Run;

@Repository
public class RunDAOImpl implements RunDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public Run addRun(String proName, int student_id) {
		// TODO Auto-generated method stub
		Run run = new Run();
		run.setPro_name(proName);
		run.setStudent_id(student_id);
		
		dbopt.save(run);
		
		return run;
	}

	@Override
	public List<Run> queryRuns(int student_id, String proName) {
		// TODO Auto-generated method stub
		String hql = "from run as run where run.student_id=? and run.pro_name=?";
		
		@SuppressWarnings("unchecked")
		List<Run> list = dbopt.findList(hql, student_id , proName);
		
		return list;
	}

}
