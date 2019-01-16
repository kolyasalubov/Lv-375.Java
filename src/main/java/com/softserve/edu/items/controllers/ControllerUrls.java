package com.softserve.edu.items.controllers;

public enum ControllerUrls {
	ROOT_SERVLET("/"),			// Get
	LOGIN_SERVLET("/login"),	// Get	// Post login+password
	LOGOUT_SERVLET("/logout"),	// Post
	//
	USER_CREATE_SERVLET("/usercreate"),	// Get?
	USER_EDIT_SERVLET("/useredit"),		// Post
	USER_UPDATE_SERVLET("/userupdate"),	// Post
	USER_CANCEL_SERVLET("/usercancel"),	// Post
	//
	ITEM_CREATE_SERVLET("/itemcreate"),	// Post
	ITEM_EDIT_SERVLET("/itemedit"),		// Post
	ITEM_UPDATE_SERVLET("/itemupdate"),	// Post
	ITEM_CANCEL_SERVLET("/itemcancel"),	// Post
	//
	ITEM_DELETE_SERVLET("/itemdelete"), // Post
	//
	//CONFIRMATION_ITEM_SERVLET("/confirmationitem"), 	// Post
	//CONFIRMATION_DELETE_SERVLET("/confirmationdelete"), // Post
	//CONFIRMATION_CANCEL_SERVLET("/confirmationcancel"), // Post
	//
	ITEM_COUNT_SERVLET("/itemcount"), // Post
	USER_ITEMS_SERVLET("/useritems"); // Post // and pagination
	//
	private String url;

	private ControllerUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
