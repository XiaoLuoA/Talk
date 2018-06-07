<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	
	<link rel="stylesheet" type="text/css" href="css/changepsd.css">
	

  </head>
  
  <body>
  <div class="top"></div>   
    <div class="form">
     <div class="text">修 改 密 码</div>
  <form id="changeForm" action="userchangePass.action" method="post">
    <table>
      <tr>
       <td>
         <div class="td">
          <input type="password" class="input" name="password" required="required"placeholder="密码" />
         </div>
       </td>
      </tr>
      <tr>
       <td>
         <div class="td">
          <input type="password" class="input" name="password" required="required"placeholder="重复密码" />
         </div>
       </td>
      </tr>
    </table>  	   
  	  <input  class="button" type="submit" value="确定" />
  </form>
  </div>
  <script type="text/javascript" src="jquery/jquery.min-v1.12.4.js" ></script>
	<script type="text/javascript" src="jquery/jquery.form.min.js" ></script>
	<script type="text/javascript" src="layer/layer/layer.js"></script>
    <script type="text/javascript">
    
		$(document).ready(function() {
			$("#changeForm").ajaxForm(
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
							 ,content: '修改成功！',
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
	</script>
  </body>
</html>
      