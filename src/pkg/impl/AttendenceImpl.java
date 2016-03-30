package pkg.impl;

import org.springframework.stereotype.Repository;

import pkg.dao.AttendenceDAO;
import pkg.dao.DBOperation;
import pkg.entity.Attendence;
import pkg.hibernate.HibernateServiceSupport;

@Repository
public class AttendenceImpl extends HibernateServiceSupport implements AttendenceDAO{

	private DBOperation dbopt = DBOperation.getInstance();
	
	@Override
	public int addAttendence(Attendence attendence) {
		// TODO Auto-generated method stub
		System.out.println("saving attendence");
		return dbopt.save(attendence);
	}

}
