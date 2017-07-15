package com.clint.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
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
	
	
	private static final Version version = Version.LUCENE_4_9;
	long kaishi = 0;
	long jieshu = 0;
	//测试lucene高速搜索功能
	public Object getUser() throws Exception{
		
		String hql = " from UsrUsers where id<10";
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
		//把数据库中所有数据放入lucene
		//每次读取条数
		int mcdq = 10000;
		//开始id
		int ksid = 0;
		
		//最大id
		int maxid = 0;
		//lucene最大id
		Integer neicunZuihou = 0;
		//判断lucene中是否没有数据，如果有则进入查询，如果没有则获取最大id开始存入内存
		

		
		Analyzer analyzer = new StandardAnalyzer(version);
		Directory directory = FSDirectory.open(new File("E:/suoyin"));
		//建立写入模块
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);
		
		// 建立查询模块
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// 建立查询解析器
		QueryParser parser = new QueryParser(version, "fieldname", analyzer);
		//开启正则表达式
		parser.setAllowLeadingWildcard(true);
		Query query = parser.parse("*");
		ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
		// 迭代结果：
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			System.out.println(hitDoc.get("fieldname"));
		}

		
		
		
		
		
		
		
//		if (listAllUser.size() == 0) {
//			
//			
//			
//			
//			
//			kaishijishi("转存开始");
//			//获取最大id
//			maxid = getMaxId();
//		
//			do {
//				//获取指定条数记录
//				List<UsrUsers> listUserDb = getUserDB(ksid, mcdq);
//				//将记录存入内存
//				listAllUser.addAll(listUserDb);
//				//判断最后的ID是否达到最大id，如果达到则结束进入查询，如果没有达到则继续转存
//				neicunZuihou = listAllUser.get(listAllUser.size()-1).getId();
//				ksid = neicunZuihou;
//				
//				
//				System.out.println(ksid);
//				jieshujishi();
//			} while (neicunZuihou != maxid);
//			
//			
//			
//		}
		//关闭写入模块
		iwriter.close();
		//关闭查询模块
		ireader.close();
		directory.close();
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
