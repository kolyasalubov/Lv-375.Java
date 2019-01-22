package com.softserve.academy.DTO;

import java.util.ArrayList;
import java.util.List;

public class UserTripDto {
	private final int DEFAULT_PAGE_OFFSET = 10; // TODO
	
	private String userLogin;
	private List<TripDto> trips;
	private int pageCount;
	private int pageOffset;

	public UserTripDto(String userLogin) {
		this.userLogin = userLogin;
		this.trips = new ArrayList<>();
		this.pageCount = trips.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	public UserTripDto(String userLogin, List<TripDto> trips) {
		this.userLogin = userLogin;
		this.trips = trips;
		this.pageCount = trips.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	// setters

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setTrips(List<TripDto> trips) {
		this.trips = trips;
	}

	public void addTripDto(TripDto trip) {
		trips.add(trip);
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	// getters

	public String getUserLogin() {
		return userLogin;
	}

	public List<TripDto> getTrips() {
		return trips;
	}

	public int getPageCount() {
		return  pageCount;
	}

	public int getPageOffset() {
		return  pageOffset;
	}

	@Override
	public String toString() {
		return "(" + "userLogin=" + userLogin  
				+ " items=" + trips.toString() 
				+ " pageCount=" + pageCount
				+ " pageOffset=" + pageOffset
				+ ")";
	}

}
