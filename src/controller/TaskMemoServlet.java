package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TaskDao;
import model.Account;
import model.TaskMemo;

@WebServlet("/TaskMemoServlet")
public class TaskMemoServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			// セッションからアカウント情報を取得
			HttpSession session = request.getSession(false);
			Account account = (Account)session.getAttribute("account");
			String id = account.getId();
			String password = account.getPassword();

			request.setCharacterEncoding("UTF-8");
			String taskTittle = request.getParameter("taskTittle");
			String taskText = request.getParameter("taskText");
			String message = null;
		try {

				TaskDao taskDao = new TaskDao();
				// DAOクラスに渡すためにタスク情報クラスに値を格納
				TaskMemo taskMemo = new TaskMemo(taskTittle, taskText, id, password);
					taskDao.insertTask(taskMemo);
					message = "タスクを登録しました";
					request.setAttribute("taskMemo", taskMemo);
		} catch (Exception e) {
			message = e.getMessage();
			request.setAttribute("error", "true");
			e.printStackTrace();
			}
			// 次の画面に遷移
			request.setAttribute("message", message);
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}

	}



