package com.hairgroup.choose.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hairgroup.choose.dao.IUserDao;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.entity.User;
import com.hairgroup.choose.until.TxDbUtils;

public class UserDaoImpl implements IUserDao {

	/**
	 * 娉ㄥ唽鎻掑叆涓ゅ紶鐩稿叧琛紝鏁呭紑鍚簨鍔�
	 * 鎻掑叆鐢ㄦ埛琛ㄦ椂杩斿洖涓婚敭鍊硷紝闅忓悗杩涜瀵瑰簲琛ㄧ殑淇℃伅鎻掑叆
	 */
	@Override
	public int register(User user, Teacher teacher) {

		try {
			String sqlInsertUser = "insert into user values(default, ?, ?, ?)";

			String sqlInserTeacher = "insert into teacher values(default, ?, ?, ?, ?, default)";

			TxDbUtils.startTx();

			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());

			BigInteger u_id = runner.insert(sqlInsertUser, new ScalarHandler<BigInteger>(), user.getUsername(),
					user.getPassword(), 1);

			BigInteger newTeaID = runner.insert(sqlInserTeacher, new ScalarHandler<BigInteger>(), teacher.getT_name(),
					teacher.getT_gender(), teacher.getT_age(), u_id.intValue());

			TxDbUtils.commit();

			return newTeaID.intValue();

		} catch (SQLException e) {
			TxDbUtils.rollBack();
		} finally {
			TxDbUtils.release();
		}

		return 0;
	}

	/**
	 * 娉ㄥ唽鎻掑叆涓ゅ紶鐩稿叧琛紝鏁呭紑鍚簨鍔�
	 * 鎻掑叆鐢ㄦ埛琛ㄦ椂杩斿洖涓婚敭鍊硷紝闅忓悗杩涜瀵瑰簲琛ㄧ殑淇℃伅鎻掑叆
	 */
	@Override
	public int register(User user, Student student) {

		try {
			String sqlInsertUser = "insert into user values(default, ?, ?, ?)";

			String sqlInserStudent = "insert into student values(default, ?, ?, ?, ?)";

			TxDbUtils.startTx();

			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());

			BigInteger u_id = runner.insert(sqlInsertUser, new ScalarHandler<BigInteger>(), user.getUsername(),
					user.getPassword(), 0);

			BigInteger newStuID = runner.insert(sqlInserStudent, new ScalarHandler<BigInteger>(), student.getS_name(), student.getS_gender(),
					student.getS_age(), u_id);
			
			TxDbUtils.commit();
			
			return newStuID.intValue();

		} catch (SQLException e) {
			TxDbUtils.rollBack();
		} finally {
			TxDbUtils.release();
		}

		return 0;
	}

	/**
	 * 鐧婚檰鏂规硶锛岃繑鍥炲�间富瑕佸唴瀹硅鏄庯細
	 * 	韬唤淇℃伅
	 * 	鐢ㄦ埛ID
	 * 	鏁欏笀ID/瀛︾敓ID
	 */
	@Override
	public Map<String, Integer> login(String username, String password) {
		
		Map<String, Integer> map = new HashMap<>();
		
		try {
			
			String sqlLogin = "select * from user where username = ? and password = ?";
			
			QueryRunner runner = new QueryRunner(TxDbUtils.getSource());
			
			User user = runner.query(sqlLogin, new BeanHandler<>(User.class), username, password);

			if (user == null) {
				return null;
			}
			
			String role = user.getIdentity() == true ? "teacher" : "student";
			
			map.put("role", user.getIdentity() == true ? 1 : 0);
			
			if (user.getIdentity()) {
				String sqlReturnInfo = "select * from teacher where u_id = ?";
				Teacher teacher = runner.query(sqlReturnInfo, new BeanHandler<>(Teacher.class), user.getU_id());
				map.put("u_id", user.getU_id());
				map.put("t_id", teacher.getT_id());
				return map;
			} else {
				String sqlReturnInfo = "select * from student where u_id = ?";
				Student student = runner.query(sqlReturnInfo, new BeanHandler<>(Student.class), user.getU_id());
				map.put("u_id", user.getU_id());
				map.put("s_id", student.getS_id());
				return map;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
