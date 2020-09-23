<%--
  Created by IntelliJ IDEA.
  User: Yue Wu
  Date: 2020/9/23
  Time: 10:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script>
        $(function () {
            $("button").click(function () {
                $.ajax({
                    url: "login",
                    type: "post",
                    dataType: "json",
                    data: {
                        "method": "login",
                        "username": "171",
                        "password": "123"
                    },
                    success: function (data) {
                        alert("token" + data.message);
                    },
                    error: function () {
                        alert("请求失败！");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <button>测试</button>
</body>
</html>
