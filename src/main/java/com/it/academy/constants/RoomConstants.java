package com.it.academy.constants;

public enum RoomConstants {

    ID("idRoom"),
    NUMBER("number"),
    TYPE("type"),

    ROOMS("rooms"),
    ROOM_DTO("roomDto");

    private String constant;

    private RoomConstants(String constant) {
        this.constant = constant;
    }

    @Override
    public String toString() {
        return constant;
    }
}
