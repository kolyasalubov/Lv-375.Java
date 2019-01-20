package com.it.academy.dto;

public class RoomDto {

    private String idRoom;
    private String number;
    private String type;

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "idRoom=" + idRoom +
                ", number=" + number +
                ", type='" + type + '\'' +
                '}';
    }
}
