package com.clint.yinyue_xiazai.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


//java通过url读取网络图片
public class DownImage {

	//将图片存到硬盘
	public void saveToFile(String destUrl , String imgName) {
		
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			
			httpUrl.setRequestMethod("GET");
			
			httpUrl.setRequestProperty("Accept", "*/*");
			httpUrl.setRequestProperty("Cache-Control", "no-cache");
			httpUrl.setRequestProperty("Connection", "Keep-Alive");
			httpUrl.setRequestProperty("Host", "www.vvvdj.com");
			httpUrl.setRequestProperty("Pragma", "no-cache");
			httpUrl.setRequestProperty("Referer", "http://www.vvvdj.com/play/169733.html");
			httpUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E; Shuame)");
			httpUrl.setRequestProperty("Cookie", "rtime=0; cnzz_eid=85173118-1317289499-http%3A//www.vvvdj.com/; Hm_lvt_d89009fe03c70c3c98531373f1b90625=1548392172; Hm_lvt_597685a72dadb90d39ad0191f13b72af=1548392172; ltime=1548392702281; Hm_lpvt_d89009fe03c70c3c98531373f1b90625=1548392702; Hm_lpvt_597685a72dadb90d39ad0191f13b72af=1548392703");
	


			
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream("E://ceshi/"+imgName);
			while ((size = bis.read(buf)) != -1) {
				System.out.println(1);
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
		} catch (ClassCastException e) {
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
				
			} catch (IOException e) {
			} catch (NullPointerException e) {
			}
		}
		
	}
	
    
	public static void main(String[] args) {

		DownImage dw = new DownImage();
		dw.saveToFile("http://dj.vvvdj.com:1935/breezebar/mp4:c2/2019/01/172041-9bb589.mp4", "vvv1111111vv.mp4");
		System.out.println("完成");
	}
}