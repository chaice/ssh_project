<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" href="/static/bootstrap/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="include/head.jsp"%>
    <%@include file="include/sidebar.jsp"%>
    <div class="content-wrapper">
        <!--点击修改密码后显示-->
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
                                            <input type="password" class="form-control" name="new" id="new">
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
        <!--修改成功显示-->
        <div class="example-modal">
            <div class="modal modal-success" id="success">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">Congratulation</h4>
                        </div>
                        <div class="modal-body">
                            <p>修改成功</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline pull-right" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->
        </div>
        <!-- Content Header (Page header) -->
        <%--<section class="content-header">--%>
            <%--<h1>--%>
                <%--这是一个项目的开始--%>
                <%--<small>关注它!</small>--%>
            <%--</h1>--%>
        <%--</section>--%>
        <!-- Main content nei rong  -->
        <section class="content" id="content">
            <div class="box" id="UserLog" style="visibility: hidden">
                <div class="box-header">
                    <button type="button" class="close" id="log1"><span>&times;</span></button>
                    <h1 class="box-title">登录日志</h1>
                </div>
                <div class="box-body">
                   <div class="col-sm-12">
                      <table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
                        <thead>
                        <tr role="row">
                            <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 181px;">id</th>
                            <th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 224px;">登录时间</th>
                            <th class="sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 197px;">IP地址</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                   </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/jquery.validate.min.js"></script>
<script src="/static/bootstrap/js/jquery.dataTables.min.js"></script>
<script src="/static/bootstrap/js/dataTables.bootstrap.min.js"></script>
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
                        $("#success").modal({
                            'show':"true"
                        })
                        location.href = "/";
                    }
                }).fail(function(){
                    alert("密码修改失败!")
                })
            }
        });
       $("#exit").click(function(){
          location.href = "/exit";
       });
       $("#log").click(function(){
                $("#UserLog").css("visibility","visible");
                $("#example1").DataTable({
                    "serverSide":true,
                    "ajax":"/admin/log.json",
                    "order":[0,"desc"],
                    "searching":false,
                    "ordering":false,
                    "lengthMenu":[5,10,20],
                    "columns":[
                        {"data":"id"},
                        {"data":"logintime"},
                        {"data":"ip"}],
                    "columnDefs":[{targets:0,"visible": false},
                        {"targets":[1,2],
                            "orderable":false}],
                    "language":{
                        "lengthMenu":"每页显示_MENU_条数据",
                        "paginate": {
                            "first":      "首页",
                            "last":       "末页",
                            "next":       "下一页",
                            "previous":   "上一页"
                        },
                        "info": "显示了第 _START_  条到第  _END_  条共  _TOTAL_ 条数据",
                        "infoEmpty":      "没有数据！"
                    }
                })
        });
       $("#log1").click(function(){
            $("#UserLog").css("visibility","hidden");
        });
    });
</script>
</body>
</html>

