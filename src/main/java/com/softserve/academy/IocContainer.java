package com.softserve.academy;

import com.softserve.academy.DAO.RoleDao;
import com.softserve.academy.DAO.TripDao;
import com.softserve.academy.DAO.UserDao;
import com.softserve.academy.services.TripService;
import com.softserve.academy.services.UserService;
import com.softserve.academy.services.UserTripService;
import com.softserve.academy.services.UsersService;

public final class IocContainer {

	private static volatile IocContainer instance = null;

	private UserDao userDao;
	private RoleDao roleDao;
	private TripDao tripDao;

	private UserService userService;
	private TripService tripService;
	private UserTripService userTripService;
	private UsersService usersService;

	private IocContainer() {
		initDaos();
		initServices();
	}

	// initialization of DAO
	private void initDaos() {
		userDao = new UserDao();
		roleDao = new RoleDao();
		tripDao = new TripDao();
	}

	// initialization of Service
	private void initServices() {
		userService = new UserService(userDao, roleDao);
		tripService = new TripService(tripDao);
		userTripService = new UserTripService(userDao, tripDao);
		usersService = new UsersService(userDao);
	}

	public static IocContainer get() {
		if (instance == null) {
			synchronized (IocContainer.class) {             // .class return an instance of IocContainer
				if (instance == null) {
					instance = new IocContainer();
				}
			}
		}
		return instance;
	}

	// getters for DAO's and Services
	public UserDao getUserDao() {
		return userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public TripDao getTripDao() {
		return tripDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public TripService getTripService() {
		return tripService;
	}

	public UserTripService getUserTripService() {
		return userTripService;
	}

	public UsersService getUsersService() {
		return usersService;
	}

}
