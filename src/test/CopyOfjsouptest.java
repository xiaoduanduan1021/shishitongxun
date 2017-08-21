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
			
			Elements elements = doc.getElementsByTag("img");
			for (int i = 0; i < elements.size(); i++) {
				Element element = elements.get(i);
				System.out.println(element.attr("data-img"));
			}
	}
}
