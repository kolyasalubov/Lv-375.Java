package com.it.academy.daoOLD;

import com.it.academy.entity.Booking;

import java.util.List;

public interface IBookingDao extends IDao{

    void addBooking(Booking booking);
    List<Booking> getFutureByRoom(long roomId);
    List<Booking> getArchiveByRoom(long roomId);
    List<Booking> getFutureByUser(long userId);
    List<Booking> getArchiveByUser(long userId);
    Booking getInfo(long id);
    void editInfo(Booking booking);
    void deleteBooking(long id);
    boolean isExists(Booking booking);

}
