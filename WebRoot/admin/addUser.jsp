<%@ page language="java" import="java.util.*,com.xiaoluo.model.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
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
  <%
         List<Menu> list=(List) session.getAttribute("menuList"); 
         int i;
         int j;  
      %>
  
  
  <div class="page-box">
  
          <div class="jfa-left-box">
             <div class="jfa-logo"></div>
             <div class="jfa-menu-box">
                  <a class="jfa-main-menu jfa-cur-menu" href="" home="true">
                    
                                                     首页
                  </a>
                  
                  
                   <%
              
              for( i=0;i<list.size();i++){
            	 %> 
            	 <a class="jfa-main-menu" home="false"  href="<% out.print(list.get(i).getMenuUrl());%>"><% out.print(list.get(i).getMenuName());%></a>
            	 <%
            	 
            	  for(j=0;j<list.get(i).getSubMenuList().size();j++){
            		  %>
            		  <ul class="jfa-sub-menu" style="display: block;"> 
            		  <li> <a   href="<%out.print(list.get(i).getSubMenuList().get(j).getMenuUrl());%>"><%out.print(list.get(i).getSubMenuList().get(j).getMenuName());%></a></li>
                 	   </ul>
                 	
                 	 <%
            	    }
            	  }
            	 %>
                                          
             </div>
                      
          </div>
       
          <div class="jfa-right-box">
          
             <div class="jfa-header-box"><div class="text">添加用户</div></div>
                         
             <div class="jfa-content-box">
                                                  
    <!-- 推荐使用Struts2的标签库 -->
    <div class="addUserform">
    <s:form action="auser_addUser" >  
      <table class="addUser1">  
       <tr>
        <td>
          <s:textfield name="name" label="用户名" ></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
         <s:password name="password" label="密码" requiredLabel="true" requiredPosition="left"></s:password>
        </td>
       </tr>
        <tr>
        <td>
         <s:select name="sex" list="#{'0':'男','1':'女'}" label="性别" headerKey="" headerValue="请选择性别"></s:select>
        </td>
       </tr>
        <tr>
        <td>
         <s:select name="status" list="#{'0':'冻结','1':'正常','2':'未激活'}" label="状态" headerKey="" headerValue="设置状态"></s:select>
        </td>
       </tr>
        <tr>
        <td>
         <s:select name="roles" list="#{'1':'普通用户','2':'管理员','3':'超级管理员'}" label="角色" headerKey="" headerValue="设置角色"></s:select>
        </td>
       </tr>
        <tr>
        <td>
         <s:textfield name="pic" label="头像"></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
         <s:textfield name="createTime" label="创建日期：yyyy-MM-dd"></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
         <s:textfield name="reportNum" label="举报数量"></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
         <s:textfield name="tel" label="电话"></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
         <s:textfield name="email" label="邮箱"></s:textfield>
        </td>
       </tr>
        <tr>
        <td>
        
        </td>
       </tr>
              <s:submit class="button" value="确定"></s:submit>     
       </table> 
             
    </s:form>
   </div>
    <br/> 
        </div>
    
    
    </div>
  </body>
</html>