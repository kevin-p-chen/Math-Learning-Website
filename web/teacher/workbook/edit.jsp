<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fun With Math</title>
    <style>
        * {margin: 0;padding: 0;}
        body{
            background-color: #efefef;
        }
        a {
            text-decoration: none;
        }
        .layout-header {
            background-color: white;
        }
        .header-wrap {
            padding:0 30px ;
            display: flex;
            flex-flow: row;
            justify-content: space-between;
            align-items: stretch;
            font-size: 20px;
            line-height: 60px;
        }
        .header-wrap .log-wrap{}
        .header-wrap .log-wrap .log-txt{color: #06abab;}
        .header-wrap .nav-wrap{font-size: 24px;flex: 1;padding: 0 30px;}
        .header-wrap .nav-wrap .nav-item{display: inline-block;padding:0 30px;color: #333;}
        .header-wrap .nav-wrap .nav-item.active{background-color: cornflowerblue;color: white;}
        .header-wrap .login-wrap{}
        .header-wrap .login-wrap .login-name{padding-right: 10px;}
        .header-wrap .login-wrap .lnk-exit{}
        .layout-body {
            width: 80%;
            min-width: 1280px;
            margin: 0 auto;
        }

        .w-box {
            background-color: white;
            border-radius: 4px;
            margin: 10px auto;
        }
        .w-box .w-box-header{padding:5px 10px;line-height: 30px;font-size: 18px;font-weight: bold;border-bottom: 1px solid #ccc;}
        .w-box .w-box-body{padding:10px;}

        .btn-create {
            display: block;
            width: 100%;
            border: none;
            background-color: #0094ff;
            color: white;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 20px;
        }
        .f-tb {}
        .f-tb td {padding: 5px;}
        .ipt-txt {
            padding: 2px 3px;
            font-size: 18px;
            width: 300px;
        }
        .tip-txt {
            color: red;
        }
    </style>
    <script>
        function check(form) {
            var name=form.name.value.trim();
            if(!name){
                document.getElementById('tips').innerText='entry name!';
                return false;
            }else{
                return true;
            }
        }
    </script>
</head>
<body>
<div class="layout-header">
    <div class="header-wrap">
        <div class="log-wrap"><span class="log-txt">Fun With Math</span></div>
        <div class="nav-wrap">
            <a href="/users/list" class="nav-item">STUDENT</a>
            <a href="/workbook/list" class="nav-item active">WORKBOOK</a>
        </div>
        <div class="login-wrap">
            <span class="login-name"><%=session.getAttribute("username")%></span>
            <a title="logout" class="lnk-exit" href="/users/logout">Exist</a>
        </div>
    </div>
</div>
<div class="layout-body">
    <div class="w-box">
        <div class="w-box-header"><span>EDIT WORKBOOK</span></div>
        <div class="w-box-body">
            <form action="/workbook/edit" name="add_form" method="post" onsubmit="return check(this)">
                <input type="hidden" name="id" value="${requestScope.workBook.id}">
                <input type="hidden" name="questionsId" value="${requestScope.workBook.questionsId}">
                <table class="f-tb">
                    <tr>
                        <td>name:</td>
                        <td><input type="text" class="ipt-txt" name="name" value="${requestScope.workBook.name}" maxlength="30"/></td>
                    </tr>
                    <tr>
                        <td>status:</td>
                        <td>
                            <label><input type="radio" name="status" value="0" <c:if test="${requestScope.workBook.status == 0}">checked="checked"</c:if>/> Enable</label>
                            <label><input type="radio" name="status" value="1" <c:if test="${requestScope.workBook.status == 1}">checked="checked"</c:if>/> Disable</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id='tips' class='tip-txt'></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input class="btn-create" type="submit" value="SUBMIT"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>