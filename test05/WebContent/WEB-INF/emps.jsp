<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="Dao.*"%>
<%@ page import="entity.*"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">

<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}

#emp .select {
	background: #337ab7
}
</style>
</head>
<body>
	<%
		List<Employee> list = (List<Employee>) request.getAttribute("emps");
	int jiluyeshu=(int)request.getAttribute("jiluyeshu");
	int count=(int)request.getAttribute("count");
	int xianshiyeshu=(int)request.getAttribute("xianshiyeshu");
	%>
	

	
	<div id="main">
	
	
	
	
		<table id="emp" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>

					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>修改</th>

				</tr>
			</thead>
			<tbody id="tbody">
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr>
				<tr data-id="<%=list.get(i).getId()%>">

					<td name="name"><%=list.get(i).getName()%></td>
					<td name="sex"><%=list.get(i).getSex()%></td>
					<td name="age"><%=list.get(i).getAge()%></td>
					<td name="dep" data-did=<%=list.get(i).getDep().getId()%>><%=list.get(i).getDep().getName()%></td>
					<td><input type="button" value="编辑"></td>

				</tr>
				<%
					}
				%>
			</tbody>
		</table>
<ul class="pagination fy">
	<li><a>上一页</a></li>
	<li><a><%=xianshiyeshu-2%></a></li>
	<li><a><%=xianshiyeshu-1 %></a></li>
	<li><a><%=xianshiyeshu %></a></li>
	<li><a><%=xianshiyeshu+1 %></a></li>
	<li><a><%=xianshiyeshu+2 %></a></li>
	<li><a>下一页</a></li>
</ul><br>
		<button id="showAdd" type="button" class="btn btn-primary">增加</button>
		<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
		<button id="delete" type="button" class="btn btn-primary">删除</button>
		<button id="showUpdateBatch1" type="button" class="btn btn-primary">批量修改1</button>
		<button id="showUpdateBatch2" type="button" class="btn btn-primary">批量修改2</button>
		<button id="deleteBatch" type="button" class="btn btn-primary">批量删除</button>
		<button id="save" type="button" class="btn btn-primary">保存</button>
	</div>
</body>
</html>
<script type="text/javascript" src="js/jquery.js">
	
</script>
<script type="text/javascript">
	$()
			.ready(
					function() {
						var selectId = -1;
						$("#showAdd").click(function() {
							location.href = "Employee?type=showEmployee"
						});
						$("#showUpdate")
								.click(
										function() {

											if (selectId > -1) {
												location.href = "Employee?type=showUpdate&id="
														+ selectId
											}

											else {
												alert("请选中一条数据！");
											}

										});
						$("#delete")
								.click(
										function() {

											if (selectId > -1) {
												location.href = "Employee?type=delete&id="
														+ selectId
											}

											else {
												alert("请选中一条数据！");
											}

										});
						function doBatch(type) {
							var length = $("#emp .select").length;
							var ids = new Array();
							if (length > 0) {
								$("#emp .select").each(
										function(index, element) {
											ids.push($(this).data("id"));
										})

								location.href = "Employee?type=" + type
										+ "&ids=" + ids
							} else {
								alert("请选数据！");
							}

						}
						$("#deleteBatch").click(function() {
							doBatch("deleteBatch")
						});

						$("#showUpdateBatch1").click(function() {
							doBatch("showUpdateBatch1")
						});
						$("#showUpdateBatch2").click(function() {
							doBatch("showUpdateBatch2")
						});
						$("#tbody").on("dblclick", "tr", function() {
							var oldVal = $(this).text;

						})
						$("input:button")
								.click(
										function() {

											str = $(this).val() == "编辑" ? "确定"
													: "编辑";

											$(this).val(str); // 按钮被点击后，在“编辑”和“确定”之间切换

											$(this).parent().parent().addClass(
													"modified");
											$(this)
													.parent()
													.siblings("td")
													.each(
															function() { // 获取当前行的其他单元格

																obj_text = $(
																		this)
																		.find(
																				"input:text"); // 判断单元格下是否有文本框

																if (!obj_text.length) // 如果没有文本框，则添加文本框使之可以编辑

																	$(this)
																			.html(
																					"<input type='text' value='"
																							+ $(
																									this)
																									.text()
																							+ "'>");

																else
																	// 如果已经存在文本框，则将其显示为文本框修改的值

																	$(this)
																			.html(
																					obj_text
																							.val());

															});

										});
						$("tr").click(function() {

							$(this).toggleClass("select");
							//selectId = $("this").children().eq(0).text();
							selectId = $(this).data("id");

						});
						$("#save")
								.click(
										function() {
											if ($(".modified").length > 0) {
												var emps = "";
												$(".modified")
														.each(
																function() {
																	var id = $(
																			this)
																			.data(
																					"id");
																	var name = $(
																			this)
																			.find(
																					"[name=name]")
																			.text();
																	var sex = $(
																			this)
																			.find(
																					"[name=sex]")
																			.text();
																	var age = $(
																			this)
																			.find(
																					"[name=age]")
																			.text();
																	var dep = $(
																			this)
																			.find(
																					"[name=dep]")
																			.data(
																					"did");
																	emps += id
																			+ ","
																			+ name
																			+ ","
																			+ sex
																			+ ","
																			+ age
																			+ ","
																			+ dep
																			+ ";";
																});
												emps = emps.substring(0,
														emps.length - 1);
												window.location.href = "Employee?type=updateBatch2&emps="
														+ emps;
											}

										});
						var count=<%=count%>;
						var jiluyeshu=<%=jiluyeshu%>;
						$('.fy li').click(function(){
							if(($(this).text()=="上一页")&&(jiluyeshu>1)){
								jiluyeshu=jiluyeshu-1;	
							}else if(($(this).text()=="上一页")&&(jiluyeshu=1)){
								jiluyeshu=1;
							}
							if(($(this).text()=="下一页")&&(jiluyeshu<(count/2))){
								jiluyeshu=jiluyeshu-(-1);
							} else{
								jiluyeshu=jiluyeshu;
							}
							if(($(this).text()!="上一页")&&($(this).text()!="下一页")){
									jiluyeshu=$(this).text();
								}
							location.href="Employee?bianliang="+jiluyeshu;
						})
					});

		$(".fy li").each(function(){			
		    if($(this).text()==<%=jiluyeshu%>){
		    	$(this).find("a").css("background-color", "orangered");
		    }
		
		});
</script>