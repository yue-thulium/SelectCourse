package com.hairgroup.choose.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hairgroup.choose.dao.ICourseDao;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.until.TxDbUtils;

public class CourseDaoImpl implements ICourseDao {

    @Override
    public List<Course> getSelectCourses() {

        try {

            String sql = "select * from course where c_id in (select c_id from tea_cou_rela)";

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
    public List<Course> getNoneSelectCourses() {
        try {

            String sql = "select * from course where c_id not in (select c_id from tea_cou_rela)";

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
    public synchronized boolean setCourseTeacher(int c_id, int t_id) {

        try {
            String sql = "insert into tea_cou_rela values(default, ?, ?)";

            QueryRunner runner = new QueryRunner(TxDbUtils.getSource());

            int update = runner.update(sql, t_id, c_id);

            return update > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            TxDbUtils.release();
        }
    }

    @Override
    public boolean removeCourseTeacher(int c_id, int t_id) {

        try {
            String sql = "delete from tea_cou_rela where c_id = ? and t_id = ?";

            QueryRunner runner = new QueryRunner(TxDbUtils.getSource());

            int update = runner.update(sql, c_id, t_id);

            return update > 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            TxDbUtils.release();
        }

        return false;
    }

}
