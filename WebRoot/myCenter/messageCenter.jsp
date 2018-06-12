<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>消息中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/myCenter.css">
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">

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
        <div class="myMessage">
          <div class="mycenter"> 
                 您暂时没有收到任何消息！
           </div>
           <div class="expression2"></div>
        </div>                        
      </div>
        <div class="foot">
          <%@include file="../index/foot.html"%>
        </div>
     </div> 
  </body>
</html>
