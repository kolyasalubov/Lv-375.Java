package com.softserve.edu;

interface IFirstname {
	ILastname setFirstname(String firstname);
}

interface ILastname {
	ILogin setLastname(String lastname);
}

interface ILogin {
	IPassword setLogin(String login);
}

interface IPassword {
	IUserBuild setPassword(String password);
}

interface IUserBuild {
	IUserBuild setEmail(String email);

	IUserBuild setAddress(String address);

	IUserBuild setSubscribe(boolean subscribe);

	//5. Add Builder
	//User build();
	//6. Dependency Inversion
	IUser build();
}

//5. Add Builder
//public class User implements IFirstname, ILastname, ILogin, IPassword, IUserBuild {
//6. Dependency Inversion
public class User implements IFirstname, ILastname, ILogin,
							IPassword, IUserBuild, IUser {

	private String firstname; // Required
	private String lastname; // Required
	private String login; // Required
	private String password; // Required
	private String email; // Optianal
	private String address; // Optianal
	private boolean subscribe; // Optianal

	// 1. Classic Constructor
//	public User(String firstname, String lastname, String login,
//			String password, String email, String address,
//			boolean subscribe) {
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.login = login;
//		this.password = password;
//		this.email = email;
//		this.address = address;
//		this.subscribe = subscribe;
//	}

	// 2. Default Constructor and Setters
//	public User() {
//		this.firstname = "";
//		this.lastname = "";
//		this.login = "";
//		this.password = "";
//		this.email = "";
//		this.address = "";
//		this.subscribe = true;
//	}

	// 4. Add Static Factory
//	private User() {
//		this.firstname = "";
//		this.lastname = "";
//		this.login = "";
//		this.password = "";
//		this.email = "";
//		this.address = "";
//		this.subscribe = true;
//	}

	// 5. Add Builder
	private User() {
		this.email = "";
		this.address = "";
		this.subscribe = true;
	}
	
	// 4. Add Static Factory
	//public static User get() {
	// 5. Add Builder
	public static IFirstname get() {
		return new User();
	}

	// settres

	// 2. Default Constructor and Setters
	// public void setFirstname(String firstname) {
	// 3. Add Fluent Interface
	//public User setFirstname(String firstname) {
	// 5. Add Builder
	public ILastname setFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ILogin setLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public IPassword setLogin(String login) {
		this.login = login;
		return this;
	}

	public IUserBuild setPassword(String password) {
		this.password = password;
		return this;
	}

	public IUserBuild setEmail(String email) {
		this.email = email;
		return this;
	}

	public IUserBuild setAddress(String address) {
		this.address = address;
		return this;
	}

	public IUserBuild setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
		return this;
	}

	// 5. Add Builder
	//public IUser build() {
	//6. Dependency Inversion
	public IUser build() {
		return this;		
	}
	
	// getters

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

}
