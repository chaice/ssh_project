<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <c:if test="${not empty message}">
      <div class="container">
          <p class="bg-info">操作成功！</p>
      </div>
    </c:if>
<div class="container">
    <a type="button" class="btn btn-success" href="/books/add">新增书籍</a>
    <div class="well well-sm">
        <form method="get" class="form-inline" action="/books">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" name="bookname" class="form-control" value="${bookname}">
            </div>
            <div class="form-group">
                <select name="type" class="form-control">
                    <option value="">请选择类型</option>
                    <c:forEach items="${bookTypeList}" var="type">
                        <option value="${type.id}" ${typeid == type.id?'selected':''}>${type.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <select name="pub" class="form-control">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="pub">
                        <option value="${pub.id}" ${pubid == pub.id?'selected':''}>${pub.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-default">搜索</button>
        </form>
    </div>
 <div class="bs-example" data-example-id="contextual-table">
     <c:choose>
     <c:when test="${empty page}">
         <div class="container">
             <p class="bg-danger">没有符合的内容！</p>
         </div>
     </c:when>
    <c:otherwise>
    <table class="table">
        <thead>
        <tr class="active">
            <th scope="row">书名</th>
            <th>价格</th>
            <th>作者</th>
            <th>数量</th>
            <th>类型</th>
            <th>出版社</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="book">
            <tr class="success">
                <td scope="row">${book.bookname}</td>
                <td>${book.bookprice}</td>
                <td>${book.bookauthor}</td>
                <td>${book.booknum}</td>
                <td>${book.bookType.booktype}</td>
                <td>${book.publisher.pubname}</td>
                <td><button type="button" rel="${book.id}" class="btn btn-warning">修改</button>
                    <button type="button" rel="${book.id}" class="btn btn-danger">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
     <%--<nav>--%>
         <%--<ul class="pagination">--%>
             <%--<c:choose>--%>
                 <%--<c:when test="${page.page == 1}">--%>
                     <%--<li class="disabled"><a href="javascript:;">首页</a></li>--%>
                     <%--<li class="disabled"><a href="javascript:;">上一页</a></li>--%>
                 <%--</c:when>--%>
                 <%--<c:otherwise>--%>
                     <%--<li><a href="/books">首页</a></li>--%>
                     <%--<li><a href="/books?p=${page.page-1}">上一页</a></li>--%>
                 <%--</c:otherwise>--%>
             <%--</c:choose>--%>
             <%--<c:choose>--%>
                 <%--<c:when test="${page.page == page.totalPage}">--%>
                     <%--<li class="disabled"><a href="javascript:;">下一页</a></li>--%>
                     <%--<li class="disabled"><a href="javascript:;">末页</a></li>--%>
                 <%--</c:when>--%>
                 <%--<c:otherwise>--%>
                     <%--<li><a href="/books?p=${page.page+1}">下一页</a></li>--%>
                     <%--<li><a href="/books?p=${page.totalPage}">末页</a></li>--%>
                 <%--</c:otherwise>--%>
             <%--</c:choose>--%>
         <%--</ul>--%>
     <%--</nav>--%>
        <div class="panel-footer text-right">
            <ul class="pagination" id="page"></ul>
         </div>
        </c:otherwise>
     </c:choose>
  </div>
</div>
</div>
    <script src="/static/js/jquery-3.0.0.min.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        (function(){
            $("#page").twbsPagination({
                totalPages:${page.totalPage},
                visiblePages:5,
                first:'首页',
                prev:'上一页',
                next:'下一页',
                last:'末页',
                href:'?bookname=${bookname}&type=${typeid}&pub=${pubid}&p={{number}}'
            });
            $("button.btn-danger").click(function(){
                var id = this.getAttribute("rel");
                if(confirm("确认删除？")){
                    location.href = "/books/"+id+"/del"
            }});
            $("button.btn-warning").click(function(){
                console.log(this);
                var id = this.getAttribute("rel");
                location.href = "/books/new/"+id;
                });
        })();
    </script>
</body>
</html>