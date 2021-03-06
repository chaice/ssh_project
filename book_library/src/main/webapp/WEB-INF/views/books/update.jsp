<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span> Register </span>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form" action="/books/new" method="post">
                        <input type="hidden" name="id" value="${book.id}">
                        <div class="form-group">
                            <label class="col-md-4 control-label">书名</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="bookname" value="${book.bookname}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">价格</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="bookprice" value="${book.bookprice}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">作者</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="bookauthor" value="${book.bookauthor}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">数量</label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="booknum" value="${book.booknum}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">类型</label>
                            <div class="col-md-6">
                                <select name="typeid">
                                    <c:forEach items="${bookTypeList}" var="type">
                                       <option value="${type.id}" ${book.typeid == type.id?'selected':''}>${type.booktype}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">出版社</label>
                            <div class="col-md-6">
                                <select name="pubid">
                                    <c:forEach items="${publisherList}" var="pub">
                                        <option  value="${pub.id}" ${book.pubid == pub.id? 'selected':''}>${pub.pubname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <button class="btn btn-primary">
								<span> <i class="fa fa-btn fa-check-circle"></i>确定
								</span>
                                </button>
                            </div>
                        </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
