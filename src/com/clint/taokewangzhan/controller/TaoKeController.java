package com.clint.taokewangzhan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//套壳网站
@Controller
@RequestMapping(value = "/taoke")
public class TaoKeController {

	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/toTaoke")
	public String toTaoke(HttpServletRequest request){
		
		String url = "http://m.mmjpg.com/";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements elements = doc.getElementsByTag("img");
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			System.out.println(element.attr("data-img"));
		}
		
		System.out.println(doc.toString());
		if (doc==null) {
			request.setAttribute("html", "访问失败");
		}else{
			request.setAttribute("html", doc.toString());
		}
		
		return "taoke/taoke.jsp";
	}
}
