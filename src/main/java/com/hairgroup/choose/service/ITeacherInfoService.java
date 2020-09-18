package com.hairgroup.choose.service;

import java.util.List;

import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Teacher;

public interface ITeacherInfoService {
	Teacher getInfo(int u_id);
	int updateTeaInfo(Teacher teacher);
	List<Course> getCourseInfo(int t_id);
}
