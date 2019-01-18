package com.it.academy;

import com.it.academy.dao.BookingDao;
import com.it.academy.dao.RoomDao;
import com.it.academy.dao.UserDao;
import com.it.academy.entity.Booking;
import com.it.academy.entity.Room;
import com.it.academy.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainDao {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        RoomDao roomDao = new RoomDao();
        BookingDao bookingDao = new BookingDao();

        // ---> create tables <---
//        userDao.createTableIfNotExists();
//        roomDao.createTableIfNotExists();
//        bookingDao.createTableIfNotExists();

        // ---> USER <---
        User u1 = new User(1, "in@gmail.com", "2", "Inesa",
                "hermaniuk","junior", "380");
        User u2 = new User("ines@gmail.com", "22", "Inesa", "hermaniuk",
                "junior", "380");

//        System.out.println(userDao.insert(u1));
//        System.out.println(userDao.insert(u2));
//
//        System.out.println(userDao.getById(1).toString());
//        System.out.println(userDao.getByFieldName("email" , "inesusja@gmal.com").toString());
//
//        for(User u : userDao.getAll()){
//            System.out.println(u.toString());
//        }
//
//        System.out.println(userDao.updateFieldById("is_admin", Boolean.toString(true), 1));
//        System.out.println(userDao.updateFieldByField("is_admin", Boolean.toString(false),
//                "email", "inesusja@gmal.com"));
//
//        System.out.println(userDao.updateEntityById(u1));
//        System.out.println(userDao.updateEntityByField(u1, "email", "inesusja@gmail.com"));
//
//        System.out.println(userDao.isExist(u1));
//        System.out.println(userDao.isExist(u2));

        // ---> ROOM <---
        Room r1 = new Room(5, "IT ....");
        Room r2 = new Room(2, 7, "IT room");

//        System.out.println(roomDao.insert(r1));
//        System.out.println(roomDao.insert(r2));
//
//        System.out.println(roomDao.getById(1).toString());
//        System.out.println(roomDao.getByFieldName("number" , "5").toString());
//
//        for(Room r : roomDao.getAll()){
//            System.out.println(r.toString());
//        }
//
//        System.out.println(roomDao.updateEntityById(r2));
//        System.out.println(roomDao.updateEntityByField(r1, "number", "5"));
//
//        System.out.println(roomDao.deleteById(2));
//        System.out.println(roomDao.deleteByFieldName("number", "5"));

        // ---> BOOKING <---
        Booking b1 = new Booking(3, 1, "2019-01-14 00:00:22", getCurrentDateTime(), "aka");
        Booking b2 = new Booking(5, 4, 1, getCurrentDateTime(), getCurrentDateTime(), "baka");

//        System.out.println(bookingDao.insert(b1));
//        System.out.println(bookingDao.insert(b2));
//
//        System.out.println(bookingDao.getById(2).toString());
//        System.out.println(bookingDao.getByFieldName("room_id" , "3").toString());
//
//        System.out.println(bookingDao.updateEntityById(b2));
//        System.out.println(bookingDao.updateEntityByField(b2, "start_date", "2019-01-13 23:56:28"));
//
//        System.out.println(bookingDao.deleteById(4));
//        System.out.println(bookingDao.deleteByFieldName("start_date", "2019-01-13 23:59:35"));

//        System.out.println(bookingDao.isExist(b1));

//        System.out.println(bookingDao.getPastByField("user_id", "1"));
//        System.out.println(bookingDao.getFutureByField("room_id", "4"));


    }

    private static String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
