package com.test.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class duquZongshu {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 Directory directory = FSDirectory.open(new File("E:/suoyin"));
		IndexReader reader = MultiReader.open(directory);
		System.out.println(reader.numDocs());
		reader.close();
	}

}
