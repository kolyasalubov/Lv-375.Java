package com.it.academy.service;

import com.it.academy.common.ObjContainer;
import com.it.academy.dao.BookingDao;
import com.it.academy.dao.RoomDao;
import com.it.academy.dao.UserDao;

public class InitService {

    private UserDao userDao;
    private RoomDao roomDao;
    private BookingDao bookingDao;

    public InitService(){
        bookingDao = ObjContainer.getInstance().getBookingDao();
        userDao = ObjContainer.getInstance().getUserDao();
        roomDao = ObjContainer.getInstance().getRoomDao();
    }

    public boolean initDataBases(){
        boolean result = true;
        try {
            userDao.createTableIfNotExists();
            roomDao.createTableIfNotExists();
            bookingDao.createTableIfNotExists();
        } catch (Exception e){
            e.printStackTrace();
            result = false;
        } return result;
    }
}
