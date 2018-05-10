package com.clint.pinchewang.util;

import java.util.HashMap;
import java.util.Map;

public class PincheUtil {

	//手机端查询每页显示条数
	public static final int pagesize = 30;
	
	//手机端查询
	//筛选条件和关键词对应关系
	public static Map<String, String[]> FK = new HashMap<String, String[]>();
	
	static{
		
		String [] h1 = {"回%北京","去%北京","到%北京"};
		FK.put("到北京", h1);
		
		String [] h2 = {"回%大名","去%大名","到%大名"};
		FK.put("到大名", h2);
	}
}
