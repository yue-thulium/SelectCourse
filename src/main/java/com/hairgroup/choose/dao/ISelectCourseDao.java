package com.hairgroup.choose.dao;

import java.util.List;

import com.hairgroup.choose.entity.Course;

/**
 * 学生选课操作相关接口
 * @author Administrator
 *
 */
public interface ISelectCourseDao {
	//获取当前可以选课的课程列表
	List<Course> getCanSelectCourse(int s_id);
	//设置选课
	int setCourse(int cla_id, int s_id);
	//取消选课方法
	int removeSelect(int cla_id, int s_id);
}
