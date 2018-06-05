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
          <div class="addGroup" ><a href="admin/addGroup.jsp">增加</a></div>
          
            
          <s:form action="agroups_searchLikeGroupsList">
           <s:textfield name="searchName"  label="请输入查询的用户名"></s:textfield>
       
        
            <s:submit value="确定"></s:submit>
          
            </s:form>
          
          
          
          
          
                 <table border="1">
                   <tr>
                      <td>Groupsid</td>
                      <td>群组名字</td>
                      <td>群组详情</td>
                      <td>群聊人数 </td>
                      <td>创建时间</td>
                      <td>群聊头像</td>
                      <td>操作1</td>
                      <td>操作2</td>
  
                  </tr>
		<s:iterator  value="allGroups" var="g" >
	      <tr >
	       <td><s:property value="#g.id"/></td>
	       <td><s:property value="#g.groupName"/></td>
	       <td><s:property value="#g.groupDetail"/></td>
			<td><s:property value="#g.groupNum"/></td>
			<td><s:property value="#g.createTime"/></td>
			<td><s:property value="#g.groupPic"/></td>

			<td><a href="agroups_findGroups?id=<s:property value="#g.id"/>">修改</a></td>
			<td><a href="agroups_deleteGroups?id=<s:property value="#g.id"/>">删除</a>
			
				</s:iterator>
	
		
		
		 
  </table>
  
 
  
             
             
             
             
           


  

          
          
           </div>
    
    
    </div>
  </body>
</html>
