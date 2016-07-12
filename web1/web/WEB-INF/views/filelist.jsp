<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="navbar-header">
            <h1>文件列表</h1>
        </div>
        <table class="table table-bordered">
           <thead>
           <a href="/file" class="btn-success">文件</a>
                <tr>
                    <th>文件名</th>
                    <th>大小</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                 <c:forEach items="${list}" var="file">
                     <tr>
                         <td>${file.realname}</td>
                         <td>${file.size}</td>
                         <td>
                             <c:if test="${file.privew}">
                                 <a class="btn-default" type="button" href="/fileview?id=${file.id}" target="_blank">预览</a>
                             </c:if>
                             <button class="btn-success">下载</button>
                         </td>
                     </tr>
                 </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
