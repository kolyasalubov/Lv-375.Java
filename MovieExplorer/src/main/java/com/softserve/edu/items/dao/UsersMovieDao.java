package com.softserve.edu.items.dao;

import java.util.Arrays;
import java.util.List;

import com.softserve.edu.items.entity.UsersMovie;
import com.softserve.edu.items.entity.UsersMovie.UserMovieEntityQueries;

public class UsersMovieDao extends ADaoCRUD<UsersMovie> {
	
	public UsersMovieDao() {
		super();
		init();
	}
	
	@Override
	protected void init() {
		for (UserMovieEntityQueries userMovieEntityQueries : UserMovieEntityQueries.values()) {
			sqlQueries.put(userMovieEntityQueries.getSqlQuery(), userMovieEntityQueries);
		}
	}
	
	@Override
	protected UsersMovie createInstance(List<String> args) {
		return new UsersMovie(
			Long.parseLong(args.get(0) == null ? "0" : args.get(0)),
			Long.parseLong(args.get(1) == null ? "0" : args.get(1))
		);
	}
		
	@Override
	protected List<String> getFields(UsersMovie entity) {
		return Arrays.asList(
			"",
			entity.getUserId().toString(),
			entity.getMovieId().toString()
		);
	}

	//Not needed
	@Override
	protected List<String> getUpdateFields(UsersMovie entity) {
		return null;
	}

}
