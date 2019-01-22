package com.clint.yinyue_xiazai.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;
import util.string.UnicodeToUtf8;


//下载网站，千千音乐
//http://music.taihe.com/

public class QianQianYinYue {
	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	//根据一个URL
	//获取其中的音频文件
	public String[] urlToMusic(String url) throws IOException{
		
			
		//获取songIds
		//先判断是哪一种地址
		String sonid = "";
		if(url.indexOf("/song/")>0){
			String uu [] = url.split("/song/");
			sonid = uu[1];
		}else{
			// 正则表达式
			String regex = "__a=\\d*&__";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			while (matcher.find()) {
				sonid = matcher.group();
			}
			sonid = sonid.replace("__a=", "");
			sonid = sonid.replace("&__", "");
		}
		
		
		
		String urldb = "http://play.taihe.com/data/music/songlink?songIds="+sonid;

		
		//获取请求连接
        Connection con = Jsoup.connect(urldb).timeout(1000 * 30).ignoreContentType(true);
        
        con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        con.header("Accept-Encoding", "gzip, deflate");
        con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        con.header("Cache-Control", "max-age=0");
        con.header("Connection", "keep-alive");
        con.header("Cookie", "BAIDUID=8DF557E5AA1B8B27B6450F956A39A61D:FG=1; app_vip=show; log_sid=15481388384778DF557E5AA1B8B27B6450F956A39A61D; sort-guide-showtimes=1; sort-guide-lastshow=1548138843214; Hm_lvt_2b0f0945031c52df2a103f3ed5d7c3aa=1548138892,1548139581,1548139638,1548139656; Hm_lpvt_2b0f0945031c52df2a103f3ed5d7c3aa=1548139656");
        con.header("Host", "play.taihe.com");
        con.header("Upgrade-Insecure-Requests", "1");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        		
        		
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        String neirong = doc.body().text();
        
        neirong = UnicodeToUtf8.decodeUnicode(neirong);
        
        JSONObject json = JSONObject.fromBean(neirong);
        json = json.getJSONObject("data");
        net.sf.json.JSONArray jary = json.getJSONArray("songList");
        JSONObject js = (JSONObject) jary.get(0);
        
        String jieguo[] = {js.getString("albumName"),js.getString("songLink")};
        
        return jieguo; 
	}
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		new QianQianYinYue().urlToMusic("http://play.taihe.com/?__m=mboxCtrl.playSong&__a=569080829&__o=/search||songListIcon&fr=-1||www.baidu.com&__s=%E6%97%A0%E9%97%AE%E4%B8%9C%E8%A5%BF#");
		new QianQianYinYue().urlToMusic("http://music.taihe.com/song/569080829");
	}

}
