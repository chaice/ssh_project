
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Document</title>
</head>
<body>
<form action="/web" method="post">
    <input type="hidden" name="token" value="${requestScope.token}">
    <label for="txt">姓名</label><input type="text" name="name" id="txt">
    <button>提交</button>
</form>
</body>
</html>
