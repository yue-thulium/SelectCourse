package com.hairgroup.choose.web;

import com.hairgroup.choose.service.IUserService;
import com.hairgroup.choose.service.impl.UserServiceImpl;
import com.hairgroup.choose.until.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class LoginRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 242201438076253396L;

    /**
     * 请求分发方法，用于对一类功能键进行分发
     * <p>
     * 使用反射机制实现
     *
     * @param request  请求
     * @param response 相应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取请求方法名
            String methodName = request.getParameter("method");
            //判断方法名是否为空
            if (methodName == null || methodName.trim().length() == 0) {
                throw new Exception("匹配的mentod参数不存在");
            }
            //获取方法对象
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法
            method.setAccessible(true);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆方法 —— 登陆进行Session中的JES。。的信息的保存，实现单点登录
     *
     *      ！！！
     *          基于token认证机制的工具类已实现，本项目因保证联系到session等相关的操作
     *          放弃了基于token的认证模式
     *      ！！！
     *
     *  该系统登录模式为单点登录
     *
     * @param request  请求
     * @param response 响应
     */
    private void login(HttpServletRequest request, HttpServletResponse response) {

        //获取用户名及密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        IUserService userService = new UserServiceImpl();
        //登陆功能，并进行session的保存(拓展：token签发，以注释形式给出)
        Map<String, Integer> infoMap = userService.login(username, password);

        //进行登陆逻辑判断
        if (infoMap != null) {
            String token = JWTUtil.createToken(infoMap.get("u_id"), infoMap.get("role"), infoMap.get("identity_id"));
            System.out.println(token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        } else {
            try {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");

                PrintWriter writer = response.getWriter();

                writer.write("请输入正确的用户名或密码");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void teacherRegister(HttpServletRequest request, HttpServletResponse response) {

    }

    private void studentRegister(HttpServletRequest request, HttpServletResponse response) {

    }
}
