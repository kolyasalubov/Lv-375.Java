package ua.cjhrxS;

import java.time.LocalDate;

import ua.cjhrxS.DAO.CourseDao;
import ua.cjhrxS.DAO.RoleDao;
import ua.cjhrxS.DAO.TeacherDao;
import ua.cjhrxS.DAO.UsersDao;
import ua.cjhrxS.DTO.SignInDTO;
import ua.cjhrxS.Entity.CourseEntity;
import ua.cjhrxS.Entity.TeacherEntity;
import ua.cjhrxS.Entity.UsersEntity;
import ua.cjhrxS.Services.CourseService;
import ua.cjhrxS.Services.CreatorService;
import ua.cjhrxS.Services.TeacherServices;
import ua.cjhrxS.Services.UserService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		
	CourseService course = new CourseService();
	//System.out.println(course.getAll());
	     UserService user = new UserService();
	    System.out.println(user.getUserDTO(new SignInDTO("admin", "admin")));
//		teach.create();
//		course.create();
        
	

		
		
		
		
		

	}
}
