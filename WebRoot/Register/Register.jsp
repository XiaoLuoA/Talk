<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="" method="post">
        <table>
          <tr>
            <td>
                   用户名：<input type="text" name="name" required="required">
            </td>
          
          </tr>
           <tr>
            <td>
                 密   码： <input type="password" name="password" required="required">
            </td>            
          </tr>
          <tr>
      	      <td> 
      	        性   别： <input  type="radio"  name="sex" checked="checked" value="男">男 <input  type="radio" name="sex" value="女">女 	             
             </td>
      	   </tr>
      	   <tr>
      	      <td>
      	           邮   箱：<input type="email" name="email" required="required">
      	      </td>       	    
      	   </tr>    	   
       
         <tr>
      	   <td>
      	           上传头像：<input type="file" name="pic" required="required">
      	    </td>       	    
      	 </tr>  
      	  </table>
        <input type="submit" value="确定">
    </form>
  </body>
</html>
