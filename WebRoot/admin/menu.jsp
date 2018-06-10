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
    
    <title>菜单</title>
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
                   if(list.size()>0){
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
                   }
            	 %>                                        
             </div>                     
          </div>      
          <div class="jfa-right-box">
          
             <div class="jfa-header-box"><div class="text">菜单管理</div></div>
            <div class="jfa-content-box">
       <div class="menu">                 
          <form action="amenu_searchLikeMenuList">
          
          <table class="table1"> 
             <tr>
               <td>请输入查询内容：</td><td> <input type="text" required="required" name="searchName">&nbsp;&nbsp;<input type="submit" class="button1" value="确定"> </td>  
               <div class="addMenu">
            <a href="admin/addMenu.jsp">添加菜单</a>           
             </tr>                    
            </table>          
            </form>   
           </div>
              <div class="table2">                     
                 <table border="1" class="table3">
                   <tr>
                      <td>Menuid</td>
                      <td>菜单名字</td>
                      <td>菜单路径</td>
                      <td>父类Id</td>
                      <td>菜单图标</td>
                      <td>排列大小</td>
                      <td>创建时间</td>
                      
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
		
			<td><a href="amenu_findMenu?id=<s:property value="#u.id"/>">修改</a></td>
			<td><a href="amenu_deleteMenu?id=<s:property value="#u.id"/>">删除</a>			
				</s:iterator>	 
              </table> 
            </div>  
                  
           
       </div>   
    </div>
  </body>
</html>