<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath}/css/Style2.css"
	type="text/css" rel="stylesheet">
<style type="text/css">
	*{
	padding:0;
	margin:0;
	}
	table{
	margin:30px auto;
	width:60%
	}
	th{
	width:150px;
	height:50px;
	background-color:rgb(231,245,233);
	}
	td{
	width:50%;
	height:25px;
	text-align:center;
	}
	
</style>

</HEAD>

<body>
	<form id="userAction_save_do" name="Form1"action="${pageContext.request.contextPath}/addClazz.action" method="post"  >
		&nbsp;
		<table cellSpacing="1" cellPadding="5" width="" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>   
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
					height="26"><strong><STRONG>添加班级信息</STRONG> </strong>
				</th>
			</tr>
 
 
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01"  >班级名称：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"	name="classname" class="bg"/>
				</td>
				
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01" >班级人数：</td>
				<td class="ta_01" bgColor="#ffffff"><input type="text"	name="classpreson" class="bg" />
				</td>
				
			</tr>
		
			
			<TR>
				<td align="center" colSpan="4" class="sep1"><img
					src="${pageContext.request.contextPath}/images/shim.gif">
				</td>
			</TR>


			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="4">
					
					
						
					<input type="submit" class="button_ok" value="确定">	
						
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					
					
					
					<input type="reset" value="重置" class="button_cancel">

					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1">
					
					</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</HTML>
