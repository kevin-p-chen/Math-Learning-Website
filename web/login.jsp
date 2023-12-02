<!DOCTYPE html>
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
        .module-login {
            display: flex;flex: 1;justify-content: center;align-items: center;
            height: 100%;
        }
        .module-login .login-content-wrap {
            width: auto;height: auto;
            background-color: rgba(255,255,255,0.6);
            border-radius: 5px; box-shadow: 0 0 5px;padding: 10px 20px;
            width: 300px;
        }
        .module-login .login-form-title{
            color: #0094ff;
            text-align: center;
            line-height: 1.6em;
            border-bottom: 4px solid #0094ff;
            font-size: 26px;
            margin-bottom: 10px;
        }
        .module-login .login-form-row{ padding: 10px 0; }
        .module-login .form-ipt{
            display: block;
            border: none;
            width: 100%;
            line-height: 2em;
            font-size: 16px;
            padding: 2px 4px;
            border-radius: 4px;
        }
        .module-login .btn-login{
            display: block;
            border: none;
            background-color: #0094ff;
            color: white;
            width: 100%;
            line-height: 2.2em;
            font-size: 20px;
            border-radius: 4px;
        }

    </style>
</head>
<body>
<div class="module-login">
    <div class="login-content-wrap">
        <div class="login-form-title">STUDENT LOGIN</div>
        <form action="/login" method="post" id="login_form">
            <div class="login-form-row">
                <input class="form-ipt" type="text" id="email" name="email" placeholder="email" />
            </div>
            <div class="login-form-row">
                <input class="form-ipt" type="password" id="password" name="password" placeholder="password" />
            </div>
            <div class="login-form-row">
                <div id="tips" class="txt-tip">${requestScope.msg}</div>
            </div>
            <div class="login-form-row">
                <input type="submit" class="btn-login" value="LOGIN" />
            </div>
        </form>
        <div class="login-form-foot">
            <a href="/regist.jsp" class="lnk-regist">regist</a>
        </div>
    </div>
</div>
</body>
</html>