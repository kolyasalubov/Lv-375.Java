package com.it.academy.dto;

public class BookingDto {

    private long idBooking;
    private String startDate;
    private String endDate;
    private String purpose;

    public BookingDto(){}

    public BookingDto(String startDate, String endDate, String purpose) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.purpose = purpose;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public long getIdBooking() {
        return idBooking;
    }
}
