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
        <div class="well well-sm">
            <form action="/book/" class="form-inline" method="get">
                <input type="text" class="form-control" placeholder="请输入书籍名" name="q_like_bookname">
                <input type="text" class="form-control" placeholder="请输入最低价格" name="q_ge_bookprice">--
                <input type="text" class="form-control" placeholder="请输入最高价格" name="q_le_bookprice">
                &nbsp;
                <button class="btn btn-primary" type="submit">搜索</button>
            </form>
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
                    <th>价格</th>
                    <th>出版社</th>
                    <th>类型</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.items}" var="book">
                    <tr>
                        <td>${book.bookname}</td>
                        <td>${book.bookauthor}</td>
                        <td>${book.booknum}</td>
                        <td>${book.bookprice}</td>
                        <td>${book.publisher.pubname}</td>
                        <td>${book.bookType.booktype}</td>
                        <td>
                            <a href="/book/update/${book.id}" class="btn btn-warning">修改</a>
                            <a href="/book/delete/${book.id}" class="btn btn-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div id="limit" class="pull-right"></div>
    </div>
    <script src="/static/js/jquery-3.0.0.min.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function () {
            $("#limit").twbsPagination({
                totalPages: ${page.totalPage},
                visiblePages: 5,
                first : '首页',
                prev : '上一页',
                next: '下一页',
                last : '末页',
                href: '?p={{number}}'
            })
        })
    </script>
</body>
</html>
