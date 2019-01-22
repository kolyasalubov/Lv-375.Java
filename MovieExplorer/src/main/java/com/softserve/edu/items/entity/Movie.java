package com.softserve.edu.items.entity;

public class Movie implements BaseEntity{
	
	private Long id;
	private String title; 
	private String imdbMovieID; 	
	private String information;
	private String posterUrl;
	
	public static enum MovieEntityQueries {
		CREATE(SqlQueries.CREATE, "create table if not exists movie (\r\n" + 
				"	ID bigint(50) not null auto_increment primary key,\r\n" + 
				"    title varchar(100) not null UNIQUE,\r\n" + 
				"    imdb_movie_ID varchar(50) not null,\r\n" + 
				"    information varchar (10000) not null,\r\n" + 
				"    poster_url varchar(255) not null UNIQUE\r\n" + 
				");"),
		INSERT(SqlQueries.INSERT, "INSERT INTO movie (title, imdb_movie_ID, information, poster_url) VALUES ('%s', '%s', '%s', '%s');"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, title, imdb_movie_ID, information, poster_url "
									  + "FROM movie WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, title, imdb_movie_ID, information, poster_url "
											+ "FROM movie WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, title, imdb_movie_ID, information, poster_url "
												    + "FROM movie"),
		GET_ALL_USER_MOVIES(SqlQueries.GET_ALL_USER_MOVIES,
				"select m.id, m.title, m.imdb_movie_ID, m.information, m.poster_url from movie m \r\n" + 
						"inner join users_movie u on m.ID = u.movie_ID \r\n" + 
						"where u.user_ID = '%s';"),
		GET_ALL_ORDER_BY(SqlQueries.GET_ALL_ORDER_BY, "SELECT id, title, imdb_movie_ID, information, poster_url "
												    + "FROM movie ORDER BY %s LIMIT %s, %s"),
		GET_BY_TITLE_AND_NOT_ID(SqlQueries.GET_BY_TITLE_AND_NOT_ID, "SELECT id, title, imdb_movie_ID, information, poster_url "
				+ "FROM movie WHERE title = '%s' AND id != '%s';"),
		GET_USER_MOVIES_OFFSET(SqlQueries.GET_USER_MOVIES_OFFSET,
				"select m.id, m.title, m.imdb_movie_ID, m.information, m.poster_url from movie m \r\n" + 
				"inner join users_movie u on m.ID = u.movie_ID \r\n" + 
				"where u.user_ID = '%s' \r\n" + 
				"order by m.title \r\n" +
				"limit %s, %s \r\n" +
				";"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE movie "
				+ "SET title = '%s', imdb_movie_ID = '%s', information = '%s', poster_url = '%s' "
											+ "WHERE id = %s;"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM movie WHERE id = %s;"),
		SEARCH(SqlQueries.SEARCH, "SELECT id, title, imdb_movie_ID, information, poster_url FROM movie "
				+ "WHERE title LIKE '%s' ORDER BY title LIMIT %s, %s;");
		
		private SqlQueries sqlQuery;
		private String query;

		private MovieEntityQueries(SqlQueries sqlQuery, String query) {
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

	public Movie() {
	}

	public Movie(Long id, String title, String imdbMovieID, String information, String posterUrl) {
		this.id = id;
		this.title = title;
		this.imdbMovieID = imdbMovieID;
		this.information = information;
		this.posterUrl = posterUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImdbMovieID() {
		return imdbMovieID;
	}

	public void setImdbMovieID(String imdbMovieID) {
		this.imdbMovieID = imdbMovieID;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", imdbMovieID=" + imdbMovieID + ", information=" + information
				+ ", posterUrl=" + posterUrl + "]";
	}

	
	
}
