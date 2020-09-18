package com.hairgroup.choose.service;

import java.util.List;

import com.hairgroup.choose.entity.Course;

public interface ISelectCourseService {
	List<Course> getCanSelectCourse(int s_id);
	int setCourse(int cla_id, int s_id);
	int removeSelect(int cla_id, int s_id);
}
