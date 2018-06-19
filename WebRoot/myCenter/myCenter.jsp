<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/myCenter.css">
	<!-- <link href="CSS/reset.css" rel="stylesheet" type="text/css"> -->

  </head>
  
  <body>  
   <div class="container">    
    <jsp:include page="../index/navi.jsp"/>
    <div class="center">   
          <div class="left">
            <ul>
               <li><a href="myCenter/myCenter.jsp">个人信息</a></li>
               <li><a href="myCenter/messageCenter.jsp">消息中心</a></li>
               <li><a href="myCenter/changeInformation.jsp">修改信息</a></li>
            </ul>
            </div>
        <div class="myInformation"> 
         
          <div class="pic">
          <img class="pic1" src="<s:property value="#session.user.pic"/>"/>
          
          </div>
          <div class="information">
           <ul>
            <li> 用户名：<s:property value="#session.user.name"/></li>
            
            <li> 性    别：<s:property value="#session.user.sex"/></li>
            <li> 邮    箱：<s:property value="#session.user.email"/></li>
           </ul>
        </div>
         <div class="expression1"></div>
        </div>                        
      </div>
        <div class="foot">
          <%@include file="../index/foot.html"%>
        </div>
     </div> 
      
  </body>
</html>
 