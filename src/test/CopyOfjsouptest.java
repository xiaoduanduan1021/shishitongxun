package test;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.string.UnicodeToUtf8;

public class CopyOfjsouptest {

	public static void main(String[] args) throws IOException {
			
			String url = "http://m.mm131.com/qingchun/";
			
			Document doc = Jsoup.connect(url).get();

			System.out.println(doc.toString());
	}
}
