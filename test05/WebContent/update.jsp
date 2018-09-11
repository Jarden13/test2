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
<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<%
		Employee emp = (Employee) request.getAttribute("emp");
	%>
	<div id="main">
		<form action="Employee" class="form-horizontal" role="form"
			method="post">
			<input type="hidden" name="type" value="update" />
			<input type="hidden" name="id" value="<%=emp.getId() %>" />
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
					<input type="radio" name="employeeSex"  <%if(emp.getSex().equals("男")){ %>checked<%} %> value="男">男
					<input type="radio" name="employeeSex" value="女"<%if(emp.getSex().equals("女")){ %>checked<%} %> id="sex">女

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

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">
						保存
						<tton>
				</div>
			</div>
		</form>
	</div>

</body>
</html>