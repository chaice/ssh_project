<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
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
    <%@include file="../include/head.jsp"%>
    <%@include file="../include/sidebar.jsp"%>
    <div class="content-wrapper">
        <section class="content">
            <div class="box" id="UserLog" style="visibility: hidden">
                <div class="box-header">
                    <button type="button" class="close" id="log1"><span>&times;</span></button>
                    <h1 class="box-title">员工列表</h1>
                </div>
                <div class="box-body">
                    <div class="col-sm-12">
                        <table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
                            <thead>
                            <tr role="row">
                                <th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending"style="width: 181px;">id</th>
                                <th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1"  style="width: 224px;">员工</th>
                                <th class="sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 197px;">真实姓名</th>
                                <th class="sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 197px;">角色</th>
                                <th class="sorting" tabindex="0"  rowspan="1" colspan="1"  style="width: 197px;">操作</th>
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
        $("#example1").dataTable({
            "serverSide":true,
            "ajax":{"url":"",
               "dataSource":""},
            "columns":{
                "data":"ip",
                "data":"username",
                "data":"role.rolename",
                "data":function(row){
                    
                }
            }
        })
    })
</script>
</body>
</html>
