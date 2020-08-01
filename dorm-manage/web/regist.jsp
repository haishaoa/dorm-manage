<%--
  Created by IntelliJ IDEA.
  User: 爱你的嗨少
  Date: 2020/5/21
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宿舍管理系统登录</title>
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>

    <script type="text/javascript">
        var xmlHttp;

        function loadCheck() {
            var userName = document.getElementById("userName").value;
            var password = document.getElementById("password").value;
            var email = document.getElementById("email").value;
            if (userName == null || userName == ""){
                document.getElementById("error").innerHTML = "用户名不能为空";
            }else if (password == null || password == ""){
                document.getElementById("error").innerHTML = "密码不能为空";
            } else if (email == null || email == ""){
                document.getElementById("error").innerHTML = "邮箱不能为空";
            } else {
                xmlHttp = createXmlHttpRequest();
                xmlHttp.onreadystatechange = getResult;
                xmlHttp.open("POST","UserServlet?action=regist",true);
                xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                var data = "username=" + userName + "&password=" + password + "&email=" + email;
                xmlHttp.send(data);
            }
        }

        function createXmlHttpRequest() {
            if (window.XMLHttpRequest){
                return new XMLHttpRequest();
            } else {
                return new ActiveXObject("Microsoft.XMLHTTP");
            }
        }

        function getResult() {
            if (xmlHttp.readyState === 4){
                if (xmlHttp.status === 200){
                    var result = xmlHttp.responseText;
                    if (result == "注册成功"){
                        window.location='login.jsp';
                    } else {
                        document.getElementById("error").innerHTML = result;
                    }
                }
            }
        }
    </script>

    <style type="text/css">
        body {
            padding-top: 200px;
            padding-bottom: 40px;
            background-image: url('images/zc.jpg');
            /*background-position:;*/
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        .radio {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .form-signin-heading {
            text-align: center;
        }

        .form-signin {
            max-width: 300px;
            padding: 19px 29px 0px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }

        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>

</head>
<body>
<div class="container" style="margin: 0px 0px 0px 200px">
    <form name="myForm" class="form-signin" action="" method="post">
        <h2 class="form-signin-heading"><font color="gray">宿舍管理系统</font></h2>
        <input id="userName" name="userName" value="${user.userName }" type="text" class="input-block-level"
               placeholder="用户名...">
        <input id="password" name="password" value="${user.password }" type="password" class="input-block-level"
               placeholder="密码...">
        <input id="email" name="email" value="${user.email }" type="text" class="input-block-level"
               placeholder="邮箱...">
        <a href="login.jsp">去登录</a><font id="error" color="red"
                                 style="margin: 0px 0px 0px 80px;font-size: 18px">${error }</font>

        <div style="margin: 0px 0px 0px 50px">
            <button class="btn btn-large btn-primary" type="button" onclick="loadCheck()">注册</button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button class="btn btn-large btn-primary" type="button">重置</button>
        </div>
    </form>
</div>
</body>
</html>
