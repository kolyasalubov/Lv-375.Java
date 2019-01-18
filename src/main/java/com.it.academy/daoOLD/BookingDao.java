package com.it.academy.daoOLD;

import com.it.academy.entity.Booking;

import java.sql.Connection;
import java.util.List;

public class BookingDao implements IBookingDao{

    private Connection connection = null;

    //TODO
    //CRUD

    
    public void createTable(){
        //language=MySQL
        String createTable = "CREATE TABLE IF NOT EXISTS bookings(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "room_id BIGINT," +
                "user_id BIGINT," +
                "start_date DATETIME NOT NULL," +
                "end_date DATETIME NOT NULL," +
                "purpose VARCHAR(100)," +
                "FOREIGN KEY(room_id) REFERENCES rooms(id) ON DELETE CASCADE," +
                "FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE" +
                ")";
        executeStatement(connection, createTable);
    }

    @Override
    public void addBooking(Booking booking) {
        //language=MySQL
        String addBooking = "INSERT INTO bookings(room_id, user_id, start_date, end_date, purpose) " +
                "VALUES ( " +
                    "(SELECT id " +
                        "FROM rooms " +
                        "WHERE number=?), " +
                    "(SELECT id " +
                        "FROM users " +
                        "WHERE email=?), " +
                " ?, ?, ?)";
    }

    @Override
    public List<Booking> getFutureByRoom(long roomId) {
        //language=MySQL
        String getFutureByRoom = "SELECT * " +
                "FROM bookings " +
                "WHERE room_id=? AND start_date > ? " + //current date
                "ORDER BY start_date";

        return null;
    }

    @Override
    public List<Booking> getArchiveByRoom(long roomId) {
        //language=MySQL
        String getArchiveByRoom = "SELECT * " +
                "FROM bookings " +
                "WHERE room_id=? AND start_date < ? " + //current date
                "ORDER BY start_date DESC";

        return null;
    }

    @Override
    public List<Booking> getFutureByUser(long userId) {
        //language=MySQL
        String getFutureByUser = "SELECT * " +
                "FROM bookings " +
                "WHERE user_id=? AND start_date > ? " + //current date
                "ORDER BY start_date";

        return null;
    }

    @Override
    public List<Booking> getArchiveByUser(long userId) {
        //language=MySQL
        String getArchiveByUser = "SELECT * " +
                "FROM bookings " +
                "WHERE user_id=? AND start_date < ? " + //current date
                "ORDER BY start_date DESC";

        return null;
    }

    @Override
    public Booking getInfo(long id) {
        //language=MySQL
        String getById = "SELECT * " +
                "FROM bookings " +
                "WHERE id=?";

        return null;
    }

    @Override
    public void editInfo(Booking booking) {
        //language=MySQL
        String updateBooking = "UPDATE bookings AS b " +
                "SET room_id = " +
                    "(SELECT r.id " +
                        "FROM rooms AS r " +
                        "WHERE number=?), " +
                "user_id = " +
                    "(SELECT u.id " +
                        "FROM users AS u " +
                        "WHERE email=?), " +
                "start_date=?, end_date=?, purpose=? " +
                "WHERE b.id=?";

    }

    @Override
    public void deleteBooking(long id) {
        //language=MySQL
        String updateBooking = "DELETE FROM bookings " +
                "WHERE id=?";
    }

    @Override
    public boolean isExists(Booking booking) {
        //language=MySQL
        String getByTime = "SELECT id " +
                "FROM bookings " +
                "WHERE (start_date < ? AND end_date > ?) " + //current start_date
                    "OR (start_date > ? " + //current start_date
                    "AND start_date < ?)"; //current end_date

        return false;
    }
}
