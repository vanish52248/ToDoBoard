package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.TaskException;
import model.Task;
import model.TaskMemo;

public class TaskDao extends CommonDao {
//タスク情報関係のDAOクラス

	public TaskDao() throws TaskException {
		super();
		//コンストラクタ
		//スーパークラスのCommonDaoの public CommonDao()を呼んでDB接続を実施
		//DB接続に失敗したらTaskException発生する
	}

	public List <Task> findAllTask() throws TaskException {
		//タスクテーブルに登録されている全てのタスク情報を検索する
		//検索結果が0件の場合は空のリストを返却する
		ArrayList <Task> taskList = new ArrayList<>();
		//タスク情報のリスト
		try {
			String sql = "SELECT * FROM task"; //taskを全て選択するSQL文
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			//検索の実行

			while (rs.next()) {
				//検索結果からタスクタイトルと本文を取得してリストに格納
				String taskTittle = rs.getString("taskTittle");
				String taskText = rs.getString("taskText");
				Task task = new Task(taskTittle, taskText);
				taskList.add(task);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new TaskException("タスクの取得に失敗しちゃったよ");
		}

		return taskList;
		//例外が起きなければタスク情報のリストを返却 （0件なら空のリスト）
	}

	public void insertTask(TaskMemo taskMemo) throws TaskException {
		String taskTittle = taskMemo.getTaskTittle();
		String taskText = taskMemo.getTaskText();
		//メモ情報からDBに登録する値を取得

		try {
			String sql = "INSERT INTO task(taskTittle, taskText) VALUE(?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, taskTittle);
			ps.setString(2, taskText);
			ps.executeUpdate();
			//タスクテーブルへの情報の追加
			//ユーザー登録の際にも流用できる
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TaskException("タスクの登録に失敗しちゃったよ");
		}
	}

	public void deleteTask() throws TaskException {
		//タスク削除用のメソッド
		try {
			String sql = "DELETE FROM task WHERE id = ? , id";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rs.getInt("id"));
			//id = ? の部分の？がid番目の ？ がつく1番目の人という意味
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TaskException("タスクの削除に失敗しちゃったよ");
		}
	}
}
