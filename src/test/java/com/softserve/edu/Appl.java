package com.softserve.edu;

public class Appl {

	public static void main(String[] args) {
		// 1. Classic Constructor
//		User user = new User("firstname1",  "lastname1",  "login1",
//				 "password1",  "email1",  "address1", true);
//		System.out.println("login = " + user.getLogin());
		//
		// 2. Default Constructor and Setters
//		User user = new User();
//		user.setFirstname("firstname2");
//		user.setLastname("lastname2");
//		user.setLogin("login2");
//		user.setPassword("password2");
//		user.setEmail("email2");
//		user.setAddress("address2");
//		user.setSubscribe(true);
//		System.out.println("login = " + user.getLogin());
		//
		// 3. Add Fluent Interface
//		User user = new User()
//			.setFirstname("firstname3")
//			.setLastname("lastname3")
//			.setLogin("login3")
//			.setPassword("password3")
//			.setEmail("email3")
//			.setAddress("address3")
//			.setSubscribe(true);
//		System.out.println("login = " + user.getLogin());
		//
		// 4. Add Static Factory
//		User user = User.get()
//				.setFirstname("firstname4")
//				.setLastname("lastname4")
//				.setLogin("login4")
//				.setPassword("password4")
//				.setEmail("email4")
//				.setAddress("address4")
//				.setSubscribe(true);
//		System.out.println("login = " + user.getLogin());
		//
		// 5. Add Builder
//		User user = User.get()
//				.setFirstname("firstname5")
//				.setLastname("lastname5")
//				.setLogin("login5")
//				.setPassword("password5")
//				.setAddress("address5")
//				.build();
//		System.out.println("login = " + user.getLogin());
		// Code
//		System.out.println("login = " + user.setLogin("111")); // Defect
		// Code
//		System.out.println("login = " + user.getLogin());
		//
		// 6. Dependency Inversion
//		IUser user = User.get()
//				.setFirstname("firstname5")
//				.setLastname("lastname5")
//				.setLogin("login5")
//				.setPassword("password5")
//				.setAddress("address5")
//				.build();
//		System.out.println("login = " + user.getLogin());
		// Code
		//System.out.println("login = " + user.setLogin("111")); // Compile Error
//		System.out.println("login = " + ((User) user).setLogin("111")); // Code Smell
		// Code
//		System.out.println("login = " + user.getLogin());
		//
		// 6. Singleton
		// 7. Repository (static methods, singletone, enum)
		IUser user = UserRepository.get().admin();
		System.out.println("login = " + user.getLogin());
		//
	}
}
