<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fun With Math</title>
    <style>
        * {
            margin: 0;padding: 0;
            box-sizing: border-box;
        }
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
            display: flex;
            flex-flow: row;
            justify-content: space-between;
            align-items: stretch;
        }
        .left-body {
            flex: 1;
            padding-right: 5px;
        }
        .right-body {
            width: 400px;
            padding-left: 5px;
        }
        .w-box {
            background-color: white;
            border-radius: 4px;
            margin: 10px auto;
        }
        .w-box .w-box-header{padding:5px 10px;line-height: 30px;font-size: 18px;font-weight: bold;border-bottom: 1px solid #ccc;}
        .w-box .w-box-body{padding:10px;}

        .q-list-wrap {}
        .q-list-wrap .q-item .seq-wrap{color: #333;padding-right: 10px;background-color: #eee;}
        .q-list-wrap .q-item .form-wrap{flex: 1;padding-left: 10px;}
        .q-list-wrap .q-item {
            border-bottom: 1px dashed #ccc;
            padding: 10px 0;
            display: flex;
            flex-flow: row;
            justify-content: space-between;
            align-items: stretch;
        }
        .ipt-txtarea {
            display: block;
            height: 4em;
            width: 100%;
            padding: 2px 3px;
            background-color: #eee;
            border: 1px solid #eee;
        }
        .ipt-txt {
            display: inline-block;
            padding: 2px 3px;
            background-color: #eee;
            border: 1px solid #eee;
            width: 300px;
        }
        .btn-update {
            font-size: 16px;
            line-height: 1em;
            display: inline-block;
            vertical-align: middle;
            border: none;
            background-color: #0094ff;
            color: white;
            padding: 8px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-del {
            font-size: 16px;
            line-height: 1em;
            display: inline-block;
            vertical-align: middle;
            border: none;
            background-color: #cf0c2f;
            color: white;
            padding: 8px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn-create {
            display: block;
            width: 100%;
            border: none;
            background-color: #0094ff;
            color: white;
            padding: 5px 10px;
            border-radius: 4px;
            cursor: pointer;
        }
        .part-title{
            color: #888;
        }
        .part-item {
            padding-bottom: 10px;
        }
    </style>
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
    <div class="left-body">
        <div class="w-box">
            <div class="w-box-header"><span>${requestScope.workName}</span></div>
            <div class="w-box-body">
                <div class="q-list-wrap">

                    <c:forEach items="${questionsList}" var="list"  varStatus="status">
                        <div class="q-item">
                            <div class="seq-wrap">Q${status.index + 1}.</div>
                            <div class="form-wrap">
                                <form name="form_${list.id}" method="post" action="/questions/edit" >
                                    <input name='id' type="hidden" value="${list.id}"/>
                                    <input name='workId' type="hidden" value="${requestScope.workId}"/>
                                    <input name='workName' type="hidden" value="${requestScope.workName}"/>
                                    <input name='ids' type="hidden" value="${requestScope.questionsIds}"/>
                                    <div class="part-item">
                                        <div class="part-title">TITLE</div>
                                        <textarea class="ipt-txtarea" name="question" value="" >${list.question}</textarea>
                                    </div>
                                    <div class="part-item">
                                        <div class="part-title">OPTIONS</div>
                                        <c:forEach items="${list.choiceArray}" var="choiceArray"  varStatus="i">
                                            <div>
                                                <c:if test="${i.index == 0}">A. </c:if>
                                                <c:if test="${i.index == 1}">B. </c:if>
                                                <c:if test="${i.index == 2}">C. </c:if>
                                                <c:if test="${i.index == 3}">D. </c:if>
                                                <input type="text" name="choice_${i.index}" value="${choiceArray}" class="ipt-txt"/></div>
                                        </c:forEach>
                                    </div>
                                    <div class="part-item">
                                        <span class="part-title">STANDARD: </span>
                                        <label class="standard-txt">A. <input type="radio" name="answer" <c:if test="${list.answer == 0}">checked="checked"</c:if> value="0"/></label>
                                        <label class="standard-txt">B. <input type="radio" name="answer" <c:if test="${list.answer == 1}">checked="checked"</c:if> value="1"/></label>
                                        <label class="standard-txt">C. <input type="radio" name="answer" <c:if test="${list.answer == 2}">checked="checked"</c:if> value="2"/></label>
                                        <label class="standard-txt">D. <input type="radio" name="answer" <c:if test="${list.answer == 3}">checked="checked"</c:if> value="3"/></label>
                                    </div>
                                    <div class="part-item">
                                        <input type="submit" class="btn-update" value="UPDATE"/>
                                        <a href="/questions/delete?id=${list.id}&ids=${requestScope.questionsIds}&workId=${requestScope.workId}&workName=${requestScope.workName}" class="btn-del">DELETE</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
    <div class="right-body">
        <div class="w-box">
            <div class="w-box-header"><span>question add</span></div>
            <div class="w-box-body">
                <div class="create-wrap">
                    <form name="f_create" method="post" action="/questions/add" >
                        <input name='workId' type="hidden" value="${requestScope.workId}"/>
                        <input name='workName' type="hidden" value="${requestScope.workName}"/>
                        <input name='ids' type="hidden" value="${requestScope.questionsIds}"/>
                        <div class="part-item">
                            <div class="part-title">TITLE</div>
                            <div>
                                <textarea class="ipt-txtarea" name="question" value="" ></textarea>
                            </div>
                        </div>
                        <div class="part-item">
                            <div class="part-title">OPTIONS</div>
                            <div>
                                <div>A. <input type="text" name="choice_0" value="" class="ipt-txt"/></div>
                                <div>B. <input type="text" name="choice_1" value="" class="ipt-txt"/></div>
                                <div>C. <input type="text" name="choice_2" value="" class="ipt-txt"/></div>
                                <div>D. <input type="text" name="choice_3" value="" class="ipt-txt"/></div>
                            </div>
                        </div>
                        <div class="part-item">
                            <div class="part-title">STANDARD</div>
                            <div>
                                <label class="standard-txt">A. <input type="radio" name="answer" value="0"/></label>
                                <label class="standard-txt">B. <input type="radio" name="answer" value="1"/></label>
                                <label class="standard-txt">C. <input type="radio" name="answer" value="2"/></label>
                                <label class="standard-txt">D. <input type="radio" name="answer" value="3"/></label>
                            </div>
                        </div>
                        <div class="part-item">
                            <input type="submit" class="btn-create" value="Add Question"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>