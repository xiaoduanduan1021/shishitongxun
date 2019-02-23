package util.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;






import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.page.PageList;




public class BaseHibernate{
	
	@Autowired
	private  HibernateTemplate hibernateTemplate;
	
	public Object getObject(Class clazz, Serializable id){
		Object o = hibernateTemplate.get(clazz, id);
		return o;
	}
 /* protected final Log log = LogFactory.getLog(super.getClass());


  public void deleteObj(Class clazz, Serializable id){
	  
	  this.getHibernateTemplate().delete(getObject(clazz, id));
  }
  





  
  public List getObjects(String hql, int pagesize){
    if (pagesize == 0){
      return getHibernateTemplate().find(hql);
    }
    List lst = null;
    Query query = null;
    Session s = getSession();
    try{
      query = s.createQuery(hql);
      query.setFirstResult(0);
      query.setMaxResults(pagesize);
      lst = query.list();
    }catch (Exception ex){
      ex.printStackTrace();
    }finally{
      releaseSession(s);
    }
    return lst;
  }

  public PageList getPageObjects(String hql, int start, int pagesize){
    List lst = null;
    PageList page = null;
    int totalCount = 0;
    Query query = null;
    Session s = getSession();
    try {
      query = s.createQuery(hql);
      query.setFirstResult(start);
      query.setMaxResults(pagesize);

      lst = query.list();
      page = new PageList((ArrayList)lst, start, pagesize, totalCount);
    }catch(Exception ex){
      ex.printStackTrace();
    }finally{
      releaseSession(s);
    }
    return page;
  }
*/
  public List getObjects(String hql){
    return hibernateTemplate.find(hql);
  }

/*  public void delObjects(List lst){
    getHibernateTemplate().deleteAll(lst);
  }

  public void delObjects(String hql){
    List lst = getObjects(hql);
    getHibernateTemplate().deleteAll(lst);
  }

  public Object saveOrUpdateObject(Object o){
    if (o != null){
      getHibernateTemplate().saveOrUpdate(o);
    }
    return o;
  }

  
  public int saveBatch(List list,Object obj){
	  int i ;
	  Session s = this.getHibernateTemplate().getSessionFactory().openSession();
	  s.beginTransaction();
	  try{
		  for(i=0;i<list.size();i++){
			  obj = (Object)list.get(i);
			  s.save(obj);
			  if(i%20 == 0){
				  s.flush();
				  s.clear();
			  }
		  }
		  s.getTransaction().commit();
	  }catch(Exception e){
		  s.getTransaction().rollback();
		  i = 0;
		  e.printStackTrace();
	  }finally{
		  s.close();
	  }
	  return i;
  }
  
  
  public PageList getPageObject(final String  hql, final int start, final int pagesize) {
	List l =   this.getHibernateTemplate().executeFind(new HibernateCallback() {     
		   public Object doInHibernate(Session session)   throws HibernateException, SQLException {     
		    	    //获取list
			   		 Query query = session.createQuery(hql);     
		    	     query.setFirstResult(start);     
		    	     query.setMaxResults(pagesize);  
		    	     List list = query.list();		    	    
		    	     return list;
		    }
	  });	  
	  return new PageList((ArrayList)l, start, pagesize, this.getObjects(hql).size());
  }
  
  public List getObject(String hql,Object[] params){
	  List list = this.getHibernateTemplate().find(hql, params);
	  return list;
  }
  
*/
  public Object addObject(Object o){
	    if (o != null) {
	      hibernateTemplate.save(o);
	    }
	    return o;
  }
  
  public PageList getPageObjects(final String  hql,final Object[] params, final int start, final int pagesize) {
	  List l =   hibernateTemplate.executeFind(new HibernateCallback() {     
		   public Object doInHibernate(Session session)   throws HibernateException, SQLException {     
		    	    //获取list			   			
			   		 Query query = session.createQuery(hql);   
			   		 query = setQuery(query,params);
		    	     query.setFirstResult(start);     
		    	     query.setMaxResults(pagesize);		    	     
		    	     List list = query.list();		    	   
		    	     return list;
		    }
	  });	  
	  
	  //后面的参数是总条数
	  return new PageList((ArrayList)l, start, pagesize, 0);
  }
  
  public List getObjects(String hql,Object[] params){
	  List list = hibernateTemplate.find(hql, params);
	  return list;
  }
  
  public Object getObjectByHqlAndParams(String hql,Object[] params){
	  List list = hibernateTemplate.find(hql, params);
	  if (list.size()>0) {
		return list.get(0);
	  }
	  return null;
  }
  
  
  public Query setQuery(Query query,Object[] params){
	  for(int i=0;i<params.length;i++){
		  Object obj = params[i];
		  if(obj instanceof String){
			  query.setString(i, (String) obj);
		  } else if(obj instanceof Integer) {
			  query.setInteger(i, (Integer) obj);
		  } else if(obj instanceof Double) {
			  query.setDouble(i, (Double) obj);
		  } else if(obj instanceof Long) {
			  query.setLong(i, (Long) obj);
		  } else if(obj instanceof Float){
			  query.setFloat(i, (Float)obj);
		  }  else if(obj instanceof Character) {
			  query.setCharacter(i, (Character) obj);
		  } else if(obj instanceof Short) {
			  query.setShort(i, (Short) obj);
		  } else if(obj instanceof Boolean) {
			  query.setBoolean(i, (Boolean) obj);
		  } else if(obj instanceof Date){
			  query.setDate(i, (Date) obj);
		  } else if(obj  instanceof Byte) {
			  query.setByte(i, (Byte) obj);
		  } else if(obj instanceof BigDecimal) {
			  query.setBigDecimal(i, (BigDecimal) obj);
		  }		 
	  }
	  return query;
  }
  /*
  
  //通过id批量删除数据或更新数据
  public int bulkDeleteOrUpdate(final String hql, final List<Integer> ids) {
	  if(ids!=null && ids.size()>0){
		  int count = (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					query.setParameterList("ids", ids.toArray());
					return query.executeUpdate();
				}
			});
			return count;
		} 
	  return 0;
  }
  
  //通过查询条数中有枚举数组的参数来获取数据
  public List findBatch(final String hql,final List<Integer> ids){
	  if(ids!=null && ids.size()>0){
		 List list = (List) this.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					query.setParameterList("ids", ids.toArray());
					return query.list();
				}
			});
		 return list;
	  }
	  return null;
  }
  
  //通过id批量删除数据
  public int bulkDelete(final String hql) {	  
		int count = (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
		return count;
  }
  

	
  //获取查询符合条件的hql语句的数量
  //String hql = "select count(*) from User"
  public long getCount(final String  hql,final Object[] params) {
	long count = (Long) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				 query = setQuery(query,params);
				return query.uniqueResult();
			}
		});
	  return count;
  }
  
  
  //hibernate执行原生生sql语句
  public List executeNativeSql(final String  sql) {
	List list =  (List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				return query.list();
			}
		});
	return list;
  }*/
  //获取第一个符合hql的object，如果没有返回null
	public Object getObjectByHql(String hql) {
		List<Object> l = hibernateTemplate.find(hql);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}
	
	  public Object updateObject(Object o){
	    if (o != null) {
	      hibernateTemplate.update(o);
	    }
	    return o;
	  }
	  
	  public Object delObject(Object o){
	    if (o != null) {
	      hibernateTemplate.delete(o);
	    }
	    return o;
	  }
}