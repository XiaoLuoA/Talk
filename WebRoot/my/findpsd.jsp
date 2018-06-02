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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form id="find_form" action="userfindPass.action" method="post">
       <table class="l2">
         <tr>                       
           <td>
                <input type="text"  placeholder="账号" required="required" name="name">
           </td>              
         </tr> 
         <tr>
      	   <td>
      	      <div class="td">
      	         <input    type="email"   name="email" placeholder="邮箱" required="required">
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
    
    
       <form id="check_form" action="userreg.action" method="post">
         <table class="l2">
            <tr>
              <td>
                <div class="td1">
      	         <input class="num" type="text" name="regNum" placeholder="验证码" required="required">
      	         </div>
                 <div class="td2">
                    <input class="mbutton" type="submit"  value="确定" >
                 </div>
              </td>           
            </tr>
                
         </table>
        </form>
        
       
         <script type="text/javascript" src="jquery/jquery.min-v1.12.4.js" ></script>
	<script type="text/javascript" src="jquery/jquery.form.min.js" ></script>
	<script type="text/javascript" src="layer/layer/layer.js"></script>
    <script type="text/javascript">
    
		$(document).ready(function() {
			
			$("#find_form").ajaxForm(
			{
				dataType: "json"
				, 
				beforeSubmit: function(formData, jqForm, options)
				{       // 表单提交之前回调
					
				}
				, success: function(ret) 
				{
					// 业务层验证成功
					if(ret.isOk)
					{
						layer.open({
							 title: '页面提示'
							 ,content: '邮件发送成功，注意查收！',
							 yes: function(index, layero){
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
		}
		);
		
		$(document).ready(function() {
			$("#check_form").ajaxForm(
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
		}
		
		);
		
		
	</script>
  </body>
</html>

