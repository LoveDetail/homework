package com.gp.homework.pattern.template.myJdbc;

public class User {
	private Integer id ;
	private String user_code ;
	private String user_name ;
	private String user_pass ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_code=" + user_code + ", user_name=" + user_name + ", user_pass=" + user_pass
				+ "]";
	}
}
