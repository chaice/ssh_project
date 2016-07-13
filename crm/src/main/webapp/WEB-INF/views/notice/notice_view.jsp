<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/default.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <small><i class="fa fa-comments-o"></i></small>&nbsp;${notice.title}
                  &nbsp;&nbsp;&nbsp;
                    <small class="text-muted">发表人:</small>${notice.realname}
                  &nbsp;&nbsp;&nbsp;
                    <small class="text-center"><i class="fa fa-clock-o"></i>&nbsp;<fmt:formatDate value="${notice.creattime}" pattern="y-M-d H:m"></fmt:formatDate></small>
                <shiro:hasRole name="经理">
                    <a type="button" class="btn btn-success pull-right" href="/notice/new"><i class="fa fa-plus"></i> 新增公告</a>
                </shiro:hasRole>
            </h1>
        </section>
        <section class="content">
            <div class="box box-success">
                <div class="box-body">
                    ${notice.context}
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/highlight.pack.js"></script>
<script>hljs.initHighlightingOnLoad();</script>
</body>
</html>
