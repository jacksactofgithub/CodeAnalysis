package lmooc.modulize.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import lmooc.modulize.entity.Attendence;
import util.HibernateUtil;

public class AttendenceDAO{

	public void addAttendence(Attendence attendence){
		Session session = HibernateUtil.sessionFactory.getCurrentSession();
		
		Transaction trans = session.beginTransaction();
		
		session.save(attendence);
		trans.commit();
	}
	
//	public static void main(String[] args){
//		AttendenceDAO dao = new AttendenceDAO();
//		
//		Attendence attendence = new Attendence("1", "1", "1", 1, 1, 1, 1, 1, 1);
//		
//		dao.addAttendence(attendence);
//	}
	
}
