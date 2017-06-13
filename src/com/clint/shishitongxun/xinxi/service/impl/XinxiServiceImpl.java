package com.clint.shishitongxun.xinxi.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import util.page.PageDto;
import util.page.PageList;
import util.string.StringCode;

import com.clint.shishitongxun.xinxi.dao.XinxiDao;
import com.clint.shishitongxun.xinxi.model.Guke;
import com.clint.shishitongxun.xinxi.model.Xinxi;
import com.clint.shishitongxun.xinxi.service.XinxiService;


@Service(value="xinxiService")
public class XinxiServiceImpl implements XinxiService {

	@Resource(name="xinxiDao")
	private XinxiDao xinxiDao;
	
	//保存聊天信息/客服和顾客发的消息都会存储在这里
	public void savePerson(JSONObject jsonObject){
		Xinxi xinxi = new Xinxi();
		xinxi.setDatetime(jsonObject.getString("date"));
		xinxi.setFasongzhe(Integer.valueOf(jsonObject.getInt("fasongzhe")));
		xinxi.setGukeId(jsonObject.getString("gukeId"));
		xinxi.setKefuId(Integer.valueOf(jsonObject.getString("kefuId")));
		xinxi.setStutas(Integer.valueOf(jsonObject.getString("status")));
		xinxi.setNeirong(jsonObject.getString("neirong"));
		xinxiDao.savePerson(xinxi);
		//判断如果是顾客发的消息并且该消息为客服不在线
		if(jsonObject.getInt("fasongzhe")==2){
			if(jsonObject.getInt("status")==0){
				//存储该顾客为有新消息状态
				Guke guke = this.xinxiDao.getGukeByHttpSessionid(jsonObject.getString("gukeId"), jsonObject.getInt("kefuId"));
				guke.setNewXinxi(1);
				this.xinxiDao.updateGuke(guke);
			}
		}
	}
	//获取信息列表
	public PageList getPageXinxi(Map<String, String> tiaojian,PageDto pageDto){
		return this.xinxiDao.getPageXinxi(tiaojian, pageDto);
	}
	//查询该顾客是否需要存储到数据库
	public String saveGuke(JSONObject jsonObject){
		//判断是否已经注册
		//根据httpsessionid（gukeid）获取顾客
		Guke gukedb = this.xinxiDao.getGukeByHttpSessionid(jsonObject.getString("gukeId"), jsonObject.getInt("kefuId"));
		if(null == gukedb){
			//添加顾客
			//添加顾客
			Guke guke = new Guke();
			guke.setHttpSessionId(jsonObject.getString("gukeId"));
			guke.setKefuId(jsonObject.getInt("kefuId"));
			guke.setZhuceShijian(StringCode.getDateTime());
			
			//存入地区信息
			String ip = jsonObject.getString("ip");
			JSONObject ipJsonObject = JSONObject.fromObject(ip);
			if (ipJsonObject.getString("code").equals("0")){
				
				String data = ipJsonObject.getString("data");
				JSONObject dataJsonobject = JSONObject.fromObject(data);
				
				guke.setIp(dataJsonobject.getString("ip"));
				guke.setCountry(dataJsonobject.getString("country"));
				guke.setCountry_id(dataJsonobject.getString("country_id"));
				guke.setArea(dataJsonobject.getString("area"));
				guke.setArea_id(dataJsonobject.getString("area_id"));
				guke.setRegion(dataJsonobject.getString("region"));
				guke.setRegion_id(dataJsonobject.getString("region_id"));
				guke.setCity(dataJsonobject.getString("city"));
				guke.setCity_id(dataJsonobject.getString("city_id"));
				guke.setCounty(dataJsonobject.getString("county"));
				guke.setCounty_id(dataJsonobject.getString("county_id"));
				guke.setIsp(dataJsonobject.getString("isp"));
			}
			//存入访问记录id
			guke.setAccessSessionId(jsonObject.getString("accessSessionId"));
			this.xinxiDao.addGuke(guke);
		}
		return "";
	}
	//获取顾客列表
	//按照有新消息的在上，无新消息的在下
	//分页获取
	public PageList getPageGukeByKefuId(Map<String, Object> tiaojian){
		return this.xinxiDao.getPageGukeByKefuId(tiaojian);
	}
	
	//修改指定客服下的顾客为无新消息
	public Guke updateGukeNoNewXinxi(String httpSessionid,int kefuid){
		Guke g = this.xinxiDao.getGukeByHttpSessionid(httpSessionid, kefuid);
		g.setNewXinxi(0);
		return this.xinxiDao.updateGuke(g);
	}
	//测试访问spring和hibernate
	public List<Xinxi> getAllXinxi(){
		return this.xinxiDao.getAllXinxi();
	}
}
