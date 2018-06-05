<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Talk A Talk</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
 
  <s:if test="#session.user.name!=null">
  	 <a href="userinfoPage"><s:property value="#session.user.name"/></a>
  </s:if>
  
    <div id="app">
    
     <!--   <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
        <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
        <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
	-->  
	
	<a href="chatjava.action"  target="_blank">java交流</a>
	<a href="chatmaster.action"  >考研交流</a>
	<a href="chatfound.action"  target="_blank">招领启事</a>
	<a href="userloginPage.action">登录</a>
    </div>
</body>
</html>
