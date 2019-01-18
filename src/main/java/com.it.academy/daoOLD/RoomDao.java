package com.it.academy.daoOLD;

import com.it.academy.entity.Room;

import java.sql.Connection;
import java.util.List;

public class RoomDao implements IRoomDao{

    private Connection connection = null;

    //TODO
    //CRUD


    public void createTable(){
        //language=MySQL
        String createTable = "CREATE TABLE IF NOT EXISTS rooms(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "number INT NOT NULL," +
                "type VARCHAR(40)," +
                "UNIQUE(number)" +
                ")";
        executeStatement(connection, createTable);
    }

    @Override
    public void addRoom(Room room) {
        //language=MySQL
        String addRoom = "INSERT INTO rooms(number, type) " +
                "VALUES (?, ?)";
    }

    @Override
    public List<Room> getAll() {
        //language=MySQL
        String getAll = "SELECT * " +
                "FROM rooms";

        return null;
    }

    @Override
    public Room getInfo(long id) {
        //language=MySQL
        String getById = "SELECT * " +
                "FROM rooms " +
                "WHERE id=?";

        return null;
    }

    @Override
    public void editInfo(Room room) {
        //language=MySQL
        String updateRoom = "UPDATE rooms " +
                "SET number=?, type=? " +
                "WHERE id=?";
    }

    @Override
    public void deleteRoom(long id) {
        //language=MySQL
        String updateRoom = "DELETE FROM rooms " +
                "WHERE id=?";
    }
}
