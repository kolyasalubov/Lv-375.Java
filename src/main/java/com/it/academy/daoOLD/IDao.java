package com.it.academy.daoOLD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface IDao {

    String URL = "jdbc:mysql://localhost:3306/room_booking?createDatabaseIfNotExist=true";
    String USER = "root";
    String PASS = "root";

    default Connection getConnection(Connection connection){
        try {
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(URL, USER, PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return connection;
    }

    default void closeConnection(Connection connection){
        try {
            if (!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    default void executeStatement(Connection connection, String sql){
        try {
            connection = getConnection(connection);
            Statement s = connection.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
