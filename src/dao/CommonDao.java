package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.TaskException;

public abstract class CommonDao {
	//DAOクラスの共通処理をまとめたスーパークラス
	//CommoDaoは抽象クラスとしてこのクラス単体では使えないようにする
	//必ずサブクラス（継承）として利用する

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;
	//DB使用する変数の準備

	public CommonDao() throws TaskException {
		getConnection();
		//このメソッドをsuper()で呼べばDBに接続する処理走る
	}

	private void getConnection() throws TaskException {
		//DB接続処理 CommonDao()からしか呼ばれないから private
		//DB接続に失敗した際に TaskExceptionが発生する
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost/task";
				String user = "root";
				String password = "hosiimiki2";
				//接続する DB名・ユーザー名・パスワードを指定
				con = DriverManager.getConnection(url, user, password);
			}
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw new TaskException("JDBCドライバが見つからないよ");
		} catch(SQLException e) {
			e.printStackTrace();
			throw new TaskException("SQL実行中に例外が見つかったよ");
		}
	}

	protected void close() throws TaskException {
		//DB接続解除処理 継承したところから呼べるようにprotected
		//DB接続解除に失敗した際に TaskExceptionが発生する
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		//全てnullチェックして null なら close
		} catch(SQLException e) {
			throw new TaskException("close処理中に例外が発生したよ");
		}
	}





}
