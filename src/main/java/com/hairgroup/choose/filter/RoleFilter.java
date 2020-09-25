package com.hairgroup.choose.filter;

import com.alibaba.fastjson.JSON;
import com.hairgroup.choose.entity.ResultMod;
import com.hairgroup.choose.until.JWTUtil;
import com.hairgroup.choose.until.RedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created on 2020/9/23
 *
 * @author Yue Wu
 */
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String token = req.getHeader("token");
        PrintWriter writer = resp.getWriter();

        if (token == null) {
            writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("请先登录")));
            return;
        } else {
            Integer userID = JWTUtil.getUserID(token);
            Integer userRole = JWTUtil.getUserRole(token);
            Integer userIdentity = JWTUtil.getUserIdentity(token);

            Jedis jedis = RedisUtil.getJedis();

            if (jedis.exists(String.valueOf(userID))) {
                if (userRole == 0) {
                    req.setAttribute("u_id", userID);
                    req.setAttribute("u_role", userRole);
                    req.setAttribute("s_id", userIdentity);
                } else {
                    req.setAttribute("u_id", userID);
                    req.setAttribute("u_role", userRole);
                    req.setAttribute("t_id", userIdentity);
                }
            } else {
                writer.write(JSON.toJSONString(ResultMod.getInstance().fail().message("登录状态异常，请重新登录")));
                return;
            }


        }



        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
