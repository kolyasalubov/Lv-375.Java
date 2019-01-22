package com.softserve.edu.items.dto;

public class LoginDto {

	private String email;
	private String password;

	public LoginDto(String login, String password) {
		this.email = login;
		this.password = password;
	}

	// setters

	public void setLogin(String login) {
		this.email = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// getters

	public String getLogin() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "(" + "login=" + email 
				+ " password=" + password 
				+ ")";
	}
}
