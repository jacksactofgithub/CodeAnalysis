package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.AttendenceDAO;
import pkg.dao.DBOperation;
import pkg.entity.Attendence;

@Repository
public class AttendenceDAOImpl implements AttendenceDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public int addAttendence(int student, String subject, int second, int line_count, int note_count, int method_count,
			int var_count, int max_cyclomatic) {
		// TODO Auto-generated method stub
		Attendence attendence = new Attendence(1,student,subject,second,line_count,note_count,
				method_count,var_count,max_cyclomatic);
		return dbopt.save(attendence);
	}

	@Override
	public int addAttendence(Attendence attendence) {
		// TODO Auto-generated method stub
		return dbopt.save(attendence);
	}

	@Override
	public List<Attendence> queryAttendences(int stu_id, String proName) {
		// TODO Auto-generated method stub
		String hql = "from attendence as att where att.student_id=? and pro_name=?";
		@SuppressWarnings("unchecked")
		List<Attendence> list = dbopt.findList(hql, stu_id , proName);
		
		return list;
	}

}
