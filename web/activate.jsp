<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <meta name="renderer" content="webkit"/>
    <title>Fun With Math</title>
    <style>
        * {
            padding:0;
            margin: 0;
            box-sizing: border-box;
        }
        a {
            text-decoration: none;
        }
        html {
            height: 100%;
        }
        body {
            height: 100%;
            background-color: cornflowerblue;
        }
        .module-regist {
            display: flex;flex: 1;justify-content: center;align-items: center;
            height: 100%;
        }
        .module-regist .regist-content-wrap {
            width: auto;height: auto;
            background-color: rgba(255,255,255,0.8);
            border-radius: 5px; box-shadow: 0 0 5px;padding: 10px 20px;
            width: 300px;
        }
        .module-regist .regist-form-title{
            color: #0094ff;
            text-align: center;
            line-height: 1.6em;
            border-bottom: 4px solid #0094ff;
            font-size: 26px;
            margin-bottom: 10px;
        }
        .module-regist .sucess-wrap{ padding:5px 0 25px 0; }
    </style>
</head>
<body>
<div class="module-regist">
    <div class="regist-content-wrap">
        <div class="regist-form-title">STUDENT REGIST</div>
        <div class="sucess-wrap">
            <div style="text-align: center;font-size: 30px;color: green;margin-bottom: 10px;">Success!</div>
            <div>your account <i style="color:dodgerblue"><%=session.getAttribute("email")%></i> is actived now!</div>
            <div><a href="/login.html">Click to jump to the login page</a></div>
        </div>
    </div>
</div>
</body>
</html>