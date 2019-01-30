package com.it.academy.constants;


/**
 * Enum RoomConstants keeps all constants which are connected with Rooms
 */
public enum RoomConstants {

    ID("idRoom"),
    NUMBER("number"),
    TYPE("type"),

    ROOMS("rooms"),
    ROOM("room"),
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
