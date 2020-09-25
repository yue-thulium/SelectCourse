package com.hairgroup.choose.dao.impl;

import com.hairgroup.choose.dao.IGetAllDao;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.until.TxDbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public class GetAllDaoImpl implements IGetAllDao {
    @Override
    public List<Course> getAllCourse() {
        try {

            String sql = "SELECT c.c_id,c.c_name,c.c_info,c.c_room,c.c_num,t.t_name\n" +
                    "FROM teacher AS t RIGHT JOIN (SELECT c.c_id,c.c_name,c.c_info,c.c_room,c.c_num,tc.t_id\n" +
                    "FROM course as c LEFT JOIN tea_cou_rela AS tc ON c.c_id = tc.c_id) AS c ON t.t_id = c.t_id";

            QueryRunner runner = new QueryRunner(TxDbUtils.getSource());

            List<Course> query = runner.query(sql, new BeanListHandler<>(Course.class));

            return query;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            TxDbUtils.release();
        }

        return null;
    }

    @Override
    public String getTeacher(int c_id) {

        return null;
    }
}
