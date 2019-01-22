package com.softserve.edu.items.services;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.items.dao.UsersDao;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.entity.Users;

public class UserService {

	UsersDao usersDao;
	//Temporary
	public UserService() {
		this.usersDao = new UsersDao();
	}

	public UserService(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	

	//Create user
		public boolean createUser(UsersDto userDto) {
			try {
				Users user = new Users(0l, userDto.getFullName(), userDto.getRole(), userDto.getPassword(),
						userDto.getEmail(), userDto.getIsActive());	
				usersDao.insert(user);
			} catch (Exception e) {
				System.out.println("User update exception: " + e.getMessage());
				return false;
			}
			return true;
		}
	
	public boolean updateUser(UsersDto userDto) {
		boolean result = true;
		Users user = new Users(userDto.getId(), 
							   userDto.getFullName(),
							   userDto.getRole(),
							   userDto.getPassword(), 
							   userDto.getEmail(),
							   userDto.getIsActive()
						);
		try {
			usersDao.updateByEntity(user);
		} catch (Exception e) {
			System.out.println("User update exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public UsersDto getUserDto(LoginDto loginDto) {
		Users user = usersDao.getUserEntityByLogin(loginDto.getLogin());
		return new UsersDto(
				user.getId(),
				user.getFullName(), 
				user.getRole().toString(), 
				user.getPassword(),
				user.getEmail(),
				user.getIsActive()
			);
	}
	
	public Long getIdUserByLogin(LoginDto loginDto) {
		return usersDao.getUserEntityByLogin(loginDto.getLogin()).getId();
	}
	
	//get all Users ordered by email
	public List<UsersDto> getUsersList(String orderBy) {
		List<UsersDto> toReturn = new ArrayList<>();
		List<Users> usersList = usersDao.getAllOrderBy(orderBy, 0, 50);
		for(Users u : usersList) {
			System.out.println("user: " + u);
			UsersDto userDto = new UsersDto(
				u.getId(), 
				u.getFullName(), 
				u.getRole().toString(), 
				u.getPassword(), 
				u.getEmail(), 
				u.getIsActive()
			);
			System.out.println("userDto: " + userDto);
			toReturn.add(userDto);
		}
		return toReturn;
	}

//	public Long getIdUserByLogin(UsersDto userDto) {
//		return usersDao.getUserEntityByLogin(userDto.getEmail()).getId();
//	}
	
	public boolean isValid(LoginDto loginDto) {
		try {
			Users users = usersDao.getUserEntityByLogin(loginDto.getLogin());
			return users.getPassword().equals(loginDto.getPassword());
		} catch (Exception e) {
			System.out.println("*****************User is not valid, message: " + e.getMessage());
			return false;
		}
	}
	
}
