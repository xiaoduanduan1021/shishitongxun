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
	public String urlToMusic(String url) throws IOException{
		
		url = "https://www.kugou.com/song/#hash=D9056CAEF52789770D3B6909BADFB53B&album_id=11815159";
		
		
		
		
		
		
		
		
		//获取bumid
		String uu [] = url.split("&album_id=");
		String bumid = uu[1];
		
		
		//获取hash
		String hash = uu[0].split("#hash=")[1];
		
		
		
		String urldb = "https://wwwapi.kugou.com/yy/index.php?r=play/getdata&hash="+hash+"&album_id="+bumid;
		
		
		
//		String cookie = "kg_mid=8d7ab51e5017749db301e44dd3ea33ea; __fx_unionId__=11-0; fx_coin=0; KuGoo=KugooID=1015200378&KugooPwd=AAF32ADA6643FA0D58EE67989CA21972&NickName=%u5c0f%u6bb5%u54e5%u54e5&Pic=http://imge.kugou.com/kugouicon/165/20170223/20170223191733439269.jpg&RegState=1&RegFrom=&t=cee193f5d2ae5105fa81edd890b46bf74ebb6480402a4cc5e6f3731903a34adf&a_id=1001&ct=1547531126&UserName=%u006b%u0067%u006f%u0070%u0065%u006e%u0031%u0030%u0031%u0035%u0032%u0030%u0030%u0033%u0037%u0038; Hm_lvt_aedee6983d4cfc62f509129360d6bb3d=1547177056,1547531128; Hm_lpvt_aedee6983d4cfc62f509129360d6bb3d=1547532366";
		
		System.out.println(urldb);
		//获取请求连接
        Connection con = Jsoup.connect(urldb).timeout(1000 * 30).ignoreContentType(true);
        //请求头设置，特别是cookie设置
//        con.header("accept", "*/*"); 
//        con.header("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"); 
//        con.header("cookie", cookie);
//        
//        con.header("referer", "https://www.kugou.com/song/");
//        
//        
//        
//        
//        con.header(":authority", "wwwapi.kugou.com");
//        con.header(":method", "GET");
//        con.header(":path", "/yy/index.php?r=play/getdata&callback=jQuery191039295432371005257_1547532366292&hash=C92229AE08195E8D8F65D31EB0017DD2&album_id="+bumid+"&_=1547532366293");
//        con.header(":scheme", "https");
//        con.header("accept-encoding", "gzip, deflate, br");
//        con.header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        
        
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
		return null; 
	}
	
	
	
	
	//根据一个地址获取一批下载地址
	public List<String> urlToMusicList(String url) throws IOException{
		
		
		String cookie = "ZDEDebuggerPresent=php,phtml,php3; Qeyser=Jquery; Hm_lvt_ff3ea3d1bfd7c9624abfda66265b6e06=1547177097; bdshare_firstime=1547177097344; open_player=Y; ylmvHistory=10259%23%u6211%u8981%u4E0A%u5B66%u6821%23http%3A//www.190757.com/dance/play/id/10259%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10237%23%u656C%u60C5%u8C0A%20%20Mc%u7F6A%u5929%23http%3A//www.190757.com/dance/play/id/10237%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10248%23%u5439-%u5587-%u53ED%23http%3A//www.190757.com/dance/play/id/10248%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10252%23%u864E%u4E8C%20-%20%u4E24%u4E2A%u4EBA%u7684%u56DE%u5FC6%u4E00%u4E2A%u4EBA%u8FC7%23http%3A//www.190757.com/dance/play/id/10252%23http%3A//www.190757.com/dance/lists/id/21/1%23%u6B4C%u66F2%u5927%u5168*ylmv*10254%23%u5531%u8154%u53E6%u7C7B%23http%3A//www.190757.com/dance/play/id/10254%23http%3A//www.190757.com/dance/lists/id/21/1%23%u6B4C%u66F2%u5927%u5168*ylmv*10266%23Where%20Is%20My%20Head%23http%3A//www.190757.com/dance/play/id/10266%23http%3A//www.190757.com/dance/lists/id/3/1%23%u56FD%u5916%u821E%u66F2*ylmv*; Hm_lpvt_ff3ea3d1bfd7c9624abfda66265b6e06=1547177680";
		
		
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        //请求头设置，特别是cookie设置
        con.header("Accept", "*/*"); 
        con.header("Content-Type", "application/javascript");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"); 
        con.header("Cookie", cookie);
        
        con.header("Accept-Encoding", "gzip, deflate");
        con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        con.header("Host", "www.190757.com");
        con.header("Connection", "keep-alive");
        con.header("Referer", "http://www.190757.com/dance/play/id/10259");
        con.header("X-Requested-With", "ShockwaveFlash/22.0.0.192");
        
        
        
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        System.out.println("--------------------------------页面内容");
        System.out.println(doc.toString());
        System.out.println("--------------------------------页面内容");
		
		//查找音频文件地址
		
        
        
        
        
        
        
		// 使用正则获取试听链接
		// 从字符串中提取指定的字符串
		String s = doc.toString();

		// 书写正则表达式
		String regex = "http://www.190757.com/dance/play/id/.*\">";

		// 将正则表达式转成正则对象
		Pattern pattern = Pattern.compile(regex);

		// 正则对象与字符串匹配
		Matcher matcher = pattern.matcher(s);

		
		// 匹配成功后打印出找到的结果              
		System.out.println("====================试听链接");
		
		
		List<String> shiting_lianjie = new ArrayList<String>();
		//去重复
		while (matcher.find()) {
			shiting_lianjie.add(matcher.group());
		}
		shiting_lianjie = this.removeDuplicate(shiting_lianjie);

		
		
		//获取下载链接
		List<String> jieguo = new ArrayList<String>();
		for (String shitingurl:shiting_lianjie) {
			//净化试听链接，
			shitingurl.replace("\">", "");
			//获取下载地址
			String yuanshi_dizhi = this.urlToMusic(shitingurl);
			System.out.println(yuanshi_dizhi);
			jieguo.add(yuanshi_dizhi);
			if(jieguo.size()>40){
				return jieguo;
			}
		}
		
		System.out.println("====================试听链接");
        
        
        
        
		return jieguo; 
	}
	
	//专门用于获取首页的，自动播放列表的mp3
	public List<String> shouye_urlToMusicList() throws IOException{
		
		String url = "http://www.190757.com/index.php/dance/real";
		
		String cookie = "ZDEDebuggerPresent=php,phtml,php3; Qeyser=Jquery; Hm_lvt_ff3ea3d1bfd7c9624abfda66265b6e06=1547177097; bdshare_firstime=1547177097344; open_player=Y; ylmvHistory=10259%23%u6211%u8981%u4E0A%u5B66%u6821%23http%3A//www.190757.com/dance/play/id/10259%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10237%23%u656C%u60C5%u8C0A%20%20Mc%u7F6A%u5929%23http%3A//www.190757.com/dance/play/id/10237%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10248%23%u5439-%u5587-%u53ED%23http%3A//www.190757.com/dance/play/id/10248%23http%3A//www.190757.com/dance/lists/id/20/1%23MC%u558A%u9EA6*ylmv*10252%23%u864E%u4E8C%20-%20%u4E24%u4E2A%u4EBA%u7684%u56DE%u5FC6%u4E00%u4E2A%u4EBA%u8FC7%23http%3A//www.190757.com/dance/play/id/10252%23http%3A//www.190757.com/dance/lists/id/21/1%23%u6B4C%u66F2%u5927%u5168*ylmv*10254%23%u5531%u8154%u53E6%u7C7B%23http%3A//www.190757.com/dance/play/id/10254%23http%3A//www.190757.com/dance/lists/id/21/1%23%u6B4C%u66F2%u5927%u5168*ylmv*10266%23Where%20Is%20My%20Head%23http%3A//www.190757.com/dance/play/id/10266%23http%3A//www.190757.com/dance/lists/id/3/1%23%u56FD%u5916%u821E%u66F2*ylmv*; Hm_lpvt_ff3ea3d1bfd7c9624abfda66265b6e06=1547177680";
		
		//获取请求连接
		Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
		//请求头设置，特别是cookie设置
		con.header("Accept", "*/*"); 
		con.header("Content-Type", "application/javascript");
		con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36"); 
		con.header("Cookie", cookie);
		
		con.header("Accept-Encoding", "gzip, deflate");
		con.header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		con.header("Host", "www.190757.com");
		con.header("Connection", "keep-alive");
		con.header("Referer", "http://www.190757.com/dance/play/id/10259");
		con.header("X-Requested-With", "ShockwaveFlash/22.0.0.192");
		
		
		
		//解析请求结果
		Document doc=con.get(); 
		//获取标题
		System.out.println("--------------------------------页面内容");
		System.out.println(doc.toString());
		System.out.println("--------------------------------页面内容");
		
		//查找音频文件地址
		
		
		
		// 使用正则获取试听链接
		// 从字符串中提取指定的字符串
		String s = doc.toString();
		
		//每条数据之间换行，否则正则表达式获取到的是整个的字符串
		s = s.replaceAll(";DATA", "\n");
		
		
		
		// 书写正则表达式
		String regex = "http.*mp3";
		
		// 将正则表达式转成正则对象
		Pattern pattern = Pattern.compile(regex);
		
		// 正则对象与字符串匹配
		Matcher matcher = pattern.matcher(s);
		
		
		// 匹配成功后打印出找到的结果              
		System.out.println("====================链接");
		
		
		//去重复
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		
		
		
		System.out.println("====================链接");
		
		
		return null; 
	}
	
//	list去重复
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	} 
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		new KuGou().urlToMusic("");
		
	}

}
