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
		int BUFFER_SIZE = 1024*1024*1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		try {
			url = new URL(destUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			
			
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream("D://java扫描西瓜下载/"+imgName);
			while ((size = bis.read(buf)) != -1) {
				System.out.print(1);
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