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
 <div class="bs-example" data-example-id="contextual-table">
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
        <c:forEach items="${bookList}" var="book">
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
  </div>
</div>
</div>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script>
        (function(){
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