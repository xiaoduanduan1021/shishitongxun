package com.clint.yinyue_xiazai.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.clint.Aware;
import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;

import util.string.RandNumber;
import util.string.StringCode;

public class yinyueUtil {

	public static String basePath = "250xyz.xyz";
	
	public static String getYuming(HttpServletRequest request){
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + "/";
		if(basePath.indexOf(yinyueUtil.basePath)>=0){
			basePath = basePath.replace(yinyueUtil.basePath, "www."+yinyueUtil.basePath);
		}
		return basePath;
	}
	
	
	
	//上次首页推送静态数据更新时间
	public static String homeDate = "";
	//上次首页推送静态数据
	public static String homeHtml = "";
	//首页获取推送数据
	public static String getHome(){
		
		long dataa = new Date().getTime();
		
		if(StringCode.getDateHH().compareTo(homeDate)>0 || homeHtml.equals("")){
			//超过一小时更新
			System.out.println("调起数据库查询");
			
			
			YinyueXiazaiDao yinyueXiazaiDao =  (YinyueXiazaiDao) Aware.context.getBean("yinyueXiazaiDao");
			
			
			
			
			
			//随机产生20首歌
			//查询当前库所有id
			List<Object> allid = yinyueXiazaiDao.getAllId();
			System.out.println("共"+allid.size()+"条");
			//随机产生10首歌id
			int [] randIds = new RandNumber().getRandmNumber(20,allid.size());
			
			String html = "";
			html+="<br>";
			html+="<br>";
			
			for (int i = 0; i < randIds.length; i++) {
				
				YinyueXiazai yinyueXiazai = yinyueXiazaiDao.getYinyueXiazaiByid((Integer)allid.get(randIds[i]));
				html+="<a href=\"http://www.250xyz.xyz/yinyue_xiazai/xiazai.action?id="+yinyueXiazai.getId()+"\">"+yinyueXiazai.getGequ_name()+"</a>";
				html+="<br>";
				html+="<br>";
			}
			
			
			
			homeHtml = html;
			homeDate = StringCode.getDateHH();
		}
		
		
		
		
		
		long datab = new Date().getTime();
		System.out.println("耗 时  ： "+(datab-dataa));
		return homeHtml;
	}
}
