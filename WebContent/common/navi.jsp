<%@ page import="model.Account" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">
<!-- ナビゲーション -->

		<a class="navbar-brand" href="login.jsp">ToDoBoard</a>
		<!-- ログインページへのリンク -->

		<%
			Account account = (Account)session.getAttribute("account");
			if(account != null) {
		%>
		<!-- ログアウト用のサーブレットへのリンク -->
		<!-- リンクをクリックすると各サーブレットのdoGetメソッドが実行される -->
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
						<a class="nav-link" href="TaskServlet?list=true">タスクの一覧</a>
					</li>
					<li class="nav-item active">
						<a class="nav-link" href="LoginServlet">ログアウト</a>
					</li>
				</ul>
				<div class="navbar-text">
					ログインユーザ名：<%=((Account)session.getAttribute("account")).getId() %>
					<!--ログインユーザー名の表示 -->
				</div>
			</div>
		<% } %>
	</div>
</nav>