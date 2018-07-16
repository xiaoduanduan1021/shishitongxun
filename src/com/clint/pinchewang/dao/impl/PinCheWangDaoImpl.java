package com.clint.pinchewang.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Repository;

import com.clint.mixin.dao.MiXinDao;
import com.clint.mixin.model.MiXin;
import com.clint.pinchewang.dao.PinCheWangDao;
import com.clint.pinchewang.model.PinCheXinXi;
import com.clint.pinchewang.util.PincheUtil;
import com.clint.xiamipinglun.mobel.BaiduLianjeiXiazaiJilu;
import com.clint.xiamipinglun.mobel.XiamiJianceJilu;

import util.dao.BaseHibernate;
import util.page.PageList;
import util.string.StringCode;




@Repository(value="pinCheWangDao")
public class PinCheWangDaoImpl extends BaseHibernate implements PinCheWangDao {
	
	private Logger log = Logger.getLogger(PinCheWangDaoImpl.class);
	
	//查看该qq号今天是否发送过相同信息
	public PinCheXinXi getPinCheXinXiByContent(PinCheXinXi pinCheXinXi){
		String hql = "from PinCheXinXi where faSongZheQQ='"+pinCheXinXi.getFaSongZheQQ()+"'";
		hql +=" and faSongShiJian like '"+StringCode.getThisYear()+"%'";
		hql +=" and content='"+pinCheXinXi.getContent()+"'";
		return (PinCheXinXi) this.getObjectByHql(hql);
	}
	public PinCheXinXi addPinCheXinXi(PinCheXinXi pxx){
		return (PinCheXinXi) this.addObject(pxx);
	}
	public PinCheXinXi updatePinCheXinXi(PinCheXinXi pxx){
		return (PinCheXinXi) this.updateObject(pxx);
	}
	
	//手机端按照条件查询信息
	public PageList getPageListPincheXinxi(Map<String, Object> tiaojian){
		
		Integer yema  = (Integer) tiaojian.get("yema");

		
		List<Object> params = new ArrayList<Object>();
		
		String hql = "from PinCheXinXi where 1=1 ";
		
		List<String[]> guanjianzirr = this.zuzhiGuanjianzi(tiaojian);
		
		if (guanjianzirr.size()>0) {
			for (String[] guanjianzir : guanjianzirr) {
				hql +="and (";
				String or = "";
				for (String guanjianzi : guanjianzir) {
					String [] guanjianzis  = guanjianzi.split(" ");
					for (String gjz : guanjianzis) {
						hql+=or+" content like '%"+gjz+"%' ";
						or ="or";
					}
				}
				hql +=") ";
			}
		}
		
		hql +=" order by faSongShiJian desc";
		System.out.println(hql);
		PageList pagelist = this.getPageObjects(hql, params.toArray(), yema*PincheUtil.pagesize, PincheUtil.pagesize);
		//存储附加返回值
		Map<String, Object> fujiazhi =new HashMap<String, Object>();
		//返回分析关键字结果
		fujiazhi.put("guanjianzirr", guanjianzirr);
		pagelist.setFujiaZhi(fujiazhi);
		
		return pagelist;
	}
	
	
	//组织关键词,根据筛选条件分析出查询关键字
	public List<String[]> zuzhiGuanjianzi(Map<String, Object> tiaojian){
		
		List<String[]> ll=new ArrayList<String[]>();
		
		//搜索关键字
		String sousuoguanjianzi = (String) tiaojian.get("guanjianzi");
		if (StringUtils.isNotBlank(sousuoguanjianzi)) {
			String [] sousuoguanjianzis  = sousuoguanjianzi.split(" ");
			for ( String sg:sousuoguanjianzis) {
				ll.add(new String[]{sg});//搜索关键字的子关键字之间关系是且，筛选关键字的子关键字之间关系是或
			}
			
		}
		//筛选条件关键字,北京到大名
		String fangxiangRadio = (String) tiaojian.get("fangxiangRadio");
		if (StringUtils.isNotBlank(fangxiangRadio)) {
			String[] fangxiangRadioArray = PincheUtil.FK.get(fangxiangRadio);
			ll.add(fangxiangRadioArray);
		}
		//筛选条件关键字，日期例如：12号
		String riqi = (String) tiaojian.get("riqi");
		if (StringUtils.isNotBlank(riqi)) {
			ll.add(new String[]{riqi});
		}
		//筛选条件关键字，类型：车找人、人找车
		String leixing = (String) tiaojian.get("leixing");
		if (StringUtils.isNotBlank(leixing)) {
			ll.add(new String[]{leixing});
		}
		//筛选条件关键字，时间：例如上午
		String shangwuxiawu = (String) tiaojian.get("shangwuxiawu");
		if (StringUtils.isNotBlank(shangwuxiawu)) {
			String[] shangwuxiawuArray = PincheUtil.FK.get(shangwuxiawu);
			ll.add(shangwuxiawuArray);
		}
		//筛选条件关键字，舒适度：例如不超员
		String [] shushidu = (String[]) tiaojian.get("shushidu");
		if (shushidu!=null && shushidu.length>0) {
			ll.add(shushidu);
		}
		
		return ll;
	}
	
	//添加虾米评论检测记录
	public XiamiJianceJilu addXiamiJianceJilu (XiamiJianceJilu xiamiJianceJilu){
		return (XiamiJianceJilu) this.addObject(xiamiJianceJilu);
	}
	
	//添加链接下载检测记录
	public BaiduLianjeiXiazaiJilu addBaiduLianjeiXiazaiJilu(BaiduLianjeiXiazaiJilu baiduLianjeiXiazaiJilu){
		return (BaiduLianjeiXiazaiJilu) this.addObject(baiduLianjeiXiazaiJilu);
	}
}