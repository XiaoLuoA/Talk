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
    <jsp:include page="../index/navi.jsp"/>
    <div class="center">   
          <div class="left">
            <ul>
               <li><a href="myCenter/myCenter.jsp">个人信息</a></li>
               <li><a href="myCenter/messageCenter.jsp">消息中心</a></li>
               <li><a href="myCenter/changeInformation.jsp">修改信息</a></li>
               
            </ul>
            </div>
    <div class="changeInformation">  
     <div class="form">
     <form id="edit" action="useredit.action" method="post" >
        <table class="table">      	     
          <tr> 
          <div class="tr">       
                用户名：<input class="input zhanghao zhanghaosize" type="text" value="<s:property value="#session.user.name"/>"  placeholder="请输入用户名"   name="name" id="uname">
           </div>
          </tr>      
           
          <tr>
      	      <div class="tr">   
      	        性   别： <input  type="radio"  name="sex" checked="checked" value="男">男 <input  type="radio" name="sex" value="女">女 	 
      	        </div>
      	   </tr>
      	   <tr>
      	     <div class="tr">   
      	           邮     箱： <input type="text"class="input" value="<s:property value="#session.user.email"/>" placeholder="请输入邮箱" name="email" >
      	        </div>      	    
      	   </tr>   
      	    
      	    <div class="button">
        <input class="button1" type="submit" value="确  定">
        </div>   	                  
      	  </table>
      	
    </form>    
        </div>   
        <div class="expression3"></div>                     
      </div>
     </div>
        <div class="foot">
          <%@include file="../index/foot.html"%>
        </div>
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
   
    if (validate_required(email,"邮箱不能为空!")==false)
    {email.focus();return false}
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
			$("#edit").ajaxForm(
			{
				dataType: "json"
				, beforeSubmit: function(formData, jqForm, options)
				{       // 表单提交之前回调
					var regFrom =  document.getElementById('edit');
					var flag = validate_form(regFrom)
					return flag;
				}
				, success: function(ret) 
				{
					// 业务层验证成功
					if(ret.isOk)
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
									location.href="indexindex.action";
								}
						);
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
