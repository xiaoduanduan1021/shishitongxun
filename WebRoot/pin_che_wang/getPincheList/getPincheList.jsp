<%@page import="util.string.StringCode"%>
<%@page import="com.clint.pinchewang.util.PincheUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>信息查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

	<link rel="stylesheet" type="text/css" href="pin_che_wang/getPincheList/pincheList.css?a=<%=Math.random()%>">

	<script type="text/javascript" src="public/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="public/js/jquery.form.js"></script>
	<script type="text/javascript" src="pin_che_wang/getPincheList/getPincheXinxi.js?a=<%=Math.random()%>"></script>
	<!-- <script type="text/javascript" src="public/vConsole-2.2.0/dist/vconsole.min.js"></script> -->

  </head>
  
  <body>
  	<div class="zhazhaoceng"></div>
  
  	<!-- 页眉区域 -->
  	<div class="yemei">
  		<a class="lianxikefu" href="mqqwpa://im/chat?chat_type=wpa&uin=2687017178&version=1&src_type=web&web_src=oicqzone.com">点击联系客服QQ:2687017178</a>
  	</div>
  	<!-- 条件区域 -->
  	<div class="tiaojian">
  		<form id="f1" action="">
  		
		  	<!-- 搜索框 -->
		  	<img alt="" class="fangdajing" src="pin_che_wang/getPincheList/img/search-icon.png">
  			 <input onkeypress="EnterPress(event)" type="text" class="guanjianzi" name="guanjianzi" id="zidingyiguanjianzi" placeholder="多个关键字请用空格隔开例如：明天 管接">

			
			
			
			<div class="diquhang">
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="radio" value="找人" name="leixing">  
				    <div class="fangxiang--radioDiv">车找人</div>
				</label>  
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="radio" value="找车" name="leixing">  
				    <div class="fangxiang--radioDiv">人找车</div>
				</label>  
	
				<div onclick="shuaxin();" class="quanbuInput">全部</div>
			</div>
			
			
			
			
			<div class="diquhang">
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="radio" value="到大名" name="fangxiangRadio">  
				    <div class="fangxiang--radioDiv">北京到大名</div>
				</label>  
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="radio" value="到北京" name="fangxiangRadio">  
				    <div class="fangxiang--radioDiv">大名到北京</div>
				</label>  
	
			</div>
			
			
			
			<div class="shijianhang">
				<div class="tiaojianwenzi">日期:</div>
				<select class="xialaliebiao" name="riqi">
				  <option value ="">不限</option>
				  <%
				  	List <String> list7ri = StringCode.testHao(7); 
				  	for(int i=0;i<list7ri.size();i++){
				  		String ri = list7ri.get(i);
				  		
				  		%><option value ="<%=ri %>号"><%=ri %>号<%
				  			if(i==0){
				  				out.print("今天");
				  			}else if(i==1){
				  				out.print("明天");
				  			}else if(i==2){
				  				out.print("后天");
				  			}
				  		%></option><%
				  	}
				  %>
				  
				</select>
				<div class="tiaojianwenzi">时间:</div>
				<select class="xialaliebiao" name="shangwuxiawu">
				  <option value="">不限</option>
				  <option value="早上">早上</option>
				  <option value="上午">上午</option>
				  <option value="下午">下午</option>
				  <option value="晚上">晚上</option>
				</select>
			</div>
			
			
			
			<div class="diquhang">
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="checkbox" value="不超员" name="shushidu">  
				    <div class="fangxiang--radioDiv">不超员</div>
				</label>  
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="checkbox" value="小车" name="shushidu">  
				    <div class="fangxiang--radioDiv">小车</div>
				</label>  
				<label class="fangxiang--label">  
				    <input class="fangxiang--radio" type="checkbox" value="私家车" name="shushidu">  
				    <div class="fangxiang--radioDiv">私家车</div>
				</label>
			</div>
			

	  		<input type="button" onclick="xiugaichaxun();" class="sousuoanniu" value="搜索">
	  		
  		</form>
  	</div>
  	
  	<!-- 内容列表 -->
  	<div class="content">
  	</div>
  	
  	<!-- 翻页底部提示 -->
  	<div class="jieweitishi">
  		<img src='pin_che_wang/getPincheList/img/lodding.gif' class='fanyezhengzaichaxun'>
  		<div class="jieguowenzitishi"></div>
  	</div>
  	
  	
  	<!-- 查询正文提示 -->
  	<div class='chaxunjiazaikuang'>
		<img src='pin_che_wang/getPincheList/img/lodding.gif' class='zhengzaichaxun'>
		<div class='chaxunzhong'>查询中...</div>
	</div>
		
		
  </body>
  
  <script type="text/javascript">
  	//每页显示多少条
  	var pagesize = <%=PincheUtil.pagesize%>;
  	
  </script>
</html>
