package com.clint.pinchewang.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.string.StringCode;

import com.clint.model.Person;
import com.clint.pinchewang.model.PinCheXinXi;
import com.clint.pinchewang.service.PinCheWangService;

@Controller
@RequestMapping(value = "/")
public class PinCheWangController {

	private Logger log = Logger.getLogger(PinCheWangController.class);

	@Autowired
	private HttpServletRequest request;

	@Resource(name = "pinCheWangService")
	private PinCheWangService pinCheWangService;

	// 进入自动录入页面
	@RequestMapping(value = "/autoSave")
	public String savePerson() {
		return "/pin_che_wang/auto_save/autoSave.jsp";
	}

	// 自动保存信息请求
	@RequestMapping(value = "/autoSavePPXX")
	public void autoSavePPXX(PinCheXinXi pinCheXinXi, HttpServletResponse response) throws IOException {

		// 按照行分割
		String[] contentHangs = pinCheXinXi.getContent().split("\n");
		
		String con = "";//暂存一条信息
		
		// 从后往前遍历
		for (int i = contentHangs.length - 1; i >= 0; i--) {
		//	System.out.println(xinxihang);
			String xinxihang = contentHangs[i];
			
			
			
			
			
			if (xinxihang.indexOf("382165866")>=0) {
				System.out.println(11111111);
			}
			
			
			
			
			
			
			
			
			
			// 每遇到一个分割信息则为一个整信息
			//正则匹配
			String pattern = ".*(.*) .*午 .*:.*:.*";
			boolean isMatch = Pattern.matches(pattern, xinxihang);
			if (isMatch) {
				
				
				PinCheXinXi pinCheXinXi2 = new PinCheXinXi();
				
				System.out.println("匹配-------------------------------------");
				System.out.println("信息行");
				System.out.println(xinxihang);
				System.out.println("完整信息");
				System.out.println(con);
				
				pinCheXinXi2.setContent(con);
				
				//分析出时间、昵称、QQ号、
				System.out.println("昵称");
				String nicheng = xinxihang.substring(0, xinxihang.indexOf("("));
				
				pinCheXinXi2.setFaSongZheNiCheng(nicheng);
				
				
				System.out.println(nicheng);
				System.out.println("QQ");
				String QQ = xinxihang.substring(xinxihang.indexOf("(")+1, xinxihang.indexOf(")"));
				
				pinCheXinXi2.setFaSongZheQQ(QQ);
				
				
				System.out.println(QQ);
				System.out.println("时间");
				//判断是否包括年月日
				String nianyueri = ".*/.*/.* 星期.*";
				boolean isNianyueri = Pattern.matches(nianyueri, xinxihang);
				
				String time = "";
				if (isNianyueri) {
					//如果有则删除星期
					//获取年
					String nian = xinxihang.substring(xinxihang.indexOf(") ")+2, xinxihang.indexOf(" 星期"));
					//获取时间
					String shijian = xinxihang.substring(xinxihang.indexOf(" 星期")+4, xinxihang.length());
					System.out.println(nian+shijian);
					time = nian+shijian;
				}else{
					//如果没有则自动补充今天时间
					//获取时间
					//获取时间
					String shijian = xinxihang.substring(xinxihang.indexOf(")  ")+3, xinxihang.length());
					
					System.out.println(StringCode.getnnt()+shijian);
					time = StringCode.getnnt()+shijian;
				}
				
				pinCheXinXi2.setFaSongShiJian(time);
				
				
				//查询这个qq今天是否已经发过相同的信息
				
				//如果发过则不再存储,但是更新时间
				
				
				
				
				
				
				
				
				
				con = "";
			}else{
				//加换行，去空行
				if (xinxihang.equals("")) {
				}else{
					con=xinxihang+"\n"+con;
				}
			}
		}
		// 再查询该信息是否已经存储，如果没有则存储，如果有则不存储并且循环停止

		// 模拟系统处理时间
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject json = new JSONObject();
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}

	public static void main(String[] args) {
		
		String aa = "旧治--李茂堤(904920590) 2018/5/5 星期六 上午 8:04:52";
		String bb = "招财进宝--（平安出行）(546942582)  上午 8:27:22";
		
		//获取年
		System.out.println();
		//获取时间
		System.out.println();
		
	}
}