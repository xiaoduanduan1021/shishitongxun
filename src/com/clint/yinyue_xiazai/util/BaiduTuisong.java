package com.clint.yinyue_xiazai.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BaiduTuisong {

//	    data+="http://www.250xyz.xyz/yinyue_xiazai/gequList.action?start=1\n";
//	    data+="http://www.250xyz.xyz/yinyue_xiazai/gequList.action?start=2\n";
//		3-152,已推送  结果是{"remain":4999845,"success":150}2019年2月24日10:54:02推送
	
//	    data+="http://www.250xyz.xyz/yinyue_xiazai/xiazai.action?id=3235\n";
//	    data+="http://www.250xyz.xyz/yinyue_xiazai/xiazai.action?id=3236\n";
//	    data+="http://www.250xyz.xyz/yinyue_xiazai/xiazai.action?id=3237\n";
	
	
//	"http://www.250xyz.xyz",
	
	
	
	
	public static void main(String[] args) {  
		//推送
		String url = "http://data.zz.baidu.com/urls?site=www.250xyz.xyz&token=e3U7VuJ3a9xFaD09";//网站的服务器连接  
		//修改
        //String url = "http://data.zz.baidu.com/update?site=www.250xyz.xyz&token=e3U7VuJ3a9xFaD09";//网站的服务器连接  
        
		java.util.List<String> param = new ArrayList<String>();
        
		for (int i = 3; i <= 152; i++) {
			param.add("http://www.250xyz.xyz/yinyue_xiazai/gequList.action?start="+i);
		}
		
		
		
        String json = Post(url, param);//执行推送方法  
        System.out.println("结果是"+json);  //打印推送结果  
  
    }  
      
    /** 
     * 百度链接实时推送 
     * @param PostUrl 
     * @param Parameters 
     * @return 
     */  
    public static String Post(String PostUrl,java.util.List<String> Parameters){  
        if(null == PostUrl || null == Parameters || Parameters.size() ==0){  
            return null;  
        }  
        String result="";  
        PrintWriter out=null;  
        BufferedReader in=null;  
        try {  
            //建立URL之间的连接  
            URLConnection conn=new URL(PostUrl).openConnection();  
            //设置通用的请求属性  
            conn.setRequestProperty("Host","data.zz.baidu.com");  
            conn.setRequestProperty("User-Agent", "curl/7.12.1");  
            conn.setRequestProperty("Content-Length", "83");  
            conn.setRequestProperty("Content-Type", "text/plain");  
               
            //发送POST请求必须设置如下两行  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
               
            //获取conn对应的输出流  
            out=new PrintWriter(conn.getOutputStream());  
            //发送请求参数  
            String param = "";  
            for(String s : Parameters){  
                param += s+"\n";  
            }  
            out.print(param.trim());  
            //进行输出流的缓冲  
            out.flush();  
            //通过BufferedReader输入流来读取Url的响应  
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while((line=in.readLine())!= null){  
                result += line;  
            }  
               
        } catch (Exception e) {  
            System.out.println("发送post请求出现异常！"+e);  
            e.printStackTrace();  
        } finally{  
            try{  
                if(out != null){  
                    out.close();  
                }  
                if(in!= null){  
                    in.close();  
                }  
                   
            }catch(IOException ex){  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    } 
    
    
}


