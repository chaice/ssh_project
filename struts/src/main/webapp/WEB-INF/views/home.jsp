<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/form">
        <h1>${user.username}</h1>
        <h1>${user.password}</h1>
        <label>用户名</label>
        <input type="text" name="user.username">
        <label>密码</label>
        <input type="text" name="user.password">
        <button type="submit">提交</button>
    </form>
</body>
</html>
