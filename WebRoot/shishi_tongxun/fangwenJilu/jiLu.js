
var sysuser;
if (sysuserid == null || sysuserid == "undefined") {
	sysuser = 0;
}else{
	sysuser = sysuserid;
}

//请求的官网地址
var baseUrl = "http://192.168.1.102:8080/shishitongxun2014";
//var baseUrl = "http://1010keke.com";

console.log("生成页面唯一id对于每个页面每次访问的唯一id");
var yemianid = getWeiyiId();

console.log("获取前一个页面url");
var sourceUrl = document.referrer;
console.log(sourceUrl);
console.log("判断是不是第一次");
var one = 2; //1表示第一次2表示再次访问

var accessSessionId = localStorage.getItem("accessSessionId"); //获取本地accessSessionId的值
console.log(accessSessionId);

if(accessSessionId == null || accessSessionId == 'undefined') {
	one = 1;
	console.log("生成唯一sessionid");
	accessSessionId = getWeiyiId();
	console.log(accessSessionId);
	localStorage.setItem("accessSessionId", accessSessionId); //设置本地accessSessionId的值
}

console.log("进入页面记录");
jilu(1, accessSessionId, one);
console.log("页面关闭记录");
//页面关闭事件
window.onbeforeunload = onbeforeunload_handler;

function onbeforeunload_handler() {
	jilu(2, accessSessionId, 2);
	return;
}

//n:1表示进入页面2表示离开页面
function jilu(n, asid, isone) {
	var datetime = new Date().getTime();
	
/*	console.log("请求");
	var img = "<img "+
		"style='display: none;'"+
		"width='0' height='0'"+
		"src='" + baseUrl + "/addAllComEvent.action?" +
		"in=" + n +
		"&sourceUrl=" + sourceUrl +
		"&accessSessionId=" + asid +
		"&one=" + isone +
		"&title=" + document.title +
		"&thisms="+datetime+
		"&yemianid="+yemianid+
		"&sysuser="+sysuserid+
		"&a=" + Math.random() + "'/>";
	document.body.innerHTML += img;
	console.log("请求完毕");*/
	
	
	$.ajax({
		url: baseUrl + "/addAllComEvent.action",
		type: "post",
		async: true, //true异步，false同步，默认异步
		dataType: "json",
		data: {
			"in": n,
			"sourceUrl": sourceUrl,
			"accessSessionId": asid,
			"one": isone,
			"title": document.title,
			"thisms": datetime,
			"yemianid": yemianid,
			"sysuser": sysuser
		},
		success: function(data) {
			console.log("SUCCESS");
			console.log(data);
			accesslogId = data.accessLog.id;
		},
		error: function() {
			console.log("error");
		}
	})
	
}

//生成唯一id
function getWeiyiId(){
	var datetime = new Date().getTime();
	var random = Math.random();
	weiyiid = datetime + "," + random;
	return weiyiid;
}
