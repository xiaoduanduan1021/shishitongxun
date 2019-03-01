package com.clint.yinyue_xiazai.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.chainsaw.Main;
import org.hibernate.mapping.Array;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;
import com.clint.yinyue_xiazai.util.BBDJ;
import com.clint.yinyue_xiazai.util.DJye;
import com.clint.yinyue_xiazai.util.Ik123;
import com.clint.yinyue_xiazai.util.KuGou;
import com.clint.yinyue_xiazai.util.QianQianYinYue;
import com.clint.yinyue_xiazai.util.WWW_72dj;
import com.clint.yinyue_xiazai.util.yinyueUtil;

import net.sf.json.JSONObject;
import util.page.PageList;
import util.string.StringCode;

@Controller
@RequestMapping(value = "/yinyue_xiazai")

public class YinyueController {
	
	
	
	@Resource(name="yinyueXiazaiDao")
	private YinyueXiazaiDao yinyueXiazaiDao;

	@Resource(name="kugou")
	private KuGou kugou;
	
	
	//首页
	@RequestMapping(value = "/home")
	public String home() {
		System.out.println("index");
		System.out.println("index");
		System.out.println("index");
		System.out.println("index");
		System.out.println("index");
		return "/index.jsp";
	}
	
	
	
	
	
	//进入客户端录入试听地址页面
	@RequestMapping(value = "/yinyue_xiazai_home")
	public String yinyue_xiazai_home() {
		return "/yinyue_xiazai/home/home.jsp";
	}
	
	
	
	
	//提交试听地址
	@RequestMapping(value = "/saveShiting")
	public void saveShiting(YinyueXiazai yinyueXiazai,HttpServletResponse response) throws IOException {

		
		
		
		JSONObject json = new JSONObject();
		//存储
		System.out.println(yinyueXiazai.toString());
		
		
		//不能为空
		if(StringUtils.isBlank(yinyueXiazai.getShiting_url())){
			
			json.put("type", "noNull");
			response.getWriter().write(json.toString());
			return;
		}
		
		
		//默认获取页面标题，获取标题名称
        Connection con = Jsoup.connect(yinyueXiazai.getShiting_url()).timeout(1000 * 30).ignoreContentType(true);
        Document doc=con.get(); 
        yinyueXiazai.setGequ_name(doc.title());
        
		
		//查询是否是酷狗，如果是则直接查询地址并存储
        if(yinyueXiazai.getShiting_url().indexOf("www.kugou.com")>0){
        	String[] kugoud = new KuGou().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setGequ_name(kugoud[0]);
        	yinyueXiazai.setXiazai_dizhi(kugoud[1]);
        	yinyueXiazai.setStatus(1);
        	
        }
        //千千音乐定制
        if(yinyueXiazai.getShiting_url().indexOf("music.taihe.com")>0){
        	
        	String[] qianqian = new QianQianYinYue().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setGequ_name(qianqian[0]);
        	yinyueXiazai.setXiazai_dizhi(qianqian[1]);
        	yinyueXiazai.setStatus(1);
        	
        }
        //Ik123定制
        if(yinyueXiazai.getShiting_url().indexOf("www.ik123.com")>0){
        	
        	String ik = new Ik123().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setXiazai_dizhi(ik);
        	yinyueXiazai.setStatus(1);
        	
        }
		
        //djye定制
        if(yinyueXiazai.getShiting_url().indexOf("www.djye.com")>0){
        	
        	String djye = new DJye().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setXiazai_dizhi(djye);
        	yinyueXiazai.setStatus(1);
        	
        }
        //djkk定制
        if(yinyueXiazai.getShiting_url().indexOf("www.djkk.com")>0){
        	
        	String djkk = new com.clint.yinyue_xiazai.util.djkk().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setXiazai_dizhi(djkk);
        	yinyueXiazai.setStatus(1);
        	
        }
        //72dj定制
        if(yinyueXiazai.getShiting_url().indexOf("www.72dj.com")>0){
        	
        	String djkk = new WWW_72dj().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setXiazai_dizhi(djkk);
        	yinyueXiazai.setStatus(1);
        	
        }
        //bbdj定制
        if(yinyueXiazai.getShiting_url().indexOf("www.bbdj.com")>0){
        	
        	String djkk = new BBDJ().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setXiazai_dizhi(djkk);
        	yinyueXiazai.setStatus(1);
        	
        }
		
		
		
		
		
		yinyueXiazai.setDatetime(StringCode.getDateTime());
		yinyueXiazaiDao.saveYinyueXiazai(yinyueXiazai);
		
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}
	
	//获取最近的下载列表
	@RequestMapping(value = "/huoquLiebiao")
	public void huoquLiebiao(String uuid,HttpServletResponse response) throws IOException {
		
		JSONObject json = new JSONObject();
		
		Map<String, Object> tiaojian = new HashMap<String, Object>();
		tiaojian.put("uuid", uuid);
		PageList pageList = yinyueXiazaiDao.getPageYinyueXiazaiS(tiaojian);
		
		json.put("data", pageList.getDatalist());
		
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}
	
	
	//处理端进入页面
	@RequestMapping(value = "/yinyueChuli")
	public String yinyueChuli() {
		return "/yinyue_xiazai/chuli/chuli.jsp";
	}
	
	//获取未处理的音乐列表，//正序排列，
	@RequestMapping(value = "/weichuliLiebiao")
	public void weichuliLiebiao(HttpServletResponse response) throws IOException {
		
		JSONObject json = new JSONObject();
		
		PageList pageList = yinyueXiazaiDao.getPageYinyue_weichuli();
		
		json.put("data", pageList.getDatalist());
		
		response.getWriter().write(json.toString());
	}
	
	
	//提交下载地址
	@RequestMapping(value = "/saveXiazai")
	public void saveXiazai(YinyueXiazai yinyueXiazai,HttpServletResponse response) throws IOException {
		
		JSONObject json = new JSONObject();
		
		YinyueXiazai yinyueXiazaiDb = yinyueXiazaiDao.getYinyueXiazai(yinyueXiazai.getId());

		//如果地址栏是空则分析失败
		if(StringUtils.isBlank(yinyueXiazai.getXiazai_dizhi())){
			yinyueXiazaiDb.setStatus(2);
		}else{
			yinyueXiazaiDb.setStatus(1);
		}
		yinyueXiazaiDb.setXiazai_dizhi(yinyueXiazai.getXiazai_dizhi());
		
		yinyueXiazaiDao.updataYinyueXiazai(yinyueXiazaiDb);
		
		json.put("type", "ok");
		response.getWriter().write(json.toString());
	}
	
	
	//下载链接获取
	@RequestMapping(value = "/XiazaiLianjie")
	public void XiazaiLianjie(HttpServletResponse response) throws IOException {
		
		response.getWriter().write("http://fs.w.kugou.com/201902171402/9dcc6059af2ea3ccacb7efcb4c9d5f57/G126/M06/1B/03/vg0DAFxk5QaAZQLmADDYqT1x8PY410.mp3");
	}
	
	
	
	
	
	
	//分析酷狗排行榜存入数据库
	@RequestMapping(value = "/fenxiPaihang")
	public void fenxiPaihang() throws IOException {
		kugou.ListToDB("https://www.kugou.com/yy/rank/home/1-31311.html?from=rank",25,"酷狗"+"自动期");
	}
	
	

	
	
	//分析酷狗排行榜存入数据库
	@RequestMapping(value = "/fenxiGedan")
	public void fenxiGedan() throws IOException {
		System.out.println("开始");
		kugou.gedanAllCunchu("https://www.kugou.com/yy/special/index/1-5-0.html");
		kugou.gedanAllCunchu("https://www.kugou.com/yy/special/index/1-6-0.html");
		kugou.gedanAllCunchu("https://www.kugou.com/yy/special/index/1-3-0.html");
		kugou.gedanAllCunchu("https://www.kugou.com/yy/special/index/1-8-0.html");
		System.out.println("结束");
	}
	
	
	//分析酷狗歌手排行榜存入数据库
	@RequestMapping(value = "/fenxiGeshou")
	public void fenxigeshou() throws IOException {
		System.out.println("开始--分析--歌手");
		kugou.bianliRemenGeshou();
		System.out.println("结束--分析--歌手");
	}
	
	
	
	
	
	//根据id获取一首歌的下载地址，进入下载页面
	@RequestMapping(value = "/xiazai")
	public String xiazai(int id, HttpServletRequest req) throws IOException {
		
		
		
		//查询出改id对应信息
		
		
		YinyueXiazai yinyueXiazai = yinyueXiazaiDao.getYinyueXiazai(id);
		
		
        
        
		
		req.setAttribute("yinyueXiazai", yinyueXiazai);
		
		
		return "/yinyue_xiazai/xiazaiByid/index.jsp";
	}
	
	
	//异步更新下载地址
	@RequestMapping(value = "/gengxin_url")
	public void gengxin_url(int id, HttpServletRequest req,HttpServletResponse response) throws IOException {
		//查询出改id对应信息
		YinyueXiazai yinyueXiazai = yinyueXiazaiDao.getYinyueXiazai(id);
		//如果是酷狗则更新下载链接
		//查询是否是酷狗，如果是则直接查询地址并存储
        if(yinyueXiazai.getShiting_url().indexOf("www.kugou.com")>0){
        	String[] kugoud = new KuGou().urlToMusic(yinyueXiazai.getShiting_url());
        	yinyueXiazai.setGequ_name(kugoud[0]);
        	yinyueXiazai.setXiazai_dizhi(kugoud[1]);
        	yinyueXiazai.setStatus(1);
        	this.yinyueXiazaiDao.updataYinyueXiazai(yinyueXiazai);
        }
        
        //返回地址
        response.getWriter().write(yinyueXiazai.getXiazai_dizhi());
	}
	
	
	//给所有歌曲增加伪原创文字，随机生成汉字，生成30行,并随机插入标题文字10次，并保存到数据库
	@RequestMapping(value = "/weiyuanchuang")
	public void weiyuanchuang() throws UnsupportedEncodingException {
		
		//查询出所有没有伪原创文章的歌曲记录
		List<YinyueXiazai> list = this.yinyueXiazaiDao.getNoWeiyuanchuang();
		
		for (int i = 0; i < list.size(); i++) {
			
			
			YinyueXiazai yy = list.get(i);
			
			
			//生成随机文字30行
			String zongwenzi = "";
			for (int j = 0; j < 30; j++) {
				//每行40以内，随机数量
				//组合成p标签字符串，插入标题多次
				zongwenzi+="<p>";
				for (int j2 = 0; j2 < 40; j2++) {
					
					//在随机位置添加歌名关键词
					if(j==5||j==16||j==23||j==28||j==32||j==38){
						if(j2==24){
							zongwenzi+="音乐250网"+yy.getGequ_name()+"  mp3高清歌曲免费下载";
						}
					}
					
					
					//添加汉字
					zongwenzi+=this.getRandomChar();
					
					//添加逗号
					if(j2==10||j2==20||j2==30){
						zongwenzi+=",";
					}
				}
				//添加句号
				zongwenzi+="。";
				zongwenzi+="</p>";
			}
			//存储到数据库

			yy.setWeiYuanChuang(zongwenzi);
			this.yinyueXiazaiDao.updataYinyueXiazai(yy);
		}
		
		
	}
	//随机汉字生成
	public static String getRandomChar() throws UnsupportedEncodingException {
		String str = null;
		int hightPos, lowPos; // 定义高低位
		Random random = new Random();
		hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值
		lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值
		byte[] b = new byte[2];
		b[0] = (new Integer(hightPos).byteValue());
		b[1] = (new Integer(lowPos).byteValue());
		str = new String(b, "GBk");//转成中文
		return str;
//        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }
	public static void main(String[] args) throws UnsupportedEncodingException {
		for (int i = 0; i < 30; i++) {
			
			System.out.println(new YinyueController().getRandomChar());
		}
	}
	//获取歌曲列表
	@RequestMapping(value = "/gequList")
	public String gequList(int start, HttpServletRequest request) throws IOException {
		
		//查询出改id对应信息
		Map<String, Object> tiaojian = new HashMap<String, Object>();
		PageList page = yinyueXiazaiDao.getPageYinyue(tiaojian, start, 20);

		
		page.setUrl(yinyueUtil.getYuming(request)+"yinyue_xiazai/gequList.action?start=");

		request.setAttribute("page", page);
		
		return "/yinyue_xiazai/yinyueList/yinyueList.jsp";
	}
	
}
