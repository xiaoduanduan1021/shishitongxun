package com.test.test;

import java.io.File;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneDemo {

	private static final Version version = Version.LUCENE_4_9;

	public static void main(String[] args) throws Exception {
		Analyzer analyzer = new StandardAnalyzer(version);

		// 将索引存储在内存中:
//		Directory directory = new RAMDirectory();
		// 要在磁盘上存储索引，请使用此索引：
		 Directory directory = FSDirectory.open(new File("E:/suoyin"));

		 
		 
		 
		 
		IndexWriterConfig config = new IndexWriterConfig(version, analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);
		
		Document doc = new Document();
		Document doc2 = new Document();
		String text = "11111111111ssssssssssssss";
		String text2 = "撒拉嘿呦哈哈哈哈哈哈哈哈哈哈哈或或或或或或或";

		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		doc2.add(new Field("fieldname", text2, TextField.TYPE_STORED));
//		iwriter.addDocument(doc);
//		iwriter.addDocument(doc2);
		iwriter.close();

		
		
		
		
		
		
		
		
		
		// 现在搜索索引
		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// 解析搜索“文本”的简单查询：
		QueryParser parser = new QueryParser(version, "fieldname", analyzer);
		//开启正则表达式
		parser.setAllowLeadingWildcard(true);
		
		Query query = parser.parse("");
		ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
		// 迭代结果：
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = isearcher.doc(hits[i].doc);
			System.out.println("这是要编入索引的文本=" + hitDoc.get("fieldname"));
		}
		ireader.close();
		directory.close();
	}
}