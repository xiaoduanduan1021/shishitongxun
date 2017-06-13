<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.lang.String"%>
<%@page import="com.clint.sysuser.model.SysUser"%>
<%
	String pathtop = request.getContextPath();
	String basePathtop = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	SysUser sysuser = (SysUser) request.getSession().getAttribute("sysUser");
%>

<!-- 统计代码 -->
<%@ include file="/cs.jsp" %>
<%CS cs = new CS(1259904564);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 统计代码 -->

	<!-- 移动端日志输出工具 -->
	<!-- <script type="text/javascript" src="public/vConsole-2.2.0/dist/vconsole.min.js"></script> -->
	<!-- 移动端日志输出工具 -->

<link rel="stylesheet" type="text/css" href="shishi_tongxun/home/public/topBootom/css+js/top.css">
	<div class="sysuserMessage">
		<% 
		if(sysuser == null){
			%>
				您还未登录
			<%
		}else{
			%>
				欢迎：<%=sysuser.getLoginName() %>
			<%
		}%>
	</div>
	<header>
		<a href="http://1010keke.com/"> 
			<div class="logo">
				<span class="a">1010</span>	
				<span class="b">keke</span>	
				<span class="c">.com</span>	
			</div>
			<h1>时时刻刻、免费通讯平台</h1>
		</a>
		<nav class="main">
			<ul>
			<%
			
				String anniuId = (String)request.getParameter("anniuId"); 
				String shouye = "";
				String tiyan = "";
				String anzhuang = "";
				String fankui = "";
				String juanzhu = "";
				if(null == anniuId || anniuId.equals("1")){
					shouye = "background-color:#A0A600;";
				}else if(anniuId.equals("2")){
					tiyan = "background-color:#A0A600;";
				}else if(anniuId.equals("3")){
					anzhuang = "background-color:#A0A600;";
				}else if(anniuId.equals("4")){
					fankui = "background-color:#A0A600;";
				}else if(anniuId.equals("5")){
					juanzhu = "background-color:#A0A600;";
				}
			%>
				<li id="home"><a style="<%=shouye%>" href="index.jsp?anniuId=1">首页</a></li>
				<li id="tiyan"><a style="<%=tiyan%>" href="shishi_tongxun/home/jsp/experience.jsp?anniuId=2">体验</a></li>
				<li id="anzhuang"><a style="<%=anzhuang%>" href="shishi_tongxun/home/jsp/install.jsp?anniuId=3">安装</a></li>
				<li id="denglu"><a href="javascript:jinrukefuduan();">注册/登录</a></li>
				<li id="fankui"><a style="<%=fankui%>" href="shishi_tongxun/home/jsp/fankui.jsp?anniuId=4">反馈</a></li>
				<li id="juanzhu"><a style="<%=juanzhu%>" href="shishi_tongxun/home/jsp/juanzhu.jsp?anniuId=5">捐助</a></li>
			</ul>
		</nav>
	</header>
<script type="text/javascript">
	var base = '<%=basePathtop%>';
</script>

<!-- 客服访问代码 -->
<script type="text/javascript" src="shishi_tongxun/home/public/js/open.js?a=<%=Math.random()%>"></script>
<div style=" position: fixed; right: 11px; top: 45%;cursor:hand;">
	<img onclick="jinrugukeduan(1);" width="110" src="shishi_tongxun/home/img/kefuanniu1.jpg">
</div>


<!-- 访问量统计 -->
<script type="text/javascript">
	//账户id此行代码应该在用户页面
	var sysuserid=1;
</script>
<script type="text/javascript" src="public/js/jquery-1.10.2.min.js">
</script>
<script type="text/javascript" src="shishi_tongxun/fangwenJilu/jiLu.js">
</script>
<!-- 访问量统计 -->




<script type="text/javascript">
	//判断如果是手机则提示跳转到移动端页面
	//判断是否是电脑
	function IsPC() {
	  var userAgentInfo = navigator.userAgent;
	  console.log(userAgentInfo);
	  var Agents = ["Android", "iPhone",
	              "SymbianOS", "Windows Phone",
	              "iPad", "iPod"];
	  var flag = true;
	  for (var v = 0; v < Agents.length; v++) {
	      if (userAgentInfo.indexOf(Agents[v]) > 0) {
	          flag = false;
	          break;
	      }
	  }
	  return flag;
	}
	if(IsPC()){
		console.log('这是电脑top');
	}else{
		console.log('这是手机top');
		//判断是否是域名
		var url = window.location.href;
		console.log(url);
		var alei = url.indexOf("www.1010keke.com");
		console.log(alei);
		var blei = url.indexOf("1010keke.com");
		console.log(blei);
		if(alei>-1){
			url = url.replace("www.1010keke.com","121.43.108.229");
			console.log('替换完成');
			console.log(url);
			window.location.href=url;
		}else if(blei>-1){
			url = url.replace("1010keke.com","121.43.108.229");
			console.log('替换完成');
			console.log(url);
			window.location.href=url;
		}

	}
</script>