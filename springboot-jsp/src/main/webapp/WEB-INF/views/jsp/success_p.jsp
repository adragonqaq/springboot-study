<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>自动排课系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/dengluhou.css" charset="UTF-8" />
<script type="text/javascript" src="scripts/function.js"></script>
<script src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
Date.prototype.Format = function (fmt) { // author: meizz
		    var o = {
		        "M+": this.getMonth() + 1, // 月份
		        "d+": this.getDate(), // 日
		        "h+": this.getHours(), // 小时
		        "m+": this.getMinutes(), // 分
		        "s+": this.getSeconds(), // 秒
		        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
		        "S": this.getMilliseconds() // 毫秒
		    };
		    if (/(y+)/.test(fmt))
		        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		            return fmt;
		}
		$(function(){
			$.ajax({
				url:"user.php",
				type:"POST",
				success:function(data){
					$("#name").html(data)
				}
			})
			$("#content ul>a").toggle(function(){
				$(this).nextAll().show(100)
			},function(){
				$(this).nextAll().hide(100)
			})
			$("#content ul li a").click(function(){
				$("#content ul li a").css({"color":"#555"})
				this.style.color="green"
			})
			$("#time").html(""+new Date().Format("yyyy-MM-dd hh:mm:ss"))
			setInterval(function(){
				$("#time").html(""+new Date().Format("yyyy-MM-dd hh:mm:ss"))
			},1000)
			
		})
		
			function exitSys() {
		
		if (window.confirm("确认退出系统吗?")) {
			location.href="userExit.action";
		}
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
	<iframe src="" name="main"></iframe>
	<div id="content">
		
			<ul id="search"><a href="javascript:;" >操作课表</a>
			<li><a href="${pageContext.request.contextPath}/enterShowKeBiao.action" target="main">查看课表</a></li>
		</ul>
	</div>
	<div id="menu"><span id="time"></span>
		<ul id="right">
			<li><a href="javascript:void(0)" onclick="exitSys()">注销</a></li>
			<li>${user.name }您好</li>
			
		</ul>
	</div>
 
</body>
</html>