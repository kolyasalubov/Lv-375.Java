package com.it.academy.dto;

public class RoomDto {

    private long idRoom;
    private int number;
    private String type;

    public RoomDto(){}

    public RoomDto(int number, String type) {
        this.number = number;
        this.type = type;
    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
