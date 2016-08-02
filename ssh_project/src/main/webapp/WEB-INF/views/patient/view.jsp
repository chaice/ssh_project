<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>患者信息</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="patient"/>
    </jsp:include>

    <div class="box box-primary container">
        <div class="box-header">
            <button class="btn btn-warning pull-right" rel="${patient.id}"><i class="fa fa-pencil-square-o"></i> 修改</button>
            <button class="btn btn-danger pull-right" rel="${patient.id}"><i class="fa fa-trash"></i> 删除</button>
            <h4><i class="fa fa-user"></i> 患者信息</h4>
        </div>
        <div class="box-body">
            <table class="table">
                <tr>
                    <th style="width: 100px">姓名</th>
                    <td style="width: 100px">${patient.patientname}</td>
                    <th style="width: 100px">性别</th>
                    <td style="width: 100px">${patient.sex.sexname}</td>
                    <th style="width: 100px">年龄</th>
                    <td style="width: 100px">${patient.age}</td>
                </tr>
                <tr>
                    <th style="width: 100px">身份证号</th>
                    <td style="width: 100px">${patient.idnumber}</td>
                    <th style="width: 100px">联系电话</th>
                    <td style="width: 100px">${patient.telnum}</td>
                    <th style="width: 100px">医保类型</th>
                    <td style="width: 120px">${patient.insurance.insname}</td>
                </tr>
                <tr>
                    <th style="width: 100px">地址</th>
                    <td colspan="5">${patient.address}</td>
                </tr>
                <tr>
                    <th colspan="6">过敏史</th>
                </tr>
                <tr>
                    <td colspan="6">${patient.history}</td>
                </tr>
                <tr>
                    <th colspan="6">备注</th>
                </tr>
                <tr>
                    <td colspan="6">${patient.note}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="modal fade" id="update_modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑患者信息</h4>
                </div>
                <div class="modal-body">
                    <form id="update_form" action="/patient/update" method="post">
                        <input type="hidden" name="id" value="${patient.id}">
                        <label class="form-group">患者姓名</label>
                        <input type="text" name="patientname" class="form-control" value="${patient.patientname}">
                        <label class="form-group">性别</label>
                        <select name="sex.id" class="form-control">
                            <c:forEach items="${sexList}" var="sex">
                                <option value="${sex.id}" ${sex.id == patient.sex.id?'selected':''}>${sex.sexname}</option>
                            </c:forEach>
                        </select>
                        <label class="form-group">身份证号码</label>
                        <input type="text" name="idnumber" class="form-control" value="${patient.idnumber}">
                        <label class="form-group">电话号码</label>
                        <input type="text" name="telnum" class="form-control" value="${patient.telnum}">
                        <label class="form-group">地址</label>
                        <input type="text" name="address" class="form-control" value="${patient.address}">
                        <label class="form-group">状态</label>
                        <select name="state.id" class="form-control" id="state">
                            <c:forEach items="${stateList}" var="state">
                                <option value="${state.id}" ${state.id == patient.state.id?'selected':''}>${state.statename}</option>
                            </c:forEach>
                        </select>
                        <label class="form-group">医保类型</label>
                        <select name="insurance.id" class="form-control" id="insurance">
                            <c:forEach items="${insList}" var="ins">
                                <option value="${ins.id}" ${ins.id == patient.insurance.id?'selected':''}>${ins.insname}</option>
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
    <div class="box box-warning container">
        <div class="box-header">
            <h4><i class="fa fa-stethoscope"></i> 就诊记录</h4>
        </div>
        <div class="box-body">
            <table class="table">
                <tr>
                    <th>日期</th>
                    <th>科室</th>
                    <th>病种</th>
                    <th>初步诊断</th>
                    <th>详细记录</th>
                </tr>
                <c:forEach items="${recordList}" var="record">
                    <tr>
                        <td><fmt:formatDate value="${record.creattime}" pattern="y-MM-dd"></fmt:formatDate></td>
                        <td>${record.office.officename}</td>
                        <td>${record.ill.illname}</td>
                        <td>${record.initlog}</td>
                        <td><a href="/record/view/${record.id}">详情</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script>
        $(function () {
            $(".btn-danger").click(function () {
                var id = $(this).attr("rel");
                if(confirm("确认删除?")){
                    location.href = "/patient/delete/"+id;
                }
            });
            $(".btn-warning").click(function () {
                var id = $(this).attr("rel");
                $("#update_modal").modal({
                    show:true,
                    keyBoard:false,
                    backDrop:'static'
                })
            });
            $("#save_update").click(function () {
                $("#update_form").submit();
            })
        })
    </script>
</body>
</html>
