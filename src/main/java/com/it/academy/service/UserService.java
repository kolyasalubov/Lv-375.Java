package com.it.academy.service;


import com.it.academy.common.ObjContainer;
import com.it.academy.dao.UserDao;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.dto.UserDto;
import com.it.academy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserDao userDao;

    public UserService(){
        userDao = ObjContainer.getInstance().getUserDao();
    }

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    private User dtoToUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPosition(userDto.getPosition());
        user.setPhone(userDto.getPhone());
        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPosition(user.getPosition());
        userDto.setPhone(user.getPhone());
        return userDto;
    }

    public boolean isValid(LoginDto loginDto){
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        return userDao.isExist(user);
    }

    public boolean createUser(UserDto userDto){
        boolean result = true;
        User user = dtoToUser(userDto);
        try{
            userDao.insert(user);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public boolean updateUser(UserDto userDto){
        boolean result = true;
        User user = dtoToUser(userDto);
        try{
            userDao.updateEntityById(user);
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    public UserDto getUserDto(LoginDto loginDto){
        User user = userDao.getByFieldName("email", loginDto.getEmail()).get(0);
        return userToDto(user);
    }

    public CollectionDto<UserDto> getUserCollectionDto() {
        CollectionDto<UserDto> users = null;
        try {
            List<User> list = userDao.getAll();
            List<UserDto> dtos = new ArrayList<>();
            for (User u : list) {
                dtos.add(userToDto(u));
            }
            users = new CollectionDto<>(dtos);
        } catch (Exception e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        return users;
    }

    public boolean adminToUser(UserDto userDto){
        return giveRightsToUser(userDto, "is_admin", userDto.isAdmin());
    }

    public boolean blockToUser(UserDto userDto){
        return giveRightsToUser(userDto, "is_blocked", userDto.isBlocked());
    }

    private boolean giveRightsToUser(UserDto userDto, String fieldName, boolean fieldValue){
        boolean result = true;
        try{
            userDao.updateFieldByField(fieldName, Boolean.toString(fieldValue),
                    "email", userDto.getEmail());
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }
}
