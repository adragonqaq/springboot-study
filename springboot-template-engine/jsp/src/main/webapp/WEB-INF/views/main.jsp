<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>自动排课系统</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function.js"></script>
<script type="text/javascript">
function changeImage() {

		document.getElementById("img").src = "${pageContext.request.contextPath}/imageCode?time="
				+ new Date().getTime();
	}
</script>
	<style type="text/css">
		*{
			padding: 0;
			margin: 0;
		}
		body,html{
			width: 100%;
			height: 100%;
		}
		#heading{
			display: table;
			width: 100%;
			height: 15%;
			text-align: center;
			background-color:rgb(231,245,233);
			
		}
		h1{
			display: table-cell;
			vertical-align: middle;
			font:bold 50px "微软雅黑";
			letter-spacing: 10px;
			
		}
		#menu{
	position: absolute;
	top:14.9%;
	width: 100%;
	height: 25px;
	background-color: #eee;
}
#time{
	position:absolute;
	left: 20px; 
	font:14px/25px "黑体";
}
#status{
	position:absolute;
	left: 80%;
	font:16px/25px "黑体";
}
#right{
	position: absolute;
	right: 20px;
	font:14px/25px "黑体";
	list-style: none;
}
#right li{
	float: right;
	padding:0 5px;
}
		
	</style>
</head>
<body>
	<div id="heading"><h1>自动排课系统</h1></div>
	<div id="menu"><span id="time"></span>
		<ul id="right">
			
			<li><a href="register.jsp">注册</a></li>
			<li><a href="main.jsp">登陆</a></li>
		</ul>
	</div>

	<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		
		<div class="box">
			<h1>欢迎登录</h1>
			<form id="loginForm" method="post" action="userLogin.action" onsubmit="return checkForm(this)">
				<table>
				<tr>
				<td colspan="2" style="text-align:center;padding-top:20px;"><font color="red">${login_state }</font></td>
				</tr>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="name" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="password" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
						<td style="color: red">${ckcode_msg }</td>
					</tr>
					<tr>
						<td></td>
						<td><img id="img" src="${pageContext.request.contextPath}/imageCode"  width="180"
									height="30" class="textinput" style="height:30px;"/>&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2018 廖智龙 All Rights Reserved.
</div>

</body>
</html>