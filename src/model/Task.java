package model;

public class Task {
//タスク情報クラス

	private String taskTittle;
	private String taskText;

	public Task(String taskTittle, String taskText) {
		this.taskTittle = taskTittle;
		this.taskText = taskText;
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

	//getterは taskTittle / taskText を返す
	//setterは taskTittle / taskText をセットする

}
