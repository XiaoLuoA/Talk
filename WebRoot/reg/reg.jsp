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
    <form id="reg_form" action="userregist.action" method="post">
        <table>
          <tr>
          <td>
           	用户名：
          </td>
            <td>
                 <input type="text" name="name" id="uname">
            </td>
          
          </tr>
           <tr>
           <td>
         	  密   码：
          </td>
            <td>
                <input type="password" name="password" >
            </td> 
                       
          </tr>
           <tr>
           <td>
         	 重复密码：
          </td>
            <td>
                <input type="password" name="password2" required="required">
            </td> 
                       
          </tr>
          <tr>
          <td>
         	 性   别：
          </td>
      	      <td> 
      	         <input  type="radio"  name="sex" checked="checked" value="男">男 <input  type="radio" name="sex" value="女">女 	             
             </td>
      	   </tr>
      	   <tr>
      	    <td>
         	邮   箱：
          </td>
      	      <td>
      	           <input type="email" name="email" required="required">
      	      </td>       	    
      	   </tr>  
      	    <tr>
      	    <td>
          </td>
      	      <td>
      	          <input type="submit" value="注册">
      	      </td>       	    
      	   </tr>  
      	  </table>
    </form>
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
		
		
     function checkName()
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
					layer.msg("Ok",
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
     
     	$('#uname').blur
     	(
     		checkName()
     	);
     	
     	
     </script>
  </body>
</html>
