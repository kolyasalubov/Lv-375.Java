package com.softserve.academy.DTO;

import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.Entity.User;

public class UsersDto {

	private final int DEFAULT_PAGE_OFFSET = 10;

	private String userLogin;
	private List<UserDto> users;
	private int pageCount;
	private int pageOffset;

	public UsersDto(String userLogin) {
		this.userLogin = userLogin;
		this.users = new ArrayList<>();
		this.pageCount = users.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	public UsersDto(String userLogin, List<TripDto> trips) {
		this.userLogin = userLogin;
		this.users = users;
		this.pageCount = trips.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

    //getters & setters
	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public void addUserDto(UserDto userDto) {
		users.add(userDto);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getDEFAULT_PAGE_OFFSET() {
		return DEFAULT_PAGE_OFFSET;
	}

	@Override
	public String toString() {
		return "UsersDto [DEFAULT_PAGE_OFFSET=" + DEFAULT_PAGE_OFFSET + ", userLogin=" + userLogin + ", users=" + users
				+ ", pageCount=" + pageCount + ", pageOffset=" + pageOffset + "]";
	}

}
