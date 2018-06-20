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
    <form id="find_form" action="userfindPass.action"  method="post">
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
       <form id="check_form"  action="userreg.action"  method="post">
         <table class="l2">
            <tr>
              <td>
                <div class="td">
      	         <input class="input" type="text" name="regNum" placeholder="验证码" >
      	         </div>
                 <div class="td1">
                    <input class="button" type="submit"  value="确定" >
                 </div>
              </td>           
            </tr>                
         </table>
        </form>
        <input type="button" class="button" value="返 回" onclick="window.location.href='login/login.jsp'">
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

  
  
  
  
  
  
 
    <script type="text/javascript" src="jquery/jquery.min-v1.12.4.js" ></script>
	<script type="text/javascript" src="jquery/jquery.form.min.js" ></script>
    
    <script type="text/javascript" src="layer/layer/layer.js"></script>
    
     <script type="text/javascript">
     
		$(document).ready(function() {
			$("#find_form").ajaxForm(
			{
				dataType: "json"
				, beforeSubmit: function(formData, jqForm, options)
				{       // 表单提交之前回调
					var regFrom =  document.getElementById('find_form');
					var flag = validate_form(regFrom)
					return flag;
				}
				, success: function(ret) 
				{
					// 业务层验证成功
					if(ret.isOk)
					{
						layer.open({
							 title: '页面提示'
							 ,content: '邮件发送成功！',
							 yes: function(index, layero){
								  // window.location.href="userloginPage.action";
								    layer.close(index); 
								  }
							});     
						return ;
					}
					
					// 业务层验证失败
					if (ret.isFail)
					{
						layer.msg(ret.msg,
								{
									shift: 6
									, shade: 0.3
									, time: 2500
									, offset: "165px"
									, closeBtn: 1
									, shadeClose: true
								} , 
								function() 
								{
									
								}
						);
						return ;
					}
					
				}
				, error: function(ret)
				{
					
				}              // ret.status != 200 时回调
				, complete: function(ret) 
				{
					
				}       // 无论是 success 还是 error，最终都会被回调
			});
		});
		
		
		$(document).ready(function() {
			$("#check_form").ajaxForm(
			{
				dataType: "json"
				, beforeSubmit: function(formData, jqForm, options)
				{       // 表单提交之前回调
					//var regFrom =  document.getElementById('check_form');
				//	var flag = validate_form(regFrom)
				//	return flag;
				}
				, success: function(ret) 
				{
					// 业务层验证成功
					if(ret.isOk)
					{
						/* layer.open({
							 title: '页面提示'
							 ,content: '邮件发送成功！',
							 yes: function(index, layero){
								  // window.location.href="userloginPage.action";
								    layer.close(index); 
								  }
							});      */
							
							window.location.href="userchangePage.action";
						return ;
					}
					
					// 业务层验证失败
					if (ret.isFail)
					{
						layer.msg(ret.msg,
								{
									shift: 6
									, shade: 0.3
									, time: 2500
									, offset: "165px"
									, closeBtn: 1
									, shadeClose: true
								} , 
								function() 
								{
									
								}
						);
						return ;
					}
					
				}
				, error: function(ret)
				{
					
				}              // ret.status != 200 时回调
				, complete: function(ret) 
				{
					
				}       // 无论是 success 还是 error，最终都会被回调
			});
		});
		
     
     </script>  
        
  </body>
</html>

      