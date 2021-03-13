package exception;

public class TaskException extends Exception {
	//システム内で発生した例外を示す 独自例外クラス

	public TaskException (String message) {
	//コンストラクタ→画面に表示するメッセージを引数としてセットする
	//例）throw new TaskException("JDBCドライバが見つからないよ");
	//→これの場合 "JDBCドライバが見つからないよ" がmessageとして渡される
		super (message);
	}

}
