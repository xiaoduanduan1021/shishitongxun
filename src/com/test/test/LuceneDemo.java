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
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.clint.dao.impl.PersonDaoImpl;
import com.test.lucene.controller.luceneController;

public class LuceneDemo {

	public static final Version version = Version.LUCENE_4_9;
	
	public static void main(String[] args) throws Exception {
		
		
		Analyzer analyzer = new StandardAnalyzer(version);

		// 要在磁盘上存储索引，请使用此索引：
		Directory directory = FSDirectory.open(new File("E:/suoyin"));
		 
		// 现在搜索索引
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);
		
		
		
		
		long kaishi = System.currentTimeMillis();
		
		// 解析搜索“文本”的简单查询：
		QueryParser parser = new QueryParser(version, "nickname", analyzer);
		//开启正则表达式
//		parser.setAllowLeadingWildcard(true);
		
//		Query query = parser.parse("*小*");
		Query query = parser.parse("消失的一段文字");
		
		int start = 1;
		int pagesize = 1000;
		
        ScoreDoc lastSd = new LuceneDemo().getLastScoreDoc(start, pagesize, query, isearcher);
//        TopDocs tds = isearcher.searchAfter(lastSd,query, pagesize , new Sort(SortField.FIELD_DOC));
        TopDocs tds = isearcher.searchAfter(lastSd,query, pagesize);
        
        System.out.println("共查到"+tds.scoreDocs.length+"条");
        
		//查询结果输出多少行
        //输出全部
     
        int hangshu = 500;
        
        
		long jieshu = System.currentTimeMillis();
		System.out.println(jieshu - kaishi+"ms");

		//输出20行
		if (tds.scoreDocs.length<hangshu) {
			
			for(ScoreDoc sd:tds.scoreDocs) {
				Document doc = isearcher.doc(sd.doc);
				System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}else{
			
			for(int i = 0 ;i<hangshu ;i ++ ) {
				ScoreDoc sd = tds.scoreDocs[i];
				Document doc = isearcher.doc(sd.doc);
				System.out.println(doc.get("id")+"  "+doc.get("openid")+"  "+doc.get("nickname")+"  "+doc.get("creat_time"));
			}
		}
        
		
		
		
		
		
		
		
		
		ireader.close();
		

		directory.close();
		

	}
    /**
     * 返回分页查询的上一条
     * @param pageIndex
     * @param pageSize
     * @param query
     * @param indexSearcher
     * @return
     */
    public ScoreDoc getLastScoreDoc(int pageIndex,int pageSize,Query query,IndexSearcher searcher){
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