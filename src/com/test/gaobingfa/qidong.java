package com.test.gaobingfa;

import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import util.string.UnicodeToUtf8;

//测试高并发，并出报表，多长时间完成处理，处理结果是否正常
public class qidong {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 800; i++) {
			Thread.sleep(1);
			new Thread() {
				public void run() {
					System.out.println("发起时间" + new Date().getTime());

					String url = "http://192.168.1.101/ajaxGodGameUserGetRedPacket.action?logid=296";
//					String url = "http://www.baidu.com";
//					String url = "http://192.168.1.101";
					Document doc = null;
					String zhuanma = "";
					try {
						doc = Jsoup.connect(url).timeout(100 * 1000).get();
						zhuanma = doc.body().text();
					} catch (IOException e) {
						e.printStackTrace();
						zhuanma = "超时";
					}
					System.out.println(zhuanma);
				
					System.out.println("结束时间" + new Date().getTime());
				}
			}.start();
		}
	}
}
