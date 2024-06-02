<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
  <style>
        form{
            position: absolute;
            width: 300px;
            height: 100px;
            padding:50px;
            background-color: #eee;
            border:1px solid #999;
            top:20%;
            left: 25%;
            background-color: skyblue;
        }
        span{
            position: absolute;
            margin: 15px;
            left: 15px;
        }
        .ues{
        	top:30px;
        }
        input{
            position: absolute;
            width: 150px;
            height: 20px;
            margin: 15px;
            right:100px;
        }
        #sub{
            position: absolute;
            width: 80px;
            height: 30px;
            left: 15px; 
            bottom:20px;
        }
        #reset{
            position: absolute;
            width: 80px;
            height: 30px;
            right:25px;
            bottom:20px;
        }
    </style>
</head>
<body>
    <form action="userLogin.action" method="post">
    <span class="ues">用户名：</span><input type="text" name="name" class="ues" ><br>
    <span>密码：</span><input type="password" name="password"><br>
    <input type="submit" value="登录" id="sub">
    <input type="reset" id="reset">    
    </form>
</body>
</html>