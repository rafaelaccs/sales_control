package model;

public class User {
	private Integer _id;
	private String name;
	private String login;
	private String password;
	private String created_at;
	
	public User(){}
	
	public User(Integer _id, String name, String login, String password, String created_at){
		this._id = _id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.created_at = created_at;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
