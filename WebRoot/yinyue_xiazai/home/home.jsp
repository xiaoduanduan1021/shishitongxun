<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>音乐下载</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
	<link rel="stylesheet" type="text/css" href="yinyue_xiazai/home/home.css?a=<%=Math.random()%>">
	<script type="text/javascript" src="public/js/jquery-1.10.2.min.js"></script>

<!-- 百度统计 -->
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?c63ca1c34af49d647022ac5e60e130ac";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

<!-- 百度统计 -->

</head>

<body>
		<div class="shuoming">
			
			<div class="biaoti1">本站可以免费下载网上的音乐，只要能在浏览器打开试听地址即可。</div>
			
			<div class="shuomingbufen">
				<div class="shumingzuo">
					<div class="biaoti2">操作步骤：</div>
					<div class="wenzihang">1：在浏览器打开酷狗音乐网站，或者其他音乐网站</div>
					<div class="wenzihang">2：搜索或找到你要的音乐，点击进入试听播放页面</div>
					<div class="wenzihang">3：复制地址栏的地址，粘贴到下面输入框</div>
					<div class="wenzihang">4：点击确定</div>
					<div class="wenzihang">4：稍等片刻在下面列表就会显示下载地址</div>
					<div class="wenzihang">5：将下载地址复制到迅雷，或者浏览器直接访问，或者其他下载工具进行下载</div>
				</div>
				<div class="shuomingyou">
					<div class="biaoti2">已纳入快速分析网站（经测试这些网站音乐是可以下载滴，如果有问题请联系客服）：</div>
					<div class="wenzihang">酷狗：<a  target="_blank" href='http://www.kugou.com'>www.kugou.com</a></div>
					<!-- 
						<div class="wenzihang">千千音乐：<a  target="_blank" href='http://music.taihe.com/'>music.taihe.com</a></div>
					 -->
					
					<div class="wenzihang">IK123深港DJ：<a  target="_blank" href='http://www.ik123.com'>www.ik123.com</a></div>
					<div class="wenzihang">百年舞曲网DJ：<a  target="_blank" href='http://www.190757.com/'>www.190757.com</a></div>
					<div class="wenzihang">DJ耶耶网：<a  target="_blank" href='http://www.djye.com/'>www.djye.com</a></div>
					<div class="wenzihang">Dj嗨嗨网：<a  target="_blank" href='http://www.djkk.com/'>www.djkk.com</a></div>
					<div class="wenzihang">未纳入的请联系客服免费纳入</div>
					<div class="wenzihang">联系客服可定制网站音乐批量下载任务，例如批量下载酷狗排行榜前五百</div>
				</div>
			</div>
			
			
			<div class="shuruweizhi">
				
				<textarea class="shuruyu" id="url" placeholder="请输入播放地址例如：https://www.kugou.com/song/#hash=37AC8A1C5C2FC33E43749C22042672F7&album_id=14586035" ></textarea>
					<br>
				<input type="button" onclick="tijiao();" class="queren" value="确认">
				
			</div>
			
			<div class="biaoti2">
				已提交的：（最近100条）
			</div>
	
	
			<table border="1" cellspacing="0" class="liebiao" id="liebiao" style="border-collapse:collapse">
			</table>
	
	
		</div>
	
	
	<div class="kefu">
		<img alt="" onclick="yincang_kefu();" class="guanbi" src="yinyue_xiazai/home/img/guanbi.jpg">
		<div class="kefu_shuoming">扫描二维码加客服，接收免费福利新功能通知</div>
		<img alt="" class="erweima" src="yinyue_xiazai/home/img/erweima.jpg">
	</div>
	
	
	
	
	
	
	
	
	
	
	<script type="text/javascript">
	
	
		console.log(localStorage.uuid);
		console.log(localStorage.uuid==null);
		
		
		//存入uuid
		if(localStorage.uuid==null){
			localStorage.uuid="<%=UUID.randomUUID().toString()%>";
		}
	
		//提交试听地址
		function tijiao(){
			
		
			$.ajax({
		        url:"yinyue_xiazai/saveShiting.action",
		        type:"post",
		        async:true,//true异步，false同步，默认异步
		        dataType:"json",
		        data:{
		            "shiting_url":$("#url").val(),
		            "uuid":localStorage.uuid,
		        },
		        success:function (data){
		        	console.log("提交完成");
		        	if(data.type == 'noNull'){
		        		alert("请输入网址");
		        	}else if(data.type == 'ok'){
			        	huoqu_liebiao();
		        	}
		        },
		        error:function (){
		        	alert("提交失败，请联系管理员");
		        }
		    })
			$("#url").val("");
		}
		
		//获取已提交列表
		function huoqu_liebiao(){
			$.ajax({
		        url:"yinyue_xiazai/huoquLiebiao.action",
		        type:"post",
		        async:false,//true异步，false同步，默认异步
		        dataType:"json",
		        data:{
		            "uuid":localStorage.uuid,
		        },
		        success:function (data){
		        	console.log(data);
		        	var data = data.data;
		        	console.log(data);
		        	
		        	
		        	var html = "";
		        	
		        	
		        	html+="<tr class=\"biaotihang\">";
		        	html+="<td>试听地址</td>";
		        	html+="<td>歌曲名称</td>";
		        	html+="<td>下载地址</td>";
		        	html+="<td>提交时间</td>";
		        	html+="<td>是否完成</td>";
		        	html+="</tr>";
			
			
		        	for(var i in data){
		        		console.log(i);
		        		console.log(data[i]);
		        		var yinyue = data[i];
		        		html+="<tr class=\"neironghang\">";
		        		
		        		html+="<td>";
		        		html+="<a href="+yinyue.shiting_url+" target=\"_blank\">"+yinyue.shiting_url+"</a>";
		        		
		        		html+="</td>";
		        		
		        		html+="<td>"+yinyue.gequ_name+"</td>";
		        		
		        		html+="<td>";
		        		html+="<a href="+yinyue.xiazai_dizhi+" target=\"_blank\">"+yinyue.xiazai_dizhi+"</a>";
		        		html+="<audio src=\""+yinyue.xiazai_dizhi+"\" controls=\"controls\">";
		        		html+="</audio>";
		        		html+="</td>";
		        		
		        		html+="<td>"+yinyue.datetime+"</td>";
		        		
		        		var statu = "正在分析，预计10秒钟。请10秒后刷新页面";
		        		
		        		if(yinyue.status == 1){
		        			statu = "已完成";
		        		}
		        		if(yinyue.status == 2){
		        			statu = "未分析出，请联系管理员，修复，感谢";
		        		}
		        		
		        		html+="<td>"+statu+"</td>";
		        		html+="</tr>";
		        	}
					
					$("#liebiao").html(html);
					
		        },
		        error:function (){
		        	alert("获取列表失败");
		        }
		    })
		}
		
		
		
		$(document).ready(function(){
			//默认获取已提交列表
			huoqu_liebiao();
		});
		
	//自动刷新	
//		setInterval(function(){
//			huoqu_liebiao();
//		},20*1000);
		
		
		
		var userAgent = "<%=request.getHeader("user-agent")%>";
	  	if(userAgent.indexOf("Android") != -1){
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
	  	}else if(userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPad") != -1){
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
		    alert("！！！请在电脑上打开，手机网站暂时无法分析");
	  	}else{
	  	}

	  	
	  	//隐藏客服二维码
	  	function yincang_kefu(){
	  		$(".kefu").hide();
	  	}
	</script>
</body>
</html>
