package com.softserve.edu.items.controllers;

public enum ControllerUrls {
	ROOT_SERVLET("/"),			// Get
	LOGIN_SERVLET("/login"),	// Get	// Post login+password
	LOGOUT_SERVLET("/logout"),
	SIGNUP_SERVLET("/signUp"),
	USER_PROFILE("/userProfile"),
	MOVIE_PROFILE("/movie"),
	MOVIES_LIST("/moviesList"),
	ADMIN_MOVIES_LIST("/adminMovieList"),
	EDIT_MOVIE_SERVLET("/editMovie"),
	ADD_MOVIE_SERVLET("/addMovie"),
	ALL_USERS_LIST_SERVLET("/allUsersList"),
	FAVOURITE_MOVIES_LIST("/favouriteMoviesList");

	private String url;

	private ControllerUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}

