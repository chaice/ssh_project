<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>编辑书籍</h1>
    <form method="post" action="/book/update">
        <input type="hidden" value="${book.id}" name="id">
        <div class="form-group">
            <label class="form-group">书籍名称</label>
            <input type="text" class="form-control" name="bookname" value="${book.bookname}">
        </div>
        <div class="form-group">
            <label class="form-group">作者</label>
            <input type="text" class="form-control" name="bookauthor" value="${book.bookauthor}">
        </div>
        <div class="form-group">
            <label class="form-group">价格</label>
            <input type="text" class="form-control" name="bookprice" value="${book.bookprice}">
        </div>
        <div class="form-group">
            <label class="form-group">数量</label>
            <input type="text" class="form-control" name="booknum" value="${book.booknum}">
        </div>
        <div class="form-group">
            <label class="form-group">书籍类型</label>
            <select name="bookType.id" class="form-control">
                <c:forEach items="${typelist}" var="type">
                    <option value="${type.id}" ${type.id == book.bookType.id?'selected':''}>${type.booktype}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="form-group">出版社</label>
            <select name="publisher.id" class="form-control">
                <c:forEach items="${publist}" var="pub">
                    <option value="${pub.id}" ${pub.id == book.publisher.id?'selected':''}>${pub.pubname}</option>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-success pull-right" type="submit">保存</button>
    </form>
</div>
</body>
</html>
