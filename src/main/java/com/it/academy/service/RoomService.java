package com.it.academy.service;

import com.it.academy.common.ObjContainer;
import com.it.academy.dao.RoomDao;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private RoomDao roomDao;

    public RoomService(){
        roomDao = ObjContainer.getInstance().getRoomDao();
    }

    public RoomService(RoomDao roomDao){
        this.roomDao = roomDao;
    }

    private Room dtoToRoom(RoomDto roomDto){
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setType(roomDto.getType());
        return room;
    }

    private RoomDto roomToDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.setIdRoom(room.getId());
        roomDto.setNumber(room.getNumber());
        roomDto.setType(room.getType());
        return roomDto;
    }

    public boolean createRoom(RoomDto roomDto){
        boolean result = true;
        Room room = dtoToRoom(roomDto);
        try{
            roomDao.insert(room);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public boolean updateRoom(RoomDto roomDto){
        boolean result = true;
        Room room = dtoToRoom(roomDto);
        try{
            roomDao.updateEntityById(room);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public CollectionDto<RoomDto> getRoomCollectionDto() {
        CollectionDto<RoomDto> rooms = null;
        try {
            List<Room> list = roomDao.getAll();
            List<RoomDto> dtos = new ArrayList<>();
            for (Room u : list) {
                dtos.add(roomToDto(u));
            }
            rooms = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return rooms;
    }

    public RoomDto fillRoomDtoInfo(RoomDto roomDto){
        Room room = roomDao.getById(roomDto.getIdRoom());
        return roomToDto(room);
    }

    public boolean deleteRoom(RoomDto roomDto){
        boolean result = true;
        try{
            roomDao.deleteByFieldName("number", String.valueOf(roomDto.getNumber()));
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }
}
