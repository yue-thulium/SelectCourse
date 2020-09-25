package com.hairgroup.choose.service.impl;

import com.hairgroup.choose.dao.IGetAllDao;
import com.hairgroup.choose.dao.impl.GetAllDaoImpl;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.service.IGetAllService;

import java.util.List;

/**
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public class GetAllServiceImpl implements IGetAllService {

    IGetAllDao getAllDao = new GetAllDaoImpl();

    @Override
    public List<Course> getAllCourse() {
        return getAllDao.getAllCourse();
    }

    @Override
    public String getTeacher(int c_id) {
        return getAllDao.getTeacher(c_id);
    }
}
