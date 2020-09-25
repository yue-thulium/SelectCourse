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
			String sql = "SELECT t1.c_id,t1.c_name,t1.c_info,t1.c_name,t1.cla_num,t2.t_name\n" +
					"FROM (SELECT cl.c_id,cl.c_name,cl.c_info,cl.c_room,cn.cla_num,cn.cla_id\n" +
					"FROM course AS cl LEFT JOIN classNo AS cn ON cl.c_id = cn.c_id\n" +
					"WHERE cn.cla_id NOT IN (SELECT cla_id FROM class_stu_rela WHERE stu_id = ?)) AS t1 LEFT JOIN \n" +
					"(SELECT t_name,c_id FROM teacher AS t,tea_cou_rela AS tcr WHERE t.t_id = tcr.t_id) AS t2\n" +
					"ON t1.c_id = t2.c_id;";
			
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
	public synchronized int setCourse(int cla_id, int s_id) {
		
		try {
			String sql = "insert into class_stu_rela values(default, ?, ?)";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, cla_id, s_id);
		} catch (SQLException e) {
			return 0;
		} finally {
			TxDbUtils.release();
		}
	}

	@Override
	public int removeSelect(int cla_id, int s_id) {
		
		try {
			String sql = "delete from class_stu_rela where cla_id = ? and stu_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, cla_id, s_id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			TxDbUtils.release();
		}
		
		return 0;
	}

}
