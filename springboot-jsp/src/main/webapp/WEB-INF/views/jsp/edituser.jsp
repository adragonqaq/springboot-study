<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"  prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/admin/css/Style2.css"
	type="text/css" rel="stylesheet">
<style type="text/css">
	*{
	padding:0;
	margin:0;
	}
	table{
	margin:30px auto;
	width:60%;
	}
	th{
	width:150px;
	height:35px;
	background-color:rgb(231,245,233);
	}
	td{
	width:150px;
	height:25px;
	text-align:center;
	}
</style>
</HEAD>
<body>
	<form 	action="${pageContext.request.contextPath}/updateUserById.action" method="post">
	
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong>编辑用户信息</strong></th>
			</tr>
			<tr>
			   <input type="hidden" name="id" value="${user.id }"/>
				<td align="center" bgColor="#f5fafe" class="ta_01">用户姓名：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="name" class="bg" value="${user.name}" /></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">用户密码：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"
					name="password" class="bg" value="${user.password }" /></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">用户权限：</td>
				<td class="ta_01" bgColor="#ffffff"><select name="role"	id="category">
						<option value="${user.role }" selected="selected">${user.role }</option>
						<option value="管理员">管理员</option>
						<option value="普通用户">普通用户</option>
				</select></td>
			</tr>
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="images/shim.gif">
				</td>
			</TR>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					<input type="submit" class="button_ok" value="确定"> 
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1"> </span></td>
			</tr>
		</table>
	</form>
</body>
</HTML>