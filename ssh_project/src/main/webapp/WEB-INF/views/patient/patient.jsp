<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>病人档案</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/simditor/styles/simditor.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="patient"/>
    </jsp:include>
    <div class="well well-sm container">
            <form class="form-inline" method="get">
                <input type="text" class="form-control" placeholder="请输入患者姓名" name="q_s_like_patientname" value="${q_s_like_patientname}">
                <input type="text" class="form-control" placeholder="请输入身份证号" name="q_s_like_idnumber" value="${q_s_like_idnumber}">
                <input type="text" class="form-control" placeholder="请输入电话" name="q_s_like_telnum" value="${q_s_like_telnum}">
                <button class="btn btn-primary pull-right" type="submit"><i class="fa"></i> 搜索</button>
            </form>
        </div>

    <div class="box box-info container">
            <div class="box-header">
                <button class="btn btn-success pull-right" id="add_patient"><i class="fa fa-plus"></i> 新增患者档案</button>
                <h4>患者档案列表</h4>
            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <tr class="success">
                        <th>患者姓名</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>电话</th>
                        <th>医保类型</th>
                        <th>地址</th>
                        <th>状态</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.items}" var="patient">
                        <tr class="active">
                            <td><a href="/patient/view/${patient.id}">${patient.patientname}</a></td>
                            <td>${patient.sex.sexname}</td>
                            <td>${patient.age}</td>
                            <td>${patient.telnum}</td>
                            <td>${patient.insurance.insname}</td>
                            <td>${patient.address}</td>
                            <td>${patient.state.statename}</td>
                            <td>
                                <fmt:formatDate value="${patient.creattime}" pattern="y-M-d"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <span id="patient_page" class="pull-right"></span>
        </div>

    <div class="modal fade" id="add_patient_modal">
            <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增患者档案</h4>
                </div>
                <div class="modal-body">
                    <form id="add_patient_form" action="/patient/add" method="post">
                        <input type="hidden" name="state.id" value="1">
                        <label class="form-group">患者姓名</label>
                        <input type="text" name="patientname" class="form-control">
                        <label class="form-group">性别</label>
                        <select name="sex.id" class="form-control">
                            <c:forEach items="${sexList}" var="sex">
                                <option value="${sex.id}">${sex.sexname}</option>
                            </c:forEach>
                        </select>
                        <label class="form-group">身份证号码</label>
                        <input type="text" name="idnumber" class="form-control">
                        <label class="form-group">电话号码</label>
                        <input type="text" name="telnum" class="form-control">
                        <label class="form-group">地址</label>
                        <input type="text" name="address" class="form-control">
                        <label class="form-group">医保类型</label>
                        <select name="insurance.id" class="form-control">
                            <c:forEach items="${insList}" var="ins">
                                <option value="${ins.id}">${ins.insname}</option>
                            </c:forEach>
                        </select>
                        <label class="form-group">过敏史</label>
                        <textarea class="form-control" id="history" name="history"></textarea>
                        <label class="form-group">备注</label>
                        <textarea class="form-control" id="note" name="note"></textarea>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">取消</button>
                    <button class="btn btn-primary" id="save_patient_add">保存</button>
                </div>
            </div>
        </div>
        </div>

    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/simditor/scripts/module.min.js"></script>
    <script src="/static/simditor/scripts/hotkeys.min.js"></script>
    <script src="/static/simditor/scripts/uploader.min.js"></script>
    <script src="/static/simditor/scripts/simditor.min.js"></script>
    <script>
        $(function () {
            $("#patient_page").twbsPagination({
                totalPages:${page.totalPage},
                visiblePages: 5,
                first : '首页',
                prev : '上一页',
                next: '下一页',
                last : '末页',
                href:'?p={{number}}'
            })
            $("#add_patient").click(function () {
                $("#add_patient_form")[0].reset();
                $("#add_patient_modal").modal({
                    show:true,
                    keyBoard:false,
                    backdrop:'static'
                })
            });
            $("#save_patient_add").click(function () {
                $("#add_patient_form").submit();
//               $("#add_patient_form").validate({
//                   errorElement:"div",
//                   errorClass:"text-danger",
//                   rules:{
//                       patientname:{required:"true"}
//                   },
//                   messages:{
//                       patientname:{required:"请输入患者姓名"}
//                   }
//               })
            });
            var editor1 = new Simditor({
                toolbar:['title', 'bold', 'italic','underline','strikethrough','ol','ul','blockquote','table','link','hr'],
                textarea: $('#history')
            });
            var editor2 = new Simditor({
                toolbar:['title', 'bold', 'italic','underline','strikethrough','ol','ul','blockquote','table','link','hr'],
                textarea: $('#note')
            });



        })
    </script>
</body>
</html>
