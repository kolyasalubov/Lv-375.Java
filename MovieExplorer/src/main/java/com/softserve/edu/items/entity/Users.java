package com.softserve.edu.items.entity;

public class Users implements BaseEntity {
	
	private Long id;
	private String fullName;
	private Role role;
	private String password;
	private String email;
	private int isActive;
		
	public static enum UserEntityQueries {
		CREATE_DATABASE(SqlQueries.CREATE_DATABASE, "create database if not exists movies_demo1;"),
		CREATE(SqlQueries.CREATE, "create table if not exists users (\r\n" + 
				"	ID bigint(50) not null auto_increment primary key,\r\n" + 
				"	full_name varchar (50) not null,\r\n" + 
				"   role varchar (10),\r\n" + 
				"   user_password varchar (25) not null,\r\n" + 
				"   email varchar (40) not null UNIQUE,\r\n" + 
				"   isActive boolean \r\n" + 
				");"),
		INSERT(SqlQueries.INSERT, "INSERT INTO users (full_name, role, user_password, email, isActive) VALUES ('%s', '%s', '%s', '%s','%s');"),
		INSERT_USER_MOVIE(SqlQueries.INSERT_USER_MOVIE, "INSERT INTO users_movie (user_ID, movie_ID) VALUES ('%s', '%s')"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id, full_name, role, user_password, email, isActive FROM users WHERE id = %s;"),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT id, full_name, role, user_password, email, isActive FROM users WHERE %s = '%s';"),
		GET_ALL(SqlQueries.GET_ALL, "SELECT id, full_name, role, user_password, email, isActive FROM users;"),
		GET_ALL_ORDER_BY(SqlQueries.GET_ALL_ORDER_BY, "SELECT id, full_name, role, user_password, email, isActive "
			    + "FROM users ORDER BY %s LIMIT %s, %s"),
		GET_USER_MOVIES(SqlQueries.GET_USER_MOVIES, "SELECT * FROM movies m"
													+ "WHERE id IN (select movie_ID from users_movie\r\n" 
													+ "WHERE user_ID = '%s')"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET full_name = '%s', role = '%s'"
				+ ", user_password = '%s', email = '%s', isActive = '%s' WHERE id = %s;"); 	
		
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

	public Users(Long id, String fullName, String role, String password, String email, int isActive) {
		this.id = id;
		this.fullName = fullName;
		this.role = Role.valueOf(role);
		this.password = password;
		this.email = email;
		this.isActive = isActive;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", fullName=" + fullName + ", role=" + role + ", password=" + password + ", email="
				+ email + "]";
	}
	
	
	
	
}
