package com.hairgroup.choose.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hairgroup.choose.dao.IStudentInfoDao;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.until.TxDbUtils;

public class StudentInfoDaoImpl implements IStudentInfoDao {

	@Override
	public Student getInfo(int u_id) {
		
		try {
			String sql = "select * from student where u_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.query(sql, new BeanHandler<>(Student.class), u_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return null;
	}

	@Override
	public int updateStuInfo(Student student) {
		
		try {
			String sql = "update student set s_name = ?, s_age = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, student.getS_name(), student.getS_age());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return 0;
	}

	@Override
	public List<Course> getCourseInfo(int s_id) {
		
		try {
			String sql = "select * from course where c_id in (select c_id from classNo where cla_id in (select cla_id from class_stu_rela where  stu_id = ?))";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.query(sql, new BeanListHandler<>(Course.class), s_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return null;
	}

}
