<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增公告</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/simditor/styles/simditor.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                新增公告
                <small>关注它!</small>
            </h1>
        </section>
        <section class="content">
            <div class="box box-success">
                <div class="box-header">
                    <h3 class="box-title">编辑公告</h3>
                </div>
                <form class="form-horizontal" method="post" id="addNotice" action="/notice/new">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">标题</label>
                            <div class="col-sm-11">
                                <input type="text" class="form-control" name="title" id="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">公告内容</label>
                            <div class="col-sm-11">
                            <textarea name="context" row="10" class="form-control" id="context"></textarea>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="box-footer">
                    <button type="button" id="save" class="btn btn-info pull-right">发表</button>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/simditor/scripts/module.min.js"></script>
<script src="/static/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/simditor/scripts/uploader.min.js"></script>
<script src="/static/simditor/scripts/simditor.min.js"></script>
<script>
    $(function(){
        var editor = new Simditor({
            textarea: $('#context')
        });
        $("#save").click(function(){
           if(!$("#title").val()){
               $("#title").focus();
               return;
           }
            if(!$("#context").val()){
                $("#context").focus();
                return;
            }
           $("#addNotice").submit();
        })
    })
</script>
</body>
</html>
