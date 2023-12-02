<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add questions</title>
</head>
<body>
<h1>add questions</h1>
<hr>
<form action="/questions/add" name="add_form" method="post">
    <table>
        <tr>
            <td>question content:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>questions status:</td>
            <td>
                <input type="radio" name="status" value="0" />Enable
                <input type="radio" name="status" value="1" />Disable
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="Submit"/></td>
        </tr>
    </table>
    <input type="hidden" name="questionsId" value="">
</form>
</body>
</html>
