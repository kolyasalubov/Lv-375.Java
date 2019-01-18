package com.it.academy.daoOLD;

import com.it.academy.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserDao implements IUserDao {

    private Connection connection = null;

    //TODO
    //CRUD

    public void createTable() {
        //language=MySQL
        String createTable = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "email VARCHAR(30) NOT NULL," +
                "password VARCHAR(20) NOT NULL," +
                "first_name VARCHAR(20) NOT NULL," +
                "last_name VARCHAR(40) NOT NULL," +
                "position VARCHAR(20)," +
                "phone VARCHAR(12)," +
                "is_admin BOOL DEFAULT FALSE ," +
                "is_blocked BOOL DEFAULT FALSE ," +
                "UNIQUE(email)" +
                ")";
        executeStatement(connection, createTable);
    }

    @Override
    public void addUser(User user) {
        //language=MySQL
        String addUser = "INSERT INTO users(email, password, first_name, last_name, position, phone) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public List<User> getAll() {
        //language=MySQL
        String getAll = "SELECT * " +
                "FROM users";

        return null;
    }

    @Override
    public User getInfo(long id) {
        //language=MySQL
        String getById = "SELECT * " +
                "FROM users " +
                "WHERE id=?";

        return null;
    }

    @Override
    public void editInfo(User user) {
        //language=MySQL
        String updateUser = "UPDATE users " +
                "SET email=?, password=?, first_name=?, last_name=?, position=?, phone=? " +
                "WHERE id=?";

    }

    @Override
    public boolean isExists(User user) {
        //language=MySQL
        String getByCredential = "SELECT id " +
                "FROM users " +
                "WHERE email=? AND password=?";

        return false;
    }

    @Override
    public void toAdmin(long id) {
        //language=MySQL
        String updateToAdmin = "UPDATE users " +
                "SET is_admin=1 " +
                "WHERE id=?";
    }

    @Override
    public void toUser(long id) {
        //language=MySQL
        String updateToUser = "UPDATE users " +
                "SET is_admin=0 " +
                "WHERE id=?";
    }

    @Override
    public void block(long id) {
        //language=MySQL
        String block = "UPDATE users " +
                "SET is_blocked=1 " +
                "WHERE id=?";
    }

    @Override
    public void unblock(long id) {
        //language=MySQL
        String unblock = "UPDATE users " +
                "SET is_blocked=0 " +
                "WHERE id=?";
    }
}
