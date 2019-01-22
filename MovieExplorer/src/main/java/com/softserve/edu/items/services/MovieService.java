package com.softserve.edu.items.services;

import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.items.dao.MovieDao;
import com.softserve.edu.items.dto.MovieDto;
import com.softserve.edu.items.dto.MoviesListDto;
import com.softserve.edu.items.entity.Movie;

public class MovieService {

	private MovieDao movieDao;
	
	//Temporary
	public MovieService() {
		this.movieDao = new MovieDao();	
	}

	public MovieService(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	public Long getMovieIdByTitle(MovieDto movieDto) {
		List<Movie> movieList = movieDao.getByFieldName("title", movieDto.getTitle());
		if(movieList.isEmpty()) {
			return -1l;
		}
		return movieList.get(0).getId();
	}
	
	//get all Movies ordered by title
	public List<MovieDto> getMoviesList(int offset, int limit) {
		List<MovieDto> toReturn = new ArrayList<>();
		List<Movie> moviesList = movieDao.getAllOrderBy("title", offset, limit);
		for(Movie m : moviesList) {
			toReturn.add(new MovieDto(
				m.getId(), 
				m.getTitle(), 
				m.getImdbMovieID(), 
				m.getInformation(), 
				false,
				m.getPosterUrl()
			));
		}
		return toReturn;
	}
	
	public int getMoviesCount() {
		return movieDao.getAll().size();
	}
	
	//get movie from id
		public MovieDto getMovieDto(long movieId) {
			Movie movie = movieDao.getById(movieId);
			return new MovieDto(movie.getId(), 
								movie.getTitle(), 
								movie.getImdbMovieID(), 
								movie.getInformation(), 
								false, 
								movie.getPosterUrl());
		}
	
	//add movie
	public boolean addMovie(MovieDto movieDto) {
		Movie movie = new Movie(0l, 
								movieDto.getTitle(), 
								movieDto.getImdbMovieID(), 
								movieDto.getInformation(),
								movieDto.getPosterUrl());
		try {
			checkIfMovieTitleExists(movieDto);
			movieDao.insert(movie);
		} catch (Exception e) {
			System.out.println("Cannot insert movie into db: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//edit movie
	public boolean editMovie(MovieDto movieDto) {
		Movie movie = new Movie(movieDto.getId(), 
								movieDto.getTitle(), 
								movieDto.getImdbMovieID(), 
								movieDto.getInformation(), 
								movieDto.getPosterUrl());
		try {
			checkIfMovieTitleUnique(movieDto);
			movieDao.updateByEntity(movie);
		} catch (Exception e) {
			System.out.println("Cannot update movie: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//check if exists
	public void checkIfMovieTitleExists(MovieDto movieDto) throws Exception {
		if(getMovieIdByTitle(movieDto) != -1l ) {
			throw new Exception("Movie with such title already exists: " + movieDto.getTitle());
		}
	}
	
	public void checkIfMovieTitleUnique(MovieDto movieDto) throws Exception {
		List<Movie> movieList = movieDao.getByTitleAndNotId(
								movieDto.getTitle(),
								String.valueOf(movieDto.getId()));
		if(movieList.size() > 0) {
			throw new Exception("Movie with such title already exists: " + movieDto.getTitle());
		}
	}
	
	//search
	public List<MovieDto> search(String title, int offset, int limit) {
		List<MovieDto> toReturn = new ArrayList<>();
		List<Movie> movies = movieDao.search("%" + title + "%", offset, limit);
		for (Movie m : movies) {
			toReturn.add(new MovieDto(m.getId(), 
								   m.getTitle(), 
								   m.getImdbMovieID(), 
								   m.getInformation(), 
								   false,
								   m.getPosterUrl())
					);
		}
		return toReturn;
	}
	
	//delete movie
	public boolean deleteMovieById(Long movieId) {
		try {
			movieDao.deleteById(movieId);
		} catch (Exception e) {
			System.out.println("Cannot insert to db: " + e.getMessage());
			return false;
		}
		return true;
	}
	
}
