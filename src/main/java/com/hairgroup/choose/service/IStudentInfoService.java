package com.hairgroup.choose.service;

import java.util.List;

import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Student;

public interface IStudentInfoService {
	Student getInfo(int u_id);
	int updateStuInfo(Student student);
	List<Course> getCourseInfo(int s_id);
}
