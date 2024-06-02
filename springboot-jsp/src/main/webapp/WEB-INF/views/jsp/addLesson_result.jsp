<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<style>
.msg { text-align:center; padding:20px; }
</style>
</head>
<body>
<div class="msg">
				<p>添加成功！</p>
				<p>正在返回上一页...</p>
				<script type="text/javascript">
					setTimeout("location.href='jsp/lesson.jsp'", 2000);
				</script>
			</div>
<body>		
</html>
