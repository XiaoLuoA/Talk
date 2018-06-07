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
             
             <a> <i class="fa fa-penil" title="修改">::before</i></a>
             <a> <i class="fa fa-trash" title="删除">::before</i></a>
             <i class="fa fa-penil" title="修改"></i>
             <i class="fa fa-trash" title="删除"></i>
          <div class="addMenu" ><a href="admin/addMenu.jsp">增加</a></div>
                   
          <s:form action="amenu_searchLikeMenuList">
           <s:textfield name="searchName"  label="请输入查询的用户名"></s:textfield>
       
        
            <s:submit value="确定"></s:submit>
          
            </s:form>
                          
                 <table border="1">
                   <tr>
                      <td>Menuid</td>
                      <td>菜单名字</td>
                      <td>菜单路径</td>
                      <td>父类Id</td>
                      <td>菜单图标</td>
                      <td>排列大小</td>
                      <td>创建时间</td>
                      <td>子菜单</td>
                      <td>操作1</td>
                      <td>操作2</td>
  
                  </tr>
		<s:iterator  value="allMenu" var="u" >
	      <tr >
	       <td><s:property value="#u.id"/></td>
	       <td><s:property value="#u.menuName"/></td>
	       <td><s:property value="#u.menuUrl"/></td>
			<td><s:property value="#u.parentId"/></td>
			<td><s:property value="#u.icon"/></td>
			<td><s:property value="#u.orderNum"/></td>
			<td><s:property value="#u.createTime"/></td>
			<td><s:property value="#u.subMenu"/></td>
			<td><a href="amenu_findMenu?id=<s:property value="#u.id"/>">修改</a></td>
			<td><a href="amenu_deleteMenu?id=<s:property value="#u.id"/>">删除</a>
			
				</s:iterator>
	
		
		
		 
  </table>
  
 
  
             
             
             
             
           


  

          
          
           </div>
    
    
    </div>
  </body>
</html>