<%@ page language="java" import="java.util.*,com.xiaoluo.model.User" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	
	<link rel="stylesheet" type="text/css" href="css/findpsd.css">
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
   <div class="top"></div>
   
    <div class="form">
     <div class="text">修 改 密 码</div>
    <form id="find_form" action="userfindPass.action" onsubmit="return validate_form(this)" method="post">
       <table>
         <tr>                       
           <td>
              <div class="td">
                <input type="text" class="input" placeholder="用户名"  name="name">
              </div>
           </td>              
         </tr> 
         <tr>
      	   <td>
      	      <div class="td">
      	         <input    type="text"  class="input" name="email" placeholder="邮箱" >
      	       </div>
      	   </td> 
      	 </tr> 
      	<tr>
      	    <td>   
      	    <div class="td">   	       
                 <input type="submit" class="button" value="发送验证码"> 
             </div>
            </td>
         </tr>       
       </table>
    </form>
    
    
       <form id="check_form"  action="userreg.action" onsubmit="return validate_form1(this)" method="post">
         <table class="l2">
            <tr>
              <td>
                <div class="td">
      	         <input class="input" type="text" name="regNum" placeholder="验证码" >
      	         </div>
                 <div class="td">
                    <input class="button" type="submit"  value="确定" >
                 </div>
              </td>           
            </tr>
                
         </table>
        </form>
      </div>  
       
        <script type="text/javascript">

function validate_required(field,alerttxt)
{
with (field)
  {
  if (value==null||value=="")
    {alert(alerttxt);return false}
  else {return true}
  }
}

/*用户名邮箱不能为空*/
function validate_form(thisform)
{
with (thisform)
  {
  if (validate_required(name,"用户名不能为空!")==false)
    {
    email.focus();return false
    }if (validate_required(email,"邮箱不能为空!")==false)
    {email.focus();return false}
    
  }
}

/*验证码不能为空*/
function validate_form1(thisform)
{
with (thisform)
  {
  if (validate_required(regNum,"验证码不能为空!")==false)
    {
    regNum.focus();return false
    }
  }
}

/*邮箱格式*/

function validate_email(field,alerttxt)
{
with (field)
{
apos=value.indexOf("@")
dotpos=value.lastIndexOf(".")
if (apos<1||dotpos-apos<2) 
  {alert(alerttxt);return false}
else {return true}
}
}

function validate_form(thisform)
{
with (thisform)
{
if (validate_email(email,"邮箱格式不正确!")==false)
  {email.focus();return false}
}
}

</script>
        
  </body>
</html>

      