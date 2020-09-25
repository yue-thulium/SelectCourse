package com.hairgroup.choose.dao;

import com.hairgroup.choose.entity.Course;

import java.util.List;

/**
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public interface IGetAllDao {
    List<Course> getAllCourse();
    String getTeacher(int c_id);
}
