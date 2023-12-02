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
            overflow: hidden;
        }
        .w-box .w-box-header{
            padding:5px 10px;line-height: 30px;font-size: 18px;font-weight: bold;border-bottom: 1px solid #ccc;
            border-left: 10px solid #0094ff;

        }
        .w-box .w-box-body{padding:10px;}

        .q-list {}
        .q-list .q-item{border-bottom: 1px dashed #ccc;padding: 10px;}
        .q-list .q-item .title{}
        .q-list .q-item .title b{color: #999;}
        .q-list .q-item .option{padding: 10px;}
        .q-list .q-item .option ul{}
        .q-list .q-item .option ul li{list-style: none;padding: 5px;}
        .q-list .q-item .option ul li label{display: block;cursor: pointer;}
        .q-list .q-item .option ul li:hover{background-color: #0094ff;}
        .q-submit {
            margin: 10px 0;
        }
        .btn-submit {
            display: block;
            font-size: 20px;
            color: white;
            background-color: #0094ff;
            text-align: center;
            padding: 10px 30px;
            border-radius: 5px;
            border: none;
        }
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
            <div class="w-box-header"><span>${requestScope.workName}</span></div>
            <div class="w-box-body">
                <form method="post" action="/ansuer/record/add" target="_self" onsubmit="return check(this)">
                    <input name='workId' type="hidden" value="${requestScope.workId}"/>
                    <input name='workName' type="hidden" value="${requestScope.workName}"/>
                    <input name='ids' type="hidden" value="${requestScope.questionsIds}"/>
                    <div class="q-list">
                        <c:forEach items="${questionsList}" var="list"  varStatus="status">
                        <input name='id_${list.id}' type="hidden" value="${list.id}"/>
                        <input name='answer_${list.id}' type="hidden" value="${list.answer}"/>
                        <div class="q-item">
                            <div class="title"><b>Q1.&nbsp;&nbsp;&nbsp;</b>${list.question}</div>
                            <div class="option">
                                <ul>
                                    <c:forEach items="${list.choiceArray}" var="choiceArray"  varStatus="i">
                                        <div>
                                            <li><label><input name="userAnswer_${list.id}" type="radio" value="${i.index}"/>&nbsp;&nbsp;&nbsp;<b>
                                                <c:if test="${i.index == 0}">A.</c:if>
                                                <c:if test="${i.index == 1}">B.</c:if>
                                                <c:if test="${i.index == 2}">C.</c:if>
                                                <c:if test="${i.index == 3}">D.</c:if>
                                            </b> ${choiceArray}</label></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                    <div class="q-submit">
                        <input type="submit" value="SUBMIT" class="btn-submit"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>