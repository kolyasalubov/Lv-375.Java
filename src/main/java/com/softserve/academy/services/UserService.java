package com.softserve.academy.services;

import com.softserve.academy.DAO.RoleDao;
import com.softserve.academy.DAO.UserDao;
import com.softserve.academy.DTO.LoginDto;
import com.softserve.academy.DTO.UserDto;
import com.softserve.academy.Entity.User;

public class UserService {

	private UserDao userDao;
	private RoleDao roleDao;
	private Long idUser = 0l;

	public UserService(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	// Insert User
	public boolean insertUser(UserDto userDto) {
		boolean result = true;
		Long idRole;
		++idUser;
		if (userDto.getRole().equals("user")) {
			idRole = 1l;
		} else {
			idRole = 2L;
		}
		User user = new User(idUser, userDto.getLogin(), userDto.getPassword(), userDto.getAlias(),
				userDto.getBlocked(), idRole);
		try {
			userDao.insert(user);
		} catch (Exception e) {
			
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	// Update User
	public boolean delete(UserDto userDto) {
		boolean result = true;
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		user.setPassword(userDto.getPassword());
		user.setAlias(userDto.getAlias());
		try {
			userDao.updateByEntity(user);
		} catch (Exception e) {
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	//Delete user
	public boolean deleteUserByLogin(String login) {
		boolean result = true;
		try {
			userDao.deleteByFieldName("login", login);
		} catch (Exception e) {
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	// Update User
	public boolean setUserDto(UserDto userDto) {
		boolean result = true;
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		user.setPassword(userDto.getPassword());
		user.setAlias(userDto.getAlias());
		user.setBlocked(userDto.getBlocked());
		try {
			userDao.updateByEntity(user);
		} catch (Exception e) {
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public UserDto getUserDto(LoginDto loginDto) {
		User user = userDao.getUserEntityByLogin(loginDto.getLogin());
		return new UserDto(user.getLogin(), user.getPassword(), user.getAlias(),
				roleDao.getById(user.getIdRole()).getName());
	}

	public boolean isAdmin(LoginDto loginDto) {
		boolean result = true;
		User user = null;
		try {
			user = userDao.getUserEntityByLogin(loginDto.getLogin());
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		result = result && (user.getIdRole().toString().equals("2"));
		return result;
	}

	public boolean isRegistered(UserDto userDto) {
		boolean result = true;
		User user = null;
		user = userDao.getUserEntityByLogin(userDto.getLogin());
		if (user == null) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	public boolean isValid(LoginDto loginDto) {
		boolean result = true;
		User user = null;
		try {
			user = userDao.getUserEntityByLogin(loginDto.getLogin());
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		result = result && (user.getPassword().equals(loginDto.getPassword()));
		return result;
	}

	// To get from session
	public UserDto getUserDtoByLogin(String login) {
		User user = userDao.getUserEntityByLogin(login);
		UserDto userDto = new UserDto(user.getLogin(), user.getPassword(), user.getAlias(),
				user.getIdRole().toString());
		return userDto;
	}

	public boolean initializeUser() {
		return userDao.createTable();
	}

	public boolean initializeRole() {
		return roleDao.createTable();
	}
	
	
	public Long getIdUserByLogin(LoginDto loginDto) {
		return userDao.getUserEntityByLogin(loginDto.getLogin()).getId();
	}

	
	public Long getIdUserByLogin(UserDto userDto) {
		return userDao.getUserEntityByLogin(userDto.getLogin()).getId();
	}
	
	

}
