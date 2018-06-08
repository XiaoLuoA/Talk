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
          
             <div class="jfa-header-box" id="jfa-header-box"></div>
            <div class="jfa-content-box">
       
              <div class="table2">                     
                 <table border="1" class="table3">
                   <tr>
                      <td>Roleid</td>
                      <td>角色名字</td>
                      <td>创建时间</td> 
                      <td>操作1</td>
                      <td>操作2</td>
  
                  </tr>
		<s:iterator  value="allRole" var="r" >
	      <tr >
	       <td><s:property value="#r.id"/></td>
	       <td><s:property value="#r.roleName"/></td>
		   <td><s:property value="#r.createTime"/></td>

			<td><a href="arole_findRole?id=<s:property value="#r.id"/>">修改</a></td>
			<td><a href="arole_deleteRole?id=<s:property value="#r.id"/>">删除</a>			
				</s:iterator>	 
              </table> 
            </div>  
              <div class="" ><a href="admin/addRole.jsp">添加角色</a></div>    
           
       </div>   
    </div>
  </body>
</html>
