package com.hairgroup.choose.web;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.entity.Student;
import com.hairgroup.choose.entity.Teacher;
import com.hairgroup.choose.entity.User;
import com.hairgroup.choose.service.IUserService;
import com.hairgroup.choose.service.impl.UserServiceImpl;
import com.hairgroup.choose.until.JWTUtil;
import com.hairgroup.choose.until.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

public class LoginRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 242201438076253396L;

    protected IUserService service = new UserServiceImpl();

    /**
     *
     * 匹配对应的请求方法
     * 通过反射机制实现
     *
     * @param req 请求
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
        this.doGet(req,resp);
    }

    /**
     * 登陆方法 —— 登陆进行token的信息的保存，实现单点登录
     *
     *  该系统登录模式为单点登录
     *
     * @param request  请求
     * @param response 响应
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //获取用户名及密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        IUserService userService = new UserServiceImpl();
        //登陆功能，并进行token签发
        Map<String, Integer> infoMap = userService.login(username, password);

        //进行登陆逻辑判断
        if (infoMap != null) {
            String token = JWTUtil.createToken(infoMap.get("u_id"), infoMap.get("role"), infoMap.get("identity_id"));
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            //获取Redis操作类
            Jedis jedis = RedisUtil.getJedis();
            String u_id = String.valueOf(infoMap.get("u_id"));
            //判断数据库中是否存在该键值
            if (jedis.exists(u_id)) {
                //存在即删除
                jedis.del(u_id);
            }
            //添加新的token进入redis缓存
            jedis.setex(u_id, 60*60*7, token);
            //将签发凭证返回给前端
            response.addCookie(cookie);

            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message(token)));
            writer.flush();
            writer.close();
        } else {
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("请输入正确的用户名或密码")));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注册方法——用于对教师用户进行注册
     * @param request
     * @param response
     */
    private void teacherRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                "0".equals(request.getParameter("identity"))
        );

        Teacher teacher = new Teacher(
                request.getParameter("t_name"),
                request.getParameter("t_gender"),
                Integer.parseInt(request.getParameter("t_age"))
        );

        int register = service.register(user, teacher);

        PrintWriter writer = response.getWriter();

        if (register > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("注册成功！即将返回登录界面")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("注册失败！")));
        }
        writer.flush();
        writer.close();

    }

    private void studentRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = new User(
                request.getParameter("username"),
                request.getParameter("password"),
                "0".equals(request.getParameter("identity"))
        );

        Student student = new Student(
                request.getParameter("s_name"),
                request.getParameter("s_gender"),
                Integer.parseInt(request.getParameter("s_age"))
        );

        int register = service.register(user, student);

        PrintWriter writer = response.getWriter();

        if (register > 0) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().success().message("注册成功！即将返回登录界面")));
        } else {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("注册失败！")));
        }
        writer.flush();
        writer.close();

    }
}
