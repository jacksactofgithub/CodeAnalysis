package pkg.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

//import cn.ztweb.util.PageModel;
//import cn.ztweb.util.SystemContext;


/**
 * 业务类的基类
 * @author wangzm
 *
 */
public class HibernateServiceSupport extends HibernateDaoSupport{

	@Autowired
	private void setSessionFactoryExtend(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * 根据ID查询实体
	 * @param classType
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findEntityById(Class<T> classType,Serializable id){
		return (T) getSession().get(classType, id);
	}
	
	/**
	 * 根据实体类名字查询所有的实体对象
	 * @param <T>
	 * @param classType
	 * @return
	 */
	
	//value 表示采用哪种缓存策略（ehcache.xml）。key:缓存的key。condition：是缓存的条件
	@SuppressWarnings("unchecked")
	public <T> List<T> findAllEntity(Class<T> classType){
		String hql=" from "+getEntityName(classType);
		HibernateTemplate h = this.getHibernateTemplate();
		List<T> list = h.find(hql);
		return list;
	}
	
	/**
	 * 执行hql语句返回单个实体对象，可以可以返回单个其他类型对象
	 * @param <T>
	 * @param hql
	 * @param classType
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findObject(String hql,Class<T> classType,Object... args){
		Session session =  getSession();
		Query query =session.createQuery(hql);
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		T t = (T) query.uniqueResult();
		releaseSession(session); 
		return t;
	}
	
	private  <T> String getEntityName(Class<T> classType){
		String className="";
		Entity entity = classType.getAnnotation(Entity.class);
		if(entity!=null){
			if(entity.name()!=null&&!"".equals(entity.name())){
				className =entity.name();
			}else{
				className=classType.getName();
			}
		}
		return className;
	}
	
	
//	/**
//	 * 
//	 * @param hql 比如："from Person p group by p.id"
//	 * @return
//	 */
//	private  String getCountHql(String hql){
//		if(hql!=null){
//			int fromIndex =hql.indexOf("from");
//			
//			String from =hql.substring(hql.lastIndexOf("from"));
//			return "select count(*) "+from;
//		}else{
//			return null;
//		}
//	}
	
	public int save(Object obj){
		
		try{
			getHibernateTemplate().save(obj);
		}catch(Exception e){
			return 0;
		}
		return 1;
	}
}
