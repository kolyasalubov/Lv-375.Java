package com.softserve.academy.controllers;

public enum ViewUrls {
	
	LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
	USERS_ADMIN_JSP("/WEB-INF/views/users/Users.jsp"),
	USER_PROFILE_JSP("/WEB-INF/views/users/UserProfile.jsp"),
	USER_EDIT_JSP("/WEB-INF/views/users/UserEdit.jsp"),
	TRIP_PROFILE_JSP("/WEB-INF/views/trips/TripProfile.jsp"),       
	TRIP_OPEN_JSP("/WEB-INF/views/trips/TripRead.jsp"),          
	USER_TRIP_JSP("/WEB-INF/views/commons/UserTrip.jsp"), 
	ALL_TRIPS_JSP("/WEB-INF/views/commons/AllTrips.jsp"),
	ERROR_JSP("/WEB-INF/views/commons/Error.jsp");
	//
	private String url;

	private ViewUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
