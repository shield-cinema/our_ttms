package model;

public class Admin {
	private String username;
	private String password;
	public Admin(){
		super();
	}
	public Admin(String usernae,String password){
		this.password = password;
		this.username = usernae;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "admin [username=" + username + ", password=" + password + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
