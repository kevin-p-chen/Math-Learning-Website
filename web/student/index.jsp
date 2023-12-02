<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
            overflow: hidden;
        }
        .w-box .w-box-header{
            padding:5px 10px;line-height: 30px;font-size: 18px;font-weight: bold;border-bottom: 1px solid #ccc;
            border-left: 10px solid #0094ff;

        }
        .w-box .w-box-body{padding:10px;}

        .tb-box {width: 100%;border: none;}
        .tb-box th{padding: 8px;background-color: cadetblue;border: none;}
        .tb-box td{padding: 5px;border-bottom: 0.5px dashed #eee;}
        .tb-box tbody tr:hover{background-color:cornflowerblue;}

        .work-list-wrap {
            display: flex;
            flex-flow: row wrap;
            justify-content: flex-start;
            align-items: flex-start;
        }
        .work-list-wrap .work-item:hover{
            border-color: #0094ff;
            color: white;
        }
        .work-list-wrap .work-item{
            display: block;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 4px #ccc;
            border:2px solid white;
            margin: 10px;
            min-width: 300px;
            background-color: white;
        }
        .work-list-wrap .work-item .name{
            font-size: 20px;color: #333;padding-bottom: 5px;border-bottom: 1px dashed #eee;
        }
        .work-list-wrap .work-item .num{font-size: 14px;color: #ccc;text-align: right;padding-top: 5px;}
        .work-list-wrap .work-item .num b{font-size: 14px;margin-left: 5px;color: #333;}
    </style>

</head>
<body>
<div class="layout-header">
    <div class="header-wrap">
        <div class="log-wrap"><span class="log-txt">Fun With Math</span></div>
        <div class="nav-wrap"></div>
        <div class="login-wrap">
            <span class="login-name"><%=session.getAttribute("username")%></span>
            <a title="logout" class="lnk-exit" href="/users/logout">Exist</a>
        </div>
    </div>
</div>
<div class="layout-body">
    <div class="w-box">
        <div class="w-box-header"><span>My Workbook</span></div>
        <div class="w-box-body">
            <div class="work-list-wrap">
                <c:forEach items="${workBookList}" var="list"  varStatus="status">
                    <a class="work-item" href="/questions/list?ids=${list.questionsId}&workId=${list.id}&workName=${list.name}" target="_self" title="do work">
                        <div class="name">${list.name}</div>
                        <div class="num">QUESTION:<b>${list.questionsNum}</b></div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="w-box">
        <div class="w-box-header"><span>My History</span></div>
        <div class="w-box-body">
            <table class="tb-box">
                <thead>
                    <tr>
                        <th>WORKBOOK</th>
                        <th>QUESTION</th>
                        <th>CORRECT</th>
                        <th>INCORRECT</th>
                        <th>TIME</th>
                        <th>LEVEL</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${answerRecords}" var="list"  varStatus="status">
                        <tr>
                            <td>${list.workbookName}</td>
                            <td>${list.questionCount}</td>
                            <td>${list.correctCount}</td>
                            <td>${list.incorrectCount}</td>
                            <td>${list.createTime}</td>
                            <td>${list.level}</td>
                            <td><a href="/ansuer/record/question?recordId=${list.id}&workName=${list.workbookName}&questionCount=${list.questionCount}&correctCount=${list.correctCount}&incorrectCount=${list.incorrectCount}&level=${list.level}">review</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>