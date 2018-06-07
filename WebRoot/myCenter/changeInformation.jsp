<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/myCenter.css">
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
    <div class="container">    
    <%@include file="../index/navi.html"%>
    <div class="center">   
          <div class="left">
            <ul>
               <li><a href="myCenter/myCenter.jsp">个人信息</a></li>
               <li><a href="myCenter/messageCenter.jsp">消息中心</a></li>
               <li><a href="myCenter/changeInformation.jsp">修改信息</a></li>
               <li><a href="">我的消息</a></li>
            </ul>
            </div>
    <div class="changeInformation">  
     <div class="form">
     <form id="reg_form" action="userregist.action" method="post">
        <table class="table">      	     
          <tr> 
          <div class="tr">       
                用户名：<input class="input zhanghao zhanghaosize" type="text"  placeholder="请输入用户名" required="required"  name="name" id="uname">
           </div>
          </tr>      
           <tr> 
           <div class="tr">                                           
                 密      码：         <input type="password" class="input" name="password" placeholder="请输入密码" required="required">    
            </div>               
          </tr>
           <tr>             
            <div class="tr">   
                密   码： <input type="password" class="input" name="password2" placeholder="请再一次输入密码" required="required">
             </div>       
          </tr>
          <tr>
      	      <div class="tr">   
      	        性   别： <input  type="radio"  name="sex" checked="checked" value="男">男 <input  type="radio" name="sex" value="女">女 	 
      	        </div>
      	   </tr>
      	   <tr>
      	     <div class="tr">   
      	           邮     箱： <input type="email"class="input" placeholder="请输入邮箱" name="email" required="required">
      	        </div>      	    
      	   </tr>   
      	   <tr>
      	      <div class="tr">   
      	           头     像： <input type="file" class="input"  name="pic" required="required">
      	        </div>      	    
      	   </tr>  
      	    <div class="button">
        <input class="button1" type="submit" value="确  定">
        </div>   	                  
      	  </table>
      	
    </form>    
        </div>                        
      </div>
     </div>
        <div class="foot">
          <%@include file="../index/foot.html"%>
        </div>
     </div> 
  </body>
</html>
