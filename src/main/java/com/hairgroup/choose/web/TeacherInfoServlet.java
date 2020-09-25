package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.service.ITeacherInfoService;
import com.hairgroup.choose.service.impl.TeacherInfoServiceImpl;

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
 * <p>
 * 教师信息相关Servlet
 *
 * @author Yue Wu
 */
public class TeacherInfoServlet extends HttpServlet {

    protected ITeacherInfoService teacherInfoService = new TeacherInfoServiceImpl();

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
     * 获取教师相关信息接口
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getTeacherInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer u_id = (Integer) req.getAttribute("u_id");

        PrintWriter writer = resp.getWriter();

        Teacher teacher = teacherInfoService.getInfo(u_id);

        if (teacher != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(teacher)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 更新教师信息接口
     * @param req
     * @param resp
     * @throws IOException
     */
    private void updateTeaInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String teaJson = req.getParameter("teacher");

        PrintWriter writer = resp.getWriter();

        Teacher teacher = JSON.parseObject(teaJson, Teacher.class);

        if (teacherInfoService.updateTeaInfo(teacher) > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("修改成功")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 获取教师教授信息接口
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getCourseInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer t_id = (Integer) req.getAttribute("t_id");

        PrintWriter writer = resp.getWriter();

        List<Course> courseInfo = teacherInfoService.getCourseInfo(t_id);

        if (courseInfo != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(courseInfo)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }
}
