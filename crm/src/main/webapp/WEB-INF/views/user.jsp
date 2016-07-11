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
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <%--<c:if test="${not empty message}">--%>
                <%--<div class="bg-green">--%>
                    <%--${message}--%>
                <%--</div>--%>
            <%--</c:if>--%>
                <div class="box box-info">
                    <div class="box-header with-border">
                        <h2 class="box-title">员工列表</h2>
                    </div>
                    <div class="box-body">
                        <!--搜索-->
                        <div class="well well-sm">
                            <a type="button" class="btn btn-success pull-right" href="javascript:;" id="add"><i class="fa fa-plus"></i> 新增</a>
                            <form class="form-inline" method="get">
                                <div class="form-group">
                                        <input type="text" placeholder="请输入账号或姓名" name="username" class="form-control" id="keyword" value="${username}">
                                </div>
                                <div class="form-group">
                                    <select name="roleid" class="form-control" id="Role">
                                        <option value="">请选择角色</option>
                                        <c:forEach items="${roleList}" var="role">
                                            <option value="${role.id}" ${roleid == role.id?'selected':''}>${role.rolename}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="button" class="btn btn-default" id="searchButton">搜索</button>
                            </form>
                        </div>
                        <!--员工列表-->
                        <div class="col-sm-12" id="UserLog">
                                    <table id="user" class="table table-bordered dataTable">
                                        <thead>
                                        <tr role="row">
                                            <th>id</th>
                                            <th>账号</th>
                                            <th>真实姓名</th>
                                            <th>角色</th>
                                            <th>微信号</th>
                                            <th>状态</th>
                                            <th>创建时间</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                        <!--增加新员工-->
                        <div class="modal fade" id="addUser">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">新增用户</h4>
                                    </div>
                                    <div class="box box-info">
                                        <form class="form-horizontal" id="addNew">
                                            <div class="box-body">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">账号</label>
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
                                                    <label class="col-sm-2 control-label" >密码(默认:000000)</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" value="000000" class="form-control" name="password">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">微信号</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="weixin">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">角色</label>
                                                    <div class="col-sm-6">
                                                        <select name="roleid" class="form-control">
                                                            <c:forEach items="${roleList}" var="role">
                                                                <option value="${role.id}">${role.rolename}</option>
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
                        <!--修改员工-->
                        <div class="modal fade" id="alterModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title">编辑用户</h4>
                                    </div>
                                    <div class="box box-info">
                                        <form class="form-horizontal" id="alterUser">
                                            <div class="box-body">
                                                <input type="hidden" name="id" id="id">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">账号</label>
                                                    <div class="col-sm-10">
                                                        <input class="form-control"type="text" name="username" id="username">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">真实姓名</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="name" id="name">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">微信号</label>
                                                    <div class="col-sm-10">
                                                        <input type="text" class="form-control" name="weixin" id="weixin">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">状态</label>
                                                    <div class="col-sm-6">
                                                        <select name="enable" class="form-control" id="enable">
                                                            <option value="true">正常</option>
                                                            <option value="false">禁用</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">角色</label>
                                                    <div class="col-sm-6">
                                                        <select name="roleid" class="form-control" id="roleid">
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
                                        <button type="button" class="btn btn-primary" id="saveAlter">保存</button>
                                    </div>
                                </div>
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
<script src="/static/bootstrap/js/moment.js"></script>
<script>
    $(function(){
     var dataTable= $("#user").DataTable({
           "serverSide":true,
           "ajax":{url:"/admin/user/dataList",
                data:function(dataSource){
                    dataSource.username=$("#keyword").val();
                    dataSource.roleid=$("#Role").val();
           }},
           "order": [0,'desc'],
           "searching": false,
           "columns":[
               {"data":"id","name":"id"},
               {"data":"username"},
               {"data":"name"},
               {"data":"role.rolename"},
               {"data":"weixin"},
               {"data":function(row){
                   if(row.enable == 1){
                       return "<span class='label label-success'>正常</span>"
                   }else{
                       return "<span class='label label-danger'>禁用</span>"
                   }
               }} ,
               {"data":function(row){
                   var timestmap = row.creattime;
                   return moment(timestmap).format("YYYY-MM-DD HH:mm");
               }},
               {"data":function(row){
                   if(row.roleid == 1){
                       return "";
                   }else{
                      return "<button class='btn-warning' rel='"+row.id+"'>修改</button>&nbsp;" +
                              "<button class='btn-danger'rel='"+row.id+"'>重置密码</button>";
                   }
               }}
           ],
         "autoWidth": false,
           "lengthMenu":[5,10,20],
           "columnDefs":[{targets:0,"visible": false},
               {"targets":[1,2,3,4,5,6,7],
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
               "infoEmpty":      "没有数据！",
               "infoFiltered":   "(从总共 _MAX_条数据查询得来 )"
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
           errorElement:"span",
           errorClass:"text-danger",
           rules: {
               username: {
                   required: true,
                   rangelength: [3, 10],
                   remote:"/admin/user/check",
               },
               name: {
                   required: true
               },
               password: {
                   required: true,
                   rangelength: [5, 10]
               },
               weixin:{
                   required:true
               }
           },
           messages: {
               username: {
                   required: "请输入用户名!",
                   rangelength:"用户名建议长度为3-10",
                   remote:"用户名已存在!"
               },
               name: {
                   required: "请输入真实姓名!"
               },
               password: {
                   required: "请输入密码!",
                   rangelength: "密码建议长度为5-10!"
               },
               weixin:{
                   required:"请输入微信号!"
               }
           },
           submitHandler:function(form){
               $.post("/admin/addUser",$(form).serialize()).done(function(data){
                   if(data == "success"){
                       $("#addUser").modal("hide");
                       dataTable.ajax.reload();
                   }
               }).fail(function(){
                   alert("添加失败！")
               })
           }
       });
       $(document).delegate(".btn-danger","click",function(){
           var id = $(this).attr("rel");
          if(confirm("确认要重置吗?")){
              $.get("/admin/reset/"+id).done(function(data){
                  if(data == "success"){
                      alert("重置成功!")
                  }
              }).fail(function(){
                  alert("删除失败!")
              })
          }
       });
       $(document).delegate(".btn-warning","click",function(){
           $("#alterModal").modal({
               "show":"true",
               "backdrop":"static",
               "keyboard":false
           })
           var id = $(this).attr("rel");
           $.get("/admin/alter/"+id).done(function(data){
               $("#id").val(data.id);
               $("#username").val(data.username);
               $("#name").val(data.name);
               $("#weixin").val(data.weixin);
               $("#enable").val(data.enable.toString());
               $("#roleid").val(data.roleid);
           }).fail(function(){
               alert("操作失败!")
           })
       });
       $("#saveAlter").click(function(){
           $("#alterUser").submit();
       });
       $("#alterUser").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                username:{
                    required:true
                },
                name: {
                    required:true
                },
                weixin:{
                    required:true
                },
                roleid:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"请输入用户名!"
                },
                name:{
                    required:"请输入真实姓名!"
                },
                weixin:{
                    required:"请输入微信号!"
                },
                roleid:{
                    required:"请选择角色!"
                }
            },
            submitHandler:function(form){
                $.post("/admin/alter",$(form).serialize()).done(function(data){
                    if(data == "success"){
                        $("#alterModal").modal("hide");
                        dataTable.ajax.reload();
                    }else{
                        alert("操作失败!")
                    }
                }).fail(function(){
                    alert("操作失败!")
                })
            }
        });
       $("#searchButton").click(function(){
           dataTable.ajax.reload();
       })
    })
</script>
</body>
</html>
