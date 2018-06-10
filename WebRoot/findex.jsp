<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ page language="java" import="com.xiaoluo.model.User" %>
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
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	
	<link rel="stylesheet" href="temp/css/reset.css" />
	<link rel="stylesheet" href="css/indexindex.css" />
	<link rel="stylesheet" href="temp/css/index.css" />
  </head>
  
  <body>
    <div class="container">    
    	<%@include file="../index/navi1.html"%>
    	
    	<div class="index">
    	    <div class="group1">
    			<div class="group-area"> 
    				<s:iterator id="groups" value="allGroup" >
    				<div class="g1"> 
          				<div class="group" data-index="<s:property value="#groups.id"/>" >群聊<span class="group-name"><s:property value="#groups.groupName"/></span></div>
					</div> 
					</s:iterator> 
				</div>  
			</div>
     	</div>
     	
        <div class="foot">
         <%@include file="../index/foot.html"%>
        </div>
     </div> 
		<script src="temp/js/jquery-3.2.1.min.js"></script>
  </body>
</html>