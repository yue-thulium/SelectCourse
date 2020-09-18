<%--
  Created by IntelliJ IDEA.
  User: Yue Wu
  Date: 2020/9/17
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="./css/home.css" />
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- import Vue before Element -->
    <script src="https://unpkg.com/vue/dist/vue.js"></script>
    <!-- import JavaScript -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <title>todolist</title>

</head>
<div id="app">

    <el-container>

        <el-header class="header"></el-header>

        <el-main style="height: 600px;display:flex;">

            <div class="loginBlock">

                <div class="intrduceBlock">
                    <h4 class="introduceTittle">登录系统</h4>

                    <p class="content">
                        bash是 Unix shell的一种,在1987年由 Brian Fox为了GNU计划而编写,并于1989年发布第一个正式版本。原本计划用在GNU操作系统上,但它也能运行于大多数类Unix系统的操作系统之上。Linux与
                        Mac OSX v10.4都将bash作为默认 shell。
                    </p>
                </div>

                <div class="formBlok">
                    <div class="realFrom">
                        <p class="loginText">用户登录</p>
                        <el-form ref="loginForm" :model="loginForm">
                            <el-form-item prop="username">
                                <el-input type="text" v-model="loginForm.username" prefix-icon="el-icon-tickets" auto-complete="off"
                                          placeholder="账号"></el-input>
                            </el-form-item>
                            <el-form-item prop="password">
                                <el-input type="password" v-model="loginForm.password" prefix-icon="el-icon-key" auto-complete="off"
                                          placeholder="密码"></el-input>
                            </el-form-item>
                            <a href="${pageContext.request.contextPath }/register.jsp" class="register">立即注册</a>
                            <a href="" class="forget">忘记密码</a>
                            <el-form-item>
                                <div class="button-div">
                                    <el-button round style="width: 80%" v-loading="loading" @click.native.prevent="submitClick">登录</el-button>
                                </div>
                            </el-form-item>
                        </el-form>
                    </div>
                </div>

            </div>

        </el-main>

    </el-container>

</div>
<body class="body">
<script>
    new Vue({ //记得声明，不然看不到element的input组件
        el: '#app',
        data: {
            loginForm: {
                username: 'admin',
                password: 'admin1222'
            },
            loading: false,
            input : ''
        },
        methods: { //这里用于定义方法
            submitClick() {
                self.location="test.html";
            }
        },
        computed: { //计算属性
        }
    })
</script>
</body>
</html>
