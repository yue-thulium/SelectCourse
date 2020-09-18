package com.hairgroup.choose.dao;

import java.util.List;

import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Student;

/**
 * 学生信息相关接口
 * @author Administrator
 *
 */
public interface IStudentInfoDao {
	//学生信息获取相关接口
	Student getInfo(int u_id);
	//学生信息更新相关接口
	int updateStuInfo(Student student);
	//学生所选课程获取接口
	List<Course> getCourseInfo(int s_id);
}
