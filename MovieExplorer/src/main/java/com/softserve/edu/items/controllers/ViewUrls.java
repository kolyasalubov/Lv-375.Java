package com.softserve.edu.items.controllers;

public enum ViewUrls {

		LOGIN_JSP("/WEB-INF/views/users/login.jsp"),
		SIGN_UP_JSP("/WEB-INF/views/users/signUp.jsp"),
		USER_PROFILE_JSP("/WEB-INF/views/users/userProfile.jsp"),
		USERS_LIST_JSP("/WEB-INF/views/admin/allUsersList.jsp"),
		
		MOVIES_LIST_JSP("/WEB-INF/views/movies/moviesList.jsp"),
		ADMIN_MOVIES_LIST_JSP("/WEB-INF/views/admin/adminMovieList.jsp"),
		
		MOVIE_PROFILE_JSP("/WEB-INF/views/movies/movie.jsp"),
		ADD_MOVIE_JSP("/WEB-INF/views/movies/addMovie.jsp"),
		EDIT_MOVIE_JSP("/WEB-INF/views/movies/editMovie.jsp"),
		
		FAVOURITE_MOVIES_JSP("/WEB-INF/views/users/favouriteMovies.jsp"),
		USER_MOVIES_JSP("/WEB-INF/views/commons/userMovies.jsp"),
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
