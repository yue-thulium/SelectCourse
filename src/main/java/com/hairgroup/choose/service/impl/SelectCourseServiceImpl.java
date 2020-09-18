package com.hairgroup.choose.service.impl;

import java.util.List;

import com.hairgroup.choose.dao.ISelectCourseDao;
import com.hairgroup.choose.dao.impl.SelectCourseDaoImpl;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.service.ISelectCourseService;

public class SelectCourseServiceImpl implements ISelectCourseService {
	
	ISelectCourseDao selectCourseDao = new SelectCourseDaoImpl();

	@Override
	public List<Course> getCanSelectCourse(int s_id) {
		return selectCourseDao.getCanSelectCourse(s_id);
	}

	@Override
	public int setCourse(int cla_id, int s_id) {
		return selectCourseDao.setCourse(cla_id, s_id);
	}

	@Override
	public int removeSelect(int cla_id, int s_id) {
		return selectCourseDao.removeSelect(cla_id, s_id);
	}

}
