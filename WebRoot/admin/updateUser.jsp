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
    
    <title>修改用户</title>
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
          
             <div class="jfa-header-box"><div class="text">修改用户</div></div>
             
             
             <div class="jfa-content-box">                    
              <s:debug>  
            <div class="updateUserform"> 
         <s:form action="auser_updateUser" method="post">        		
	      <table class="updateUser1">
	    <tr><td>账户：</td><td><input type="text"    name="name" value="<s:property value="user.name"/>"></td></tr>
	    <tr><td>密码：</td><td><input type="text"    name="password" value="<s:property value="user.password"/>"></td>   </tr>
	    <tr><td>状态：</td><td><input type="text"    name="status" value="<s:property value="user.status"/>"/></td>  </tr> 
	    <tr><td>角色：</td><td><input type="text"    name="roles" value="<s:property value="user.roles"/>"/></td></tr>
	    <tr><td>头像：</td><td><input type="text"    name="pic" value="<s:property value="user.pic"/>"/></td></tr>
	    <tr><td>性别：</td><td><input type="text"    name="sex" value="<s:property value="user.sex"/>"/></td></tr>   
	    <tr><td>创建时间：</td><td><input type="text" name="createTime" value="<s:property value="user.createTime"/>"/></td></tr>   
	    <tr><td>举报人数：</td><td><input type="text" name="reportNum" value="<s:property value="user.reportNum"/>"/></td>   </tr>
	    <tr><td>电话号码：</td><td><input type="text" name="tel" value="<s:property value="user.tel"/>"/></td>  </tr> 
	    <tr><td>邮箱：</td><td><input type="text"    name="email" value="<s:property value="user.email"/>"/></td>  </tr> 
	       	      
			<%-- <td><s:property value="#u.status"/></td>
			<td><s:property value="#u.roles"/></td>
			<td><s:property value="#u.sex"/></td>
			<td><s:property value="#u.createTime"/></td>
			<td><s:property value="#u.reportNum"/></td>
			<td><s:property value="#u.tel"/></td>
			<td><s:property value="#u.email"/></td>
			<td><a href="findUser?id=<s:property value="#u.id"/>">修改</a></td>
			<td><a href="deleteUser?id=<s:property value="#u.id"/>">删除</a>						
 --%>
          <s:submit value="提交"></s:submit>	
 		</table>		
	            
		</s:form>	
		</div>	 
  </s:debug>       
           </div>    
    </div>
  </body>
</html>