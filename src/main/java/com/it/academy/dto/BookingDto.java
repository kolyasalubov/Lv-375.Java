package com.it.academy.dto;

/**
 * Class BookingDto represents DTO object for operations with Bookings
 */
public class BookingDto {

    private String idBooking;
    private String userEmail;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String purpose;

    public BookingDto() {
    }

    public BookingDto(String idBooking, String userEmail, String startDate, String endDate, String startTime,
                      String endTime, String purpose) {
        this.idBooking = idBooking;
        this.userEmail = userEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.purpose = purpose;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BookingDto{" +
                "idBooking='" + idBooking + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
