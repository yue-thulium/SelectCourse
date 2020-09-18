package com.hairgroup.choose.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hairgroup.choose.dao.ITeacherInfoDao;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.until.TxDbUtils;

public class TeacherInfoDaoImpl implements ITeacherInfoDao {

	@Override
	public Teacher getInfo(int u_id) {
		try {
			String sql = "select * from teacher where u_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.query(sql, new BeanHandler<>(Teacher.class), u_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return null;
	}

	@Override
	public int updateTeaInfo(Teacher teacher) {
		try {
			String sql = "update student set s_name = ?, s_age = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, teacher.getT_name(), teacher.getT_age());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return 0;
	}

	@Override
	public List<Course> getCourseInfo(int t_id) {
		try {
			String sql = "select * from course where c_id in (select distinct c_id from tea_cou_rela where t_id = ?)";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.query(sql, new BeanListHandler<>(Course.class), t_id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return null;
	}

}
