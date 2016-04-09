package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.DBOperation;
import pkg.dao.RunDAO;
import pkg.entity.ProblemResult;
import pkg.entity.Run;

@Repository
public class RunDAOImpl implements RunDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public Run addRun(String proName, int student_id , int run_second , int examID) {
		// TODO Auto-generated method stub
		Run run = new Run();
		run.setPro_name(proName);
		run.setStudent_id(student_id);
		run.setRun_second(run_second);
		run.setExam_id(examID);
		
		dbopt.save(run);
		
		return run;
	}

	@Override
	public List<Run> queryRuns(int student_id, String proName , int exam) {
		// TODO Auto-generated method stub
		String hql = "from Run as run where run.student_id=? and run.pro_name=? and run.exam_id=?";
		
		@SuppressWarnings("unchecked")
		List<Run> list = dbopt.findList(hql, student_id , proName , exam);
		
		return list;
	}

	@Override
	public List<Integer> queryStudentID(String proName , int exam) {
		// TODO Auto-generated method stub
		String hql = "select distinct student_id from Run as run where run.pro_name=? and run.exam_id=?";
		
		@SuppressWarnings("unchecked")
		List<Integer> list = dbopt.findList(hql, proName , exam);
		
		return list;
	}

	@Override
	public List<ProblemResult> queryAvgRun(String proName, int exam) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
