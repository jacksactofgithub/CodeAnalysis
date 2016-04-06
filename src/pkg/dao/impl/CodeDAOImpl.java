package pkg.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.CodeDAO;
import pkg.dao.DBOperation;
import pkg.entity.Code;
import pkg.hibernate.HibernateServiceSupport;

@Repository
public class CodeDAOImpl extends HibernateServiceSupport implements CodeDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public int addCode(int student, String subject, int second, int line_count,
			int note_count, int method_count,int var_count, int max_cyclomatic) {
		// TODO Auto-generated method stub
		Code attendence = new Code(1,student,subject,second,line_count,note_count,
				method_count,var_count,max_cyclomatic);
		return dbopt.save(attendence);
	}

	@Override
	public int addCode(Code attendence) {
		// TODO Auto-generated method stub
		
		if(dbopt == null){
			System.out.println("dbopt is null");
			return 0;
		}
		System.out.println("adding attendence");
		
		return dbopt.save(attendence);
	}

	@Override
	public List<Code> queryAttendences(int stu_id, String proName) {
		// TODO Auto-generated method stub
		String hql = "from attendence as att where att.student_id=? and pro_name=?";
		@SuppressWarnings("unchecked")
		List<Code> list = dbopt.findList(hql, stu_id , proName);
		
		return list;
	}

}
