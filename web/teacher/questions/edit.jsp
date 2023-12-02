<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit workbook</title>
</head>
<body>
<h1>edit workbook</h1>
<hr>
<form action="/workbook/edit" name="add_form" method="post">
    <table>
        <tr>
            <td>workbook name:</td>
            <td><input type="text" name="name" value="${requestScope.workBook.name}"/></td>
        </tr>
        <tr>
            <td>workbook status:</td>
            <td>
                <input type="radio" name="status" value="0" <c:if test="${requestScope.workBook.status == 0}">checked="checked"</c:if> />Enable
                <input type="radio" name="status" value="1" <c:if test="${requestScope.workBook.status == 1}">checked="checked"</c:if> />Disable
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="Submit"/></td>
        </tr>
    </table>
    <input type="hidden" name="id" value="${requestScope.workBook.id}">
    <input type="hidden" name="questionsId" value="${requestScope.workBook.questionsId}">
</form>
</body>
</html>
