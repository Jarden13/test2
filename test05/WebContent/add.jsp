<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js">
</script>
<style type="text/css">
#main{
width: 400px;
margin: 20px auto;
}</style>
</head>
<body>
<div id="main">
<form action="Employee" class="form-horizontal" role="form" method="post">
<input type="hidden" name="type" value="add"/>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">姓名</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="employeeName" 
				   placeholder="请输入姓名">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">性别</label>
		<div class="col-sm-10" >
			<input type="radio"  name="employeeSex" value="男" checked="checked">男
			<input type="radio" value="女" name="employeeSex" id="sex">女
			
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">年龄</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="employeeAge"
				   placeholder="请输入年龄">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname" class="col-sm-2 control-label">部门</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="employeeDep"
				   placeholder="请输入部门id">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">保存<tton>
		</div>
	</div>
</form>
</div>

</body>
</html>