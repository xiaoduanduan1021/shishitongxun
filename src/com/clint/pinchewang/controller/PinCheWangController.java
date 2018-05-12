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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import util.page.PageList;
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
	public void getPincheListAndTiaojian(String leixing,String shangwuxiawu,String riqi,String fangxiangRadio , String guanjianzi,Integer yema,HttpServletResponse response) throws IOException, InterruptedException {
		System.out.println("查询");
		System.out.println(riqi);
		System.out.println(shangwuxiawu);
		System.out.println(fangxiangRadio);
		
//		Thread.sleep(2000);
		
		System.out.println("关键字：");
		System.out.println(guanjianzi);
		
		Map<String, String> tiaojian = new HashMap<String, String>();
		tiaojian.put("yema", String.valueOf(yema));
		tiaojian.put("guanjianzi", guanjianzi);
		tiaojian.put("fangxiangRadio", fangxiangRadio);
		tiaojian.put("shangwuxiawu", shangwuxiawu);
		tiaojian.put("riqi", riqi);
		tiaojian.put("leixing", leixing);
		
		JSONObject json = new JSONObject();
		PageList pageList = pinCheWangService.getPageListPincheXinxi(tiaojian);
		json.put("list", pageList.getDatalist());
		
		//分析得到的关键字
		json.put("fenxiGuanjianci", pageList.getFujiaZhi().get("guanjianzirr"));
		
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}
	
	
	public static void main(String[] args) {
		
		String aa = "【活跃】別說、対罘起.<yangjie136272700@qq.com> 2018/5/6 星期日 下午 2:36:12";
		String bb = "【活跃】杨保军(2019468064) 2018/5/6 星期日 下午 2:35:18";
		
		//获取时间
		
		System.out.println(aa.indexOf("<"));
		System.out.println(aa.indexOf("("));
		System.out.println(bb.indexOf("<"));
		System.out.println(bb.indexOf("("));
		
	}
}