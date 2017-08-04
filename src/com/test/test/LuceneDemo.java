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
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.clint.dao.impl.PersonDaoImpl;
import com.test.lucene.controller.luceneController;

public class LuceneDemo {

	private static final Version version = Version.LUCENE_4_9;

	public static void main(String[] args) throws Exception {
		
		long kaishi = System.currentTimeMillis();
		
		Analyzer analyzer = new StandardAnalyzer(version);

		// 要在磁盘上存储索引，请使用此索引：
		Directory directory = FSDirectory.open(new File("E:/suoyin"));
		 
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);
		
		iwriter.close();
		
		
		// 现在搜索索引
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		
		// 解析搜索“文本”的简单查询：
		QueryParser parser = new QueryParser(version, "nickname", analyzer);
		//开启正则表达式
		parser.setAllowLeadingWildcard(true);
		
//		Query query = parser.parse("*张*");
//		Query query = parser.parse("*李*");
//		Query query = parser.parse("*赵*");
		Query query = parser.parse("*小*");
		
		int start = 1;
		int pagesize = 10;
		
        ScoreDoc lastSd = new LuceneDemo().getLastScoreDoc(start, pagesize, query, isearcher);
        TopDocs tds = isearcher.searchAfter(lastSd,query, pagesize);
        
        System.out.println("共查到"+tds.scoreDocs.length+"条");
        
		
		if (tds.scoreDocs.length<20) {
			
	        for(ScoreDoc sd:tds.scoreDocs) {
	            Document doc = isearcher.doc(sd.doc);
	            System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
	        }
		}else{
			
			for(int i = 0 ;i<20 ;i ++ ) {
				ScoreDoc sd = tds.scoreDocs[i];
				Document doc = isearcher.doc(sd.doc);
				System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}
        
        
		
		ireader.close();
		

		directory.close();
		

		long jieshu = System.currentTimeMillis();
		System.out.println(jieshu - kaishi+"ms");
	}
    /**
     * 返回分页查询的上一条
     * @param pageIndex
     * @param pageSize
     * @param query
     * @param indexSearcher
     * @return
     */
    private ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher){
        if(pageIndex==1)return null;//如果是第一页就返回空
        int num = pageSize*(pageIndex-1);//获取上一页的数量
        TopDocs tds = null;
        try {
            tds = searcher.search(query, num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tds.scoreDocs[num-1];
    }
}