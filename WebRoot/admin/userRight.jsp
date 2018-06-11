<%@ page language="java" import="java.util.*,com.xiaoluo.model.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
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
  <%
         List<Menu> list=(List) session.getAttribute("menuList"); 
         int i;
         int j;  
     %>
  
  <body>
  <div class="page-box">
  
          <div class="jfa-left-box">
             <div class="jfa-logo"></div>
             <div class="jfa-menu-box">
                  <a class="jfa-main-menu jfa-cur-menu" href="" home="true">
                    
                                                     首页
                   </a>
                   <%
                   if(list.size()>0){
              for( i=0;i<list.size();i++){
            	   %> 
            	 <ul class="jfa-main-menu" home="false" style="display: block;" ><% out.print(list.get(i).getMenuName());%></ul>
            	 <%
            	 
            	  for(j=0;j<list.get(i).getSubMenuList().size();j++){
            		  %>
            		  <ul class="jfa-sub-menu" style="display: block;"> 
            		  <li> <a   href="<%out.print(list.get(i).getSubMenuList().get(j).getMenuUrl());%>"><%out.print(list.get(i).getSubMenuList().get(j).getMenuName());%></a></li>
                 	   </ul>
                 	
                 	 <%
            	    }
            	  }
                   }
            	 %>                                                   
             </div>                    
          </div>    
          <div class="jfa-right-box">         
             <div class="jfa-header-box"><div class="text">用户管理</div></div>
                        
             <div class="jfa-content-box">                   
             
             
         <div class="select">                 
  
            
          </div>  
           
              <div class="table2">                                  
                 <table border="1" class="table3">
                   <tr>
                      <td>编号</td>
                      <td>账户</td>
                      <td>状态</td>
                      <td>角色</td>
                      <td>操作1</td>
                      <td>操作2</td>  
                  </tr>
		<s:iterator  value="allUser" var="u" >
	      <tr>
	        <td><s:property value="#u.id"/></td>
	        <td><s:property value="#u.name"/></td>
			<td><s:property value="#u.status"/></td>
			<td><s:property value="#u.roles"/></td>
	
			<td><a href="aright_findRight?id=<s:property value="#u.id"/>">修改</a></td>
			<td><a href="aright_deleteRight?id=<s:property value="#u.id"/>">删除</a>			
				</s:iterator>						 
          </table>
             
        </div> 
      
          
         
           </div>      
           
    </div>
  
   </div>
    
  </body>
</html>