package com.travelbuddy.userservice.dto;

public class LoginRequestDTO {

	private String email;
	private String password;
	public LoginRequestDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public LoginRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
