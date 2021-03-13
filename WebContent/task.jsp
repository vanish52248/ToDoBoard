<%@ page import = "java.util.List" %>
<%@ page import = "model.Task" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoBoard</title>
	<jsp:include page="common/header.jsp"/>
<!-- 共通パーツ（ヘッダー）読み込み -->
</head>
<body>
		<jsp:include page="common/navi-list.jsp"/>
<!-- 共通パーツ（ナビ）読み込み -->
<section class = "py-5">
	<div class = "container">
		<h1 class = "my-4">タスク一覧</h1> <!-- タスク一覧 -->
		<table class = "table table-hover"> <!-- 見出し部分 -->
			<thead class = "thead-dark">
				<tr>
					<th scope = "col">タスク名</th>
					<th scope="col">詳細</th>
					<th scope="col">管理</th>
				</tr>
			</thead>
			<tbody>
					<% List<Task> taskList = (List<Task>)request.getAttribute("taskList"); %>
					<% for(Task task : taskList) { %>
			<!-- タスク一覧部分 -->
			<!-- データベースから取得したタスクの情報一覧をリクエストスコープから取得 -->
						<tr>
							<td><%= task.getTaskTittle() %></td>
							<td><%= task.getTaskText() %></td>
							<td>
								<a class="btn btn-primary"
								href="TaskServlet?TaskId=<%= task.getTaskTittle()%>">削除する</a>
<!-- 								jspの中に直接mysqlnのdelete文記述したい -->
							</td>
						</tr>
					<% } %>
					</tbody>
				</table>
			</div>
		</section>
		<jsp:include page="common/footer.jsp"/>
		<!-- 共通パーツ（フッター）読み込み -->
</body>
</html>