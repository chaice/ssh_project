<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>主页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="include/head.jsp"%>
    <%@include file="include/sidebar.jsp"%>
    <div class="content-wrapper">
        <div class="modal fade" id="alterPw">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">修改密码</h4>
                    </div>
                        <div class="box box-info">
                            <form class="form-horizontal" id="alterForm">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">原始密码</label>
                                        <div class="col-sm-10">
                                            <input class="form-control"type="text" name="origin" id="origin">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">新密码</label>
                                        <div class="col-sm-10">
                                            <input type="password" class="form-control" name="new">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">再次输入</label>
                                        <div class="col-sm-10">
                                            <input type="password" class="form-control" name="new2">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" id="save">保存</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
            <h4><i class="icon fa fa-check"></i> Alert!</h4>
            Success alert preview. This alert is dismissable.
        </div>
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                这是一个项目的开始
                <small>关注它!</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                <li class="active">Here</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">

            表格？

        </section>
        <!-- /.content -->
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/jquery.validate.min.js"></script>
<script>
    $(function(){
       $("#alter").click(function(){
           $("#alterPw").modal({
               'ajax':"",
               'show':"true",
               'backdrop':"true",
               'keyboard':"true"
           })
       });
       $("#save").click(function(){
           $("#alterForm").submit();
       });
        $("#alterForm").validate({
            errorElement:"span",
            errorClass:"btn-danger",
            rules:{origin:{required:"true",
            remote:"/form"},
            new:{required:"true",rangelength:[5,10]},
            new2:{required:"true",equalTo:"#new"}},
            messages:{origin:{required:"请输入原始密码!",
                remote:"原始密码错误!"},
                new:{required:"请输入新密码!",rangelength:"新密码不符合要求,建议长度为5-10"},
                new2:{required:"请再次输入新密码!",equalTo:"两次输入的密码不一致!"}},
            submitHandler:function(form){
                $.post("/form",$(form).serialize()).done(function(data){
                    if(data == "success"){
                        $("#alterPw").modal("hide");

                    }
                }).fail(function(){
                    alert("密码修改失败!")
                })
            }
        })

    });

</script>
</body>
</html>

