<%@ page language="java" import="java.util.*,com.xiaoluo.model.User" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>个人聊天页面</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	</head>
	<body>
<%
User user = (User)request.getSession().getAttribute("user");
if(user==null){
%>
		<jsp:forward page="indexfindex.action"></jsp:forward>
		
		<%
}
else{%>
	<jsp:forward page="indexindex.action"></jsp:forward>
	<%
}
		
		%>
	</body>
</html>

