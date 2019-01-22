package com.softserve.academy.services;

import com.softserve.academy.DAO.UserDao;
import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.DTO.UserDto;
import com.softserve.academy.DTO.UserTripDto;
import com.softserve.academy.DTO.UsersDto;
import com.softserve.academy.Entity.Trip;
import com.softserve.academy.Entity.User;

public class UsersService {

	private UserDao userDao;

	public UsersService(UserDao userDao) {
		this.userDao = userDao;
	}

	public UsersDto getAllUsers(UserDto userDto) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UsersDto usersDto = new UsersDto(user.getLogin());
		for (User u : userDao.getAll()) {
			UserDto userDTO = new UserDto(u.getLogin(), u.getPassword(), u.getAlias(), u.getIdRole().toString());
			usersDto.addUserDto(userDTO);
		}
		return usersDto;
	}

}
