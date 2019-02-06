package com.clint.yinyue_xiazai.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//下载网站
//http://www.ik123.com
public class Ik123 {
	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	public String urlToMusic(String url) throws IOException{
		
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        Document doc=con.get(); 
		String s = doc.toString();
//		System.out.println("+++++++++++++++++++++++++++++++++");
//		System.out.println(s);
//		System.out.println("-----------------------------------");
		// 书写正则表达式
		String regex = "furl=\".*.flv\"";

		// 将正则表达式转成正则对象
		Pattern pattern = Pattern.compile(regex);
		// 正则对象与字符串匹配
		Matcher matcher = pattern.matcher(s);
		// 匹配成功后打印出找到的结果              
		String dizhi = "";
		while (matcher.find()) {
			dizhi = matcher.group();
		}
		System.out.println(dizhi);
		
		dizhi = dizhi.replace("furl=\"", "http://www.ik123.com/ik/a/?");
		dizhi = dizhi.replace(".flv\"", ".mp4");
		System.out.println(dizhi);
        
		if(StringUtils.isBlank(dizhi)){
			System.out.println("跨过安全狗问题");
			
			
			String regex1 = "self.location=\".*\";}</script>";
			// 将正则表达式转成正则对象
			Pattern pattern1 = Pattern.compile(regex1);
			// 正则对象与字符串匹配
			Matcher matcher1 = pattern1.matcher(s);
			// 匹配成功后打印出找到的结果              
			while (matcher1.find()) {
				dizhi = matcher1.group();
				System.out.println(dizhi);
				dizhi = dizhi.replace("self.location=\"", "http://www.ik123.com");
				dizhi = dizhi.replace("\";}</script>", "");
				System.out.println(dizhi);
				//在获取一次
				return new Ik123().urlToMusic(dizhi);
			}
		}
		return dizhi; 
	}
	
	
	

	
	
	
	
	
	public static void main(String[] args) throws IOException {

		new Ik123().urlToMusic("http://www.ik123.com/mp3-dj/ik123_7678.html");
		
	}
}