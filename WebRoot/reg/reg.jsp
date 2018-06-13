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
	
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/register.css">

  </head>
  
  <body>
  <div class="top"></div>
  
   <div class="register">
   <div class="text">
     欢迎注册TalkTalk！
   </div>
    <form id="reg_form" action="userregist.action"  method="post">
    <table class="register1">      	     
          <tr> 
            <td>     
                用户名：</td><td><input class="input zhanghao zhanghaosize" type="text"  placeholder="请输入用户名"   name="name" id="uname">
           <td>
          </tr>      
           <tr> 
           <td>                                           
                 密      码：</td><td><input type="password" class="input" name="password" placeholder="请输入密码" id="psd1">    
            </td>             
          </tr>
           <tr>             
            <td>   
             密    码：</td><td> <input type="password" class="input" name="password2" placeholder="请再一次输入密码" id="psd2" onblur="check2psd()">
             </td>       
          </tr>
          <tr>
      	      <td>   
      	        性   别：</td><td> <input  type="radio"  name="sex" checked="checked" value="男">男 <input  type="radio" name="sex" value="女">女 	 
      	        </td>
      	   </tr>
      	   <tr>
      	     <td>   
      	           邮     箱：</td><td> <input type="text" class="input" placeholder="请输入邮箱" name="email" >
      	       </td>      	    
      	   </tr>   
      	                    
      	  </table>
        <input class="registerbutton" type="submit" value="确  定">
    </form>
    <input type=button class="registerbutton" value="返 回" onclick="window.location.href='login/login.jsp'">
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

/*密码不一致*/

 function check2psd(){            	       	
        	if(psd1.value!=psd2.value){
        		alert("两次密码不一致");
        		psd1.value="";
        		psd2.value="";
        	}
         }

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


/*input不能为空*/
function validate_form(thisform)
{
with (thisform)
  {

  if (validate_required(name,"用户名不能为空!")==false)
    {name.focus();return false}
    if (validate_required(password,"密码不能为空!")==false)
    {password.focus();return false}
    if (validate_required(email,"邮箱不能为空!")==false)
    {email.focus();return false}
    if (validate_email(email,"邮箱格式不正确!")==false)
   {email.focus();return false}
    if (validate_required(password2,"密码不能为空!")==false)
    {password2.focus();return false}
    if (validate_required(pic,"头像不能为空!")==false)
    {pic.focus();return false}   
  }
}


</script>

  
  
  
  
  
  
 
    <script type="text/javascript" src="jquery/jquery.min-v1.12.4.js" ></script>
	<script type="text/javascript" src="jquery/jquery.form.min.js" ></script>
    
    <script type="text/javascript" src="layer/layer/layer.js"></script>
    
     <script type="text/javascript">
     
		$(document).ready(function() {
			$("#reg_form").ajaxForm(
			{
				dataType: "json"
				, beforeSubmit: function(formData, jqForm, options)
				{       // 表单提交之前回调
					var regFrom =  document.getElementById('reg_form');
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
							 ,content: '注册成功！',
							 yes: function(index, layero){
								   window.location.href="userloginPage.action";
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
		
		
     function checkName(event)
     {
    	 txt=$("#uname").val();
    	  $.post(
    		"usercheckName.action",
    	 	{
    			  name:txt
    		}
    		,
    		
    		function(ret)
    		{
    			if(ret.isOk)
				{
					//location.href = ret.returnUrl;
					
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
    		},
    		"json");
     }
     
     	$('#uname').blur(function(event){checkName(event)} );     	
     </script>  
     
  </body>
</html>
         