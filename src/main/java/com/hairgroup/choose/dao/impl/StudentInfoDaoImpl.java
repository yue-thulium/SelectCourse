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
			String sql = "update student set s_name = ?, s_age = ?, s_gender = ? where s_id = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			return runner.update(sql, student.getS_name(), student.getS_age(), student.getS_gender(), student.getS_id());
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
			String sql = "SELECT t1.c_id,t1.c_name,t1.c_info,t1.c_name,t1.cla_num,t2.t_name\n" +
					"FROM (SELECT cl.c_id,cl.c_name,cl.c_info,cl.c_room,cn.cla_num,cn.cla_id\n" +
					"FROM course AS cl LEFT JOIN classNo AS cn ON cl.c_id = cn.c_id\n" +
					"WHERE cn.cla_id IN (SELECT cla_id FROM class_stu_rela WHERE stu_id = ?)) AS t1 LEFT JOIN \n" +
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

}
