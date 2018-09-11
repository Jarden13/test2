<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="Dao.*"%>
<%@ page import="entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js">
	
</script>
<script type="text/javascript">
	$().ready(function() {
		$("#save").click(function() {
			var emps = ""
			$(".emp").each(function(index, element) {
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=employeeName]").val();
				var sex = $(this).find("[name=employeeSex]:checked").val();
				var age = $(this).find("[name=employeeAge]").val();
				var dep =$(this).find("[name=employeeDep]").val();
				emps += id + "," + name + "," + sex + "," + age +","+dep+ ";";
			})
			emps = emps.substring(0, emps.length - 1);
			//alert("Employee?type=updateBatch2&emps=" + emps);
			window.location.href = "Employee?type=updateBatch2&emps=" + emps;
		})
	});
</script>
<style type="text/css">
#main {
	width: 900px;
	margin: 20px auto;
}

.emp {
	width: 400px;
	float: left;
	margin: 10px 50px 10px 0;
	border: 1px dashed #ccc;
	padding: 10px 10px 10px 0;
}

#saveBtn {
	clear: both;
	text-align: center;
}
</style>
</head>
<body>
	<%
		List<Employee> list = (List<Employee>) request.getAttribute("list");
	%>
	<div id="main">
		<%
			for (Employee emp : list) {
		%>
		<form action="Employee" class="form-horizontal emp" method="post">
			<input type="hidden" name="type" value="updateBatch1" /> <input
				type="hidden" name="id" value="<%=emp.getId()%>" />
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="employeeName"
						value="<%=emp.getName()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" name="employeeSex"
						<%if (emp.getSex().equals("男")) {%> checked <%}%> value="男">男
					<input type="radio" name="employeeSex" value="女"
						<%if (emp.getSex().equals("女")) {%> checked <%}%> id="sex">女

				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="employeeAge"
						value="<%=emp.getAge()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="employeeDep"
						value="<%=emp.getDep().getId()%>">
				</div>
			</div>


		</form>
		<%
			}
		%>
		<div id=saveBtn>
			<button id="save" type="button" class="btn btn-primary">保存
		</div>
	</div>

</body>
</html>