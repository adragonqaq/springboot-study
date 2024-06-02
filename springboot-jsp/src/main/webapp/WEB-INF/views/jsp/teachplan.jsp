<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("td").css("word-break","break-all")
	$("td").click(function (){
	     if($(this).hasClass("nomean")){
		    return;
		 }else{
	        if($(this).hasClass("add")){
				$(this).css("background","white")
				$(this).removeClass("add")
				$(this).html("");
			}else{
			$(this).css("background","red")
			$(this).attr("class","add");
			$(this).html("${user.name}</br>${user.role}")
			
		}
		}
	});
});
</script>
<body>
	 <form action="${pageContext.request.contextPath}/addTeachPlan.action" method="post">
	 <table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<tr>   
				<th class="ta_01" align="center" bgColor="#afd1f3" colSpan="2"
					height="26"><strong><STRONG>添加教学计划</STRONG> </strong>
				</th>
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
			<tr>
				<td align="center" bgColor="#f5fafe" class="nomean" >课程</td>
				<td class="nomean" bgColor="#ffffff">
				<select name="lesson_name" id="category">
										<option value="" selected="selected">--选择课程--</option>
										<c:forEach var="lesson" items="${lessonList }">
										<option value="${lesson.lessonname }">${lesson.lessonname }</option>
										</c:forEach>	
								</select></td>
				
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="nomean" >教师</td>
				<td class="nomean" bgColor="#ffffff">
				<select name="teacher_name" id="category">
										<option value="" selected="selected">--选择教师--</option>
										<c:forEach var="teacher" items="${teacherLsit }">
										<option value="${teacher.teachername }">${teacher.teachername }</option>
										</c:forEach>
								</select></td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="nomean" >课程周课时：</td>
				<td class="nomean" bgColor="#ffffff"><input type="text"	name="zks" class="bg" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="nomean" >教室</td>
				<td class="nomean" bgColor="#ffffff">
				<select name="classroom_id" id="category">
										<option value="" selected="selected">--不指定教室--</option>
										<option value="体育馆">体育馆</option>
								</select></td>
			</tr>
			<TR>
				<td class="nomean" align="center" colSpan="2" class="sep1" ><img
					src="${pageContext.request.contextPath}/images/shim.gif">
				</td>
			</TR>
			 <tr>
				<td  style="WIDTH: 100%" align="center"
					bgColor="#f5fafe" colSpan="2" class="nomean">
					<input type="submit" class="button_ok" value="确定" >	
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
					<input type="reset" value="重置" class="button_cancel">
					<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT> <INPUT
					class="button_ok" type="button" onclick="history.go(-1)" value="返回" />
					<span id="Label1">
					</span>
				</td>
			</tr>
		</table>
	<!-- 	<strong align="center">选择一周上课时间</strong> -->
      <!--  <table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1">
			<tr >
				<th colspan="2" align="center" ></th>
				<th align="center" >星期一</th>
				<th align="center" >星期二</th>
				<th align="center" >星期三</th>
				<th align="center" >星期四</th>
				<th align="center" >星期五</th>
				<th align="center" >星期六</th>
				<th align="center" >星期日</th>
			</tr>
			<tr >
				<td rowspan="2" class="nomean" >上午</td>
				<td class="nomean" >一</td>
				<td ></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean" >二</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr >
				<td rowspan="2" class="nomean" >下午</td>
				<td class="nomean" >三</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean" >四</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr >
				<td rowspan="2" class="nomean" >晚上</td>
				<td class="nomean" >五</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td class="nomean" >六</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
	   </table> -->
<!-- 	    <table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
	  
	   </table> -->
    </form>
</body>
</html>