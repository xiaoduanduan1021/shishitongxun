<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 统计代码 -->
<%@ include file="/cs.jsp" %>
<%CS cs = new CS(1259904564);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
<!-- 统计代码 -->