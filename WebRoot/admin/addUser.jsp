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
    
    <title>My JSP 'index.jsp' starting page</title>
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
             
             
             <div class="jfa-contnt-box" id="jfa-content-box">
             
           
          
                
    <!-- 推荐使用Struts2的标签库 -->
    <s:form action="auser_addUser" > 
        
        <s:textfield name="name" label="用户名" ></s:textfield>
        <s:password name="password" label="密码" requiredLabel="true" requiredPosition="left"></s:password>
        <s:select name="sex" list="#{'0':'男','1':'女'}" label="性别" headerKey="" headerValue="请选择性别"></s:select>
        <s:select name="status" list="#{'0':'冻结','1':'正常','2':'未激活'}" label="状态" headerKey="" headerValue="设置状态"></s:select> 
        <s:select name="roles" list="#{'1':'普通用户','2':'管理员','3':'超级管理员'}" label="角色" headerKey="" headerValue="设置角色"></s:select>         
        <s:textfield name="pic" label="头像"></s:textfield>
        <s:textfield name="createTime" label="创建日期：yyyy-MM-dd"></s:textfield>
        <s:textfield name="reportNum" label="举报数量"></s:textfield>
        <s:textfield name="tel" label="电话"></s:textfield>
        <s:textfield name="email" label="邮箱"></s:textfield>
      
        
        <s:submit value="确定"></s:submit>
        
    </s:form>
    <br/>
    
  

  
 
  
             
             
             
             
           


  

          
          
           </div>
    
    
    </div>
  </body>
</html>