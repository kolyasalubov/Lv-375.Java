package com.it.academy.service;


import com.it.academy.common.ObjContainer;
import com.it.academy.dao.UserDao;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.dto.UserDto;
import com.it.academy.entity.Booking;
import com.it.academy.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class UserService provides methods for operations with users
 */
public class UserService {

    private UserDao userDao;

    public UserService(){
        userDao = ObjContainer.getInstance().getUserDao();
    }

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * Create User based on UserDto
     */
    private User dtoToUser(UserDto userDto){
        User user = new User();
        if(userDto.getIdUser() != null)
            user.setId(Long.parseLong(userDto.getIdUser()));
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPosition(userDto.getPosition());
        user.setPhone(userDto.getPhone());
        user.setAdmin(Boolean.parseBoolean(userDto.getIsAdmin()));
        user.setBlocked(Boolean.parseBoolean(userDto.getIsBlocked()));
        return user;
    }

    /**
     * Create UserDto based on User
     */
    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setIdUser(String.valueOf(user.getId()));
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPosition(user.getPosition());
        userDto.setPhone(user.getPhone());
        userDto.setIsAdmin(String.valueOf(user.isAdmin()));
        userDto.setIsBlocked(String.valueOf(user.isBlocked()));
        return userDto;
    }

    /**
     * Checks if user which wants to log in is in DB
     */
    public boolean isValid(LoginDto loginDto){
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        return userDao.isExist(user);
    }

    /**
     * Adds new user to DB
     */
    public boolean createUser(UserDto userDto){
        boolean result = true;
        User user = dtoToUser(userDto);
        try{
            userDao.insert(user);
            if(wasFirst()){
                userDto.setIsAdmin("true");
                adminToUser(userDto);
            }
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    /**
     * Checks if the user which signed up is first in DB
     */
    private boolean wasFirst(){
        boolean isFirst = false;
        try {
            userDao.getById(2); // get the second user
        } catch (RuntimeException e){ // if id=2 is not exist, so the id=1 was added now
            isFirst = true;
        }
        System.out.println(isFirst);
        return isFirst;
    }

    /**
     * Update User info in DB
     */
    public boolean updateUser(UserDto userDto){
        boolean result = true;
        User user = dtoToUser(userDto);
        try{
            if(userDao.isEmailExist(userDto.getEmail())){
                result = false;
            } else {
                userDao.updateEntityById(user);
            }
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }

    /**
     * Get UserDto based on info from LoginDto
     */
    public UserDto getUserDto(LoginDto loginDto){
        return userToDto(getUserByLoginDto(loginDto));
    }

    /**
     * Checks if the current user is blocked
     */
    public boolean isBlocked(LoginDto loginDto){
        return getUserByLoginDto(loginDto).isBlocked();
    }

    /**
     * Checks if the current user is admin
     */
    public boolean isAdmin(LoginDto loginDto){
        return getUserByLoginDto(loginDto).isAdmin();
    }

    /**
     * Gets User based on info from LoginDto
     */
    private User getUserByLoginDto(LoginDto loginDto){
        return userDao.getByFieldName("email", loginDto.getEmail()).get(0);
    }

    /**
     * Get Collection of all users
     */
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

    /**
     * Changes admin status of user
     */
    public boolean adminToUser(UserDto userDto){
        return giveRightsToUser(userDto, "is_admin", userDto.getIsAdmin());
    }

    /**
     * Changes blocked status of user
     */
    public boolean blockToUser(UserDto userDto){
        return giveRightsToUser(userDto, "is_blocked", userDto.getIsBlocked());
    }

    /**
     * Changes some field of user
     */
    private boolean giveRightsToUser(UserDto userDto, String fieldName, String fieldValue){
        boolean result = true;
        try{
            userDao.updateFieldById(fieldName, fieldValue,
                    Long.parseLong(userDto.getIdUser()));
        } catch (Exception e){
            System.out.println("RuntimeException: " + e.getMessage());
            result = false;
        } return result;
    }
}
