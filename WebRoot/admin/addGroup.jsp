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
    
    <title>添加群聊</title>
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
                    <i class="left-icon fa fa-dashboard">
                    
                    </i>
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
                        
             <div class="jfa-content-box" id="jfa-content-box">
              
    <!-- 推荐使用Struts2的标签库 -->
    <div class="addGroup">
     <s:form action="agroups_addGroups" >  
      <table class="addGroup1">
       <tr>
        <td>
         <s:textfield name="groupName"   label="群主名字" ></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="groupNum"    label="人员数量" ></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="groupDetail" label="群组描述" ></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="groupPic"    label="群组头像"></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="createTime"  label="创建日期：yyyy-MM-dd"></s:textfield>
        </td>
       </tr>  
       <tr>
        <td>
        </td>
       </tr>                   
        <s:submit value="确定"></s:submit>   
        </table>    
    </s:form>
    </div>
    <br/>     
        </div>        
    </div>
  </body>
</html>
        