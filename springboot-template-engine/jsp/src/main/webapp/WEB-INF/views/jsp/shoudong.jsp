<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<HEAD>
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
/* $(document).ready(function(){
	$("td").css("word-break","break-all")
	$("td").click(function (){
	     if($(this).hasClass("nomean")){
		    return;
		 }else{
	        if($(this).hasClass("on")){
				$(".on").css("background","white")
				$(".on").removeClass("on")
			}else{
			$(this).css("background","red")
			$(this).attr("class","on");
			setTimeout(changeVal,500);
		}
		}
	});
}); */
function changeVal(){
            if($(".on").length==2){
			    if(confirm("确认换课吗")){
				var numb=$(".on")[0].innerHTML
				$(".on")[0].innerHTML=$(".on")[1].innerHTML
				$(".on")[1].innerHTML=numb
				$(".on").css("background","white")
				$(".on").removeClass("on")
				}else{
				$(".on").css("background","white")
				$(".on").removeClass("on")
				}
			}else if($(".on").length>2){
			    alert("不能同时选择多个")
			    $(".on").css("background","white")
				$(".on").removeClass("on")
			}
}
$(function(){
	$(".button_find").click(function(){
	  var clazz_name = $("#category").val();
	   $("#pike_content").html("");
	  $.ajax({
	  		url:"showPiKeByClazzName.action",
	  		type:"post",
	  		data:{"clazz_name":clazz_name},
	  		success:function(data){
	  			 $("#pike_content").html(data);
	  		}
	  })
	});
});
$(function(){
		$("#pike_content").click(function(e){
		var x=e.target
			  if($(x).hasClass("nomean")){
		    return ;
		 }else{
	        if($(x).hasClass("on")){
				$(".on").css("background","white")
				$(".on").removeClass("on")
			}else{
			$(x).css("background","red")
			$(x).attr("class","on");
			setTimeout(changeVal,500);
	         	}
		    }
		});
});

$(function(){

	$(".button_save").click(function(){

	if(confirm("确认修改并保存吗")){
	var teachplan_content = $("#pike_content").html();
		var clazz_name = $("#category").val();
		$.ajax({
			url:"updatePiKeByClazzName.action",
			type:"post",
			data:{"teachplan_content":teachplan_content,"clazz_name":clazz_name},
			success:function(data){
			  if(data=="ok"){
			  alert("修改成功")
			  }
			}
		})
	
	
	}
	
	});





});
</script>
<body>
	 <form action="" method="post"> 
	 
	 <table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="3"
					height="26"><strong><STRONG>选择需要手动调整的班级</STRONG> </strong></th>
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
								<input type="button" value="保存" class="button_save"></input>
								</td>
			</tr>
		</table>
		<div id="pike_content"></div>
       
	   
    </form>
</body>
</html>
