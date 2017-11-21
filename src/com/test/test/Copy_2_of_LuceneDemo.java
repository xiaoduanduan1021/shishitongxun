package com.test.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.clint.dao.impl.PersonDaoImpl;
import com.test.lucene.controller.luceneController;

public class Copy_2_of_LuceneDemo {

	public static final Version version = Version.LUCENE_4_9;
	
	public static void main(String[] args) throws Exception {
		
		
		Analyzer analyzer = new StandardAnalyzer(version);

		// 要在磁盘上存储索引，请使用此索引：
		Directory directory = FSDirectory.open(new File("E:/suoyin"));
		 
		// 现在搜索索引
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		
		
		
		long kaishi = System.currentTimeMillis();
		
        
        WildcardQuery query = new WildcardQuery(new Term("nickname","小*"));
//		FuzzyQuery query=new FuzzyQuery(new Term("nickname", "刷~0.8"),0);     
        
        
        ScoreDoc[] scoreDocs=isearcher.search(query,1000).scoreDocs;
		
        System.out.println("共查到"+scoreDocs.length+"条");
        
		//查询结果输出多少行
        //输出全部
     
        int hangshu = 1000;
        
        //输出20行
		if (scoreDocs.length<hangshu) {
			
	        for(ScoreDoc sd:scoreDocs) {
	            Document doc = isearcher.doc(sd.doc);
	            System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
	        }
		}else{
			
			for(int i = 0 ;i<hangshu ;i ++ ) {
				ScoreDoc sd = scoreDocs[i];
				Document doc = isearcher.doc(sd.doc);
				System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}
        
		long jieshu = System.currentTimeMillis();
		System.out.println(jieshu - kaishi+"ms");
        
		
		
		
		
		
		
		
		
		ireader.close();
		

		directory.close();
		

	}
}