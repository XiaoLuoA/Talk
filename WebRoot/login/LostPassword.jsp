<%@ page language="java" import="java.util.*,com.xiaoluo.model.User" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
  HttpSession h=request.getSession();
   String uname = "";
   String uemail="";
   User user=(User)h.getAttribute("user");
   if(user!=null){
	   uname = String.valueOf(user.getName()); 
	   uemail=user.getEmail();
   }
   
  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>忘记密码</title>
    
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
    <form action="lostpassAction" method="post">
       <table class="l2">
         <tr>                       
           <td>
                <input  value="<%=uname%>"    type="text"  placeholder="账号" required="required" name="id">
           </td>              
         </tr> 
         <tr>
      	   <td>
      	      <div class="td">
      	         <input  value="<%=uemail%>"  type="email"   name="email" placeholder="邮箱" required="required">
      	       </div>
      	   </td> 
      	 </tr> 
      	<tr>
      	    <td>      	       
                 <input type="submit" value="发送验证码"> 
            </td>
         </tr>       
       </table>
    </form>
       <form action="changepassAction" method="post">
         <table class="l2">
            <tr>
              <td>
                <div class="td1">
      	         <input class="num" type="text" name="num" placeholder="验证码" required="required">
      	         </div>
                 <div class="td2">
                    <input class="mbutton" type="submit"  value="确定" >
                 </div>
              </td>           
            </tr>
            <tr>
                <td>
                   <div class="td">
                      <input  class="button" type="button" value="返 回 " onclick="window.location.href='Login/login.jsp'">
                    </div>
                 </td>
            </tr>             
         </table>
        </form>
  </body>
</html>
