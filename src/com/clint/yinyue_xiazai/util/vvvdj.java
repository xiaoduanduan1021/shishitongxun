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


//清风dj
//下载网站，，获取到的音乐地址不能下载，估计是增加了防盗链判断
//http://www.vvvdj.com
public class vvvdj {
	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	public String urlToMusic(String url) throws IOException{
		
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
//	    rtmp://dj.vvvdj.com:1935/breezebar/mp4:c5/2015/10/120513-c79283.mp4	        	  
//        	    	  http://th.vvvdj.com/face/c5/2015/10/120513-c79283.mp4

        
        
        
        Document doc=con.get(); 
		
        
        String s = doc.toString();
		System.out.println(s);
		
		// 书写正则表达式
		String regex = "file: '.*', good:";
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
		
		dizhi = dizhi.replace("file: '", "http://m.oscaches.com/mp4/djmusic/");
		dizhi = dizhi.replace("', good:", ".mp4");
		System.out.println(dizhi);
        

		return dizhi; 
	}
	
	
	

	
	
	
	
	
	public static void main(String[] args) throws IOException {

		new vvvdj().urlToMusic("https://m.vvvdj.com/play/173050.html");
		
	}
}