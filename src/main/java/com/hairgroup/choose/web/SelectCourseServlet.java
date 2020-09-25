package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.Course;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.service.ISelectCourseService;
import com.hairgroup.choose.service.impl.SelectCourseServiceImpl;

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
 * 针对学生选课的servlet
 *
 * @author Yue Wu
 */
public class SelectCourseServlet extends HttpServlet {

    protected ISelectCourseService selectCourseService = new SelectCourseServiceImpl();

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
     * 获取可以进行选择的课程
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getCanSelectCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer s_id = (Integer) req.getAttribute("s_id");

        List<Course> canSelectCourse = selectCourseService.getCanSelectCourse(s_id);

        PrintWriter writer = resp.getWriter();

        if (canSelectCourse != null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(canSelectCourse)));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 学生添加选课方法
     * @param req
     * @param resp
     * @throws IOException
     */
    private void setCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cla_id = req.getParameter("cla_id");
        Integer s_id = (Integer) req.getAttribute("s_id");

        PrintWriter writer = resp.getWriter();

        if (selectCourseService.setCourse(Integer.parseInt(cla_id), s_id) > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("选课成功！")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }

    /**
     * 退课相关功能
     * @param req
     * @param resp
     * @throws IOException
     */
    private void removeSelect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cla_id = req.getParameter("cla_id");
        Integer s_id = (Integer) req.getAttribute("s_id");

        PrintWriter writer = resp.getWriter();

        if (selectCourseService.removeSelect(Integer.parseInt(cla_id), s_id) > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("退课成功！")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("未知错误")));
        }
    }
}
