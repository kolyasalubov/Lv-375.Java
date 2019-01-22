package com.softserve.academy.controllers;

public enum ControllerUrls {
	
	ROOT_SERVLET("/"),			
	LOGIN_SERVLET("/login"),	
	LOGOUT_SERVLET("/logout"),	
	
	USER_CREATE_SERVLET("/usercreate"),	
	USER_EDIT_SERVLET("/useredit"),		
	USER_CANCEL_SERVLET("/usercancel"),	
	USER_DELETE_SERVLET("/userdelete"),	
	USERS_ADMIN_SERVLET("/allusers"),     
	
	TRIP_CREATE_SERVLET("/tripcreate"),	
	TRIP_EDIT_SERVLET("/tripedit"),		
	TRIP_CANCEL_SERVLET("/tripcancel"),	
	TRIP_DELETE_SERVLET("/tripdelete"), 
	USER_TRIP_SERVLET("/usertrip"),     
	ALL_TRIP_SERVLET("/alltrips"),        
	
	ITEM_COUNT_SERVLET("/tripcount"); 
	
	
	private String url;

	private ControllerUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
