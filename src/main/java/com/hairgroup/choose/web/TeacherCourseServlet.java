package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.service.ICourseService;
import com.hairgroup.choose.service.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created on 2020/9/22
 *
 * @author Yue Wu
 */
public class TeacherCourseServlet extends HttpServlet {

    protected ICourseService courseService = new CourseServiceImpl();

    /**
     * 匹配对应的请求方法
     * 通过反射机制实现
     *
     * @param req  请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        try {
            //获取请求方法名
            String methodName = req.getParameter("method");
            //判断方法名是否为空
            if (methodName == null || methodName.trim().length() == 0) {
                throw new Exception("匹配的mentod参数不存在");
            }
            //获取方法对象
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 获取已经被选择了的课程信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getSelectCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        List<Course> selectCourses = courseService.getSelectCourses();

        if (selectCourses != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(selectCourses)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 获取未被选择的课程信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getNoneSelectCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();

        List<Course> selectCourses = courseService.getNoneSelectCourses();

        if (selectCourses != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(selectCourses)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 设置教师选课
     * @param req
     * @param resp
     * @throws IOException
     */
    private void setCourseTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String c_id = req.getParameter("c_id");
        String t_id = req.getParameter("t_id");

        PrintWriter writer = resp.getWriter();

        if (courseService.setCourseTeacher(Integer.parseInt(c_id), Integer.parseInt(t_id))) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("选课成功！")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 教师解除授课
     * @param req
     * @param resp
     * @throws IOException
     */
    private void removeCourseTeacher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String c_id = req.getParameter("c_id");
        String t_id = req.getParameter("t_id");

        PrintWriter writer = resp.getWriter();

        if (courseService.removeCourseTeacher(Integer.parseInt(c_id), Integer.parseInt(t_id))) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("退课成功！")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }
}
