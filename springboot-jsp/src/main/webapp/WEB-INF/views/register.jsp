<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>自动排课系统</title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../scripts/function.js"></script>
<script type="text/javascript" src="../scripts/jquery.min.js" ></script>
<script type="text/javascript" src="../scripts/json2.js" ></script>
<script type="text/javascript">
function changeImage() {

		document.getElementById("img").src = "${pageContext.request.contextPath}/imageCode?time="
				+ new Date().getTime();
	}
$(function(){
			$(":text[name=name]").blur(function(){
					 var username = $(this).val();
					// var url="ajaxRegister.action";
					 	 /* 	$.ajax({
					 				url:"ajaxRegister.action",
					 				type:"POST",
					 				data:{"username":username},
					 				//dataType:"json",
					 				success:function(msg){
					 						alert(msg)
					 						var json=JSON.parse(msg)
					 						alert(json)
					 							$("#span_1").html(json);
					 			                             	},
					 				error:function(){
					 						alert("请求失败")
					 						}
					 		});  */
					 	/* $.post(url,
					 				function(data){
					 				var json = JSON.parse(data);
					 				alert(json)
					 				$("#span_1").html(json);
					 				}); */
					 				$.ajax({
					 						url:"ajaxRegister.action",
					 						type:"POST",
					 						data:{"username":username},
					 						success :function(data){
					 						  if(data=="用户名已存在"){
					 						   $("#span_1").html(data);
					 						   $("#span_1").css("color","red");
					 						  }else{
					 						   $("#span_1").html(data);
					 						   $("#span_1").css("color","green");
					 							}
					 						}
					 				
					 				
					 				});
					 				
			});
});
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
			<h1>欢迎注册</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="userRegister.action" onsubmit="return checkForm(this);">
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" name="name" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
						<td id="span_1"></td>
					</tr>
					<tr>
						<td class="field">登录密码：</td>
						<td><input class="text" type="password" id="passWord" name="password" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码：</td>
						<td><input class="text" type="password" name="rePassWord" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
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
						<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
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