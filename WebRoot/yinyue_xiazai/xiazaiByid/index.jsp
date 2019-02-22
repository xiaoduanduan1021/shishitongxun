<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "---------------www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>${yinyueXiazai.gequ_name} mp3免费下载高清</title>
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8,chrome=1">

												
												
											<link href="/yinyue_xiazai/xiazaiByid/xiazai.css?a=3" type="text/css"
												rel="stylesheet">
												
												

<jsp:include page="/yinyue_xiazai/public/head/head.jsp"></jsp:include>



</head>

<body>
	
<jsp:include page="/yinyue_xiazai/public/bodyTop/bodyTop.jsp"></jsp:include>






									<div id="t_10001_1440be0070102yk6w" class="blog_title">
										<a target="_blank">${yinyueXiazai.gequ_name} mp3免费下载高清</a>
									</div>
									
									<div class="shiting">
										试听:
									</div>
									
									<audio src="${yinyueXiazai.xiazai_dizhi}" controls="controls"></audio>
									
									<div class="xiazaidizhi">
										下载地址 :（可直接点击下载，或复制到迅雷下载）
									</div>
									<a class="xiazaidizi" href="${yinyueXiazai.xiazai_dizhi}" target="_blank">${yinyueXiazai.xiazai_dizhi}</a> 
									
									

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



</body>
</html>
