package com.hairgroup.choose.service;

import com.hairgroup.choose.entity.Course;

import java.util.List;

/**
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public interface IGetAllService {
    List<Course> getAllCourse();
    String getTeacher(int c_id);
}
