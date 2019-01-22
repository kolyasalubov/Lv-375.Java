package com.softserve.academy.Entity;

public class User implements IEntity {

	public static enum UserEntityQueries {
    	CREATE(SqlQueries.CREATE, 
    			"CREATE TABLE IF NOT EXISTS users (" 
    	        + "id BIGINT (11)  NOT NULL PRIMARY KEY AUTO_INCREMENT,"
    			+ " login CHAR (30) NOT NULL,"
    			+ "password VARCHAR(20) NOT NULL," 
    			+ "alias VARCHAR (20) NOT NULL," 
    			+ "blocked VARCHAR (10) DEFAULT FALSE,"
    			+ "idRole int (2) NOT NULL,"
    			+ "UNIQUE(login)"
    			+ ");"),
		INSERT(SqlQueries.INSERT, "INSERT INTO users (login, password, alias, blocked, idRole) VALUES ('%s', '%s', '%s', '%s', %s);"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, login, password, alias, blocked, idRole FROM users WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, login, password, alias, blocked, idRole FROM users WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, login, password, alias, blocked, idRole FROM users;"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET login = '%s', password = '%s', alias = '%s', blocked = '%s', idRole = '%s' WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE users SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users WHERE id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD, "DELETE FROM users WHERE %s = '%s';");
		
		private SqlQueries sqlQuery;
		private String query;

		private UserEntityQueries(SqlQueries sqlQuery, String query) {
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
	private String login;
	private String password;
	private String alias;
	private Boolean blocked;
	private Long idRole;

	public User(Long id, String login, String password, String alias, Boolean blocked, Long idRole) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.alias = alias;
		this.blocked = false;
		this.idRole = idRole;
	}
	
	//getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", alias=" + alias + ", isblocked="
				+ blocked + ", idRole=" + idRole + "]";
	}
	
	}