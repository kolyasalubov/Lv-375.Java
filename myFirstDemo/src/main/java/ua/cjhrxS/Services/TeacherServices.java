package ua.cjhrxS.Services;

import ua.cjhrxS.DAO.TeacherDao;
import ua.cjhrxS.DTO.TeacherDTO;
import ua.cjhrxS.Entity.TeacherEntity;

public class TeacherServices {
	
	private TeacherDao teacherDao;
	
	public TeacherServices() {
		
		this.teacherDao = new TeacherDao();
		
	}

	public TeacherServices(TeacherDao teacherDao) {
		this.teacherDao = teacherDao;
	}
	

	public TeacherDTO getTeacherDto(long id) {
		TeacherEntity teacher = teacherDao.getById(id);
		return new TeacherDTO(teacher.getId(), 
							  teacher.getFirst_name(), 
							  teacher.getLast_name(), 
							  teacher.getExpierence());
	}

	public boolean setTeacherDto(TeacherDTO teacherDto) {
		boolean result = false;
		TeacherEntity teacher = new TeacherEntity(teacherDto.getId(),
												  teacherDto.getFirst_name(),
												  teacherDto.getLast_name(),
												  teacherDto.getExpierence()
												  );
		if (teacherDto.getId() > 0) {
			if (isExistTeacher(teacher.getId())) {
				teacherDao.updateByEntity(teacher);
				result = true;
			}
		} else {
			teacherDao.insert(teacher);
			result = true;
		}
		return result;
	}

	public boolean isExistTeacher(long id) {
		boolean result = true;
		try {
			teacherDao.getById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			System.out.println("Teacher not found, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean deleteItem(long id) {
		boolean result = true;
		try {
			result = result && teacherDao.deleteById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			result = false;
		}
		return result;
	}	
	
	

}
