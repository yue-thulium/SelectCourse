package com.hairgroup.choose.service.impl;

import java.util.List;

import com.hairgroup.choose.dao.IStudentInfoDao;
import com.hairgroup.choose.dao.impl.StudentInfoDaoImpl;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.service.IStudentInfoService;

public class StudentInfoServiceImpl implements IStudentInfoService {
	
	private static IStudentInfoDao studentInfoDao = new StudentInfoDaoImpl();

	@Override
	public Student getInfo(int u_id) {
		return studentInfoDao.getInfo(u_id);
	}

	@Override
	public int updateStuInfo(Student student) {
		return studentInfoDao.updateStuInfo(student);
	}

	@Override
	public List<Course> getCourseInfo(int s_id) {
		return studentInfoDao.getCourseInfo(s_id);
	}
	
}
