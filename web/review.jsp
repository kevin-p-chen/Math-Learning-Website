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

        .overview {
            background-color: #eee;
            padding: 5px 10px;
            color: #ddd;
        }
        .overview b{color: #333;}
        .q-list {}
        .q-list .q-item{border-bottom: 1px dashed #ccc;padding: 10px;}
        .q-list .q-item .title{}
        .q-list .q-item .title b{color: #999;}
        .q-list .q-item .option{padding: 10px;}
        .q-list .q-item .option ul{}
        .q-list .q-item .option ul li{list-style: none;padding: 5px;}
        .q-list .q-item .option ul li label{display: block;cursor: pointer;}
        .q-list .q-item .option ul li:hover{background-color: #0094ff;}
        .q-list .q-item .result {}
        .q-list .q-item .result .txt-item{margin-right: 10px;color: #ccc;}
        .q-list .q-item .result .txt-item b{font-size: 24px;margin: 0 0.5em;}
        .f01{color: dodgerblue;}
        .f02{color: darkslategray;}
        .fred{color: #cf0c2f;}
        .fgreen{color: forestgreen;}
        .forange{color:orangered;}
    </style>
</head>
<body>
    <div class="layout-header">
        <div class="header-wrap">
            <div class="log-wrap"><span class="log-txt">Fun With Math</span></div>
            <c:if test="${sessionScope.rule == 'teacher'}">
                <div class="nav-wrap">
                    <a href="/users/list" class="nav-item active">STUDENT</a>
                    <a href="/workbook/list" class="nav-item">WORKBOOK</a>
                </div>
            </c:if>
            <div class="login-wrap">
                <span class="login-name"><%=session.getAttribute("username")%></span>
                <a title="logout" class="lnk-exit" href="/users/logout">Exist</a>
            </div>
        </div>
    </div>
    <div class="layout-body">
        <div class="w-box">
            <div class="w-box-header"><span>${requestScope.workName}</span> - <span>${requestScope.userName}</span></div>
            <div class="w-box-body">
                <div class="overview">
                    <span class="f01">QUESTION:</span> <b style="font-size: 20px">${requestScope.questionCount}</b> | <span class="fred">CORRECT:</span> <b style="font-size: 20px">${requestScope.correctCount}</b> | <span class="fgreen">INCORRECT:</span> <b style="font-size: 20px">${requestScope.incorrectCount}</b> | <span class="forange">LEVEL:</span> <b style="font-size: 20px">${requestScope.level}</b>
                </div>
                    <div class="q-list">
                <c:forEach items="${userQuestions}" var="list"  varStatus="status">
                    <div class="q-item">
                        <div class="title"><b>Q1.&nbsp;&nbsp;&nbsp;</b>${list.questions.question}</div>
                        <div class="option">
                            <ul>
                                <c:forEach items="${list.questions.choiceArray}" var="choiceArray"  varStatus="i">
                                    <div>
                                        <li><b>
                                            <c:if test="${i.index == 0}">A. </c:if>
                                            <c:if test="${i.index == 1}">B. </c:if>
                                            <c:if test="${i.index == 2}">C. </c:if>
                                            <c:if test="${i.index == 3}">D. </c:if>
                                        </b> ${choiceArray}</li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="result">
                            <span class="txt-item">STANDARD: <b class="f01">
                                <c:if test="${list.userAnswer == 0}">A. </c:if>
                                <c:if test="${list.userAnswer == 1}">B. </c:if>
                                <c:if test="${list.userAnswer == 2}">C. </c:if>
                                <c:if test="${list.userAnswer == 3}">D. </c:if>
                            </b></span>
                            <span class="txt-item">ANSWER:<b class="f02">
                                <c:if test="${list.questionAnswer == 0}">A. </c:if>
                                <c:if test="${list.questionAnswer == 1}">B. </c:if>
                                <c:if test="${list.questionAnswer == 2}">C. </c:if>
                                <c:if test="${list.questionAnswer == 3}">D. </c:if>
                            </b></span>
                            <span class="txt-item">
                                <c:if test="${list.userAnswer == list.questionAnswer}"><b class="fred">CORRECT</b></c:if>
                                <c:if test="${list.userAnswer != list.questionAnswer}"><b class="fgreen">INCORRECT</b></c:if>
                            </span>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>