package com.softserve.edu.items.dao;

import java.util.Arrays;
import java.util.List;
import com.softserve.edu.items.entity.Movie;
import com.softserve.edu.items.entity.Movie.MovieEntityQueries;

public class MovieDao extends ADaoCRUD<Movie> {
	private final static String ID_USER_FIELDNAME = "idUser";

	public MovieDao() {
		super();
		init();
	}
	@Override
	protected void init() {
		for (MovieEntityQueries movieEntityQueries : MovieEntityQueries.values()) {
			sqlQueries.put(movieEntityQueries.getSqlQuery(), movieEntityQueries);
		}
	}
	
	@Override
	protected Movie createInstance(List<String> args) {
		return new Movie(
				Long.parseLong(args.get(0) == null ? "0" : args.get(0)), //id
				args.get(1) == null ? new String() : args.get(1), 
				args.get(2) == null ? new String() : args.get(2),
				args.get(3) == null ? new String() : args.get(3),
				args.get(4) == null ? new String() : args.get(4)
			);
	}
	
	@Override
	protected List<String> getFields(Movie entity) {
		List<String> fields = Arrays.asList(
			entity.getId().toString(),
			entity.getTitle(),
			entity.getImdbMovieID(),
			entity.getInformation(),
			entity.getPosterUrl()
		);
		return fields;
	}

	//For title, rate, information, posterUrl
	@Override
	protected List<String> getUpdateFields(Movie entity) {
		return Arrays.asList(
					entity.getTitle(),
					entity.getImdbMovieID(),
					entity.getInformation(),
					entity.getPosterUrl(),
					entity.getId().toString()
				);
	}
}
