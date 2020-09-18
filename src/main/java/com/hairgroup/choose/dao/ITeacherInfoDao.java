package com.hairgroup.choose.dao;

import java.util.List;

import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Teacher;

/**
 * 教师相关操作接口
 * @author Administrator
 *
 */
public interface ITeacherInfoDao {
	//教师信息获取接口
	Teacher getInfo(int u_id);
	//更新教师信息接口
	int updateTeaInfo(Teacher teacher);
	//获取教师教授课程接口
	List<Course> getCourseInfo(int t_id);
}
