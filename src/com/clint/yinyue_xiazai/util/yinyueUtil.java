package com.clint.yinyue_xiazai.util;

import javax.servlet.http.HttpServletRequest;

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
}
