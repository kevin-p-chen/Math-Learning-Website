<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

        .tb-box {width: 100%;border: none;}
        .tb-box th{padding: 8px;background-color: cadetblue;border: none;}
        .tb-box td{padding: 5px;border-bottom: 0.5px dashed #eee;}
        .tb-box tbody tr:hover{background-color:cornflowerblue;}
    </style>
</head>
<body>
    <div class="layout-header">
        <div class="header-wrap">
            <div class="log-wrap"><span class="log-txt">Fun With Math</span></div>
            <div class="nav-wrap">
                <a href="/users/list" class="nav-item active">STUDENT</a>
                <a href="/workbook/list" class="nav-item">WORKBOOK</a>
            </div>
            <div class="login-wrap">
                <span class="login-name"><%=session.getAttribute("username")%></span>
                <a title="logout" class="lnk-exit" href="/users/logout">Exist</a>
            </div>
        </div>
    </div>
    <div class="layout-body">
        <div class="w-box">
            <div class="w-box-header"><span>my students</span></div>
            <div class="w-box-body">
                <table class="tb-box">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>NAME</th>
                            <th>EMAIL</th>
                            <th>STATUS</th>
                            <th>REGIST</th>
                            <th>SUBMIT</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${usersList}" var="list"  varStatus="status">
                        <tr>
                            <td>${list.id}</td>
                            <td>${list.name}</td>
                            <td>${list.email}</td>
                            <td>
                                <c:if test="${list.status==0}">unactivated</c:if>
                                <c:if test="${list.status==1}">activated</c:if>
                            </td>
                            <td>${list.createTime}</td>
                            <td>${list.recordNum}</td>
                            <td><a href="/ansuer/record/list?userId=${list.id}&studentName=${list.name}">history</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>