package com.it.academy.dao;

import com.it.academy.entity.Booking;
import com.it.academy.entity.QueryNames;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends ADaoExist<Booking> {

    public BookingDao() {
        super();
    }

    @Override
    protected void init() {
        for (Booking.BookingQueries bookingQueries : Booking.BookingQueries.values()) {
            sqlQueries.put(bookingQueries.getQueryName(), bookingQueries.getQuery());
        }
    }

    @Override
    protected Booking createInstance(List<String> list) {
        return new Booking(
                Long.parseLong(list.get(0)), Long.parseLong(list.get(1)), Long.parseLong(list.get(2)),
                list.get(3), list.get(4), list.get(5)
        );
    }

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

    @Override
    protected List<Object> getUpdateFields(Booking booking) {
        List<Object> list = getFields(booking);
        list.remove(0);
        list.add(booking.getId());
        return list;
    }

    @Override
    protected List<Object> getExistFields(Booking booking) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(booking.getStartDate());
        }
        list.add(booking.getEndDate());
        return list;
    }

    public List<Booking> getFutureByField(String fieldName, String fieldValue){
        return getBookingsByTime(fieldName, fieldValue, QueryNames.GET_FUTURE_BY_FIELD);
    }

    public List<Booking> getPastByField(String fieldName, String fieldValue){
        return getBookingsByTime(fieldName, fieldValue, QueryNames.GET_PAST_BY_FIELD);
    }

    private List<Booking> getBookingsByTime(String fieldName, String fieldValue, QueryNames queryName){
        String currentDateTime = getCurrentDateTime();
        String sql = String.format(sqlQueries.get(queryName),
                fieldName, fieldValue, currentDateTime);
        return getQueryResult(sql, queryName);
    }

    private String getCurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
