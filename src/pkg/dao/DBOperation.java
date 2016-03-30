package pkg.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import pkg.hibernate.HibernateServiceSupport;


@Repository
public class DBOperation extends HibernateServiceSupport{

	private static DBOperation operation = new DBOperation();
	
	public static DBOperation getInstance(){
		return operation;
	}
	
	private DBOperation(){
		
	}
	
	//增
	public int save(Object entity){
		
		try{
			//this.save(entity);
			this.getHibernateTemplate().save(entity);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	//改
	public int update(Object entity){
		
		try{
			this.getHibernateTemplate().update(entity);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	//删
	public int delete(Object entity){
		
		try{
			this.getHibernateTemplate().delete(entity);
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
		return 1;
		
	}
	
	//查
	@SuppressWarnings("rawtypes")
	public ArrayList findList(String hql,Object... args){
		Session session = getSession();
		Query query =session.createQuery(hql);
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		ArrayList arrayList = (ArrayList) query.list();
		releaseSession(session); 
		return arrayList;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList findbyList(String hql,List<Object> list){
		Session session = getSession();
		Query query =session.createQuery(hql);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		ArrayList arrayList = (ArrayList) query.list();
		releaseSession(session);
		return arrayList;
	}
	
}
