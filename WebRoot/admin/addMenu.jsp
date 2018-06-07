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
    
    <title>添加菜单</title>
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
             <div class="jfa-content-box" id="jfa-content-box">
              
    <!-- 推荐使用Struts2的标签库 -->
   <div class="addMenu">
    <s:form action="amenu_addMenu" > 
      <table class="addMenu1">
       <tr>
        <td>
          <s:textfield name="menuUrl"  label="菜单路径" ></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="parentId" label="父类Id" ></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="icon"    label="菜单图标"></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="orderNum"  label="排列大小"></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         <s:textfield name="createTime"  label="创建时间"></s:textfield>
        </td>
       </tr>
       <tr>
        <td>
         
        </td>
       </tr>       
        <%-- <s:textfield name="orderNum"  label="子菜单"></s:textfield> ！！！！子菜单获取的是list豪哥你想想怎么用OGNL显示吧，任务多就不给你写了 --%>               
        <s:submit value="确定"></s:submit>
      </table>   
    </s:form>
    </div>
    <br/>
    
  

  
 
  
             
             
             
             
           


  

          
          
           </div>
    
    
    </div>
  </body>
</html>
