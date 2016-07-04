<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/file" enctype="multipart/form-data" method="post">
    文件描述:<input type="text" name="show">
    <input type="file" name="file">
    <button>save</button>
</form>
</body>
</html>
