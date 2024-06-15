package com.lzl.jsp.pk.pojo;
/**
 * 
 *类的功能：
 *类名User
 * @author LZL
 * @version 1.0.0
 *时间 2018年4月17日-下午12:41:43
 */
public class User {
	 
	private int id;
	private String name;
	private String password;
	private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
