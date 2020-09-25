package com.hairgroup.choose.service;

import java.util.Map;

import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.entity.User;

public interface IUserService {
	int register(User user,Teacher teacher);
	int register(User user,Student student);
	Map<String, Integer> login(String username, String password);
	String getRealName(int id,String source);
}
