package dao;

import java.sql.SQLException;

import exception.TaskException;
import model.Account;

public class AccountDao extends CommonDao {

	public AccountDao() throws TaskException {
		super();
	//コンストラクタ
	//スーパークラスのCommonDaoの public CommonDao()を呼んでDB接続を実施
	//DB接続に失敗したらTaskException発生する
	}

	public Account doLogin (String id, String password) throws TaskException {
	//指定されたIDとパスワードの組み合わせでログイン処理を行うメソッド
	//組み合わせに該当するアカウントが存在しない場合は例外でログイン不可を通知
		Account loginUser = null;
		//アカウント用の変数
		try {
			//try で 検索と実行をする
			String sql = "SELECT * FROM account WHERE id = ? AND password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				loginUser = new Account(id, password);
			}

			if (loginUser == null) {
				throw new TaskException("ありゃりゃ、ログインできなかったよ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TaskException("SQL実行中に例外が見つかったよ");
		}
		return loginUser;
		//例外が無ければ アカウント情報を返却する
	}
}
