package model;

public class TaskMemo {

	private String taskTittle; //タスクタイトル
	private String taskText;	//タスク本文
	private String id; 			//アカウントID
	private String password;	//アカウントパスワード

	public TaskMemo(String id, String password) {
		this.taskTittle = "";
		this.taskText = "";
		this.id = id;
		this.password = password;
	}

	public TaskMemo(
			String taskTittle, String taskText,
			String id, String password) {
		this.taskTittle = taskTittle;
		this.taskText = taskText;
		this.id = id;
		this.password = password;
	}


	public String getTaskTittle() {
		return taskTittle;
	}

	public void setTaskTittle(String taskTittle) {
		this.taskTittle = taskTittle;
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
