package com.softserve.edu.container;

import com.softserve.edu.items.dao.CarDao;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.services.AllCarsService;
import com.softserve.edu.items.services.AllUsersService;
import com.softserve.edu.items.services.CarService;
import com.softserve.edu.items.services.DatabaseService;
import com.softserve.edu.items.services.UserCarsService;
import com.softserve.edu.items.services.UserService;

/**
 * Container class with all the available services and DAOs Done by Singleton
 * pattern.
 * 
 * @author y3809
 *
 */
public final class IocContainer {
    private static volatile IocContainer instance = null;
    // All the DAOs
    private UserDao userDao;
    private CarDao carDao;
    // All the Services
    private DatabaseService databaseService;
    private UserService userService;
    private CarService carService;
    private UserCarsService userCarsService;
    private AllCarsService allCarsService;
    private AllUsersService allUsersService;

    /*
     * Initialization method
     */
    private IocContainer() {
	initDaos();
	initServices();
    }

    /*
     * Creating instances of all the DAOs
     */
    private void initDaos() {
	userDao = new UserDao();
	carDao = new CarDao();
    }

    /*
     * Creating instances of all the services
     */
    private void initServices() {
	databaseService = new DatabaseService(carDao, userDao);
	userService = new UserService(userDao);
	carService = new CarService(carDao);
	userCarsService = new UserCarsService(userDao, carDao);
	allCarsService = new AllCarsService(carDao);
	allUsersService = new AllUsersService(userDao);
    }

    /*
     * Part of the Singleton pattern Method returns instance of the Container
     */
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

    // Getters
    public UserDao getUserDao() {
	return userDao;
    }

    public CarDao getCarDao() {
	return carDao;
    }

    public DatabaseService getDatabaseService() {
	return databaseService;
    }

    public UserService getUserService() {
	return userService;
    }

    public CarService getCarService() {
	return carService;
    }

    public UserCarsService getUserCarsService() {
	return userCarsService;
    }

    public AllCarsService getAllCarsService() {
	return allCarsService;
    }

    public AllUsersService getAllUserService() {
	return allUsersService;
    }
}
