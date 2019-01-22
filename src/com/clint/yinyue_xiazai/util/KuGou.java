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

import net.sf.json.JSONObject;
import util.string.UnicodeToUtf8;

//酷狗下载
public class KuGou {

	
	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	//根据一个URL
	//获取其中的音频文件
	public String[] urlToMusic(String url) throws IOException{
		
		//获取bumid
		String uu [] = url.split("&album_id=");
		String bumid = uu[1];
		
		//获取hash
		String hash = uu[0].split("#hash=")[1];
		
		String urldb = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash="+hash+"&album_id="+bumid;

		//获取请求连接
        Connection con = Jsoup.connect(urldb).timeout(1000 * 30).ignoreContentType(true);
        
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        String neirong = doc.body().text();
        
        neirong = UnicodeToUtf8.decodeUnicode(neirong);
        
        String aa[] = neirong.split("\"audio_name\":\"");
        String bb[] = aa[1].split("\",\"have_album\"");
        //名称
        System.out.println(bb[0]);
        //地址
        String cc[] = bb[1].split("\"play_url\":\"");
        String dd[] = cc[1].split("\",\"authors\"");
        System.out.println(dd[0]);
		
        
        String jieguo[] = {bb[0],dd[0]};
        
        return jieguo; 
	}
	
//	list去重复
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	} 
	
	public static void main(String[] args) throws IOException {
		
		new KuGou().urlToMusic("https://www.kugou.com/song/#hash=E05338739C6D257B2F40C5AE8D5BD1B3&album_id=14632076");
		
	}

}
