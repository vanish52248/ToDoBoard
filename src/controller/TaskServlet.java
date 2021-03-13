package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TaskDao;
import exception.TaskException;
import model.Account;
import model.Task;
import model.TaskMemo;

@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet{
	//タスク画面のサーブレットクラス
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//ログイン処理後に連動して動く処理
		//LoginServlet が doPost からここに遷移するのでここも合わせてdoPost
		String nextPage = null;
		//画面遷移用の変数
		try {
			TaskDao taskDao = new TaskDao();
			List <Task> taskList = taskDao.findAllTask();
		//タスク一覧の取得

			request.setAttribute("taskList", taskList);
		//取得したタスク一覧をリクエストスコープにセット

			nextPage = "task.jsp";
		//タスク一覧画面を表示する準備
		} catch(TaskException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
		//一覧表示中に例外が発生した場合はログイン画面に遷移させる
		//一覧が表示できない可能性も考慮する為

			nextPage = "login.jsp";
		//ログイン画面を表示する準備
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		//上の処理でnextPageに代入された画面へ遷移する
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//新規登録を表示するために使用するメソッド


		String list = request.getParameter("list");

		if (list == null) {
			HttpSession session = request.getSession(false);
			Account account = (Account)session.getAttribute("account");
			TaskMemo taskMemo = null;
			//ログインしているアカウントの情報を取得
			//DBの登録（更新の際に使用する）
				String id = account.getId();
				String password = account.getPassword();
				taskMemo = new TaskMemo(id, password);
			//タスクタイトルが指定されていない場合は新規登録
			//アカウントの情報だけセット（そのほかの情報は空）
				request.setAttribute("taskMemo", taskMemo);
				//次の画面に情報をセット
			RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
			rd.forward(request, response);
			//次の画面に遷移

		} else {
			doPost(request, response);
			//back のリクエストがある場合は一覧画面を表示
			//doPostメソッドに処理を移るようにする

		}
	}



}
