package com.hairgroup.choose.dao;

import java.util.Map;

import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.entity.User;

/**
 * 登录注册相关用户接口
 * @author Administrator
 *
 */
public interface IUserDao {
	/**
	 * 所有注册功能密码均未采用加密方式进行！
	 * 
	 * @param user
	 * @param teacher
	 * @return
	 */
	//注册——教师
	int register(User user,Teacher teacher);
	//注册——学生
	int register(User user,Student student);
	//登陆接口
	/**
	 * Map返回主要包括
	 * 	身份信息
	 * 	用户ID
	 * 	教师ID/学生ID
	 * 
	 * 	此处待用JWT进行token签发优化
	 * @param username
	 * @param password
	 * @return
	 */
	Map<String, Integer> login(String username, String password);

	String getRealName(int id,String source);
}
