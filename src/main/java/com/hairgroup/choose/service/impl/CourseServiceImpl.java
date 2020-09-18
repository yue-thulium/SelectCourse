package com.hairgroup.choose.service.impl;

import java.util.List;

import com.hairgroup.choose.dao.ICourseDao;
import com.hairgroup.choose.dao.impl.CourseDaoImpl;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.service.ICourseService;

public class CourseServiceImpl implements ICourseService {
	
	private static ICourseDao courseDao = new CourseDaoImpl();

	@Override
	public List<Course> getSelectCourses() {
		return courseDao.getSelectCourses();
	}

	@Override
	public List<Course> getNoneSelectCourses() {
		return courseDao.getNoneSelectCourses();
	}

	@Override
	public boolean setCourseTeacher(int c_id, int t_id) {
		return courseDao.setCourseTeacher(c_id, t_id);
	}

	@Override
	public boolean removeCourseTeacher(int c_id, int t_id) {
		return courseDao.removeCourseTeacher(c_id, t_id);
	}

}
