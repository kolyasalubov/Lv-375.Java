package myArticles.edu.Services;


import myArticles.edu.DataBase.DAO.UserDao;
import myArticles.edu.dto.AllUsersDto;
import myArticles.edu.dto.PageInfoDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.dto.UsersArticleDto;
import myArticles.edu.entity.User;

import java.util.ArrayList;

/**
 * Only Admin can use this Service to see all users and change their
 */
public class AdminService {
    private UserDao userDao;

    public AdminService(UserDao userDao){
        this.userDao = userDao;
    }

    /**
     * Get all users from database
     *
     */
    public AllUsersDto getAllUsers(){
        AllUsersDto allUsersDto = new AllUsersDto();
        for(User user : userDao.getAll()){
            UserDto userDto = new UserDto(user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.isAdmin(),
                    user.isBlocked());

            allUsersDto.addUsers(userDto);
        }
        return allUsersDto;
    }
    public AllUsersDto getAllUsers(PageInfoDto pageInfoDto){
        AllUsersDto allUsersDto = getAllUsers();
        if(pageInfoDto.getVisible() == 100000){
            return  allUsersDto;
        }
        int start = allUsersDto.getUsers().size() > pageInfoDto.getVisible()* (pageInfoDto.getPageNumber()-1)
                ? pageInfoDto.getVisible()* (pageInfoDto.getPageNumber()-1)
                : -1;
        int end = allUsersDto.getUsers().size() > (pageInfoDto.getVisible()* (pageInfoDto.getPageNumber()))
                ? (pageInfoDto.getVisible()* (pageInfoDto.getPageNumber()))
                : -1;
        if(start != -1){
            if(end != -1){
                allUsersDto.setUsers(allUsersDto.getUsers().subList(start, end));
            }
            else {
                allUsersDto.setUsers(allUsersDto.getUsers().subList(start, allUsersDto.getUsers().size()));
            }
        }
        else {
            allUsersDto.setUsers(new ArrayList<>());
        }
        System.out.println(start +" "+end);
        return allUsersDto;
    }
}
