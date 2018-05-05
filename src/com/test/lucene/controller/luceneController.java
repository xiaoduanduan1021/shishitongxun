package com.test.lucene.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clint.service.PersonService;
import com.test.lucene.model.UsrUsers;
import com.test.test.LuceneDemo;

@Controller
@RequestMapping(value = "/lucene")
public class luceneController {
	
	
	public static IndexSearcher isearcher = null;
	public static Analyzer analyzer = null;
	
	
	@Resource(name = "personService")
	private PersonService personService;

	@RequestMapping(value = "/getUser")
	public void getUser(UsrUsers usrUsers) {
		System.out.println("开始同步，将数据库的user表的内容同步到搜索引擎库");
		personService.getUser();
	}
	
	@RequestMapping(value = "/getSearch", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getSearch(String text) throws Exception {
//		System.out.println("搜索"+text);
		long kaishi = System.currentTimeMillis();
		
		List<String> listsearche = personService.Searche(text);
		JSONObject json = new JSONObject();
		json.put("listsearche", listsearche);
		
		
		long jieshu = System.currentTimeMillis();
		
		System.out.println(jieshu - kaishi+"ms,总耗时");
		
		
		return json.toString();
	}
	
	
	
	
	
	
	static{

		
		/*去掉这个注释既可以自动启动搜索引擎
		
		System.out.println("建立搜索引擎库文件连接");
		//项目启动时建立搜索引擎读取块，搜索时可以直接搜索，这个可以写在项目的工具类中，写在这是测试方便查看
		analyzer = new StandardAnalyzer(LuceneDemo.version);

		// 要在磁盘上存储索引，请使用此索引：
		Directory directory;
		
		try {
			directory = FSDirectory.open(new File("E:/suoyin"));
		 
			// 现在搜索索引
			DirectoryReader ireader = DirectoryReader.open(directory);
			isearcher = new IndexSearcher(ireader);
		
			System.out.println("建立搜索引擎库文件连接--完成");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}
}
