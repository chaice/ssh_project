<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医保类型设置</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="setting"/>
    </jsp:include>

    <div class="box box-info container">
        <div class="box-header">
            <button class="btn btn-success pull-right" id="add_ins"><i class="fa fa-plus"></i> 新增医保类型</button>
            <h4>医保类型列表</h4>
        </div>
        <div class="box-body">
            <table class="table table-bordered">
                <thead>
                <tr class="success">
                    <th>医保类型名称</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${insList}" var="ins">
                    <tr class="active">
                        <td>${ins.insname}</td>
                        <td>
                            <button class="btn btn-warning" rel="${ins.id}"><i class="fa fa-pencil-square-o"></i> 修改</button>
                            <button class="btn btn-danger" rel="${ins.id}"><i class="fa fa-trash"></i> 删除</button>
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
                    <h4 class="modal-title">新增医保类型</h4>
                </div>
                <div class="modal-body">
                    <form id="add_form" action="/insurance/add" method="post">
                        <label class="form-group">医保类型名称</label>
                        <input type="text" name="insname" class="form-control" id="insname">
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
                    <h4 class="modal-title">编辑医保卡类型</h4>
                </div>
                <div class="modal-body">
                    <form id="update_form" action="/insurance/update" method="post">
                        <input type="hidden" name="id" id="id">
                        <label class="form-group">医保卡类型名</label>
                        <input type="text" name="insname" class="form-control" id="ins_name">
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
            $("#add_ins").click(function () {
                $("#add_form")[0].reset();
                $("#add_modal").modal({
                    show:true,
                    keyBoard:false,
                    backdrop:'static'
                })
            });
            $("#save_add").click(function () {
                if(!$("#insname").val()){
                    $("#insname").focus();
                    return;
                }
                $("#add_form").submit();
            });

            $(".btn-danger").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确认删除?")){
                    location.href = "/insurance/delete/"+id;
                }
            });

            $(".btn-warning").click(function () {
                var id = $(this).attr("rel");
                $("#update_modal").modal({
                    show:true,
                    keyBoard:false,
                    backDrop:'static'
                })
                $.get("/insurance/update/"+id).done(function (data) {
                    $("#id").val(data.id);
                    $("#ins_name").val(data.insname);
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
