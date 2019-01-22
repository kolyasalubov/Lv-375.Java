package com.softserve.edu.items;

import com.softserve.edu.items.dao.MovieDao;
import com.softserve.edu.items.dao.UsersDao;
import com.softserve.edu.items.dao.UsersMovieDao;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.entity.Users;
import com.softserve.edu.items.services.MovieService;
import com.softserve.edu.items.services.UserService;
import com.softserve.edu.items.services.UsersMovieService;

public final class IocContainer {

	private static volatile IocContainer instance = null;
	//
	private UsersDao userDao;
	private MovieDao movieDao;
	private UsersMovieDao usersMovieDao;
	//
	private UserService userService;
	private MovieService movieService;
	private UsersMovieService usersMovieService;

	private IocContainer() {
		initDaos();
		initServices();
		initDatabase();
	}
	
	private void initDaos() {
		userDao = new UsersDao();
		movieDao = new MovieDao();
		usersMovieDao = new UsersMovieDao();
	}

	private void initServices() {
		userService = new UserService(userDao);
		movieService = new MovieService(movieDao);
		usersMovieService = new UsersMovieService(userDao, movieDao, usersMovieDao);
	}
	
	private void initDatabase() {
		userDao.create();
    	movieDao.create();
    	usersMovieDao.create();
    	userDao.createAdmin(new Users(1l, 
    							 "admin", 
    							 Role.ROLE_ADMIN.toString(), 
    							 "admin123", 
    							 "admin@gmail.com", 
    							 1));
	}

	public static IocContainer get() {
		if (instance == null) {
			synchronized (IocContainer.class) {
				if (instance == null) {
					instance = new IocContainer();
				}
			}
		}
		return instance;
	}

	public UsersDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UsersDao userDao) {
		this.userDao = userDao;
	}

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public UsersMovieDao getUsersMovieDao() {
		return usersMovieDao;
	}

	public void setUsersMovieDao(UsersMovieDao usersMovieDao) {
		this.usersMovieDao = usersMovieDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	public UsersMovieService getUsersMovieService() {
		return usersMovieService;
	}

	public void setUsersMovieService(UsersMovieService usersMovieService) {
		this.usersMovieService = usersMovieService;
	}

	
}
