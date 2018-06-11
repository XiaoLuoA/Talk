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
          
             <div class="jfa-header-box" id="jfa-header-box"></div>             
             <div class="jfa-content-box">
                   
             <s:debug>    
          <div class="updateGroupform">
         <s:form action="arolemenu_updateRoleMenu" method="post">
         <table class="updateGroup1">       	      	 
	  
	    <tr><td>roleID：</td><td><input type="text"    name="roleId" value="<s:property value="rolemenu.roleId"/>"/></td></tr>
	     <tr><td>MenuID：</td><td><input type="text"    name="menuId" value="<s:property value="rolemenu.menuId"/>"></td></tr>  
	    <tr><td>创建时间：</td><td><input type="text"    name="createTime" value="<s:property value="rolemenu.createTime"/>"/></td></tr>	   
	      <s:submit  value="提交"></s:submit>	
	      </table>       
		</s:form>	
		</div>	 
  </s:debug>
         </div>   
    </div>
  </body>
</html>