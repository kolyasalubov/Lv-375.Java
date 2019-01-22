package com.softserve.edu.items.dto;

import java.util.List;

import com.softserve.edu.items.entity.Role;

public class UsersDto {

	private Long id;
	private String fullName;
	private String role;
	private String password;
	private String email;
	private int isActive;
	
	public UsersDto() {
	}

	public UsersDto(String fullName, String password, String email) {
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.role = Role.ROLE_USER.toString();
		this.isActive = 1;
	}
	
	public UsersDto(String fullName, String role, String password, String email, int isActive) {
		this.fullName = fullName;
		this.role = role;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
	}

	public UsersDto(Long id, String fullName, String role, String password, String email, int isActive) {
		this.id = id;
		this.fullName = fullName;
		this.role = role;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "UsersDto [fullName=" + fullName + ", role=" + role + ", password=" + password + 
				", email=" + email + ", isActive=" + isActive + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
