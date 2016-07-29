<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号设置</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="setting"/>
    </jsp:include>

    <div class="box box-primary container">
    <div class="box-header">
        <button class="btn btn-success pull-right" id="add_user"><i class="fa fa-plus"></i> 新增用户</button>
        <h4>用户列表</h4>
    </div>
    <div class="box-body">
        <table class="table table-bordered">
            <thead>
            <tr class="alert-info">
                <th>用户名</th>
                <th>角色</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr class="active">
                    <td>${user.username}</td>
                    <td>${user.role.rolename}</td>
                    <td>
                        <button class="btn btn-warning" rel="${user.id}"><i class="fa fa-pencil-square-o"></i> 修改</button>
                        <button class="btn btn-danger" rel="${user.id}"><i class="fa fa-trash"></i> 删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

    <div class="modal fade" id="add_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增用户</h4>
                </div>
                <div class="modal-body">
                    <form id="add_form" action="/user/add" method="post">
                        <label class="form-group">用户名</label>
                        <input type="text" name="username" class="form-control" id="username">
                        <label class="form-group">密码[默认值为:111111]</label>
                        <input type="text" name="password" class="form-control" value="111111">
                        <label class="form-group">角色</label>
                        <select name="role.id" class="form-control">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                            </c:forEach>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" id="save_add">保存</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="update_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑用户</h4>
                </div>
                <div class="modal-body">
                    <form id="update_form" action="/user/update" method="post">
                        <input type="hidden" name="id" id="id">
                        <label class="form-group">用户名</label>
                        <input type="text" name="username" class="form-control" id="user_name">
                        <input type="hidden" name="password" id="password">
                        <label class="form-group">角色</label>
                        <select name="role.id" class="form-control" id="roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                            </c:forEach>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" id="save_update">保存</button>
                </div>
            </div>
        </div>
    </div>
    
    <script src="/static/js/jquery-3.0.0.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script>
        $(function () {
            $("#add_user").click(function () {
                $("#add_form")[0].reset();
                $("#add_modal").modal({
                    show:true,
                    keyBoard:false,
                    backdrop:'static'
                })
            });
            $("#save_add").click(function () {
                if(!$("#username").val()){
                    $("#username").focus();
                    return;
                }
                $("#add_form").submit();
            });
            $(".btn-danger").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确认删除?")){
                    location.href = "/user/delete/"+id;
                }
            });
            $(".btn-warning").click(function () {
                var id = $(this).attr("rel");
                $("#update_modal").modal({
                    show:true,
                    keyBoard:false,
                    backDrop:'static'
                })
                $.get("/user/update/"+id).done(function (data) {
                        $("#id").val(data.id);
                        $("#password").val(data.password);
                        $("#user_name").val(data.username);
                        $("#roleid").val(data.role.id)
                }).fail(function () {
                    alert("数据获取异常!")
                })
            });
            $("#save_update").click(function () {
                $("#update_form").submit();
            })

        })
    </script>
    </body>
</html>
