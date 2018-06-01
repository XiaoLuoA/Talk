<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>填写密码</title>
    
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
    <form action="fillpassAction" method="post" onSubmit="checkForm();">
      <table class="c">
        <tr>
          <td>
            <div class="td">
             <input id="psd1"  type="password" placeholder="密码" required="required" name="password">
             </div>
          </td>
        </tr>
         <tr>
          <td>
            <div class="td">
             <input id="psd2"  type="password" placeholder="再次输入密码" required="required" name="password">
             </div>
          </td>
        </tr>
         <tr>
          <td>
            <div class="td">
             <input  type="submit" value="确 定" >
             </div>
          </td>
        </tr>
        <tr>
          <td>
             <div class="td">
                <input  type="button" value="返 回 " onclick="window.location.href='Login/login.jsp'">
              </div>
          </td>
        </tr> 
      </table>    
    </form>    
    <script type="text/javascript">
        function checkForm(){    
        	
        	var psd1=$("#psd1").val();
        	var psd2=$("#psd2").val();
        	alert(psd1);
        	alert(psd2);
        	if(psd1!=psd2){
        		alert("两次密码不一致");
        		return false;
        	}
        }
    </script>
  </body>
</html>
