function getScrollTop() {
		var scrollTop = 0;
		if (document.documentElement && document.documentElement.scrollTop) {
			scrollTop = document.documentElement.scrollTop;
		} else if (document.body) {
			scrollTop = document.body.scrollTop;
		}
		return scrollTop;
	}

	/********************  
	 * 取窗口可视范围的高度    
	 *******************/
	function getClientHeight() {
		var clientHeight = 0;
		if (document.body.clientHeight && document.documentElement.clientHeight) {
			var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight
					: document.documentElement.clientHeight;
		} else {
			var clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight
					: document.documentElement.clientHeight;
		}
		return clientHeight;
	}

	/********************  
	 * 取文档内容实际高度    
	 *******************/
	function getScrollHeight() {
		return Math.max(document.body.scrollHeight,
				document.documentElement.scrollHeight);
	}
	function test() {
		if (getScrollTop() + getClientHeight() == getScrollHeight()) {
			//alert("到达底部");
			zhengzaichaxun();
			chaxunyiye();
		} else {
			//alert("没有到达底部");
		}
	}
	window.onscroll = function() {
		
		test();
	}

	var fanyeType = 0;//默认等于0可以翻页，查询时等于1不可重复翻页，必须等待查询完毕才可以再次翻页

	//下拉翻页功能-------------------------------------
	
	



//显示正在查询

//将列表显示在页面
function xianshiliebiao(list,me){
	
	$(".chaxunjiazaikuang").hide();
	
	//遍历列表
	for(var i in list){
		xianshiyigexinxi(list[i])
	}
	
	//console.log("条数："+list.length);
	//判断是否还有信息，如果没有则显示已经没有更多信息
	if(list.length==0){
		//console.log("无数据");
		meiyougengduo();
	}else{
		yema++;
		//console.log("加载完成");
		shanglajiazaigengduo();
	}
	fanyeType=0;//开启翻页
}


//清空内容区域
function qingkongNeirongqu(){
	$(".content").html("");
}

//添加信息到内容区域
//清空内容区域
function tianjiaNeirongqu(html){
	$(".content").append(html);
}






//正在查询
function zhengzaichaxun(){
	$(".fanyezhengzaichaxun").css("visibility","inherit");
	$(".jieguowenzitishi").text("正在查询中...");
}
//上拉加载更多
function shanglajiazaigengduo(){
	
	$(".fanyezhengzaichaxun").css("visibility","hidden");
	$(".jieguowenzitishi").text("↑  上拉加载更多   ↑");
}
//没有更多数据
function meiyougengduo(){
	$(".fanyezhengzaichaxun").css("visibility","hidden");
	$(".jieguowenzitishi").text("没有更多数据。");
}








//在页面添加一个信息
function xianshiyigexinxi(model){
	
	var content = model.content;
	
	//将关键字改为红色
	content = dianLiangGuanjianzi(content);
	
	//判断日期是今天还是昨天
//	alert("前天："+GetDateStr(-2)); 
//	alert("<br />昨天："+GetDateStr(-1)); 
//	alert("<br />今天："+GetDateStr(0)); 
//	alert("<br />明天："+GetDateStr(1)); 
//	alert("<br />后天："+GetDateStr(2)); 
//	alert("<br />大后天："+GetDateStr(3)); 
	
	var natian = "";
	if(model.faSongShiJian.indexOf(GetDateStr(0))>-1){
		natian = "<span class='jintian'>今天<span>";
	}else if(model.faSongShiJian.indexOf(GetDateStr(-1))>-1){
		natian = "<span class='zuotian'>昨天<span>";
	}else if(model.faSongShiJian.indexOf(GetDateStr(-2))>-1){
		natian = "<span class='qiantian'>前天<span>";
	}else if(model.faSongShiJian<GetDateStr(-2)){
		natian = "<span class='gengzao'>更早<span>";
	}
	
	console.log("比对时间");
	console.log(model.faSongShiJian);
	console.log(GetDateStr(-2));
	console.log(model.faSongShiJian<GetDateStr(-2));
	
	
	var html  ="<div class='yigexinxi'>";
			html +="<div class='xinxi'>";
				html +="<div class='nicheng'>";
					html +=model.faSongZheNiCheng;
				html +="</div>";
				html +="<div class='QQ'>";
					html +="QQ:"+model.faSongZheQQ;
				html +="</div>";
				html +="<div class='shijian'>";
					html +=model.faSongShiJian+natian;
				html +="</div>";
			html +="</div>";
			html +="<div class='xinxineirong'>";
				html += content;
			html +="</div>";
		html +="</div>";
	tianjiaNeirongqu(html);
}
//点亮字符串内的关键字，并返回点亮后的字符串
function dianLiangGuanjianzi(content){
	
	//循环替换所有关键字，携带点亮样式标签
	for(i in guanjianzirr){
		for(j in guanjianzirr[i]){
			var guanjianzi = guanjianzirr[i][j];
			//guanjianzi.split("%");
			for(p in guanjianzi){
				if(guanjianzi[p]!=null && guanjianzi[p]!=""){
					//加上斜杠和斜杠g是正则表达式，表示全局替换，否则只会替换一个
//					content = content.replace(/明/g,"<span class='dianliangGuanjianzi'>明</span>");
					content = content.replace(new RegExp(guanjianzi[p], 'g'),"<span class='dianliangGuanjianzi'>"+guanjianzi[p]+"</span>");
				}
			}
		}
	}
	
	//返回
	return content;
}

//分析关键字结果列表
var guanjianzirr;
//查询一页
var yema = 0;
function chaxunyiye(me){
	
	
	
	//翻页程序----------------
	if(fanyeType == 0){
		fanyeType = 1;//关闭翻页
	}else{
		//console.log("正在查询,请不要重复查询");
		return;
	}
	//翻页程序----------------
	
	
	//console.log("第"+yema+"页");
	var options={
			url:"getPincheListAndTiaojian.action",
			type:"POST",
			dataType:"json",
			data:{
	            "yema":yema
	        },
			success:function(msg){
				//console.log(msg);
				guanjianzirr = msg.fenxiGuanjianci;
				xianshiliebiao(msg.list,me);
				
			},
			error:function(){
				alert("获取信息失败，或者网络断开");
			}
	}
	$('#f1').ajaxSubmit(options);
}


//判断是否是电脑
function IsPC() {
	var userAgentInfo = navigator.userAgent;
	var Agents = [ "Android", "iPhone", "SymbianOS", "Windows Phone", "iPad",
			"iPod" ];
	var flag = true;
	for (var v = 0; v < Agents.length; v++) {
		if (userAgentInfo.indexOf(Agents[v]) > 0) {
			flag = false;
			break;
		}
	}
	return flag;
}


//dropload对象
var publicMe = null;
var dropload = null;
$(document).ready(function() {
	
	
	//如果是电脑则遮挡页面
	if(IsPC()){
		//console.log(1);
		//隐藏遮罩层
//		$(".zhazhaoceng").hide();
		//如果域名是250xyz
		var host = window.location.host;
		
		console.log(host);
		console.log("判断是否是正式服务");
		
		
		if(host.indexOf("250xyz.xyz")>=0){
			//隐藏遮罩层
			$(".zhazhaoceng").hide();
		}else{
			
		};
	}else{
		//console.log(2);
		//隐藏遮罩层
		$(".zhazhaoceng").hide();
	}
	
	
	
	
	//默认加载第一页
	xiugaichaxun();
	
});

//当修改查询条件时，自动查询
function xiugaichaxun(){
	
	yema = 0;
	//清空内容区域
	qingkongNeirongqu();
	
	//显示正在查询
	$(".chaxunjiazaikuang").show();
	//console.log(6);
	//查询内容
	chaxunyiye();
}

//刷新页面自动显示全部
function shuaxin(){
	//console.log("刷新");
	location.reload(true);
}

//判断搜索框回车键
function EnterPress(e){ //传入 event 
	var e = e || window.event; 
	if(e.keyCode == 13){ //13代表回车符
		//TO_DO按下回车键后的动作
		e.keyCode = 0;//屏蔽回车键
        e.returnValue = false;
        xiugaichaxun();
        
        //让文本框失去焦点，这样输入法就会隐藏
        var input = document.getElementById("zidingyiguanjianzi");
        input.blur();
	}
} 

//获取今天、昨天、明天、等日期
function GetDateStr(AddDayCount) { 
	var dd = new Date(); 
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
	return dd.format('yyyy-MM-dd'); 

//	alert("前天："+GetDateStr(-2)); 
//	alert("<br />昨天："+GetDateStr(-1)); 
//	alert("<br />今天："+GetDateStr(0)); 
//	alert("<br />明天："+GetDateStr(1)); 
//	alert("<br />后天："+GetDateStr(2)); 
//	alert("<br />大后天："+GetDateStr(3)); 

} 



/** 
 *  对Date的扩展，将 Date 转化为指定格式的String 
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，  
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)  
 * 例子：  
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423  
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18  
 */  
Date.prototype.format = function (fmt) { //author: meizz   
    "use strict";  
    var o = {  
        "M+": this.getMonth() + 1, //月份   
        "d+": this.getDate(), //日   
        "h+": this.getHours(), //小时   
        "m+": this.getMinutes(), //分   
        "s+": this.getSeconds(), //秒   
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
        "S": this.getMilliseconds() //毫秒   
    };  
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    for (var k in o)  
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
    return fmt;  
};  


