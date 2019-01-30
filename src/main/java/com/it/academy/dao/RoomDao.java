package com.it.academy.dao;

import com.it.academy.entity.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Class RoomDao provides all basic methods to operate with Room Entity
 */
public class RoomDao extends ADaoCRUD<Room> {

    public RoomDao(){
        super();
    }


    /**
     * Implementation of abstract method from ADaoRead
     */
    @Override
    protected void init() {
        for(Room.RoomQueries roomQueries : Room.RoomQueries.values()){
            sqlQueries.put(roomQueries.getQueryName(), roomQueries.getQuery());
        }
    }

    /**
     * Implementation of abstract method from ADaoRead
     */
    @Override
    protected Room createInstance(List<String> list) {
        return new Room(
                Long.parseLong(list.get(0)),
                Integer.parseInt(list.get(1)),
                list.get(2)
        );
    }

    /**
     * Implementation of abstract method from ADaoCRUD
     */
    @Override
    protected List<Object> getFields(Room room) {
        List<Object> list = new ArrayList<>();
        list.add(room.getId());
        list.add(room.getNumber());
        list.add(room.getType());
        return list;
    }

    /**
     * Implementation of abstract method from ADaoCRUD
     */
    @Override
    protected List<Object> getUpdateFields(Room room) {
        List<Object> list = getFields(room);
        list.remove(0);
        list.add(room.getId());
        return list;
    }
}
