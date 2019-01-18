package com.it.academy.daoOLD;

import com.it.academy.entity.Room;

import java.util.List;

public interface IRoomDao extends IDao{

    void addRoom(Room room);
    List<Room> getAll();
    Room getInfo(long id);
    void editInfo(Room room);
    void deleteRoom(long id);

}
