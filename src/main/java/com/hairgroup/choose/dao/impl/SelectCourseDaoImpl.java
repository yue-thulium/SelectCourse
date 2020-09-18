package com.hairgroup.choose.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hairgroup.choose.dao.ISelectCourseDao;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.until.TxDbUtils;

public class SelectCourseDaoImpl implements ISelectCourseDao {

	@Override
	public List<Course> getCanSelectCourse(int s_id) {
		
		try {
			String sql = "SELECT * from course where c_id in (select c_id from classNo where cla_num < 20) and c_id in (select c_id from classNo where cla_id not in (select cla_id from class_stu_rela where stu_id =?))";
			
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

	@Override
	public int setCourse(int cla_id, int s_id) {
		
		try {
			String sql = "insert into class_stu_rela values(default, ?, ?)";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, cla_id, s_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return 0;
	}

	@Override
	public int removeSelect(int cla_id, int s_id) {
		
		try {
			String sql = "delete from class_stu_rela where cla_id = ? and stu_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, cla_id, s_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return 0;
	}

}
