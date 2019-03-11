package com.softserve.edu;

public final class UserRepository {
	private static volatile UserRepository instance = null;

	private UserRepository() {
	}
	
	public static UserRepository get() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }
	
	public IUser admin() {
		return User.get()
			.setFirstname("firstname7")
			.setLastname("lastname7")
			.setLogin("login7")
			.setPassword("password7")
			.setAddress("address7")
			.build();
	}
	
}
