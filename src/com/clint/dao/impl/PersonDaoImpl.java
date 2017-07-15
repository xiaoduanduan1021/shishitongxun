package com.clint.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import util.dao.BaseHibernate;

import com.clint.dao.PersonDao;
import com.clint.model.Person;
import com.test.lucene.model.UsrUsers;


@Repository(value="personDao")
public class PersonDaoImpl extends BaseHibernate implements PersonDao {
	
	
	@Autowired
	private  HibernateTemplate hibernateTemplate;

	Logger log = Logger.getLogger(PersonDaoImpl.class);
	public void savePerson(Person p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	public void deletePerson(Person p) {
		hibernateTemplate.delete(p);
	}

	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	public Person findPersonById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Person> findAllPerson() {
		
		List l1 = (List) hibernateTemplate.find(" from Person");
		List l = (List) hibernateTemplate.find(" from Xinxi");
		return hibernateTemplate.find(" from Person");
	}
	
	public final static List<UsrUsers> listAllUser = new ArrayList<UsrUsers>();
	
	long kaishi = 0;
	long jieshu = 0;
	//测试lucene高速搜索功能
	public Object getUser(){
		
		String hql = " from UsrUsers where id<100";
		List<UsrUsers> listusrUsrUsers = this.getObjects(hql);
		
		//------------------------------------------------------
		
//		kaishi = this.getms();
//		System.out.println("hql执行开始"+kaishi);
//		
//		String hql2 = " from UsrUsers where nickname like '%段%'";
//		List<UsrUsers> listusrUsrUsers2 = this.getObjects(hql2);
//		
//		jieshu = this.getms();
//		System.out.println("结束时间"+jieshu);
//		System.out.println("用时"+(jieshu-kaishi));
		//-------------------------------------------------------------------------
		//把数据库中所有数据放入内存
		//每次读取条数
		int mcdq = 10000;
		//开始id
		int ksid = 0;
		
		//最大id
		int maxid = 0;
		//内存最大id
		Integer neicunZuihou = 0;
		//判断内存中是否没有数据，如果有则进入查询，如果没有则获取最大id开始存入内存
		if (listAllUser.size() == 0) {
			
			
			
			
			
			kaishijishi("转存开始");
			//获取最大id
			maxid = getMaxId();
		
			do {
				//获取指定条数记录
				List<UsrUsers> listUserDb = getUserDB(ksid, mcdq);
				//将记录存入内存
				listAllUser.addAll(listUserDb);
				//判断最后的ID是否达到最大id，如果达到则结束进入查询，如果没有达到则继续转存
				neicunZuihou = listAllUser.get(listAllUser.size()-1).getId();
				ksid = neicunZuihou;
				
				
				System.out.println(ksid);
				jieshujishi();
			} while (neicunZuihou != maxid);
			
			
			
		}
		
		return null;
	}
	//获取当前毫秒
	long getms(){
		return System.currentTimeMillis();
	}
	
	//获取最大id数
	public Integer getMaxId(){
		  String hql = "select max(id) from UsrUsers";
		  Integer count = (Integer)hibernateTemplate.find(hql).listIterator().next();
		  return count;
	}
	//获取指定开始id和条数记录
	public List<UsrUsers> getUserDB(int starid , int size){
		  String hql = "from UsrUsers where id>"+starid+" and id<"+(size+starid);
		  List<UsrUsers> l = hibernateTemplate.find(hql);
		  return l;
	}
	
	//开始计时
	public void kaishijishi (String shuoming){
		kaishi = this.getms();
		System.out.println(shuoming+kaishi);
	}
	//结束计时
	public void jieshujishi(){
		jieshu = this.getms();
		System.out.println("当前时间"+jieshu);
		System.out.println("用时ms"+(jieshu-kaishi));
		System.out.println("用时s"+((jieshu-kaishi)/1000));
	}
}
