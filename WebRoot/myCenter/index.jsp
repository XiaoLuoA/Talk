<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
	
	<link rel="stylesheet" type="text/css" href="css/indexindex.css">
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
    <div class="container">    
    <%@include file="../index/navi.html"%>
    <div class="index">  
    <s:iterator id="groups" value="allGroup" >
     <div class="g1">
          群聊<span class="group-name"><s:property value="#groups.groupName"/></span>
        </div>
	<!-- <div class="group" data-index="<s:property value="#groups.id"/>" ></div> -->
	</s:iterator> 
       
          
                   
      </div>
        <div class="foot">
          <%@include file="../index/foot.html"%>
        </div>
     </div> 
  </body>
</html>
