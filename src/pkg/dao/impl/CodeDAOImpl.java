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
	public int addCode(int student,int exam_id , String subject, int second, int line_count,
			int note_count, int method_count,int var_count, int max_cyclomatic , long timestamp) {
		// TODO Auto-generated method stub
		Code attendence = new Code(1,student, exam_id ,subject,second,line_count,note_count,
				method_count,var_count,max_cyclomatic , timestamp);
		return dbopt.save(attendence);
	}

	@Override
	public int addCode(Code attendence) {
		// TODO Auto-generated method stub
		
		return dbopt.save(attendence);
	}

	@Override
	public List<Code> queryCode(int stu_id, String proName , int exam) {
		// TODO Auto-generated method stub
		String hql = "from Code as code where code.student_id=? and code.pro_name=? and code.exam_id=? order by code.second asc";
		@SuppressWarnings("unchecked")
		List<Code> list = dbopt.findList(hql, stu_id , proName , exam);
		
		return list;
	}

	@Override
	public List<Code> queryAvg(int exam, String proName) {
		// TODO Auto-generated method stub
		return null;
	}

}
