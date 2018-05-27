<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>master.jsp</title>
    
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
   <div id="name"><%= Math.random() %></div>
        <div class="content">
            <div style="width: 500px; height: 500px; background: #d2d2d2; border: 1px solid #2d2d2d;overflow: auto;" id="contentId"></div>
        </div>
        <br/>

        <input name="text" id="textId" value="i love t-io" />
        <button id="sendButton" onClick="send()">Send</button>
        <button id="clearButton" onClick="clearMsg()">Clear</button>
    </div>
  </body>
  
  <!-- 组件js，和业务不相关的 -->
<script src="page/tio/tiows.js"></script>


<!-- 和业务相关的js，业务需要修改 -->
<script src="page/master/masterHandler.js"></script>
<script src="page/master/master.js"></script>
</html>
