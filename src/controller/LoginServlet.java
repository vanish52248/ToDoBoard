package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import exception.TaskException;
import model.Account;

@WebServlet ("/LoginServlet")
public class LoginServlet extends HttpServlet {
	protected void doPost (
			HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		//このサーブレットのdoPostメソッドはログイン用に使用する

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nextPage = null;

		try {
			AccountDao accountDao = new AccountDao();
			Account account = accountDao.doLogin(id, password);
		//ログイン処理（DBにIDとパスワードの組み合わせが該当するかチェック）
		//ログインできない場合はTaskExceptionを発生させる
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
		//ログインできている場合はログインしたアカウント情報をセッションにセット
		//ログイン状態とみなす

			nextPage = "TaskServlet";
		//タスク一覧の表示へ遷移する準備

		} catch (TaskException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
		//ログインできなかった場合
		//→エラーメッセージを取得→セットしてlogin.jspに表示

			nextPage = "login.jsp";
		//ログイン失敗してるのでログイン画面へ遷移する準備
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		//上の処理でログイン成功or失敗でそれぞれ代入された次の画面に遷移する
	}

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//このサーブレットのdoGetメソッドはログアウト用に使用する

		HttpSession session = request.getSession(false);
		session.invalidate();
		//セッションの情報を破棄する処理

		request.setAttribute("message", "ログアウトしたよ");
		//login.jsp に表示するメッセージをセットする

		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
		//login.jspを表示する処理
	}
}
