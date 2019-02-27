<%@page import="com.clint.yinyue_xiazai.util.yinyueUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	
	String basePath = request.getScheme() + "://" + request.getServerName() + "/";
	
	if(basePath.indexOf(yinyueUtil.basePath)>=0){
		basePath = basePath.replace(yinyueUtil.basePath, "www."+yinyueUtil.basePath);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>${yinyueXiazai.gequ_name} 免费下载 原版 高清 mp3 音乐250 歌曲免费下载音乐网</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="${yinyueXiazai.gequ_name}歌曲试听下载">
<meta http-equiv="description" content="${yinyueXiazai.gequ_name}歌曲试听下载">



											<link href="/yinyue_xiazai/xiazaiByid/xiazai.css?a=3" type="text/css"
												rel="stylesheet">
												
												

<jsp:include page="/yinyue_xiazai/public/head/head.jsp"></jsp:include>

<script src="public/js/jquery-3.3.1.min.js"></script>

</head>

<body>
	
<jsp:include page="/yinyue_xiazai/public/bodyTop/bodyTop.jsp"></jsp:include>






									<div id="t_10001_1440be0070102yk6w" class="blog_title">
										<a target="_blank">${yinyueXiazai.gequ_name}歌曲下载</a>
									</div>
									
									<div class="shiting">
										试听:
									</div>
									
									<audio src="" id="audio" controls="controls"></audio>
									
									<div class="xiazaidizhi">
										下载地址 :（可直接点击下载，或复制到迅雷下载）
									</div>
									<a class="xiazaidizi" href="" target="_blank"></a> 
									
									

								<div style="display: none;" class="blogzz_zzlist borderc"
									id="ff_1440be0070102yk6c"></div>
								<div class="SG_j_linedot"></div>
								<div
									favmd5="{&quot;1440be0070102yk6w&quot;:&quot;37f0c064f1a6c026fc52fdfdea700115&quot;,&quot;1440be0070102yk6i&quot;:&quot;ea4114e1a665af97fa53cf1bf82e6324&quot;,&quot;1440be0070102yk6h&quot;:&quot;1cfe240745d2ce79b6f19579dede0571&quot;,&quot;1440be0070102yk6e&quot;:&quot;a8a35a4340ee4b3c6b3180db13a93986&quot;,&quot;1440be0070102yk6d&quot;:&quot;63238fb0e04e90ce9a24df124c3b754a&quot;,&quot;1440be0070102yk6c&quot;:&quot;7ee9959b931a5ad73aacf03a8053fd09&quot;}"
									classid="0" pagesize="10" total="6" id="pagination_10001"
									class="SG_page" style="display: none;"></div>
							
							<div class="wei">
								${yinyueXiazai.weiYuanChuang}
							</div>
							
							
							
							
							
							
							
							
<jsp:include page="/yinyue_xiazai/public/bodyBotom/bodyBotom.jsp"></jsp:include>

<script type="text/javascript">
	
	$(document).ready(function(){
		$.ajax({
	        url:"/yinyue_xiazai/gengxin_url.action",
	        type:"post",
	        async:true,
	        dataType:"text",
	        data:{
	            "id":${yinyueXiazai.id}
	        },
	        success:function (data){
	        	console.log("成功");
	        	
	        	$("#audio").attr("src",data);
	        	$(".xiazaidizi").attr("href",data);
	        	$(".xiazaidizi").text(data);
	        },
	        error:function (){
	        	console.log("获取下载地址失败，请联系客服");
	        }
	    })
	});

</script>

</body>
</html>
