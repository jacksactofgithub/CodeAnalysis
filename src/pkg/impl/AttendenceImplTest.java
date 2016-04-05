//package pkg.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import pkg.dao.AttendenceDAO;
//import pkg.dao.DBOperation;
//import pkg.entity.Attendence;
//import pkg.hibernate.HibernateServiceSupport;
//
//@Repository
//public class AttendenceImplTest extends HibernateServiceSupport implements AttendenceDAO{
//
//	@Autowired
//	private DBOperation dbopt;
//	
//	@Override
//	public int addAttendence(Attendence attendence) {
//		System.out.println("saving attendence");
//		return dbopt.save(attendence);
//	}
//
//	@Override
//	public int addAttendence(int student, String subject, int second, int line_count, int note_count, int method_count,
//			int var_count, int max_cyclomatic) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Attendence> queryAttendences(int stu_id, String proName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
