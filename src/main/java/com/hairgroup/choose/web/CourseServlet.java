package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.service.IGetAllService;
import com.hairgroup.choose.service.ITeacherInfoService;
import com.hairgroup.choose.service.impl.GetAllServiceImpl;
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
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public class CourseServlet extends HttpServlet {

    protected IGetAllService getService = new GetAllServiceImpl();
    protected ITeacherInfoService teacherInfoService = new TeacherInfoServiceImpl();

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

    private void getTeaAllCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Integer t_id = (Integer) req.getAttribute("t_id");

        PrintWriter writer = resp.getWriter();

        List<Course> allCourse = getService.getAllCourse();

        List<Course> chick = teacherInfoService.getCourseInfo(t_id);

        System.out.println(chick);

        System.out.println("------------------------");

        if (chick != null && chick.size() > 0) {
            for (Course c : allCourse) {
                c.setFlag(true);
                System.out.println(c);
            }
        }

        if (allCourse != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(allCourse)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("位置错误")));
        }

        writer.flush();
        writer.close();
    }

    private void getAllCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter writer = resp.getWriter();

        List<Course> allCourse = getService.getAllCourse();

        if (allCourse != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(allCourse)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("位置错误")));
        }

        writer.flush();
        writer.close();
    }
}
