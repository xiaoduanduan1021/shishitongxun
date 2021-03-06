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


@Service(value="www72dj")
//72舞曲dj网
public class WWW_72dj {

	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	public String urlToMusic(String url) throws IOException{
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
        
        con.data("Remote Address", "47.52.240.34:80");
        con.data("Referrer Policy", "no-referrer-when-downgrade");

        
		
		
		con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.header("Accept-Encoding", "gzip, deflate");
		con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		con.header("Cache-Control", "no-cache");
		con.header("Connection", "keep-alive");
		con.header("Cookie", "BAIDU_SSP_lcr=http://www.72dj.com/; ASPSESSIONIDCCCSCDBC=FGDODGHDPEEOMJIEPNKKGCLG; 33386=vitistime=2019%2F2%2F28+20%3A40%3A00; Hm_lvt_32a74560febfb6ead895c3dfcddac8bb=1550977356,1551357548,1551357905; Hm_lpvt_32a74560febfb6ead895c3dfcddac8bb=1551357911; open_player=");
		con.header("Host", "www.72dj.com");
		con.header("Pragma", "no-cache");
		con.header("Upgrade-Insecure-Requests", "1");
		con.header("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Mobile Safari/537.36");
        
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        String neirong = doc.toString();
        
        
        
        
        
        String regex1 = "var danceFilePath=\".*\";";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(neirong);
		
		String hash = "";
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			hash = matcher1.group();
		}
		hash = hash.replace("var danceFilePath=\"", "http://p21.72dj.com:83/m4adj/");
		hash = hash.replace("\";", ".m4a");
		
		//下载地址
//		System.out.println(hash);
		
        return hash;
	}
	
	
	
	
	
	
		
	public static void main(String[] args) throws IOException {
		System.out.println("开始");
		new WWW_72dj().urlToMusic("http://www.72dj.com/play/33386.htm");
		System.out.println("结束");
		
	}

}
