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
        .module-regist {
            display: flex;flex: 1;justify-content: center;align-items: center;
            height: 100%;
        }
        .module-regist .regist-content-wrap {
            width: auto;height: auto;
            background-color: rgba(255,255,255,0.6);
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
        .module-regist .regist-form-row{ padding:5px 0; }
        .module-regist .form-ipt{
            display: block;
            border: none;
            width: 100%;
            line-height: 2em;
            font-size: 16px;
            padding: 2px 4px;
            border-radius: 4px;
        }
        .module-regist .txt-tip{
            color: red;
            height: 1.2em;
        }
        .module-regist .btn-regist{
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
    <script>
        function check(regForm) {
            let email = regForm.email.value.trim();
            let name = regForm.name.value.trim();
            let pwd = regForm.password.value.trim();
            let flag=true;
            let tip = '';
            if(!pwd || pwd.length<6){
                flag=false;
                tip='enter your password'
            }
            if(!name){
                flag=false;
                tip='enter your name'
            }
            if(!/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test(email)){
                flag=false;
                tip='enter email address'
            }
            if(tip){
                document.getElementById('tips').innerText=tip;
            }
            return flag;
        }
    </script>
</head>
<body>
<div class="module-regist">
    <div class="regist-content-wrap">
        <div class="regist-form-title">STUDENT REGIST</div>
        <form name="regForm" target="_self" onsubmit="return check(this)" method="post" action="/regist/user">
            <div class="regist-form-row">
                <div>Account</div>
                <div>
                    <input name="email" class="form-ipt" type="text" maxlength="30" placeholder="email" />
                </div>
            </div>
            <div class="regist-form-row">
                <div>Name</div>
                <div>
                    <input name="name" class="form-ipt" type="text" maxlength="20" placeholder="name" />
                </div>
            </div>
            <div class="regist-form-row">
                <div>Password</div>
                <div>
                    <input name="password" class="form-ipt" type="text" maxlength="12" placeholder="password length: 6~12" />
                </div>
            </div>
            <div class="regist-form-row">
                <div id="tips" class="txt-tip">${requestScope.msg}</div>
            </div>
            <div class="regist-form-row">
                <input type="submit" class="btn-regist" value="REGIST" />
            </div>
        </form>
    </div>
</div>
</body>
</html>