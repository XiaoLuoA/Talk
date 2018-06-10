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
	
	<!-- <link rel="stylesheet" href="temp/css/reset.css" /> -->
	<link rel="stylesheet" href="css/indexindex.css" />
	<link rel="stylesheet" href="temp/css/index.css" />
  </head>
  
  <body>
    <div class="container">    
    	<%@include file="../index/navi.html"%>
    	
    	
    	<div class="index">
    			<div class="group-area"> 
    				<s:iterator id="groups" value="allGroup" >
    				<div class="g1"> 
          				<div class="group" data-index="<s:property value="#groups.id"/>" >群聊<span class="group-name"><s:property value="#groups.groupName"/></span></div>
					</div> 
					</s:iterator> 
				</div>  
     	</div>
     	
     	
        <div class="foot">
         <%@include file="../index/foot.html"%>
        </div>
     </div> 
		<script src="temp/js/jquery-3.2.1.min.js"></script>
  </body>
</html>

<!--
	作者：nyfjr@icloud.com
	时间：2018-05-26
	描述：
消息列表
| 属性名      		|  类型 		|   备注  			|
| :-------------| :---------| :-------------|
|  userItemId  	|   str 	|  用户item的id  	|
|  talkItemId  	|   str 	|  对象item的id  	|
|  userId  		|   str 	|  用户id  		|
|  userName  	|   str 	|  用户用名字  		|
|  talkerId  	|   str 	|  对象id  		|
|  talkerName  	|   str 	|  对象的名字  		|
|  talkPic  	|   str 	|  对象的头像  		|
|  newNum  		|   Number 	|   未读的数量  		|
|  lastTime  	|  str  	|  最后一条信息的时间  |
|  isBlack  	|  boolean 	|  黑名单  			|
|  messages  	|  object  	|  具体的消息  		|

具体消息
|  userMessId	|   str 	|  消息id  		|
|  itemId  		|   str  	|  itemID  		|
|  fromId  		|   str 	|  发送用户的id		|
|  toId  		|   str 	|  接受用户的id		|
|  sendTime 	|   str 	|  发送的时间  		|
|  isRead  		|   boolean |  是否已读  		|
|  content  	|   str 	|  具体内容  		|
-->
