<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>病种设置</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="setting"/>
    </jsp:include>


    <div class="well well-sm container">
            <form class="form-inline" method="get">
                <input type="text" class="form-control" placeholder="请输入疾病名称" name="q_s_like_illname" value="${q_s_like_illname}">
                <select name="q_i_eq_office.id" id="" class="form-control">
                    <option value="">请选择科室</option>
                    <c:forEach items="${officeList}" var="office">
                        <option value="${office.id}" ${office.id==q_i_eq_office.id?'selected':''}>${office.officename}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-primary pull-right" type="submit"><i class="fa"></i> 搜索</button>
            </form>
        </div>

    <div class="box box-info container">
        <div class="box-header">
            <button class="btn btn-success pull-right" id="add_ill"><i class="fa fa-plus"></i> 新增病种</button>
            <h4>病种列表</h4>
        </div>
        <div class="box-body">
            <table class="table table-bordered">
                <thead>
                <tr class="success">
                    <th>疾病名称</th>
                    <th>所属科室</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.items}" var="ill">
                    <tr class="active">
                        <td>${ill.illname}</td>
                        <td>${ill.office.officename}</td>
                        <td>
                            <button class="btn btn-warning" rel="${ill.id}"><i class="fa fa-pencil-square-o"></i> 修改</button>
                            <button class="btn btn-danger" rel="${ill.id}"><i class="fa fa-trash"></i> 删除</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
       <div id="page" class="pull-right"></div>
    </div>

    <div class="modal fade" id="add_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增科室</h4>
                </div>
                <div class="modal-body">
                    <form id="add_form" action="/ill/add" method="post">
                        <label class="form-group">病种名称</label>
                        <input type="text" name="illname" class="form-control" id="illname">
                        <label class="form-group">所属科室</label>
                        <select name="office.id" class="form-control">
                            <c:forEach items="${officeList}" var="office">
                                <option value="${office.id}">${office.officename}</option>
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
                    <h4 class="modal-title">编辑病种</h4>
                </div>
                <div class="modal-body">
                    <form id="update_form" action="/ill/update" method="post">
                        <input type="hidden" name="id" id="id">
                        <label class="form-group">病种名称</label>
                        <input type="text" name="illname" class="form-control" id="ill_name">
                        <label class="form-group">所属科室</label>
                        <select name="office.id" class="form-control" id="office_id">
                            <c:forEach items="${officeList}" var="office">
                                <option value="${office.id}">${office.officename}</option>
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
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function () {
            $("#page").twbsPagination({
                totalPages: ${page.totalPage},
                visiblePages: 5,
                first : '首页',
                prev : '上一页',
                next: '下一页',
                last : '末页',
                href:'?p={{number}}&q_s_like_illname=${q_s_like_illname}$q_i_eq_office.id=${q_i_eq_office.id}'
            })

            $("#add_ill").click(function () {
                $("#add_form")[0].reset();
                $("#add_modal").modal({
                    show:true,
                    keyBoard:false,
                    backdrop:'static'
                })
            });
            $("#save_add").click(function () {
                if(!$("#illname").val()){
                    $("#illname").focus();
                    return;
                }
                $("#add_form").submit();
            });

            $(".btn-danger").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确认删除?")){
                    location.href = "/ill/delete/"+id;
                }
            });

            $(".btn-warning").click(function () {
                var id = $(this).attr("rel");
                $("#update_modal").modal({
                    show:true,
                    keyBoard:false,
                    backDrop:'static'
                })
                $.get("/ill/update/"+id).done(function (data) {
                    $("#id").val(data.id);
                    $("#ill_name").val(data.illname);
                    $("#office_id").val(data.office.id);
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
