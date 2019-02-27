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


@Service(value="kugou")
//酷狗下载
public class KuGou {

	
	@Resource(name="yinyueXiazaiDao")
	private YinyueXiazaiDao yinyueXiazaiDao;
	
	
	//对外链接-----------------------
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
        
        String jieguo[] = {"error","error"};
        try {
			
        String aa[] = neirong.split("\"audio_name\":\"");
        String bb[] = aa[1].split("\",\"have_album\"");
        //名称
        //地址
        String cc[] = bb[1].split("\"play_url\":\"");
        String dd[] = cc[1].split("\",\"authors\"");
		
        
        jieguo[0] = bb[0];
        jieguo[1] = dd[0];
        
        
        } catch (Exception e) {
        	System.out.println(e.toString());
        }
        
        return jieguo; 
	}
	
	
	//对外链接-----------------------
	//根据一个列表地址，获取所有下载地址，，包括下一页直到第五页，并且存入数据库
	public void ListToDB(String url,int zong_yema,String shuoming) throws IOException{
		System.out.println("批量地址"+url);
		String mudiUrl = "";
//		自动循环获取每一页的列表
		for (int i = 1; i <= zong_yema; i++) {
//			替换页码
			mudiUrl = url.replace("/home/1", "/home/"+i);
//			获取该页的所有预备地址
			List<String> listYubei = this.urlToMusicList(mudiUrl);
//			循环所有预备地址，
			int g = 0;
			for (String yubei : listYubei) {
				g++;
				System.out.println("当前第"+i+"页 , 当前第"+g+"个");
				
				//根据预备地址获取hash
				
				String hash = this.urlToMusic1(yubei);
				
				//hash地址拼接
				String shitingUrl = "https://www.kugou.com/song/u052n19.html#hash="+hash+"&album_id=0";
				
				
//				获取下载地址
				String xiazaiUrl [] = this.urlToMusic(shitingUrl);
				
				System.out.println("名称："+xiazaiUrl[0]);
				
				//查询是否已经存在这个标题
				if(this.yinyueXiazaiDao.getYinyueBytitle(xiazaiUrl[0]).size()>0){
					System.out.println("已经存在");
				}else{
				
					System.out.println("地址："+xiazaiUrl[1]);
					
					YinyueXiazai yinyueXiazai = new YinyueXiazai();
					yinyueXiazai.setDatetime(StringCode.getDateTime());
					yinyueXiazai.setGequ_name(xiazaiUrl[0]);
					yinyueXiazai.setShiting_url(shitingUrl);
					yinyueXiazai.setStatus(1);
					yinyueXiazai.setUuid(shuoming);
					yinyueXiazai.setXiazai_dizhi(xiazaiUrl[1]);
	//				保存歌曲名称和下载地址到数据库
					yinyueXiazaiDao.saveYinyueXiazai(yinyueXiazai);
				}
			}
		}
		
		System.out.println("完成");
	}
	
//	list去重复
	public static List removeDuplicate(List list) {   
	    HashSet h = new HashSet(list);   
	    list.clear();   
	    list.addAll(h);   
	    return list;   
	} 
	
	
	//根据初次试听地址获取hash码
	
	public String urlToMusic1(String url) throws IOException{
		
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
        //解析请求结果
        Document doc=con.get(); 
        String html =doc.toString();
        //System.out.println(html);
		
        String regex1 = "var dataFromSmarty = .*timelength";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(html);
		
		String hash = "";
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			hash = matcher1.group();
			//System.out.println(hash);
		}
        
		hash = hash.replace("var dataFromSmarty = [{\"hash\":\"","");
		hash = hash.replace("\",\"timelength","");
		
		//System.out.println(hash);
        return hash; 
	}
	
	
	
	//根据链接获取所有列表内的预备地址
	public List<String> urlToMusicList(String url) throws IOException{
		
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
        //解析请求结果
        Document doc=con.get(); 
        String html =doc.toString();
        //System.out.println(html);
		
        String regex1 = "https://www.kugou.com/song/.*.html\" data-active=";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(html);
		
		List<String> list = new ArrayList<String>();
		
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			String dizhi = matcher1.group();
			dizhi = dizhi.replace("\" data-active=", "");
					
			list.add(dizhi);
		}
        
        return list; 
	}
	
	
	//自动扫描歌单内的歌曲列表,返回歌曲名和hash
	public List<String[]> gedan(String url) throws IOException{
		
		//获取请求连接
		Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
		
		//解析请求结果
		Document doc=con.get(); 
		String html =doc.toString();
		//System.out.println(html);

		//获取hash和歌曲名称
		
		
		String regex1 = "title=.*><input type=\"checkbox\"";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(html);
		
		List<String[]> list = new ArrayList<String[]>();
		
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			String dizhi = matcher1.group();
			
			//去掉无用字符
			dizhi = dizhi.replace("title=\"", "");
			dizhi = dizhi.replace("\" hidefocus=\"true\" href=\"javascript:;\" ", "");
			dizhi = dizhi.replace("\"><input type=\"checkbox\"", "");
			//System.out.println(dizhi);
			String ds [] = dizhi.split("data=\"");
			
			
			//歌曲名
			String geming = ds[0];
			//System.out.println(geming);
			
			//hash地址
			String ds2[] = ds[1].split("\\|");
			String hash = ds2[0];
			//System.out.println(hash);
			
			//System.out.println();
			//System.out.println();
			//System.out.println();
			//System.out.println();
			//System.out.println();
			//System.out.println();

			String [] aa={geming,hash};
			list.add(aa);
		}
		
		return list; 
	}
	
	//根据歌单列表网址获取所有歌单地址
	public List<String> gedanList(String url) throws IOException{
		
		//获取请求连接
		Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
		
		//解析请求结果
		Document doc=con.get(); 
		String html =doc.toString();
		//System.out.println(html);

		//获取hash和歌曲名称
		
		
		String regex1 = "\" href=\"https://www.kugou.com/yy/special/single/.*html\" onclick=\"sdnClick\\(";
		// 将正则表达式转成正则对象
		Pattern pattern1 = Pattern.compile(regex1);
		// 正则对象与字符串匹配
		Matcher matcher1 = pattern1.matcher(html);
		
		List<String> list = new ArrayList<String>();
		
		// 匹配成功后打印出找到的结果              
		while (matcher1.find()) {
			String dizhi = matcher1.group();
			//System.out.println(dizhi);
			
			dizhi = dizhi.replace("\" href=\"", "");
			dizhi = dizhi.replace("\" onclick=\"sdnClick(", "");
			
			list.add(dizhi);
			//System.out.println(dizhi);
		}
		
		return list; 
	}
	
	
	//根据歌单列表地址，获取所有歌单内的所有歌曲，并存储
	
	public String gedanAllCunchu(String url) throws IOException{
		
		//根据URL获取所有歌单
		List<String> gedanUrls = this.gedanList(url);
		
		for (int i = 0; i < gedanUrls.size(); i++) {
			System.out.println("歌单："+gedanUrls.get(i));
			List<String[]> gequList = this.gedan(gedanUrls.get(i));
			
			for (int j = 0; j < gequList.size(); j++) {
				String geming = gequList.get(j)[0];
				String hash = gequList.get(j)[1];
				System.out.println("歌名："+geming);
				//查询是否已经存在这个标题
				if(this.yinyueXiazaiDao.getYinyueBytitle(geming).size()>0){
					System.out.println("已经存在");
				}else{
				
					System.out.println("hash："+hash);
					
					YinyueXiazai yinyueXiazai = new YinyueXiazai();
					yinyueXiazai.setDatetime(StringCode.getDateTime());
					yinyueXiazai.setGequ_name(geming);
					
					//hash地址拼接
					String shitingUrl = "https://www.kugou.com/song/u052n19.html#hash="+hash+"&album_id=0";
					
					yinyueXiazai.setShiting_url(shitingUrl);
					yinyueXiazai.setStatus(1);
					yinyueXiazai.setUuid("歌单自动");
					yinyueXiazai.setXiazai_dizhi("");
	//				保存歌曲名称和下载地址到数据库
					yinyueXiazaiDao.saveYinyueXiazai(yinyueXiazai);
				}
			}
		}
		
		
		//根据每个歌单获取所有歌曲名
		
		return "";
		
	}
	
	
	
	
		
	public static void main(String[] args) throws IOException {
		System.out.println("开始");
		new KuGou().gedanAllCunchu("https://www.kugou.com/yy/special/index/1-6-0.html");
		System.out.println("结束");
		
	}

}
