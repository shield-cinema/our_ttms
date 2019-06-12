package model;

import java.util.Arrays;

/**
 * 实体类User
 * @author Administrator
 *
 */
public class User {
	private int id;
	private String sex;
	private String username;
	private String password;
	private String telphone;
	private byte[] icon;
	
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public User(String password,String username,String telphone,int id){
		this.password = password;
		this.username = username;
		this.telphone = telphone;
		this.id = id;
	}
	public User(String password,String username,String telphone){
		this.password = password;
		this.username = username;
		this.telphone = telphone;
	}
	public User(){
		super();
	}
	public User(String username,String password){
		this.password = password;
		this.username = username;		
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
	
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", sex=" + sex + ", username=" + username
				+ ", password=" + password + ", telphone=" + telphone
				+ ", icon=" + Arrays.toString(icon) + "]";
	}
	public User(int id, String sex, String username, String password,
			String telphone, byte[] icon) {
		super();
		this.id = id;
		this.sex = sex;
		this.username = username;
		this.password = password;
		this.telphone = telphone;
		this.icon = icon;
	}
	
	
}
