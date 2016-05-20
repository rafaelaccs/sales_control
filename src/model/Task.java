package model;

public class Task {
	private Integer _id;
	private String task;
	private String date_create;
	private String date_completed;
	
	public Task(){}
	
	public Task(Integer _id, String task, String date_create, String date_completed){
		this._id = _id;
		this.task = task;
		this.date_create = date_create;
		this.date_completed = date_completed;
	}
	
	public Integer get_id() {
		return _id;
	}
	
	public void set_id(Integer _id) {
		this._id = _id;
	}
	
	public String getTask() {
		return task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
	
	public String getDate_create() {
		return date_create;
	}
	
	public void setDate_create(String date_create) {
		this.date_create = date_create;
	}
	
	public String getDate_completed() {
		return date_completed;
	}
	
	public void setDate_completed(String date_completed) {
		this.date_completed = date_completed;
	}
}
