package util.string;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class UrlConnection {

	public static void main(String[] args) throws Exception {
		double a = new Date().getTime();
		System.out.println(new UrlConnection().getIpRegionByTaobao("114.113.18.86"));
		double b = new Date().getTime();
		System.out.println(b-a);
		//System.out.println(new UrlConnection().getHtmlStringByUrlAndUnicodeToUtf8("https://www.baidu.com/"));
	}

	// 根据连接获取请求返回内容，地址源码
	public String getHtmlStringByUrl(String url) {
		URL url1;
		try {
			url1 = new URL(url);
			URLConnection conn = url1.openConnection();// 获得UrlConnection 连接对象
			InputStream is = conn.getInputStream();// 获得输入流
			// isr = new InputStreamReader(System.in);//system.in标准系统输入
			BufferedReader br = new BufferedReader(new InputStreamReader(is));// buffered表示缓冲类
			String html = "";
			String str;
			while (null != (str = br.readLine())) {
				html+=str;
			}
			br.close();
			is.close();
			return html;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// 获取地址源码，unicode转中文
	public String getHtmlStringByUrlAndUnicodeToUtf8(String url){
		String s = this.getHtmlStringByUrl(url);
		return new UnicodeToUtf8().decodeUnicode(s);
	}
	
	/*
	 * 使用淘宝接口获取指定ip地址的地区信息
	 * 
		1. 请求接口（GET）：
		/service/getIpInfo.php?ip=[ip地址字串]
		2. 响应信息：
		（json格式的）国家 、省（自治区或直辖市）、市（县）、运营商
		3. 返回数据格式：
		{"code":0,"data":{"ip":"210.75.225.254","country":"\u4e2d\u56fd","area":"\u534e\u5317",
		"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
		"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
		"county_id":"-1","isp_id":"100017"}}
		其中code的值的含义为，0：成功，1：失败。
	 */
	public String getIpRegionByTaobao(String ip){
		return new UrlConnection().getHtmlStringByUrlAndUnicodeToUtf8("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
	}
}