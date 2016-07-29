<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>科室设置</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="setting"/>
    </jsp:include>

    <div class="box box-info container">
        <div class="box-header">
            <button class="btn btn-success pull-right" id="add_office"><i class="fa fa-plus"></i> 新增科室</button>
            <h4>科室列表</h4>
        </div>
        <div class="box-body">
            <table class="table table-bordered">
                <thead>
                <tr class="success">
                    <th>科室名</th>
                    <th>负责人</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${officeList}" var="office">
                    <tr class="active">
                        <td>${office.officename}</td>
                        <td>${office.responsname}</td>
                        <td>
                            <button class="btn btn-warning" rel="${office.id}"><i class="fa fa-pencil-square-o"></i> 修改</button>
                            <button class="btn btn-danger" rel="${office.id}"><i class="fa fa-trash"></i> 删除</button>
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
                    <h4 class="modal-title">新增科室</h4>
                </div>
                <div class="modal-body">
                    <form id="add_form" action="/office/add" method="post">
                        <label class="form-group">科室名</label>
                        <input type="text" name="officename" class="form-control" id="officename">
                        <label class="form-group">负责人</label>
                        <input type="text" name="responsname" class="form-control" id="responsname">
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
                    <h4 class="modal-title">编辑科室</h4>
                </div>
                <div class="modal-body">
                    <form id="update_form" action="/office/update" method="post">
                        <input type="hidden" name="id" id="id">
                        <label class="form-group">科室名</label>
                        <input type="text" name="officename" class="form-control" id="office_name">
                        <label class="form-group">负责人</label>
                        <input type="text" name="responsname" id="respons_name" class="form-control">
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
            $("#add_office").click(function () {
                $("#add_form")[0].reset();
                $("#add_modal").modal({
                    show:true,
                    keyBoard:false,
                    backdrop:'static'
                })
            });
            $("#save_add").click(function () {
                if(!$("#officename").val()){
                    $("#officename").focus();
                    return;
                }
                if(!$("#responsname").val()){
                    $("#responsname").focus();
                    return;
                }
                $("#add_form").submit();
            });

            $(".btn-danger").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确认删除?")){
                    location.href = "/office/delete/"+id;
                }
            });

            $(".btn-warning").click(function () {
                var id = $(this).attr("rel");
                $("#update_modal").modal({
                    show:true,
                    keyBoard:false,
                    backDrop:'static'
                })
                $.get("/office/update/"+id).done(function (data) {
                    $("#id").val(data.id);
                    $("#office_name").val(data.officename);
                    $("#respons_name").val(data.responsname);
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
