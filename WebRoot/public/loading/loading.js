//在页面中增加loding
var loadingstring = "  <div class=\"loadingceng\" id=\"loadingceng\">" +
					"  	<div class=\"loadingclass\" id=\"loadingwenzi\">loading</div>" +
					"  </div>" +
					"";
//加载loading层
document.write(loadingstring);
//循环动画
var dianshuliang = 1;
function jiazaidonghua(){
	dian = "";
	for(var i = 0; i<dianshuliang;i++){
		dian+=".";
	}
	document.getElementById("loadingwenzi").innerHTML="loading"+dian;
	if(dianshuliang == 3){
		dianshuliang = 0
	}else{
		dianshuliang++;
	}
}

//启动动画
setInterval("jiazaidonghua()", 500);

//判断是否加载完成
document.onreadystatechange = subSomething;// 当页面加载状态改变的时候执行这个方法.
function subSomething(){
	if(document.readyState == "complete"){ // 当页面加载状态
		//页面加载完成
		//隐藏加载层
		//测试5秒后停止
//		setTimeout(function(){document.getElementById("loadingceng").style.display="none";},3000);
		document.getElementById("loadingceng").style.display="none";
	}
}