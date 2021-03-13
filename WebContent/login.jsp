<%@ page import="model.Account" %>

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
		<jsp:include page="common/navi.jsp"/>
<!-- 共通パーツ（ナビ）読み込み -->
		<div class="login align-items-center py-5">
				<div class="container">
				<div class="row">
					<div class="col-md-9 col-lg-8 mx-auto">
						<h3 class="login-heading my-4">Login</h3>
						<%
							String message = (String)request.getAttribute("message");
							String error = (String)request.getAttribute("error");
				//サーブレットでメッセージの設定処理を入れていない場合でも影響はない
				//ログイン・ログアウト結果の表示処理
				// 処理メッセージとエラー判定を取得

				//正常終了した場合のメッセージを表示
							if(message != null && error == null) { %>
								<div class="alert alert-success" role="alert">
									<%= message %>
								</div>
						<%
							// 異常終了した場合のメッセージを表示
							} else if(message != null && error != null) { %>
								<div class="alert alert-danger" role="alert">
									<%= message %>
								</div>
						<% } %>
						<form action="LoginServlet" method="post">
							<!-- ログインID -->
							<div class="form-label-group">
								<input type="text" id="id"
									name="id"
									class="form-control">
								<label for="id">ID</label>
							</div>
							<!-- パスワード -->
							<div class="form-label-group">
								<input type="password" id="password"
									name="password"
									class="form-control">
								<label for="passowrd">Password</label>
							</div>
							<button class="btn btn-lg btn-primary btn-block btn-login font-weight-bold mb-2"
								type="submit">
								ログイン
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 共通パーツ（フッター）読み込み -->
		<jsp:include page="common/footer.jsp"/>
	</body>
</html>