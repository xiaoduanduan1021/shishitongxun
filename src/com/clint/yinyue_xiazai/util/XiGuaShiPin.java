package com.clint.yinyue_xiazai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;

import net.sf.json.JSONObject;
import util.string.StringCode;
import util.string.UnicodeToUtf8;

//西瓜视频爬取
/**
 * 
 * 
 * 
 * 
1首先有用户手动从浏览器访问如下地址

http://m.ixigua.com/?channel=subv_movie#channel=subv_movie
	
	
	2启动浏览器f12，调整到手机模式，打开XHR
	
	3向下翻页不停的翻页，知道够用为止
	
	4将控制台产生的地址复制下来，每个地址里面有10个视频地址和封面
	
	5将复制下来的文本，在在线文本提取器中提取，用正则表达式
	
	6将地址粘贴在本类的下面来执行，程序将自动下载视频和封面
	
	*/
public class XiGuaShiPin {

	
	//根据一个url获取一个下载链接，这个是试听页面的地址
	public String urlToMusic(String url) throws IOException{
		//获取请求连接
        Connection con = Jsoup.connect(url).timeout(1000 * 30).ignoreContentType(true);
        
        
		con.header(":authority", "www.ixigua.com");
		con.header(":method", "GET");
		con.header(":path", "/i6496066227780207118");
		con.header(":scheme", "https");
		con.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		con.header("accept-encoding", "gzip, deflate, br");
		con.header("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
		con.header("cache-control", "max-age=0");
		con.header("cookie", "_ga=GA1.2.1148979710.1551858889; WEATHER_CITY=%E5%8C%97%E4%BA%AC; tt_webid=6667350672986834440; _gid=GA1.2.68123408.1552363597; __tasessionId=ibulfid4c1552373134743");
		con.header("upgrade-insecure-requests", "1");
		con.header("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        
        
        
        //解析请求结果
        Document doc=con.get(); 
        //获取标题
        String neirong = doc.body().text();
        
        System.out.println(neirong);
        
        System.out.println("转换成中文");
		//neirong = new UnicodeToUtf8().decodeUnicode(neirong);
		System.out.println(neirong);
		
		System.out.println("转换成json");
		JSONObject json = new JSONObject().fromString(neirong);
		net.sf.json.JSONArray data = json.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			System.out.println(i);
			JSONObject yitiao =  (JSONObject) data.get(i);
			
			String title = yitiao.getString("title");
			String image_url = yitiao.getString("large_image_url");
			System.out.println("标题："+title);
			System.out.println("图片地址："+image_url);
		 	String video_id = yitiao.getString("video_id");
		 	String videoUrl = this.getDownloadUrl(video_id);
		 	System.out.println("视频地址："+videoUrl);
		 	
		 	System.out.println("下载图片");
		 	DownImage dwimg = new DownImage();
		 	dwimg.saveToFile(image_url, title+".jpg");
		 	System.out.println("下载视频");
		 	DownImage dwvidwo = new DownImage();
		 	dwvidwo.saveToFile(videoUrl, title+".mp4");
		 	
		 	
		 	
		 	
		 	
		 	
		}
		
        return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 获得真实可下载的url地址
	 * @param url
	 * @return
	 */
	public static String  getDownloadUrl(String videoId){
		//根据videoId获得url
		String baseUrl="https://ib.365yg.com";
		String url="/video/urls/v/1/toutiao/mp4/"+videoId+"?r=";
	    StringBuffer randomNum=new StringBuffer();
	    Random r = new Random();
	    while(randomNum.length()<16){
	    	randomNum.append(r.nextInt(10));
	    }
	    url+=randomNum;
	    CRC32 crc32=new CRC32();
		crc32.update(url.getBytes());
		url=baseUrl+url+"&s="+crc32.getValue();
		//解析真实可下载的url地址
		String mainUrl="";
		HttpURLConnection conn =null;
		StringBuffer searchResult = new StringBuffer("");
		
		System.out.println(url);
		
		try {
			URL target = new URL(url);
			conn = (HttpURLConnection) target.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        if (200 == conn.getResponseCode()){
				InputStreamReader inSr = new InputStreamReader(conn.getInputStream(), "UTF-8");
				BufferedReader in = new BufferedReader(inSr);
				String line;
				if (in != null) {
					while ((line = in.readLine()) != null) {
						searchResult.append(line);
					}
					
					
					System.out.println("真实地址获取内容"+searchResult.toString());
					//用json
					//获取最清晰的视频
					JSONObject json = JSONObject.fromString(searchResult.toString());
					
					JSONObject data = (JSONObject) json.get("data");
					JSONObject video_list = (JSONObject) data.get("video_list");
					
					
					if(video_list.has("video_4")){
						System.out.println("video4");
						JSONObject video_4 = (JSONObject) video_list.get("video_4");
						String zhenshiUrl = new String(Base64.decodeBase64(video_4.getString("main_url")));
						System.out.println(zhenshiUrl);
						return zhenshiUrl;
					}
					if(video_list.has("video_3")){
						System.out.println("video3");
						JSONObject video_3 = (JSONObject) video_list.get("video_3");
						String zhenshiUrl = new String(Base64.decodeBase64(video_3.getString("main_url")));
						System.out.println(zhenshiUrl);
						return zhenshiUrl;
					}
					if(video_list.has("video_2")){
						System.out.println("video2");
						JSONObject video_2 = (JSONObject) video_list.get("video_2");
						String zhenshiUrl = new String(Base64.decodeBase64(video_2.getString("main_url")));
						System.out.println(zhenshiUrl);
						return zhenshiUrl;
					}
					if(video_list.has("video_1")){
						System.out.println("video1");
						JSONObject video_1 = (JSONObject) video_list.get("video_1");
						String zhenshiUrl = new String(Base64.decodeBase64(video_1.getString("main_url")));
						System.out.println(zhenshiUrl);
						return zhenshiUrl;
					}
					
				}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn != null)conn.disconnect();
		}
        return mainUrl;
	}

	
	
	
	
		
	public static void main(String[] args) throws IOException {
		System.out.println("开始");
		List<String> list = new ArrayList<String>();
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A125DCF837A6D3A&cp=5C87063D332ADE1&max_behot_time=1552354327&_signature=hdFDWQAA2WJUfCN.otzHkoXRQ0&i=1552354327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A185ECB8B786D3B&cp=5C87766DA36BEE1&max_behot_time=1552350727&_signature=hdFDWQAA2WJUfCN.otz4yYXRQ0&i=1552350727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1258CB887E6D3C&cp=5C87D61D23FC6E1&max_behot_time=1552347127&_signature=hdFDWQAA2WJUfCN.otzgHIXRQ0&i=1552347127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1B54CB82746D3D&cp=5C87262D634D1E1&max_behot_time=1552343527&_signature=hdFDWQAA2WJUfCN.otwRYoXRQ0&i=1552343527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A135AC584726D3E&cp=5C87569D037EFE1&max_behot_time=1552339927&_signature=hdFDWQAA2WJUfCN.otxSzYXRQ0&i=1552339927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A125EC5827B6D40&cp=5C8736BD8440FE1&max_behot_time=1552336327&_signature=hdFDWQAA2WJUfCN.otwpC4XRQ0&i=1552336327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1154CA81776D41&cp=5C8716EDE4910E1&max_behot_time=1552332727&_signature=hdFDWQAA2WJUfCN.otxaQoXRQ0&i=1552332727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D53CA82716D42&cp=5C87C64DB452EE1&max_behot_time=1552329127&_signature=hdFDWQAA2WJUfCN.otxBlYXRQ0&i=1552329127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1658C08A716D43&cp=5C87862D94D38E1&max_behot_time=1552325527&_signature=hdFDWQAA2WJUfCN.otxyzIXRQ0&i=1552325527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D53CE88746D65&cp=5C8706ADF675CE1&max_behot_time=1552321927&_signature=hdFDWQAA2WJUfCN.otykA4XRQ0&i=1552321927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1054C58E736D68&cp=5C87161D56C86E1&max_behot_time=1552318327&_signature=hdFDWQAA2WJUfCN.otyLVoXRQ0&i=1552318327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1657C08C736D69&cp=5C8726ADA629FE1&max_behot_time=1552314727&_signature=hdFDWQAA2WJUfCN.oty7rIXRQ0&i=1552314727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1959CE80746D6B&cp=5C87161DF65B7E1&max_behot_time=1552311127&_signature=hdFDWQAA2WJUfCN.otySy4XRQ0&i=1552311127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D5DC08D7C6D6C&cp=5C87D6AD06DCEE1&max_behot_time=1552307527&_signature=hdFDWQAA2WJUfCN.otzUNoXRQ0&i=1552307527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A105AC889736D6D&cp=5C87067D168D6E1&max_behot_time=1552303927&_signature=hdFDWQAA2WJUfCN.otwFfIXRQ0&i=1552303927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A165EC58E7B6D6F&cp=5C87062D064F0E1&max_behot_time=1552300327&_signature=hdFDWQAA2WJUfCN.otzbq4XRQ0&i=1552300327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D53CD8D766D70&cp=5C87869D57D06E1&max_behot_time=1552296727&_signature=hdFDWQAA2WJUfCN.otzySYXRQ0&i=1552296727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D56C5877F6D73&cp=5C87161D47D37E1&max_behot_time=1552293127&_signature=hdFDWQAA2WJUfCN.otzIh4XRQ0&i=1552293127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1F5CC282746D74&cp=5C87869DE744BE1&max_behot_time=1552289527&_signature=hdFDWQAA2WJUfCN.otwK4oXRQ0&i=1552289527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1057CF8C786D75&cp=5C87F60D87954E1&max_behot_time=1552285927&_signature=hdFDWQAA2WJUfCN.otw7OIXRQ0&i=1552285927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D56C1827D6D78&cp=5C87966D77289E1&max_behot_time=1552282327&_signature=hdFDWQAA2WJUfCN.otwSV4XRQ0&i=1552282327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1A59C0807B6D7A&cp=5C87762D571ABE1&max_behot_time=1552278727&_signature=hdFDWQAA2WJUfCN.otxTwoXRQ0&i=1552278727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1A5FC58A7E6D7B&cp=5C87D61D876B8E1&max_behot_time=1552275127&_signature=hdFDWQAA2WJUfCN.otwq4YXRQ0&i=1552275127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1150CB8E716D7D&cp=5C87665DD7AD8E1&max_behot_time=1552271527&_signature=hdFDWQAA2WJUfCN.otxbN4XRQ0&i=1552271527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1A54C68C7C6D7E&cp=5C87568D974E9E1&max_behot_time=1552267927&_signature=hdFDWQAA2WJUfCN.otydg4XRQ0&i=1552267927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1855CC87796D7F&cp=5C8726ADC7AF3E1&max_behot_time=1552264327&_signature=hdFDWQAA2WJUfCN.otxzwYXRQ0&i=1552264327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1850C183786D80&cp=5C8786FD58F05E1&max_behot_time=1552260727&_signature=hdFDWQAA2WJUfCN.otyk-IXRQ0&i=1552260727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1E5CC68C7A6D82&cp=5C87961D58420E1&max_behot_time=1552257127&_signature=hdFDWQAA2WJUfCN.otyMS4XRQ0&i=1552257127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A105AC5817E6D83&cp=5C8746FD18139E1&max_behot_time=1552253527&_signature=hdFDWQAA2WJUfCN.oty9goXRQ0&i=1552253527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1754CE81756D84&cp=5C87566DC8347E1&max_behot_time=1552249927&_signature=hdFDWQAA2WJUfCN.otz-7YXRQ0&i=1552249927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1C56C68A796D97&cp=5C8706BDD987FE1&max_behot_time=1552246327&_signature=hdFDWQAA2WJUfCN.otzVK4XRQ0&i=1552246327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1458C48A726D98&cp=5C87D6CD69E8CE1&max_behot_time=1552242727&_signature=hdFDWQAA2WJUfCN.otwGcYXRQ0&i=1552242727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1E56CC80706D9A&cp=5C87462DD91A1E1&max_behot_time=1552239127&_signature=hdFDWQAA2WJUfCN.otzttYXRQ0&i=1552239127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1E56CC80706D9A&cp=5C87462DD91A1E1&max_behot_time=1552235527&_signature=hdFDWQAA2WJUfCN.otwe-4XRQ0&i=1552235527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1555C28E706D9C&cp=5C87E65D19EC6E1&max_behot_time=1552231927&_signature=hdFDWQAA2WJUfCN.otxPUYXRQ0&i=1552231927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1D5AC28B7F6D9D&cp=5C87767D79DD0E1&max_behot_time=1552228327&_signature=hdFDWQAA2WJUfCN.otw2pIXRQ0&i=1552228327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1752C8877C6D9E&cp=5C8776EDA93EAE1&max_behot_time=1552224727&_signature=hdFDWQAA2WJUfCN.otxn24XRQ0&i=1552224727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A125FC88D736DA0&cp=5C87E6ED5A807E1&max_behot_time=1552221127&_signature=hdFDWQAA2WJUfCN.otw--oXRQ0&i=1552221127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1658C286766DA1&cp=5C87D69DAA712E1&max_behot_time=1552217527&_signature=hdFDWQAA2WJUfCN.otyAZYXRQ0&i=1552217527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1B5CCA82786DA2&cp=5C87E6CD1A024E1&max_behot_time=1552213927&_signature=hdFDWQAA2WJUfCN.otyxnIXRQ0&i=1552213927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A145CCB87796DA6&cp=5C87E62D1A166E1&max_behot_time=1552210327&_signature=hdFDWQAA2WJUfCN.otyH2oXRQ0&i=1552210327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1656C189736DA7&cp=5C87A6DD1AD74E1&max_behot_time=1552206727&_signature=hdFDWQAA2WJUfCN.otzJRYXRQ0&i=1552206727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1B53C087726DA9&cp=5C87A65D4AA97E1&max_behot_time=1552203127&_signature=hdFDWQAA2WJUfCN.otygZIXRQ0&i=1552203127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1B53C087726DA9&cp=5C87A65D4AA97E1&max_behot_time=1552199527&_signature=hdFDWQAA2WJUfCN.oty2IYXRQ0&i=1552199527");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1859C481726DAB&cp=5C87B69DBAFBDE1&max_behot_time=1552195927&_signature=hdFDWQAA2WJUfCN.otznWIXRQ0&i=1552195927");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1A51CA88766DAC&cp=5C87769D4AFC4E1&max_behot_time=1552192327&_signature=hdFDWQAA2WJUfCN.oty-d4XRQ0&i=1552192327");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1156C685716DAD&cp=5C8796AD8AED9E1&max_behot_time=1552188727&_signature=hdFDWQAA2WJUfCN.otz.4oXRQ0&i=1552188727");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1E57C48D746DAE&cp=5C87A63D7A5EEE1&max_behot_time=1552185127&_signature=hdFDWQAA2WJUfCN.otzWIIXRQ0&i=1552185127");
			list.add("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1A52C68A7D6DB0&cp=5C8726DD1BB0BE1&max_behot_time=1552181527&_signature=hdFDWQAA2WJUfCN.otwHZoXRQ0&i=1552181527");

			new XiGuaShiPin().urlToMusic("http://m.ixigua.com/list/?tag=subv_movie&ac=wap&count=20&format=json_raw&as=A1E57C48D746DAE&cp=5C87A63D7A5EEE1&max_behot_time=1552185127&_signature=hdFDWQAA2WJUfCN.otzWIIXRQ0&i=1552185127");
//			new XiGuaShiPin().getDownloadUrl("v02004700000bh950r0ckqblmb0bb7m0");
			
			
		System.out.println("结束");
	}
}