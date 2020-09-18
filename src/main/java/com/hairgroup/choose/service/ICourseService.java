package com.hairgroup.choose.service;

import java.util.List;

import com.hairgroup.choose.entity.Course;

public interface ICourseService {
	List<Course> getSelectCourses();

	List<Course> getNoneSelectCourses();

	boolean setCourseTeacher(int c_id, int t_id);

	boolean removeCourseTeacher(int c_id, int t_id);
}
