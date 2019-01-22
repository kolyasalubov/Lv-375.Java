package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.softserve.edu.items.dao.MovieDao;
import com.softserve.edu.items.dao.UsersDao;
import com.softserve.edu.items.dao.UsersMovieDao;
import com.softserve.edu.items.entity.Movie;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.entity.Users;
import com.softserve.edu.items.entity.Users.UserEntityQueries;
import com.softserve.edu.items.entity.UsersMovie;

public class Appl {
	
	static Connection connection = null;

	public static void main(String[] args) throws SQLException {
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/movies_demo1?"
				+ "createDatabaseIfNotExist=true?"
				+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&"
				+ "useLegacyDatetimeCode=false&serverTimezone=UTC\r\n" + 
				"";
		String user = "root";
		String password = "asd123";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("No driver found" + e.getMessage());
			return;
		}
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Incorrect url" + e.getMessage());
		}
		
		if (connection != null) {
			System.out.println("Connection Successful! \n");
		} else {
			System.out.println("Connection ERROR \n");
			System.exit(1);
		}
		
	}
}
		
//		UsersDao users = new UsersDao();
//		MovieDao movie = new MovieDao();
//		UsersMovieDao userMovie = new UsersMovieDao();
//		users.create();
//		movie.create();
//		userMovie.create();
////		users.insert(new Users((long)2, "Name", Role.ROLE_ADMIN.toString(), "pass", "anb@gmail.com", true));
//		movie.insert(new Movie((long)4, "Ending", 4.5, "2015 bla bla bla"));
////		movie.insert(new Movie((long)4, "BlaBla", 4.5, "2015 bla bla bla"));
////		userMovie.insert(new UsersMovie((long)1, 1l));
////		userMovie.insert(new UsersMovie((long)1, 2l));
////		movie.deleteById(1l);
////		userMovie.delete(new UsersMovie(1l, 1l));
//		
//		//Test update 
////		Users u = users.getById(1l);
////		System.out.println(u.toString());
////		u.setFullName("Bobby Bob");
////		users.updateByEntity(u);
////		Users newU = users.getById(1l);
////		System.out.println(newU.toString());
//		
//		
//		
////		createDatabase();
////		createTableUsers();
////		createTableMovie();
////		createTableUsersMovie();
////		createTableComments();
////		createAlterTableComments();
//		
//		Statement st = connection.createStatement();
//		
//		
//		if (connection != null)
//		    try { connection.close(); }
//		    catch (SQLException ignore) { }
//	}
//	
//	private static void createDatabase() throws SQLException {
//		String dropQuery = "drop database if exists movies_demo1";
//        String query = "CREATE DATABASE movies_demo1;";
//        String usequery = "use movies_demo1;";
//        Statement stmt = connection.createStatement();
//        stmt.execute(dropQuery);
//        stmt.executeUpdate(query);
//        stmt.executeUpdate(usequery);
//        stmt.close();
//        System.out.println("db has been successfully created");
//    }
//	
//	public static void createTableUsers() throws SQLException {
//		
//		String dropQuery = "drop table if exists users";
//		String query = "create table users (\r\n" + 
//				"	ID bigint(50) not null auto_increment primary key,\r\n" + 
//				"	full_name varchar (50) not null,\r\n" + 
//				"    role varchar (10) not null,\r\n" + 
//				"    user_password varchar (25),\r\n" + 
//				"    email varchar (40),\r\n" + 
//				"    isActive boolean \r\n" + 
//				");";
//		Statement stmt = connection.createStatement();
//		stmt.execute(dropQuery);
//		stmt.execute(query);
//		stmt.close();
//	}
//	
//	public static void createTableMovie() throws SQLException {
//		String dropQuery = "drop table if exists movie";
//		String query = "create table movie (\r\n" + 
//				"	ID bigint(50) not null auto_increment primary key,\r\n" + 
//				"    title varchar(100) not null,\r\n" + 
//				"    rate smallint,\r\n" + 
//				"    information varchar (255)\r\n" + 
//				");";
//		Statement stmt = connection.createStatement();
//		stmt.execute(dropQuery);
//		stmt.execute(query);
//		stmt.close();
//	}
//	
//	public static void createTableUsersMovie() throws SQLException {
//		String dropQuery = "drop table if exists users_movie";
//		String query = "create table users_movie (\r\n" + 
//				"	 user_ID bigint,\r\n" + 
//				"    movie_ID bigint,\r\n" + 
//				"    foreign key (user_ID) references users(ID),\r\n" + 
//				"    foreign key (movie_ID) references movie(ID),\r\n" + 
//				"    primary key (user_ID, movie_ID)\r\n" + 
//				");";
//		Statement stmt = connection.createStatement();
//		stmt.execute(dropQuery);
//		stmt.execute(query);
//		stmt.close();
//	}
//	
////	public static void createTableComments() throws SQLException {
////		String dropQuery = "drop table if exists comments";
////		String query = "create table comments (\r\n" + 
////				"	ID bigint(50) not null auto_increment primary key,\r\n" + 
////				"    comment_text varchar(250) not null,\r\n" + 
////				"    user_ID bigint,\r\n" + 
////				"    movie_ID bigint\r\n" + 
////				");";
////		Statement stmt = connection.createStatement();
////		stmt.execute(dropQuery);
////		stmt.execute(query);
////		stmt.close();
////	}
//
////	public static void createAlterTableComments() throws SQLException {
////		String query = "alter table comments add(\r\n" + 
////				"    foreign key (user_ID) references users(ID),\r\n" + 
////				"    foreign key (movie_ID) references movie(ID)\r\n" + 
////				");";
////		Statement stmt = connection.createStatement();
////		stmt.execute(query);
////		stmt.close();
//	}
	
	
//}
