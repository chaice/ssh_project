<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书列表</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
</head>
<body>
    <div class="container">
        <div class="panel-title">
            <h1>图书列表</h1>
        </div>
        <div class="pull-right">
            <a href="/book/new" class="btn btn-success"><i class="fa fa-plus"> 新增书籍</i></a>
        </div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>书名</th>
                    <th>作者</th>
                    <th>数量</th>
                    <th>出版社</th>
                    <th>类型</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${booklist}" var="book">
                    <tr>
                        <th>${book.bookname}</th>
                        <th>${book.bookauthor}</th>
                        <th>${book.booknum}</th>
                        <th>${book.publisher.pubname}</th>
                        <th>${book.bookType.booktype}</th>
                        <th>
                            <a href="/book/update/${book.id}" class="btn btn-warning">修改</a>
                            <a href="/book/delete/${book.id}" class="btn btn-danger">删除</a>
                        </th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
