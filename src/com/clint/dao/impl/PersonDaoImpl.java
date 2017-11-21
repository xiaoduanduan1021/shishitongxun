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
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiReader;
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
	//该方法调用后系统自动从数据库的 usruser表中获取数据自动累加到lucene的索引文件中
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
		//链接lucene索引
		Analyzer analyzer = new StandardAnalyzer(version);
		Directory directory = FSDirectory.open(new File("E:/suoyin"));
		//把数据库中所有数据放入lucene
		//每次读取条数
		int mcdq = 10000;
		//开始id
		int ksid = 0;
		
		//获取数据库最大id
		int maxid = getMaxId();
		
		//判断lucene中是否没有数据，如果有则进入查询，如果没有则获取最大id开始存入内存
		
		//查询当前lucene中是否有数据
		int DangQianZongShu = 0;
		
		
		//获取总数
		DangQianZongShu = getLuceneIndexNum(directory);
		//获取最后一个id
		int luceneMaxid = 0;
		if (DangQianZongShu==0) {
			
		}else{
			
			Document docButton = getDocumentByDocId(DangQianZongShu-1, directory);
			luceneMaxid = (int)Integer.valueOf(docButton.get("id"));
		}
		
		
		//索引中的最大id
		System.out.println(luceneMaxid);
		
		if (DangQianZongShu == 0) {
			ksid =0;
		}else{
			ksid = luceneMaxid;
		}
		
		//如果当前索引中没有数据或者数据的最大id没有数据库大则开始更新转存
		if (DangQianZongShu == 0 || luceneMaxid<maxid) {
			
			kaishijishi("转存开始");
			
		
			do {
				//获取指定条数记录
				List<UsrUsers> listUserDb = getUserDB(ksid, mcdq);
				
				//建立写入模块
				IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
				IndexWriter iwriter = new IndexWriter(directory, config);
				//将记录存入lucene
				for (int i = 0; i < listUserDb.size(); i++) {
					UsrUsers db = listUserDb.get(i);
					
					Document doc = new Document();

					doc.add(new Field("id", ""+db.getId(), TextField.TYPE_STORED));
					doc.add(new Field("openid", db.getOpenid()==null ? "":db.getOpenid(), TextField.TYPE_STORED));
					doc.add(new Field("nickname", db.getNickname() == null ? "" : db.getNickname(), TextField.TYPE_STORED));
					doc.add(new Field("creat_time", db.getCreat_time() == null ? "" : db.getCreat_time(), TextField.TYPE_STORED));
					
					iwriter.addDocument(doc);
				}
				iwriter.close();
				
				//判断最后的ID是否达到最大id，如果达到则结束进入查询，如果没有达到则继续转存
				luceneMaxid = listUserDb.get(listUserDb.size()-1).getId();
				ksid = luceneMaxid;
				System.out.println(ksid);
				jieshujishi();
			} while ( luceneMaxid < maxid);
			
			
			
		}
		
		//查询关键字
		
		//关闭索引
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
		  if (l.size()==0) {
			  return getUserDB(starid, size+10000);
		  }
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
	//获取lucene总文档数
	//获取总数
	public int getLuceneIndexNum(Directory directory) throws IOException{
		IndexReader reader = null;
		int zongshu =0;
		try {
			reader = MultiReader.open(directory);
			zongshu = reader.numDocs();
			reader.close();
		} catch (IOException e) {
			zongshu = 0;
		}
		return zongshu;
	}
	
	//根据docmentId
	public Document getDocumentByDocId(int docId,Directory directory) throws IOException{
		DirectoryReader ireader = DirectoryReader.open(directory);
		Document hitDoc = ireader.document(docId);
		ireader.close();
		return hitDoc;
	}
	
	//lucene搜索功能
	public ScoreDoc[] getDocByserch(Directory directory,int pageSize,String key,String field,Analyzer analyzer) throws IOException, ParseException{
		// 现在搜索索引
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		// 解析搜索“文本”的简单查询：
		QueryParser parser = new QueryParser(version, field, analyzer);
		//开启正则表达式
		parser.setAllowLeadingWildcard(true);
		
		Query query = parser.parse(key);
		ScoreDoc[] hits = isearcher.search(query, null, 10).scoreDocs;
		
		ireader.close();
		
		return hits;
	}
}