<%--
  Created by IntelliJ IDEA.
  User: Yue Wu
  Date: 2020/9/17
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>

<style type="text/css">

    body{
        txt-align: center;
    }

</style>

<body>

<div>
    <h1>--- 注册页面 ---</h1>
</div>

<div>
    <form method="post" action="${pageContext.request.contextPath }/loginRegisterServlet?method=login">
        <p>
            账号：<input type="text" name="sname" id="sname"/>
        </p>
        <p>
            密码：<input type="password" name="spassword" id="spassword" />
        </p>
        <p>
            密码：<input type="password" id="spassword2" />
        </p>
        <p>
            年龄：<input type="text" name="age" id="age"/>
        </p>
        <p>
            验证：<input type="text" name="validateCode"/>
        </p>
        <p>
            <input type="submit" value="立即前往"/>
        </p>
    </form>
</div>

</body>
</html>
