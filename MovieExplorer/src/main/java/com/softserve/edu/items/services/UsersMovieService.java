package com.softserve.edu.items.services;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.items.dao.MovieDao;
import com.softserve.edu.items.dao.UsersDao;
import com.softserve.edu.items.dao.UsersMovieDao;
import com.softserve.edu.items.dto.MovieDto;
import com.softserve.edu.items.entity.Movie;
import com.softserve.edu.items.entity.UsersMovie;

public class UsersMovieService {

	private UsersDao usersDao;
	private MovieDao movieDao;
	private UsersMovieDao userMovieDao;
	
	//Temporary
	public UsersMovieService() {
		this.usersDao = new UsersDao();
		this.movieDao = new MovieDao();
		this.userMovieDao = new UsersMovieDao();
	}
	
	public UsersMovieService(UsersDao usersDao, MovieDao movieDao, UsersMovieDao userMovieDao) {
		this.usersDao = usersDao;
		this.movieDao = movieDao;
		this.userMovieDao = userMovieDao;
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public UsersMovieDao getUserMovieDao() {
		return userMovieDao;
	}

	public void setUserMovieDao(UsersMovieDao userMovieDao) {
		this.userMovieDao = userMovieDao;
	}
	
	//get movie for current user
	public MovieDto getMovieDto(long userId, long movieId) {
		Movie movie = movieDao.getById(movieId);
		Boolean isFavourite = !userMovieDao.getByTwoId(userId, movieId).isEmpty();
		return new MovieDto(movie.getId(), 
							movie.getTitle(), 
							movie.getImdbMovieID(), 
							movie.getInformation(), 
							isFavourite, 
							movie.getPosterUrl());
	}
	
	//get all favorite movies for current user
	public List<MovieDto> getAllFavoriteMovies(long userId, int offset, int limit) {
		List<MovieDto> toReturnMovies = new ArrayList<>();
		List<Movie> moviesList = movieDao.getAllUserMoviesOrderBy(userId, offset, limit);
		for (Movie movie : moviesList) {
			toReturnMovies.add(new MovieDto(
					movie.getId(),
					movie.getTitle(),
					movie.getImdbMovieID(), 
					movie.getInformation(), 
					true, 
					movie.getPosterUrl())
					);
		}	return toReturnMovies;
		
	}
	
	//add to favorites
	public boolean addToFavorites(Long usersId, Long movieId) {
		UsersMovie userMovie = new UsersMovie(usersId, movieId);
		try {
			userMovieDao.insert(userMovie);
		} catch (Exception e) {
			System.out.println("RuntimeException, message: " + e.getMessage());
			return false;
		}
		return true;
	}

	public int getUsersMovieCount(Long userId) {
		return movieDao.getAllById(userId).size();
	}
	
}
