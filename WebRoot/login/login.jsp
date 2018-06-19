<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">	
	<link href="CSS/reset.css" rel="stylesheet" type="text/css">
		
  </head> 
  <body>
  <div class="top">
          </div>      
          <div class="login">
            <div class="login1">
            <div class="text">欢 迎 登 录</div>
             <form action="userlogin.action" id="login_form" method="post">  
               <table class="login2">   
               <tr>
                 <td>
                  <div class="td">
                   <input class="input1 zhanghao zhanghaosize" type="text" placeholder="账号"   name="name">
                   </div>
                 </td>
               </tr>                                                  
               <tr>
                 <td>
                 <div class="td1">
                   <input class="input2 mima mimasize" type="password" placeholder="密码"   name="password">
                   </div> 
                 </td>
               </tr>                                                                          
               </table> 
               <div class="link1">
                <a href="userregPage.action">立即注册</a> 
            </div>   
               <div class="link2">
                <a href="userfindPage.action">忘记密码</a>
             </div>
               <input  class="loginbutton" type="submit" value="登 录">                
              </form>
              <input type=button class="loginbutton" value="返 回" onclick="window.location.href='index.jsp'">
            </div>        
      </div>    
    <script type="text/javascript" src="jquery/jquery.min-v1.12.4.js" ></script>
	<script type="text/javascript" src="jquery/jquery.form.min.js" ></script>
	<script type="text/javascript" src="layer/layer/layer.js"></script>

    <script type="text/javascript">
    
		$(document).ready(function() {
			$("#login_form").ajaxForm(
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
						location.href = ret.returnUrl;
						//initWs();
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
