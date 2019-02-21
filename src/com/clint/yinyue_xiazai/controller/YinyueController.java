package com.clint.yinyue_xiazai.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clint.yinyue_xiazai.dao.YinyueXiazaiDao;
import com.clint.yinyue_xiazai.model.YinyueXiazai;
import com.clint.yinyue_xiazai.util.DJye;
import com.clint.yinyue_xiazai.util.Ik123;
import com.clint.yinyue_xiazai.util.KuGou;
import com.clint.yinyue_xiazai.util.QianQianYinYue;

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
	
	
	//分析排行榜存入数据库
	@RequestMapping(value = "/fenxiPaihang")
	public void fenxiPaihang() throws IOException {
		kugou.ListToDB("https://www.kugou.com/yy/rank/home/1-22163.html?from=rank",25,"中国TOP排行榜"+" 2019-02-18期");
	}
	
	//根据id获取一首歌的下载地址，进入下载页面
	@RequestMapping(value = "/xiazai")
	public String xiazai(int id, HttpServletRequest req) {
		
		//查询出改id对应信息
		YinyueXiazai yinyueXiazai = yinyueXiazaiDao.getYinyueXiazai(id);
		req.setAttribute("yinyueXiazai", yinyueXiazai);
		
		return "/yinyue_xiazai/xiazaiByid/index.jsp";
	}
	
}
