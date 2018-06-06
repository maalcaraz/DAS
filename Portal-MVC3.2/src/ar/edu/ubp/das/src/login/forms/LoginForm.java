package ar.edu.ubp.das.src.login.forms;

import java.sql.Timestamp;

public class LoginForm {

	
	private String username;
	private String password;
	private Timestamp timestamp;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
