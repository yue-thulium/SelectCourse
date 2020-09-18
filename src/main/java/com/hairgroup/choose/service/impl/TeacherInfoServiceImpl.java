package com.hairgroup.choose.service.impl;

import java.util.List;

import com.hairgroup.choose.dao.ITeacherInfoDao;
import com.hairgroup.choose.dao.impl.TeacherInfoDaoImpl;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.service.ITeacherInfoService;

public class TeacherInfoServiceImpl implements ITeacherInfoService {
	
	private static ITeacherInfoDao teacherInfoDao = new TeacherInfoDaoImpl();

	@Override
	public Teacher getInfo(int u_id) {
		return teacherInfoDao.getInfo(u_id);
	}

	@Override
	public int updateTeaInfo(Teacher teacher) {
		return teacherInfoDao.updateTeaInfo(teacher);
	}

	@Override
	public List<Course> getCourseInfo(int t_id) {
		return teacherInfoDao.getCourseInfo(t_id);
	}

}
