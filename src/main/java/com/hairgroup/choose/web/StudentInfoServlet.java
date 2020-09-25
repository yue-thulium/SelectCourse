package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.service.IStudentInfoService;
import com.hairgroup.choose.service.impl.StudentInfoServiceImpl;

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
 * 学生相关Servlet
 *
 * @author Yue Wu
 */
public class StudentInfoServlet extends HttpServlet {

    protected IStudentInfoService studentInfoService = new StudentInfoServiceImpl();

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
        doGet(req,resp);
    }

    /**
     * 获取学生信息接口
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getStudentInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer u_id = (Integer) req.getAttribute("u_id");

        PrintWriter writer = resp.getWriter();

        Student student = studentInfoService.getInfo(u_id);

        if (student != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(student)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 更新学生信息接口
     * @param req
     * @param resp
     * @throws IOException
     */
    private void updateStuInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String teaJson = req.getParameter("student");

        PrintWriter writer = resp.getWriter();

        Student student = JSON.parseObject(teaJson, Student.class);

        if (studentInfoService.updateStuInfo(student) > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("修改成功")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 获取学生选课列表接口
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getCourseInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer t_id = (Integer) req.getAttribute("s_id");

        PrintWriter writer = resp.getWriter();

        List<Course> courseInfo = studentInfoService.getCourseInfo(t_id);

        if (courseInfo != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(courseInfo)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }
}
