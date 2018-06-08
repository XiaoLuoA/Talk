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
