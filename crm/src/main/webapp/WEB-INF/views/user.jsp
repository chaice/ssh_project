<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%@include file="include/head.jsp"%>
    <%@include file="include/sidebar.jsp"%>
    <div class="content-wrapper">
        <section class="content">
            <c:if test="${not empty message}">
                <div class="bg-green">
                    ${message}
                </div>
            </c:if>
                <div class="well well-sm">
                    <a type="button" class="btn btn-success pull-right" href="javascript:;" id="add">新增员工</a>
                    <form class="form-inline" method="get">
                        <div class="form-group">
                            <input type="text" placeholder="请输入员工" name="userName" class="form-control" id="bookName">
                        </div>
                        <div class="form-group">
                            <select name="role" class="form-control" id="type">
                                <option value="">请选择角色</option>
                                <c:forEach items="${roleList}" var="role">
                                    <option value="${role.id}" ${roleid == role.id?'selected':''}>${role.rolename}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="button" class="btn btn-default" id="searchButton">搜索</button>
                    </form>
                </div>
            <div class="box" id="UserLog">
                <div class="box-header">
                    <h1 class="box-title">员工列表</h1>
                </div>
                <div class="box-body">
                    <div class="col-sm-12">
                        <table id="user" class="table table-bordered table-striped dataTable">
                            <thead>
                            <tr role="row">
                                <th>id</th>
                                <th>员工</th>
                                <th>真实姓名</th>
                                <th>角色</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="addUser">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增员工</h4>
                        </div>
                        <div class="box box-info">
                            <form class="form-horizontal" id="addNew">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">员工</label>
                                        <div class="col-sm-10">
                                            <input class="form-control"type="text" name="username">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">真实姓名</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">密码</label>
                                        <div class="col-sm-10">
                                            <input type="password" class="form-control" name="password">
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色</label>
                                      <div class="col-sm-6">
                                        <select name="roleid" class="form-control">
                                          <c:forEach items="${roleList}" var="role">
                                            <option value="${role.id}" ${roleid == role.id?'selected':''}>${role.rolename}</option>
                                          </c:forEach>
                                        </select>
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
     var dataTable= $("#user").DataTable({
           "serverSide":true,
           "ajax":"/user/data.json",
           "order": [0,'desc'],
           "searching": false,
           "columns":[
               {"data":"id","name":"id"},
               {"data":"username"},
               {"data":"name"},
               {"data":"role.rolename"},
               {"data":function(row){
                   return "<button class='btn-warning' rel='"+row.id+"'>修改</button>&nbsp;" +
                           "<button class='btn-danger'rel='"+row.id+"'>删除</button>"
               }}
           ],
           "lengthMenu":[5,10,20],
           "columnDefs":[{targets:0,"visible": false},
               {"targets":[1,2,3,4],
                   "orderable":false}],
           "language":{
               "search":"搜索",
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
       $("#add").click(function(){
           $("#addNew").reset;
           $("#addUser").modal({
               "show":"true",
               "backdrop":"static",
               "keyboard":false
                })
       });
       $("#save").click(function(){
           $("#addNew").submit();
       });
       $("#addNew").validate({
           errorElemet:"span",
           errorClass:"text-danger",
           rules:{"username":{required:"true"},
           "name":{required:"true"},
           "password":{required:"true",rangelength:[5,10]},
           "selectrole":{required:"true"}},
           messages:{"username":{required:"请输入员工"},
               "name":{required:"请输入真实姓名"},
               "password":{required:"请输入密码",rangelength:"密码建议长度为5-10"},
               "selectrole":{required:"请输入角色"}},
           submitHandler:function(form){
               $.post("/addUser",$(form).serialize()).done(function(data){
                   if(data == "success"){
                       $("#addUser").modal("hide");
                       dataTable.ajax.reload();
                   }
               }).fail(function(){
                   alert("添加失败！")
               })
           }
       });
        
    })
</script>
</body>
</html>
