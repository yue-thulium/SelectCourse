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
			String sql = "update teacher set t_name = ?, t_age = ? where t_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, teacher.getT_name(), teacher.getT_age(), teacher.getT_id());
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
			String sql = "SELECT c.c_id,c.c_name,c.c_info,c.c_room,c.c_num,t.t_name\n" +
					"FROM teacher AS t \n" +
					"RIGHT JOIN \n" +
					"(SELECT c.c_id,c.c_name,c.c_info,c.c_room,c.c_num,tc.t_id\n" +
					"FROM course as c LEFT JOIN tea_cou_rela AS tc ON c.c_id = tc.c_id) AS c \n" +
					"ON t.t_id = c.t_id\n" +
					"WHERE c.c_id IN (SELECT c_id FROM tea_cou_rela AS tc WHERE tc.t_id=?)";
			
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
