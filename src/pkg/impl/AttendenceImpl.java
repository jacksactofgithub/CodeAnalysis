package pkg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pkg.dao.AttendenceDAO;
import pkg.dao.DBOperation;
import pkg.entity.Attendence;
import pkg.hibernate.HibernateServiceSupport;

@Repository
public class AttendenceImpl extends HibernateServiceSupport implements AttendenceDAO{

	@Autowired
	private DBOperation dbopt;
	
	@Override
	public int addAttendence(Attendence attendence) {
		System.out.println("saving attendence");
		return dbopt.save(attendence);
	}

}
