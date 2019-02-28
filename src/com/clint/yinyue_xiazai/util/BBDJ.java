package com.clint.yinyue_xiazai.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;

import net.sf.json.JSONObject;
import util.string.StringCode;
import util.string.UnicodeToUtf8;


@Service(value="bBDJ")
//宝贝dj网
public class BBDJ {

	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	public String urlToMusic(String url) throws IOException{
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        String neirong = doc.toString();
        
        
        
        
        
        String regex1 = ",playurl=\".*\",nextMusicId=";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(neirong);
		
		String hash = "";
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			hash = matcher1.group();
		}
		hash = hash.replace(",playurl=\"", "http://bbtm.vvvdj.com/mp4");
		hash = hash.replace("\",nextMusicId=", ".mp4");
		
		//下载地址
//		System.out.println(hash);
		
        return hash;
	}
	
	
	
	
	
	
		
	public static void main(String[] args) throws IOException {
		System.out.println("开始");
		new BBDJ().urlToMusic("http://www.bbdj.com/html/play/74245.html");
		System.out.println("结束");
		
	}

}
