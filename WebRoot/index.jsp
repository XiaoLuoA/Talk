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
    
    <title>颜色转换</title>
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
    <div id="app">
     <!--   <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
        <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
        <iframe src="./demo.html" style="width:600px; height:600px;"></iframe>
	-->  
	
	<a href="page/java/java.jsp"  target="_blank">java交流</a>
	<a href="page/master/master.jsp"  target="_blank">考研交流</a>
	<a href="page/found/found.jsp"  target="_blank">招领启事</a>
	
    </div>

</body>
</html>
