<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改密码</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="include/head.jsp"%>
    <%@include file="include/sidebar.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>密码管理</h1>
        </section>
        <section class="content">
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">修改密码</h3>
                </div>
                <form class="form-horizontal" id="alterForm">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">原始密码</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="origin">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="new" id="new">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">再次输入</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" name="new2">
                            </div>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="button" class="btn btn-default">取消</button>
                        <button type="button" class="btn btn-info pull-right" id="save">保存</button>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/jquery.validate.min.js"></script>
<script>
    $(function(){
        $("#save").click(function(){
            $("#alterForm").submit();
        })
        $("#alterForm").validate({
            errorElement:"span",
            errorClass:"btn-danger",
            rules:{
                origin:{
                    required:"true",
                    remote:"/form"
                },
                new:{
                    required:"true",
                    rangelength:[5,10]
                },
                new2:{
                    required:"true",
                    equalTo:"#new"
                }
            },
            messages:{
                origin:{
                    required:"请输入原始密码!",
                    remote:"原始密码错误!"
                },
                new:{
                    required:"请输入新密码!",
                    rangelength:"新密码不符合要求,建议长度为5-10"
                },
                new2:{
                    required:"请再次输入新密码!",
                    equalTo:"两次输入的密码不一致!"
                }
            },
            submitHandler:function(form){
                $.post("/form",$(form).serialize()).done(function(data){
                    if(data == "success"){
                       alert("修改成功!")
                       location.href = "/";
                    }
                }).fail(function(){
                    alert("密码修改失败!")
                })
            }
        });
    })
</script>
</body>
</html>
