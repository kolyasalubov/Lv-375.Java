package com.softserve.academy.Database;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
	private final static String FAILED_JDBC_DRIVER = "Failed to Create JDBC Driver";

	private DataSourceRepository() {
	}

	public static DataSource getDefault() {
		return getMySqlLocalHost();
	}

	public static DataSource getMySqlLocalHost() {
		Driver sqlDriver;
		try {
			
			sqlDriver = new com.mysql.cj.jdbc.Driver();
		} catch (SQLException e) {
			throw new RuntimeException(FAILED_JDBC_DRIVER);
		}
		return new DataSource(sqlDriver,
				"jdbc:mysql://localhost:3306/travel?createDatabaseIfNotExist=true&useSSL=false",
				"root", 
				"test");

	}

}
