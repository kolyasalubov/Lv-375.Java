package com.softserve.academy.DTO;

public class UserDto {

    private String login;
    private String password;
    private String alias;
    private Boolean blocked;
    private String role;
    
	public UserDto(String login, String password, String alias, String role) {
		this.login = login;
		this.password = password;
		this.alias = alias;
		this.blocked = false;
		this.role = role;
	}
	
	//getters & setters

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	@Override
	public String toString() {
		return "UserDto [login=" + login + ", password=" + password + ", alias=" + alias + ", blocked=" + blocked
				+ ", role=" + role + "]";
	}

}


