<%@ page language="java" import="java.util.*,com.xiaoluo.model.User" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>navi</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/navi.css">

  </head>
  
  <body>
 <% User user=(User)session.getAttribute("user"); %> 
   
    <div class="top"></div>
      <div class="navi">
          <div class="n1"><a href="index.jsp">首页</a></div>
          <div class="n2"><a href="myCenter/myCenter.jsp">个人中心</a></div>
          <div class="n3"><a href="reg/reg.jsp">注册</a></div>
          <div class="n4">
        <%if(user==null) {%>
          <a href="login/login.jsp">登录</a>
          <%}else{ %>
                 <a href="findex.jsp">退出登录</a><%} %>
          </div>
      </div>
  </body>
</html>
