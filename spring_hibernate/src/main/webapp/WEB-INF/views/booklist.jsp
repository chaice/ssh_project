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
                <input type="text" class="form-control" placeholder="请输入书籍名或作者" name="q_s_like_bookname_or_bookauthor" value="${q_s_like_bookname_or_bookauthor}">
                <input type="text" class="form-control" placeholder="请输入最低价格" name="q_f_ge_bookprice" value="${q_f_ge_bookprice}">--<input type="text" class="form-control" placeholder="请输入最高价格" name="q_f_le_bookprice" value="${q_f_le_bookprice}">
                <select name="q_i_eq_bookType.id" class="form-control">
                    <option value="">请选择书籍类型</option>
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.id}" ${type.id==q_i_eq_bookType.id?'selected':''}>${type.booktype}</option>
                    </c:forEach>
                </select>
                <select name="q_i_eq_publisher.id" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${pubList}" var="pub">
                        <option value="${pub.id}" ${pub.id==q_i_eq_publisher.id?'selected':''}>${pub.pubname}</option>
                    </c:forEach>
                </select>
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
                href:'?p={{number}}&q_s_like_bookname_or_bookauthor=${q_s_like_bookname_or_bookauthor}&q_f_ge_bookprice=${q_f_ge_bookprice}&q_f_le_bookprice=${q_f_le_bookprice}&q_i_eq_bookType.id=${q_i_eq_bookType.id}&q_i_eq_publisher.id=${q_i_eq_publisher.id}'
                });
        })
    </script>
</body>
</html>
