<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>个人聊天页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<link rel="stylesheet" href="temp/css/reset.css" />
		<link rel="stylesheet" href="temp/css/index.css" />
	</head>
	<body>
		<div class="page-moment">
			假装这是背景
			<div class="group-area">
				<!-- 使用strust标签输出群组-->
				<s:iterator id="groups" value="allGroup" >
				
					
					<div class="group" data-index="<s:property value="#groups.id"/>" >群聊<span class="group-name"><s:property value="#groups.groupName"/></span></div>
				</s:iterator>
				
				
			</div>
			<button id="groupChatBtn" class="group-chat-btn btn">群聊</button><button id="chatBtn" class="chat-btn btn">个人聊天</button>
			
			<div class="chat-overlay overlay hidden">
				<div id="MessageArea" class="chat-area overlay-showarea"><!--聊天信息-->
					<div class="chose-list scrll-y">
						<div class="chose-item" data-index="a">a<span class="cls-btn float-r">关闭</span></div>
						<div class="chose-item" data-index="v">v<span class="cls-btn float-r">关闭</span></div>
					</div>
					<div class="detail-list">
						<div class="detail-item scrll-y">c</div>
						<div class="detail-item scrll-y">s</div>
					</div>
				
				
				<div class="item">
					<div class="item-top">
						<p>文字</p>
						<span>消息</span> <span>新消息</span>
					</div>
					<div class="item-list scrll-y"><!--消息列表-->
						<div class="item-item">
							<div class="head-img"><img src="avatar4.png"></div>
							<div class="ietm-item-detail">
								<p><span class="name">AI</span><span class="last-time">2015-12-12-12-12</span></p>
								<span class="content">这是一条消息</span>
							</div>
						</div>	
					</div>
				</div>
				</div>
			</div>
			
			
			<div class="group-chat-overlay overlay hidden">
				<div id="GroupMessageArea" class="group-chat-area overlay-showarea"><!--聊天信息-->
					<div class="group-chose-list scrll-y">
					<!--在这里切换和关闭群聊-->
					</div>
				
					<div class="group-detail-list">
					<!--聊天信息的交互页面 -->
					<!--群聊成员的显示-->
					</div>
			
				</div>
			</div>
			
		</div>
		
		
		<script src="temp/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="page/tio/tiows.js"></script>
		<% 
		String sess = (String)request.getSession().getAttribute("sessionId");
		User user = (User)request.getSession().getAttribute("user");
		out.print("<script>var queryString = 'sessionId="+sess+"';");
		out.print("var sessionId= '"+sess+"';");
		out.print("var UserId= '"+user.getId()+"';</script>");
		%>
		<s:property value="#session.sessionId"/>
		<script>
			var items =	[
				{
					userItemId :1,
					talkItemId :11,
					userId :1,
					userName :'符文',
					talkerId :2,
					talkerName :'二号',
					talkPic :'avatar4.png',
					newNum :0,
					lastTime:'2017-05-45|12:00',
					lastContent:'你好啊',
					isBlack :false,
					messages:[{
						userMessId :1,
						itemId :1,
						fromId :2,
						toId:1,
						sendTime :'2017-05-45|12:00',
						isRead:true,
						content:'这是一条信息',
					},],
				},
				{userItemId :2,talkItemId :12,userId :1,userName :'符文',talkerId :3,talkerName :'三号',talkPic :'avatar5.png',newNum :0,lastTime:'2017-05-45|12:00',lastContent:'你好个P',isBlack :false,messages:[{userMessId :1,itemId :2,fromId :3,toId:1,sendTime :'2017-05-45|12:00',isRead:true,content:'这是一条信息',},],},
			];
			
			var groups =[
				{
					id:'1',
					groupName:'群A',
					groupDetail:'测试群',
					groupNum:13,
					createTime:'2015-03-02',
					groupPic:'avatar4.png',
					groupMess:[{
						id:'1',
						content:'a',
						time:'2015-03-02',
						talkerPic:'avatar4.png',
						talkerId:'1',
						talkerName:'Ai2',
					},],
				},];
		</script>
		<script type="text/javascript" src="temp/js/tool.js" ></script>
		<script type="text/javascript" src="temp/js/index.js" ></script>
		<script type="text/javascript" src="temp/js/evet.js"></script>
		
		
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
