package com.softserve.edu.items.entity;

public class UsersMovie  implements BaseEntity{

	private Long userId;
	private Long movieId;
	
	public UsersMovie() {
	}

	public UsersMovie(Long userId, Long movieId) {
		this.userId = userId;
		this.movieId = movieId;
	}

	public static enum UserMovieEntityQueries {
		CREATE(SqlQueries.CREATE, "create table if not exists users_movie (\r\n" + 
				"	 user_ID bigint,\r\n" + 
				"    movie_ID bigint,\r\n" + 
				"    foreign key (user_ID) references users(ID) ON DELETE CASCADE,\r\n" + 
				"    foreign key (movie_ID) references movie(ID) ON DELETE CASCADE,\r\n" + 
				"    primary key (user_ID, movie_ID)\r\n" + 
				");"),
		INSERT(SqlQueries.INSERT, "INSERT INTO users_movie (user_ID, movie_ID) VALUES ('%s', '%s');"),
		GET_BY_TWO_ID(SqlQueries.GET_BY_TWO_ID, "SELECT * FROM users_movie WHERE user_ID = '%s' AND movie_ID = '%s'"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users WHERE id = %s;");
		//
		private SqlQueries sqlQuery;
		private String query;

		private UserMovieEntityQueries(SqlQueries sqlQuery, String query) {
			this.sqlQuery = sqlQuery;
			this.query = query;
		}

		public SqlQueries getSqlQuery() {
			return sqlQuery;
		}

		@Override
		public String toString() {
			return query;
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	@Override
	public Long getId() {
		return null;
	}

	
	

}
