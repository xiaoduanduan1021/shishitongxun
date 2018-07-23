package com.clint.pinchewang.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.page.PageList;
import util.string.EmojiFilter;
import util.string.StringCode;
import util.string.UnicodeToUtf8;

import com.alibaba.fastjson.JSONArray;
import com.clint.model.Person;
import com.clint.pinchewang.model.PinCheXinXi;
import com.clint.pinchewang.service.PinCheWangService;
import com.clint.xiamipinglun.HttpGetPost;
import com.clint.xiamipinglun.mobel.BaiduLianjeiXiazaiJilu;
import com.clint.xiamipinglun.mobel.XiamiJianceJilu;

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

		//去掉单引号和双引号防止mysql查询识别出错
		pinCheXinXi.setContent(pinCheXinXi.getContent().replaceAll("'", ""));
		pinCheXinXi.setContent(pinCheXinXi.getContent().replaceAll("\"", ""));
		//过滤掉emoji表情或者特殊符号，数据库不识别，
		pinCheXinXi.setContent(EmojiFilter.filterEmoji(pinCheXinXi.getContent()));
		
		
		// 按照行分割
		String[] contentHangs = pinCheXinXi.getContent().split("\n");
		
		String con = "";//暂存一条信息
		
		// 从后往前遍历，并存储
		for (int i = contentHangs.length - 1; i >= 0; i--) {
		//	System.out.println(xinxihang);
			String xinxihang = contentHangs[i];
			
			
			
			
			
			
			
			
			
			
			
			
			
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
				
				
				
				//判断是否是尖括号，邮箱注册的qq是尖括号
				String kuohaozuo = "(";
				String kuohaoyou = ")";
				if (xinxihang.indexOf("<")>0) {
					kuohaozuo="<";
					kuohaoyou=">";
				}
				
				
				
				
				
				String nicheng = xinxihang.substring(0, xinxihang.indexOf(kuohaozuo));
				
				pinCheXinXi2.setFaSongZheNiCheng(nicheng);
				
				
				System.out.println(nicheng);
				System.out.println("QQ");
				String QQ = xinxihang.substring(xinxihang.indexOf(kuohaozuo)+1, xinxihang.indexOf(kuohaoyou));
				
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
					String nian = xinxihang.substring(xinxihang.indexOf(kuohaoyou+" ")+2, xinxihang.indexOf(" 星期"));
					//获取时间
					String shijian = xinxihang.substring(xinxihang.indexOf(" 星期")+4, xinxihang.length());
					System.out.println(nian+shijian);
					time = nian+shijian;
				}else{
					//如果没有则自动补充今天时间
					//获取时间
					//获取时间
					String shijian = xinxihang.substring(xinxihang.indexOf(kuohaoyou+"  ")+3, xinxihang.length());
					
					System.out.println(StringCode.getnnt()+shijian);
					time = StringCode.getnnt()+shijian;
				}
				
				pinCheXinXi2.setFaSongShiJian(StringCode.zhuanhuan(time));
				System.out.println("转换后"+pinCheXinXi2.getFaSongShiJian());
				
				//查询这个qq今天是否已经发过相同的信息
				PinCheXinXi pdb = pinCheWangService.getPinCheXinXiByContent(pinCheXinXi2);
				if (pdb == null) {
					pinCheWangService.addPinCheXinXi(pinCheXinXi2);
				}else{
					//查看时间是否相同
					if (pdb.getFaSongShiJian().equals(pinCheXinXi2.getFaSongShiJian())) {
						//相同则停止存储和循环，不同则更新时间
						break;//跳出循环不再存储剩下的
					}else{
						if(pinCheXinXi2.getFaSongShiJian().compareTo(pdb.getFaSongShiJian())>0){
							pdb.setFaSongShiJian(pinCheXinXi2.getFaSongShiJian());
							pinCheWangService.updatePinCheXinXi(pdb);
						}
					}
				}
				
				
				
				
				
				
				
				
				
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

//		// 模拟系统处理时间
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		JSONObject json = new JSONObject();
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}

	
	
	
	//查询页面
	@RequestMapping(value = "/getPincheList")
	public String getPincheList() {
		return "/pin_che_wang/getPincheList/getPincheList.jsp";
	}
	
	
	//提交查询条件，返回查询数据
	@RequestMapping(value = "/getPincheListAndTiaojian")
	public void getPincheListAndTiaojian(String [] shushidu,String leixing,String shangwuxiawu,String riqi,String fangxiangRadio , String guanjianzi,Integer yema,HttpServletResponse response) throws IOException, InterruptedException {
		System.out.println("查询");
		System.out.println(riqi);
		System.out.println(shangwuxiawu);
		System.out.println(fangxiangRadio);
		System.out.println(shushidu);
		
//		Thread.sleep(5000);
		
		System.out.println("关键字：");
		System.out.println(guanjianzi);
		
		Map<String, Object> tiaojian = new HashMap<String, Object>();
		tiaojian.put("yema", yema);
		tiaojian.put("guanjianzi", guanjianzi);
		tiaojian.put("fangxiangRadio", fangxiangRadio);
		tiaojian.put("shangwuxiawu", shangwuxiawu);
		tiaojian.put("riqi", riqi);
		tiaojian.put("leixing", leixing);
		tiaojian.put("shushidu", shushidu);
		
		JSONObject json = new JSONObject();
		PageList pageList = pinCheWangService.getPageListPincheXinxi(tiaojian);
		json.put("list", pageList.getDatalist());
		
		//分析得到的关键字
		json.put("fenxiGuanjianci", pageList.getFujiaZhi().get("guanjianzirr"));
		
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//虾米评论检测，每分钟一次，检测是否在第一
		@RequestMapping(value = "/xiamijiance")
		public void xiamijiance() throws InterruptedException, IOException{
			System.out.println("启动");
			System.out.println("检查是否第一");
			while (true) {
				
				String list [][][] = new HttpGetPost().getList();
				
				//存储第一个数
				int diyigeshu = 0;
				//超时个数
				int chaoshi = 0;
				
				
				XiamiJianceJilu xiamiJianceJilu = new XiamiJianceJilu();
				xiamiJianceJilu.setDate(StringCode.getDateTime());
				
				
				
				
				
				for (int i = 0; i < list.length; i++) {
					String yigezhanghu[][] = list[i];
					for (int j = 0; j < yigezhanghu[1].length; j++) {
						String oid = yigezhanghu[1][j];
						
						System.out.println(oid);
						String diyi = new HttpGetPost().dyno1("https://www.xiami.com/collect/"+oid);
						System.out.println(diyi);
						if (diyi.equals("true")) {
							diyigeshu++;
						}else if(diyi.equals("chaoshi")){
							chaoshi++;
						}
						//延迟
						for (int k = 0; k < 0; k++) {
							Thread.sleep(1000);
							System.out.print(k+",");
						}
						System.out.println();
					}
				}
				
				
				
				//存储
				xiamiJianceJilu.setXiaodan(diyigeshu);
				xiamiJianceJilu.setChaoshi(chaoshi);
				
				System.out.println("完成");
				
				
				//检测362是否是第一
				String diyi = new HttpGetPost().dyno1("https://www.xiami.com/collect/"+355288362);
				if(diyi.equals("true")){
					xiamiJianceJilu.setDadan(1);
				}else if(diyi.equals("chaoshi")){
					xiamiJianceJilu.setDadanchaoshi(1);
				}
				
				
				
				
				xiamiJianceJilu.setEnddate(StringCode.getDateTime());
				
				
				this.pinCheWangService.addXiamiJianceJilu(xiamiJianceJilu);
				
				
				
				
				//保存百度链接检测记录
				this.jiancebaidu();
				
				
				
				//延迟1分钟检测一测
				for (int k = 0; k < 60*5; k++) {
					Thread.sleep(1000);
					System.out.print(k+",");
				}
			}
		}
		
		
		
		
		
		

		@RequestMapping(value = "/jiancebaidu")
//		百度网盘下载量自动保存
		public void jiancebaidu(){
			
			//查询百度链接访问次数下载次数
		 	Document doc;
		 	String jieguo = "";
			try {
				doc = HttpGetPost.httpGet("https://pan.baidu.com/share/record?channel=chunlei&clienttype=0&web=1&page=1&order=ctime&desc=1&_=1531731797366&bdstoken=f663457ca3da98a18ee020ec11228a46&channel=chunlei&web=1&app_id=250528&logid=MTUzMTczMTc5NzM2NjAuMDQ3ODUwNTM2NTE5OTc2NjE2&clienttype=0", "BAIDUID=F4F6FBDA54B4897FD1C0FF6F7262A1EB:FG=1; BIDUPSID=F4F6FBDA54B4897FD1C0FF6F7262A1EB; PSTM=1531702896; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; PANWEB=1; PSINO=2; FP_UID=234b328ccb905a05a502f09cedfd8966; BDUSS=JKTUNtUW85aEp5QU0zNTYwTHBEbFYyalNnendzYVlGYVpENXI5OUFzWDNsbk5iQVFBQUFBJCQAAAAAAAAAAAEAAAAVPfYIMTAyMTU2NzIwNQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPcJTFv3CUxbQn; pan_login_way=1; SCRC=67413c0e6450ed3929cd9cae6dfd9fc6; STOKEN=66a35f5c0cd91dee1b0bcf736263407f805fd28fbe056570192d44fa82960faa; cflag=15%3A3; locale=zh; H_PS_PSSID=26522_1434_26458_21098_18559_20697_26350_26807; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1531705676,1531709948,1531726349,1531731226; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1531731340; PANPSC=10365827697105222768%3ARjPPFO%2Fmao%2FxEZ7VecQ9BpL0sWaEh1tro0KDZL2BkTcxFT2j7aBV%2BHgXofrWESI4QGhj5WrRaLoEt%2Bn2pTdhc9tRhTZfsH67Rh8sLk7draU%2F0we9031ImHQXStQWRQyPwL9JXASrr014%2B2HacCA3yTsuktMw6gulbVFcNkn0FT5VvmSKYmKRhKQRuwhxVv30");
				jieguo = doc.body().text();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		    jieguo = new UnicodeToUtf8().decodeUnicode(jieguo);
		 	System.out.println(jieguo);
		    
		    JSONObject json = JSONObject.fromObject(jieguo);
		    
		    if (json.getInt("errno") == 0) {
				
			    net.sf.json.JSONArray list = json.getJSONArray("list");
			    for (int i = 0; i < list.length(); i++) {
			    	JSONObject jo = list.getJSONObject(i);
			    	
			    	if (jo.getString("typicalPath").indexOf("抖音")>0) {
			    		System.out.println(jo);
			    		
			    		BaiduLianjeiXiazaiJilu baiduLianjeiXiazaiJilu = new BaiduLianjeiXiazaiJilu();
			    		baiduLianjeiXiazaiJilu.setJianceDate(StringCode.getDateTime());
			    		baiduLianjeiXiazaiJilu.setLianJieMing(jo.getString("typicalPath"));
			    		baiduLianjeiXiazaiJilu.setChakanCishu(jo.getInt("vCnt"));
			    		baiduLianjeiXiazaiJilu.setBaocunCishu(jo.getInt("tCnt"));
			    		baiduLianjeiXiazaiJilu.setXiazaiCishu(jo.getInt("dCnt"));
			    		this.pinCheWangService.addBaiduLianjeiXiazaiJilu(baiduLianjeiXiazaiJilu);
			    		
					}
				}
		    
		    }
		    
		    
		    
		}
}