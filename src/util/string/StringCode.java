package util.string;


import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StringCode {

	public static String getnnt(){
		return new SimpleDateFormat("yyyy/M/d ").format(new Date());
	}
	
	public static String zhuanhuan(String yuanString){
		DateFormat format= new SimpleDateFormat("yyyy/MM/dd a hh:mm:ss");
		Date date = null;
		try {
			date = format.parse(yuanString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "异常原信息："+yuanString;
		}
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		return df.format(date);
	}
	
	public static String strCode(String s){
		String str = "";
		try{
			if(s != null && !"".equals(s)){
				str =  URLDecoder.decode(s,"UTF-8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	//当前年月日时间
	public static String getDateTime(){
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		return df.format(cal.getTime());
	}
	//当前年月日时间
	public static String getDateHH(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");     
		return df.format(cal.getTime());
	}
	
	public static String getNextYear(){
		String str = "";
        try{
			Calendar cal = Calendar.getInstance();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
			cal.add(Calendar.YEAR,+1);
			str = df.format(cal.getTime());
			}catch(Exception e){
				e.printStackTrace();
			}
		return str;
	}
	
	public static String getNextYear1(){
		String str = "";
        try{
			Calendar cal = Calendar.getInstance();    
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
			cal.add(Calendar.YEAR,+1);
			cal.add(Calendar.DATE,-1);
			str = df.format(cal.getTime());
			}catch(Exception e){
				e.printStackTrace();
			}
		return str;
	}
	
	public static String getAddDay(String dateStr, int s){
		String str = "";
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date date = format.parse(dateStr);
			Calendar calendar=Calendar.getInstance(); 
			calendar.setTime(date); 
		    calendar.add(Calendar.DAY_OF_MONTH, s); 
		    str = format.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return str;
	}
	
	/**
	 * 获取昨天的日期。
	 */
	public static String getYesterday(){
		return getAddDay(getThisYear(),-1);
	}
	
	public static String getAddYear(String dateStr){
		String str = "";
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    try {
			Date date = format.parse(dateStr);
			Calendar calendar=Calendar.getInstance(); 
			calendar.setTime(date); 
		    calendar.add(Calendar.YEAR,+1); 
		    str = format.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return str;
	}

	

	public static String getThisYear(){
		Calendar cal = Calendar.getInstance();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");   
		return df.format(cal.getTime());
	}

	public static String getCurrentYear(){
		Calendar cal = Calendar.getInstance();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy");   
		return df.format(cal.getTime());
	}
	
	public static String getCurrentMonth(){
		Calendar cal = Calendar.getInstance();    
		SimpleDateFormat df = new SimpleDateFormat("MM");   
		return df.format(cal.getTime());
	}

	public static String getCurrentDay(){
		Calendar cal = Calendar.getInstance();    
		SimpleDateFormat df = new SimpleDateFormat("dd");   
		return df.format(cal.getTime());
	}

	public static String getTime(){
		Calendar cal = Calendar.getInstance();    
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");   
		return df.format(cal.getTime());
	}
	

	public static String getStringTodayMMddHHmm(){
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("MMddHHmm");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	}

	public static String getyyyyMMddHHmmss(){
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	}

	/**
	 * 
	 * @param isDate  要跟当前比较的日期
	 * @return 如果isDate大于当前日期，返回true
	 */
	public static boolean inDate(String isDate){
		boolean flag = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d1 = df.parse(isDate);
			Date d2 = df.parse(getThisYear());
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			if(days < 0){
				flag = false;
			}else{
				flag = true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @param 
	 * @return 如果第一个日期大于第二个日期，返回false
	 */
	public static boolean inDate(String sDate, String eDate){
		boolean flag = false;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Date d1 = df.parse(eDate);
			Date d2 = df.parse(sDate);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			if(days <= 0){
				flag = false;
			}else{
				flag = true;
			}
		}catch (Exception e){
		}
		return flag;
	}
	
	
	//计算两日期相差的天数
	public static int daysBetween(String early, String late){
		Date earlydate = new Date();   
        Date latedate = new Date();   
        DateFormat df = DateFormat.getDateInstance();   
        try{
            earlydate = df.parse(early);   
            latedate = df.parse(late);   
        }catch (Exception e){
              e.printStackTrace();   
          }   
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(earlydate);   
        caled.setTime(latedate);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
	
	
	 /** 
	  * 传入起始时间与结束时间。如果当前时间在范围内，返回true。反之返回false。
	  * String日期转换为Long
	  */    
	public static boolean transferStringDateToLong(String start,String end){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		boolean flag = false;
		try {
			Date dt = sdf.parse(StringCode.getDateTime());
			Date dtstart = sdf.parse(start);
			Date dtend = sdf.parse(end);
			if((dtstart.getTime() < dt.getTime()) && (dt.getTime() < dtend.getTime())){
				flag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	
	/** 
	    * 获取过去或者未来 任意天内的日期数组 
	    * @param intervals      intervals天内 
	    * @return              日期数组 
	    */  
	   public static ArrayList<String> test(int intervals ) {  
	       ArrayList<String> pastDaysList = new ArrayList<>();  
	       ArrayList<String> fetureDaysList = new ArrayList<>();  
	       for (int i = 0; i <intervals; i++) {  
	           pastDaysList.add(getPastDate(i));  
	           fetureDaysList.add(getFetureDate(i));  
	       }  
	       return pastDaysList;  
	   }  
	   /** 
	    * 获取过去或者未来 任意天内的日期数组 
	    * 只返回号
	    * @param intervals      intervals天内 
	    * @return              日期数组 
	    */  
	   public static ArrayList<String> testHao(int intervals ) {  
		   ArrayList<String> pastDaysList = new ArrayList<>();  
		   ArrayList<String> fetureDaysList = new ArrayList<>();  
		   for (int i = 0; i <intervals; i++) {  
			   pastDaysList.add(getPastDateHao(i));  
			   fetureDaysList.add(getFetureDateHao(i));  
		   }  
		   return fetureDaysList;  
	   }  
	  
	   /** 
	    * 获取过去第几天的日期 
	    * 
	    * @param past 
	    * @return 
	    */  
	   public static String getPastDate(int past) {  
	       Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	       String result = format.format(today);  
	       return result;  
	   }  
	   /** 
	    * 获取过去第几天的日期,只返回号 
	    * 
	    * @param past 
	    * @return 
	    */  
	   public static String getPastDateHao(int past) {  
		   Calendar calendar = Calendar.getInstance();  
		   calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
		   Date today = calendar.getTime();  
		   SimpleDateFormat format = new SimpleDateFormat("d");  
		   String result = format.format(today);  
		   return result;  
	   }  
	  
	   /** 
	    * 获取未来 第 past 天的日期 
	    * @param past 
	    * @return 
	    */  
	   public static String getFetureDate(int past) {  
	       Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	       String result = format.format(today);  
	       return result;  
	   }  
	   /** 
	    * 获取未来 第 past 天的日期 ,只返回号
	    * @param past 
	    * @return 
	    */  
	   public static String getFetureDateHao(int past) {  
		   Calendar calendar = Calendar.getInstance();  
		   calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
		   Date today = calendar.getTime();  
		   SimpleDateFormat format = new SimpleDateFormat("d");  
		   String result = format.format(today);  
		   return result;  
	   }  
	   
	   
	public static void main(String args[]) throws ParseException {
		System.out.println(StringCode.testHao(7));
	}

}