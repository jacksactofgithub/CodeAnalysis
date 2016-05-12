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
			int note_count, int method_count,int var_count,
			int max_cyclomatic , long timestamp , String file_name) {
		// TODO Auto-generated method stub
		Code attendence = new Code(1,student, exam_id ,subject,second,line_count,note_count,
				method_count,var_count,max_cyclomatic , timestamp , file_name);
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

	@Override
	public List<String> queryAllProNames(int examId) {
		// TODO Auto-generated method stub
		String hql = "select distinct pro_name from Code as code where code.exam_id=?";
		
		@SuppressWarnings("unchecked")
		List<String> results = dbopt.findList(hql, examId);
		
		return results;
	}

	@Override
	public List<String> queryStuFileNames(int examId, int stu_id, String proName) {
		// TODO Auto-generated method stub
		String hql = "select distinct file_name from Code as code where code.exam_id=? and code.student_id=? and code.pro_name=?";
		
		@SuppressWarnings("unchecked")
		List<String> result = dbopt.findList(hql, examId , stu_id , proName);
		
		return result;
	}

	@Override
	public long getCodeCount(int examId) {
		// TODO Auto-generated method stub
		String hql = "select count(exam_id) from Code as code where code.exam_id=?";
		
		@SuppressWarnings("unchecked")
		List<Long> result = dbopt.findList(hql, examId);
		
		if(result.size() == 0){
			return 0;
		}
		
		return result.get(0);
	}

}
