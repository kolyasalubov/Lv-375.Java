package myArticles.edu.Services;


import myArticles.edu.DAO.UserDao;
import myArticles.edu.dto.AllUsersDto;
import myArticles.edu.dto.PageInfoDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.entity.User;

import java.util.ArrayList;

/**
 * Only Admin can use this Service to see all users and change their
 */
public class AdminService {
    private UserDao userDao;

    public AdminService(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Get all users from database
     */
    public AllUsersDto getAllUsers() {
        AllUsersDto allUsersDto = new AllUsersDto();
        for (User user : userDao.getAll()) {
            UserDto userDto = new UserDto(user.getUserName(),
                    user.getPassword(),
                    user.getEmail(),
                    user.isAdmin(),
                    user.isBlocked());

            allUsersDto.addUsers(userDto);
        }
        return allUsersDto;
    }

    /**
     * In this method we get Users with we need to show on page
     * used information in PageInfoDto object
     * @param pageInfoDto - info about page
     * @return - Dto object with all User with we must show
     */
    public AllUsersDto getAllUsers(PageInfoDto pageInfoDto) {
        AllUsersDto allUsersDto = getAllUsers();
        if (pageInfoDto.getVisible() == 100000) {
            return allUsersDto;
        }
        // start = -1 in case if users is less then we need, in another case we found start index
        int start = allUsersDto.getUsers().size() > pageInfoDto.getVisible() * (pageInfoDto.getPageNumber() - 1)
                ? pageInfoDto.getVisible() * (pageInfoDto.getPageNumber() - 1)
                : -1;
        // end = -1 in case if users is less then we need, in another case we found end index
        int end = allUsersDto.getUsers().size() > (pageInfoDto.getVisible() * (pageInfoDto.getPageNumber()))
                ? (pageInfoDto.getVisible() * (pageInfoDto.getPageNumber()))
                : -1;
        if (start != -1) {
            if (end != -1) {
                //get sublist with users what we need
                allUsersDto.setUsers(allUsersDto.getUsers().subList(start, end));
            } else {
                //get all users after start index
                allUsersDto.setUsers(allUsersDto.getUsers().subList(start, allUsersDto.getUsers().size()));
            }
        } else {
            //our user's number is less than we need
            allUsersDto.setUsers(new ArrayList <>());
        }

        return allUsersDto;
    }
}
