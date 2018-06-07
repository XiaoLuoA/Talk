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
    
    <title>修改群聊</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="jquery/jquery.form.min.js">
	<link rel="stylesheet" type="text/css" href="jquery/jquery.min-v1.12.4.js">
	<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/nprogress.css">
	<link rel="stylesheet" type="text/css" href="bootstrap/switchery.min.css">
	
  </head>
  
  <body>
  <div class="page-box">
          <div class="jfa-left-box">
             <div class="jfa-logo"></div>
             <div class="jfa-menu-box">
                  <a class="jfa-main-menu jfa-cur-menu" href="" home="true">
                    <i class="left-icon fa fa-dashboard">
                    
                    </i>
                                                     首页
                  </a>
                  <a class="jfa-main-menu" home="false">
                    <i class="left-icon fa fa-file-o">
                    
                    </i>
                                                      用户管理
                    <i class="right-icon fa fa-file-o">
                    
                    </i>
                  </a>
                  <ul class="jfa-sub-menu" style="display: block;">
                     <li>
                         <a href="">项目管理</a>
                     </li>
                     <li>
                         <a href="">分享管理</a>
                     </li>
                     <li>
                         <a href="">反馈管理</a>
                     </li>
                  </ul>
                  <a class="jfa-main-menu" home="false"> 
                     <i class="left-icon fa fa-user-o">
                    
                    </i>
                                                        分组管理
                    <i class="right-icon fa fa-lg fa-angle-down">
                    
                    </i>                  
                  </a>
                  <ul class="jfa-sub-menu" style="display: block;">
                     <li>
                         <a href="">账户管理</a>
                     </li>
                     <li>
                         <a href="">角色管理</a>
                     </li>
                     <li>
                         <a href="">权限管理</a>
                     </li>                  
                  </ul>                                         
             </div>                      
          </div>
    
          <div class="jfa-right-box">
          
             <div class="jfa-header-box" id="jfa-header-box"></div>             
             <div class="jfa-content-box">
                   
             <s:debug>    
          <div class="updateGroupform">
         <s:form action="agroups_updateGroups" method="post">
         <table class="updateGroup1">       	      	 
	    <tr><td>群组名字：</td><td><input type="text"    name="groupName" value="<s:property value="user.name"/>"></td></tr>
	    <tr><td>群组描述：</td><td><input type="text"    name="groupDetail" value="<s:property value="user.password"/>"></td></tr>
	    <tr><td>成员数量：</td><td><input type="text"    name="groupNum" value="<s:property value="user.status"/>"/></td>  </tr> 
	    <tr><td>创建时间：</td><td><input type="text"    name="createTime" value="<s:property value="user.roles"/>"/></td></tr>
	    <tr><td>群组头像：</td><td><input type="text"    name="groupPic" value="<s:property value="user.pic"/>"/></td>     </tr>	 	   				
	      <s:submit  value="提交"></s:submit>	
	      </table>       
		</s:form>	
		</div>	 
  </s:debug>
         </div>   
    </div>
  </body>
</html>