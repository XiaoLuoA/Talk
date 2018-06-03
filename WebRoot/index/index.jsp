<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>TalkTalk</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="index/index.css">
	
  </head>
  
  <body>
    <div class="container">
      <div class="top"></div>
      <div class="navi">
         <div class="n1"><a href="">首页</a></div>
          <div class="n2"><a href="">个人中心</a></div>
          <div class="n3"><a href="">消息中心</a></div>
          <div class="n4"><a href="">首页</a></div>
      </div>
    <%@include file="foot.html" %>  
    </div>
  </body>
</html>
