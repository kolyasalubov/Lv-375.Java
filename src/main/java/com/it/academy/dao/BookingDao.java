package com.it.academy.dao;

import com.it.academy.entity.Booking;
import com.it.academy.entity.QueryNames;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class BookingDao provides all basic methods to operate with Booking Entity
 */
public class BookingDao extends ADaoExist<Booking> {

    public BookingDao() {
        super();
    }


    /**
     * Implementation of abstract method from ADaoRead
     */
    @Override
    protected void init() {
        for (Booking.BookingQueries bookingQueries : Booking.BookingQueries.values()) {
            sqlQueries.put(bookingQueries.getQueryName(), bookingQueries.getQuery());
        }
    }

    /**
     * Implementation of abstract method from ADaoRead
     */
    @Override
    protected Booking createInstance(List<String> list) {
        return new Booking(
                Long.parseLong(list.get(0)), Long.parseLong(list.get(1)), Long.parseLong(list.get(2)),
                list.get(3), list.get(4), list.get(5)
        );
    }

    /**
     * Implementation of abstract method from ADaoCRUD
     */
    @Override
    protected List<Object> getFields(Booking booking) {
        List<Object> list = new ArrayList<>();
        list.add(booking.getId());
        list.add(booking.getRoomId());
        list.add(booking.getUserId());
        list.add(booking.getStartDate());
        list.add(booking.getEndDate());
        list.add(booking.getPurpose());
        return list;
    }

    /**
     * Implementation of abstract method from ADaoCRUD
     */
    @Override
    protected List<Object> getUpdateFields(Booking booking) {
        List<Object> list = getFields(booking);
        list.remove(0);
        list.add(booking.getId());
        return list;
    }

    /**
     * Implementation of abstract method from ADaoExist
     */
    @Override
    protected List<Object> getExistFields(Booking booking) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(booking.getStartDate());
        }
        list.add(booking.getEndDate());
        list.add(booking.getRoomId());
        return list;
    }


    /**
     * Gets List of future bookings
     */
    public List<Booking> getFutureByField(String fieldName, String fieldValue){
        return getBookingsByTime(fieldName, fieldValue, QueryNames.GET_FUTURE_BY_FIELD);
    }

    /**
     * Gets List of past bookings
     */
    public List<Booking> getPastByField(String fieldName, String fieldValue){
        return getBookingsByTime(fieldName, fieldValue, QueryNames.GET_PAST_BY_FIELD);
    }

    /**
     * Execute query which gets List of Bookings for some period of time (past/future)
     */
    private List<Booking> getBookingsByTime(String fieldName, String fieldValue, QueryNames queryName){
        String currentDateTime = getCurrentDateTime();
        String sql = String.format(sqlQueries.get(queryName),
                fieldName, fieldValue, currentDateTime);
        return getQueryResult(sql, queryName);
    }

    /**
     * Gets current time in MySql format
     */
    private String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
