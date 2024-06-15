<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/admin/css/Style2.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/admin/js/public.js"></script>
<script type="text/javascript">

	function deletep(){
	
	alter("确定删除吗")
	
	//delete.product?id=${pro.id }
	}
	function delBook(id){
	
	  if(confirm("是否确定删除？")){
	    
	      location.href="deleteUserById.action?id="+id;
	      alert("删除成功")
	  }
	
	}

</script>
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
<!-- ../products/edit.jsp -->
	<br>
	<form id="Form1" name="Form1" action="findbyother.product"
		method="post"  onsubmit="return checkForm()">
		<table cellSpacing="1" cellPadding="5" width="100%" align="center"
			bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
			<TBODY>
				<tr>
					<th class="ta_01" align="center" ><strong>用户列表</strong>
					</Th>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1">
							<tr>
								<th align="center" width="24%">用户姓名</th>
								<th align="center" width="18%">用户权限</th>
								<th width="8%" align="center">编辑</th>
								<th width="8%" align="center">删除</th>
							</tr>
	    <c:forEach var="user" items="${userList }">				 	
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%">${user.name }</td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="8%">${user.role }</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
	<a href="showUserById.action?id=${user.id }"><img	src="images/i_edit.gif"	border="0" style="CURSOR: hand"> </a>
									</td>
									<td align="center" style="HEIGHT: 22px" width="7%">
	<a   href="javascript:delBook('${user.id }')" ><img	src="images/i_del.gif"	width="16" height="16" border="0" style="CURSOR: hand">
									</a>
									</td>
								</tr>
							</c:forEach>	
						</table>
					</td>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

