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
	<style>
	.layui-layer-btn a{
		font-weight:400;
		font-size:14px;}
	.layui-layer.layui-anim.layui-layer-dialog
	{
		width:260px;
		heoght:154px;
	} 
	
</style>
  </head>
  
  <body>
    <div class="container">    
   <%@include file="../index/navi1.html"%> 
    	<%-- <jsp:include page="../index/navi1.jsp"/> --%>
    	<div class="index">
    	    <div class="group1">
    			<div class="group-area"> 
    				<s:iterator id="groups" value="allGroup" >
    				
    				
          				<div class="group" data-index="<s:property value="#groups.id"/>" >

          				<div class="group-message">
          					<img class="group-img" src="#groups.groupPic" />
          					<p class="group-name"><s:property value="#groups.groupName"/></p>
          					<p class="group-content"><s:property value="#groups.groupDetail"/></p>
          				</div>
          				<div class="group-button">
          					<button class="group-btn join-btn" >不感兴趣</button>
          					<button class="group-btn pass-btn" >加入群聊</button>
          				</div>
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
		<script type="text/javascript" src="layer/layer/layer.js"></script>
		 <script type="text/javascript">
		/*  $.('body').on('click',function(event){
			 var slectors = ['.group-btn'];
			 var $dom = event.target;
			 var slector;
			 for(let index=slectors.length-1;index>=0;index--)
			 {
				 slector = slectors[index];
				 $dom.is(slectors)?true:$dom = $dom.parents(selctor);
				 if($dom.is(slectors))
				 {
					 toOther();
				 }
			 }
			 
		 }); */
		 
		 
    function toOther(){
    	<% User user = (User)request.getSession().getAttribute("user");
    	if(user==null){
    	%>
    	//询问框

    	layer.confirm('现在前往登录么？', {
    	  btn: ['好滴','拒绝'] //按钮
    	}, function(){
    		location.href="userloginPage.action"
    	 // layer.msg('的确很重要', {icon: 1});
    	}, function(){
    	});
    	
    	<%
    	}
    	%>
    }
	</script>
  </body>
</html>