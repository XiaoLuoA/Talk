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
             <div class="jfa-header-box"><div class="text">用户管理</div></div>
                        
             <div class="jfa-content-box">                   
             
             
         <div class="select">                 
          <form action="auser_searchLikeUserList">
            <table class="table1"> 
             <tr>
               <td>请输入查询内容：</td><td><input type="text" required="required" name="searchName">&nbsp;&nbsp;<input type="submit" class="button1" value="确定"></td>     
               <div class="addUser">
            <a href="admin/addUser.jsp">添加用户</a>
           </div>          
             </tr>                    
            </table>        
            </form> 
            
          </div>  
           
              <div class="table2">                                  
                 <table border="1" class="table3">
                   <tr>
                      <td>编号</td>
                      <td>账户</td>
                      <td>密码</td>
                      <td>状态</td>
                      <td>角色</td>
                      <td>性别</td>
                      <td>创建时间</td>
                      <td>举报人数</td>
                      <td>电话号码</td>
                      <td>邮箱地址</td>
                      <td>操作1</td>
                      <td>操作2</td>  
                  </tr>
		<s:iterator  value="allUser" var="u" >
	      <tr>
	        <td><s:property value="#u.id"/></td>
	        <td><s:property value="#u.name"/></td>
	        <td><s:property value="#u.password"/></td>
			<td><s:property value="#u.status"/></td>
			<td><s:property value="#u.roles"/></td>
			<td><s:property value="#u.sex"/></td>
			<td><s:property value="#u.createTime"/></td>
			<td><s:property value="#u.reportNum"/></td>
			<td><s:property value="#u.tel"/></td>
			<td><s:property value="#u.email"/></td>
			<td><a href="auser_findUser?id=<s:property value="#u.id"/>">修改</a></td>
			<td><a href="auser_deleteUser?id=<s:property value="#u.id"/>">删除</a>			
				</s:iterator>						 
          </table>
             
        </div> 
         
            
             <%--   ${sessionScope.menuList[0].menuName}
               ${sessionScope.menuList[1].menuName}
             
               ${sessionScope.menuList[0].subMenu[1].menuName}
               ${sessionScope.menuList[1].subMenu[0].menuName}
               ${sessionScope.menuList[1].subMenu[1].menuName} --%>
               
                <%--  <s:debug>
            <s:iterator value="menuList" var="s">
          
           <a href="<s:property value="#s.menuUrl"/>"><s:property value="#s.menuName"></s:property></a>
          
            <s:property value="#s.subMenu"></s:property>
              <s:property value="#s.subMenu[0]"></s:property>    
                
                </s:iterator>
              
                
               </s:debug> --%>
          
         
           </div>      
           
    </div>
  
   </div>
    
  </body>
</html>
            