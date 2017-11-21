package com.clint.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.stereotype.Service;

import com.clint.dao.PersonDao;
import com.clint.model.Person;
import com.clint.service.PersonService;
import com.test.lucene.controller.luceneController;
import com.test.test.LuceneDemo;



@Service(value="personService")
public class PersonServiceImpl implements PersonService {

	@Resource(name="personDao")
	private PersonDao personDao;
	
	public void savePerson(Person p) {
		personDao.savePerson(p);
	}
	public void deletePerson(Person p) {
		personDao.deletePerson(p);
	}
	public List<Person> findAllPerson() {
		return personDao.findAllPerson();
	}
	public Person findPersonById(String id) {
		return personDao.findPersonById(id);
	}
	public void updatePerson(Person p) {
		personDao.updatePerson(p);
	}
	//测试lucene高速搜索功能
	public Object getUser(){
		try {
			return this.personDao.getUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personDao;
	}
	
	public List<String> Searche(String text) throws Exception{
		
		List<String> ljieguo = new ArrayList<String>();
		
		IndexSearcher isearcher = luceneController.isearcher;
		
		long kaishi = System.currentTimeMillis();
		
		// 解析搜索“文本”的简单查询：
		QueryParser parser = new QueryParser(LuceneDemo.version, "nickname", luceneController.analyzer);
		//开启正则表达式
		parser.setAllowLeadingWildcard(true);
		
		Query query = parser.parse(text);
		
		int start = 1;
		int pagesize = 100;
		
        ScoreDoc lastSd = new LuceneDemo().getLastScoreDoc(start, pagesize, query, isearcher);
        TopDocs tds = isearcher.searchAfter(lastSd,query, pagesize);
        
        ljieguo.add("共查到"+tds.scoreDocs.length+"条");
        
		long jieshu = System.currentTimeMillis();
		ljieguo.add(jieshu - kaishi+"ms");
		
		//查询结果输出多少行
		if (tds.scoreDocs.length<pagesize) {
			for(ScoreDoc sd:tds.scoreDocs) {
				Document doc = isearcher.doc(sd.doc);
				ljieguo.add(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}else{
			for(int i = 0 ;i<pagesize ;i ++ ) {
				ScoreDoc sd = tds.scoreDocs[i];
				Document doc = isearcher.doc(sd.doc);
				ljieguo.add(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}
		return ljieguo;

	}
}
