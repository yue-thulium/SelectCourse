package com.hairgroup.choose.service.impl;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.hairgroup.choose.config.ExecutorConfig;
import com.hairgroup.choose.dao.IUserDao;
import com.hairgroup.choose.dao.impl.UserDaoImpl;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.entity.User;
import com.hairgroup.choose.service.IUserService;

public class UserServiceImpl implements IUserService {
	
	ExecutorService loginPool = ExecutorConfig.createThreadPool();
	
	private static IUserDao userDao = new UserDaoImpl();

	@Override
	public int register(User user, Teacher teacher) {
		return userDao.register(user, teacher);
	}

	@Override
	public int register(User user, Student student) {
		return userDao.register(user, student);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Integer> login(String username, String password) {
		
		Future future = loginPool.submit(new Callable() {
			@Override
			public Object call() throws Exception {
				return userDao.login(username, password);
			}
		});
		
		try {
			if (future != null) {
				return (Map<String, Integer>) future.get();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
