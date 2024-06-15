<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<HEAD>
<title>教学安排表</title>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	height:50px;
	background-color:rgb(231,245,233);
	}
	td{
	width:150px;
	height:25px;
	text-align:center;
	}
	.nomean{
	width:20px;
	padding:5px 2px;
	}
</style>

</HEAD>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

$(function(){
	$(".button_find").click(function(){
	  var clazz_name = $("#category").val();
	   $("#pike_content").html("");
	  $.ajax({
	  		url:"showKeBiaoByClazzName.action",
	  		type:"post",
	  		data:{"clazz_name":clazz_name},
	  		success:function(data){
	  			 $("#pike_content").html(data);
	  		}
	  })
	});
});

function doPrint() { 
   if($("#pike_content").html()==""||$("#pike_content").html()==null){
       alert("没有需要打印的内容")
       return;
   }  
    bdhtml=window.document.body.innerHTML;   
    sprnstr="<!--startprint-->";   
    eprnstr="<!--endprint-->";   
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);   
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));   
    window.document.body.innerHTML=prnhtml;  
    window.print();   
    location.href="enterShowKeBiao.action";
}   
</script>
<body>
	 <form action="" method="post"> 
	 
	 <table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="3"
					height="26"><strong><STRONG>选择需要查看课表的班级</STRONG> </strong></th>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe"  class="nomean">班级</td>
				<td  bgColor="#ffffff" class="nomean">
				<select name="clazz_name" id="category">
										<option value="" selected="selected">--选择班级--</option>
										<c:forEach var="clazz" items="${clazzList  }">	
										<option value="${clazz.classname }">${clazz.classname }</option>
										</c:forEach>
									<%-- <option value="生活">${user.role }</option> --%>
								</select></td>
								<td align="center" bgColor="#f5fafe"  class="nomean">
								<input type="button" value="检索" class="button_find"></input>
								<input type="button" value="下载" class="button_download" onclick="doPrint()"></input>
								
								</td>
			 </tr>
		</table>
		<!--startprint--><!--注意要加上html里star和end的这两个标记--> 
		<div id="pike_content">
		
		</div>
		<!--endprint--> 
    </form>
</body>
</html>
