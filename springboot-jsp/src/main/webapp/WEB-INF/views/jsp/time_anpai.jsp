<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<HEAD>

<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

table {
	margin: 30px auto;
	width: 60%;
}

th {
	width: 150px;
	height: 50px;
	background-color: rgb(231, 245, 233);
}

td {
	width: 150px;
	height: 25px;
	text-align: center;
}

.nomean {
	width: 20px;
	padding: 5px 2px;
}
</style>

</HEAD>
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
  
   var zks

	$(document).ready(function() {
		$("td").css("word-break", "break-all")
		$("td").click(function() {
		
			if ($(this).hasClass("nomean")) {
				return;
			} else {
				if ($(this).hasClass("add")) {
					$(this).css("background", "white")
					$(this).removeClass("add")
					$(this).html("");
				} else {
				if($(".add").length==zks){
			alert("不能超过一周的总课时")
			return;
		}else{
					$(this).css("background", "red")
					$(this).attr("class", "add");
					//$(this).html("${user.name}</br>${user.role}")
				//	$(this).html("111")
				}
				}
			}
		});
	});
$(function(){
     $("#category").blur(function(){
     			var clazz_name = $("#category").val()
     			$.ajax({
     		     	url : "findZks.action",
					type : "post",
					data : {
						"clazz_name" : clazz_name
					},
     				success : function(data){
     				
     				  zks=data
     				  alert("系统检测到   "+clazz_name+"   一周总课时为   "+zks+"   课时，请在下表选择一周上课时间")
     				}
     			});
     });
});

	$(function(){
			$(".button_ok").click(function(){
			//console.log($("#all .add").length)
			var clazz_name = $("#category").val()
			if ($("#all .add").length == 0) {
				$.ajax({
					url : "clickAutoPiKe.action",
					type : "post",
					data : {
						"clazz_name" : clazz_name
					},
					success : function(data) {
						//	console.log(data)
						var values = $.parseJSON(data)
						//	console.log(values)
						//	var classElements =getElementsByClassName(add)
	                                 var classElements = []
												var allElements = document
														.getElementsByTagName('td');
												for (var i = 0; i < allElements.length; i++) {
													if (allElements[i].className == "add") {
														classElements.push(allElements[i]);
													}
												}
											//	for (i in values) {
													//alert(i.lesson_name)
												//	var value = values[i]
											
													/* for(var i = 0;i<values.length;i++){
													var j = values[i].zks
													if(j>1){
														for(var u=i;u<j+i;u++){
														$(classElements[u]).html(values[i].lesson_name+"<br>"+values[i].teacher_name+"<br>"+values[i].classroom_name)
														r++
														}
													i=r      
													}else{
															$(classElements[i]).html(values[i].lesson_name+"<br>"+values[i].teacher_name+"<br>"+values[i].classroom_name)
													}
													
												
													} */
														var r=0,i=0,x=0;
														console.log(zks)
														while(i<zks){
														var j = values[x].zks;
														if(j>1){
															for(var u=i;u<j+i;u++){
																$(classElements[u]).html(values[x].lesson_name+"<br>"+values[x].teacher_name+"<br>"+values[x].classroom_name)
																r++;
															}
																i=r;
																x++;
														}else{
															$(classElements[i]).html(values[x].lesson_name+"<br>"+values[x].teacher_name+"<br>"+values[x].classroom_name)
															i++;
															r++;
															x++;
														}
														}
											}
										});
							}
						});
	});
	$(function(){
	$(".button_save").click(function(){
	if(confirm("确认保存吗")){
		if($("td").hasClass("add")){
		$("td").css("background", "white")
		$("td").removeClass("add")
		}
		var teachplan_content = $("#pike_content").html();
		var clazz_name = $("#category").val();
		$.ajax({
		        	url : "savePiKeContent.action",
					type : "post",
					data:{"teachplan_content":teachplan_content,"clazz_name":clazz_name},
					success:function(data){
								if(data=="success"){
									alert("保存成功")
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
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="2"
					height="26"><strong><STRONG>选择需要排课的班级</STRONG> </strong></th>
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
			</tr>
		</table>
		<div id="pike_content">
		<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
			border="1" id="DataGrid1">
			<!-- <tr>
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="9"
					height="26"><strong><STRONG>选择一周上课时间</STRONG> </strong></th>
			</tr> -->
			<tr>
				<th colspan="2" align="center" class="nomean"></th>
				<th align="center" class="nomean">星期一</th>
				<th align="center" class="nomean">星期二</th>
				<th align="center" class="nomean">星期三</th>
				<th align="center" class="nomean">星期四</th>
				<th align="center" class="nomean">星期五</th>
				<th align="center" class="nomean">星期六</th>
				<th align="center" class="nomean">星期日</th>
			</tr>
			<tr>
				<td rowspan="2" class="nomean">上午</td>
				<td class="nomean">一</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean">二</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td rowspan="2" class="nomean">下午</td>
				<td class="nomean">三</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean">四</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td rowspan="2" class="nomean">晚上</td>
				<td class="nomean">五</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean">六</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		</div>
		<table cellspacing="0" cellpadding="1" rules="all" bordercolor="gray"
			border="1" id="DataGrid1">
			<tr >
				<td style="WIDTH: 100%" align="center" bgColor="#f5fafe" colspan="4"
					class="nomean"><input type="button" class="button_ok"
					value="开始排课"></td>	
					<td style="WIDTH: 100%" align="center" bgColor="#f5fafe" colspan="5"
					class="nomean"><input type="button" class="button_save"
					value="确认保存"></td>	
			</tr>
		
			</table>
		
	</form>
</body>
</html>
