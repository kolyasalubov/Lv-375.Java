package com.softserve.edu.items.dto;

import java.util.List;

public class UsersListDto {

	private final int DEFAULT_PAGE_OFFSET = 10; // TODO
	//
	private List<UsersDto> users;
	private int pageCount;
	private int pageOffset;
	
	public UsersListDto() {
	}

	public UsersListDto(List<UsersDto> users) {
		this.users = users;
		this.pageCount = users.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	public List<UsersDto> getUsers() {
		return users;
	}

	public void setUsers(List<UsersDto> users) {
		this.users = users;
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
	
	
	
	
	
}
