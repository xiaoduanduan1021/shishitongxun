
//显示正在查询

//将列表显示在页面
function xianshiliebiao(list,me){
	
	$(".chaxunjiazaikuang").hide();
	
	//遍历列表
	for(var i in list){
		xianshiyigexinxi(list[i])
	}
	
	console.log("条数："+list.length);
	//判断是否还有信息，如果没有则显示已经没有更多信息
	if(list.length==0){
		console.log("无数据");
		if(me!=null){
			//me.lock();
			me.noData();
			me.resetload();
		}
	}else{
		yema++;
		if(me!=null){
			me.resetload();
		}
	}
	
	if(publicMe!=null){
		console.log("重置数据");
		publicMe.resetload();
	}
	
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


//修改结尾信息
function jieweitishi(text){
	$(".jieweitishi").txt(text);
}

//在页面添加一个信息
function xianshiyigexinxi(model){
	var html  ="<div class='yigexinxi'>";
			html +="<div class='xinxi'>";
				html +="<div class='nicheng'>";
					html +=model.faSongZheNiCheng;
					html +=model.id;
				html +="</div>";
				html +="<div class='QQ'>";
					html +=model.faSongZheQQ;
				html +="</div>";
				html +="<div class='shijian'>";
					html +=model.faSongShiJian;
				html +="</div>";
			html +="</div>";
			html +="<div class='xinxineirong'>";
				html +=model.content;
			html +="</div>";
		html +="</div>";
	tianjiaNeirongqu(html);
}





//查询一页
var yema = 0;
function chaxunyiye(me){
	console.log("第"+yema+"页");
	var options={
			url:"getPincheListAndTiaojian.action",
			type:"POST",
			dataType:"json",
			data:{
	            "yema":yema
	        },
			success:function(msg){
				console.log(msg);
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
$(document).ready(function() {
	
	
	//如果是电脑则遮挡页面
	if(IsPC()){
		console.log(1);
	}else{
		console.log(2);
		//隐藏遮罩层
		$(".zhazhaoceng").hide();
	}
	
	
	
	
	//默认加载第一页,dropload会自动执行一次
	
	console.log(3);
	xiugaichaxun(null);
	//判断翻页，屏幕下拉到最下方触发
	$("body").dropload({					
		scrollArea : window,
		autoLoad:false,
		loadDownFn : function(me) {
			publicMe = me;
			console.log(4);
			chaxunyiye(me);
		},
		loadUpFn : function(me) {//上拉刷新
			console.log(5);
			xiugaichaxun(me);
		},
		error : function(xhr, type) {
			//alert("Ajax error!");
			// 即使加载出错，也得重置
			me.resetload();
		}
	})
	
});

//当修改查询条件时，自动查询
function xiugaichaxun(me){
	
	yema = 0;
	//清空内容区域
	qingkongNeirongqu();
	
	//显示正在查询
	$(".chaxunjiazaikuang").show();
	console.log(6);
	//查询内容
	chaxunyiye(me);
}
