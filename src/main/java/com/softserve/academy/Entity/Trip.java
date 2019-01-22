package com.softserve.academy.Entity;

public class Trip implements IEntity {
	
    public static enum TripEntityQueries {
    	CREATE(SqlQueries.CREATE, 
    			"CREATE TABLE IF NOT EXISTS trips ("
    			+ "id int (11)  NOT NULL PRIMARY KEY AUTO_INCREMENT,"
    			+ " country varchar (20)  NOT NULL,"
    			+ " title varchar (20) NOT NULL,"
    			+ " date varchar (10)  NOT NULL,"
    			+ " description varchar (500) NOT NULL,"
    			+ " authors_alias varchar (10) NOT NULL,"
    			+ " idUser int (11)  NOT NULL REFERENCES users (id)"
    			+ ");"),
    	
        INSERT(SqlQueries.INSERT, "INSERT INTO trips (country, title, date, description, authors_alias, idUser) VALUES ('%s',  '%s', '%s', '%s', '%s', %s);"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, country, title, date, description, authors_alias, idUser FROM trips WHERE id = %s;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, country, title, date, description, authors_alias, idUser FROM trips WHERE %s = '%s';"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT id, country, title, date, description, authors_alias, idUser FROM trips;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE trips SET country = '%s', title = '%s', date = '%s', description = '%s', authors_alias = '%s', idUser = '%s' WHERE id = %s;"),
        UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE trips SET %s = '%s' WHERE %s = '%s';"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM trips WHERE id = %s;"),
        DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM trips WHERE %s = '%s';");
    	
        private SqlQueries sqlQuery;
        private String query;

        private TripEntityQueries(SqlQueries sqlQuery, String query) {
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

	private Long id;
	private String country;
	private String title;
	private String date;
	private String description;
	private String authors_alias;
	private Long idUser;
	
	public Trip(Long id, String country, String title, String date, String description, String authors_alias, Long idUser) {
		this.id = id;
		this.country = country;
		this.title = title;
		this.date = date;
		this.description = description;
		this.authors_alias = authors_alias;
		this.idUser = idUser;
	}

	//getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getAuthors_alias() {
		return authors_alias;
	}

	public void setAuthors_alias(String authors_alias) {
		this.authors_alias = authors_alias;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", country=" + country + ", date=" + date + ", title=" + title + ", description="
				+ description + ", authors_alias=" + authors_alias + ", idUser=" + idUser + "]";
	}
	


}