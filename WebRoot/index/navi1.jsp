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
	
	<link rel="stylesheet" type="text/css" href="css/navi1.css">
	

  </head>
  
  <body>
    <%  User user=(User)session.getAttribute("user"); %>
   
    <div class="top"></div>
      <div class="navi1">
          <div class="n11"><a href="index.jsp"><span class="span">首页</span></a></div>
          <div class="n22"><a href="javascript:void(0);" onclick="toOther()"><span class="span">个人中心</span></a></div>
          <div class="n33"><a href="reg/reg.jsp"><span class="span">黑名单</span></a></div>
          <div class="n44">
        <%if(user==null) {%>
          <a href="login/login.jsp"><span class="span">登录</span></a>
          <%}else{ %>
                <a href="findex.jsp"><span class="span">退出登录</span></a>  <%} %>
          </div>
      </div>
  </body>
</html>
