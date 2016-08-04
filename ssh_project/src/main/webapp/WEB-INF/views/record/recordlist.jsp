<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>就诊记录</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/css/daterangepicker.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="record"/>
    </jsp:include>
    <div class="well well-sm container">
        <form class="form-inline" method="get" action="/record/list">
            <input type="text" class="form-control" placeholder="请输入患者姓名" name="q_s_like_patient.patientname" value="${requestScope['q_s_like_patient.patientname']}">
            <input type="text" class="form-control" placeholder="请输入电话" name="q_s_like_patient.telnum" value="${requestScope['q_s_like_patient.telnum']}">
            <input type="text" class="form-control" placeholder="就诊时间"  id="time">
            <input type="hidden" name="q_d_ge_creattime" id="start">
            <input type="hidden" name="q_d_le_creattime" id="end">
            <select name= "q_i_eq_patient.state.id" class="form-control">
                <option value="">请选择患者状态</option>
                <option value="1" ${1==q_i_eq_patient.state.id?'selected':''}>新建</option>
                <option value="2" ${2==q_i_eq_patient.state.id?'selected':''}>在诊</option>
                <option value="3" ${3==q_i_eq_patient.state.id?'selected':''}>已出院</option>
            </select>
            <button class="btn btn-primary pull-right" type="submit"><i class="fa"></i> 搜索</button>
        </form>
    </div>

    <div class="box box-info container">
        <div class="box-header">
            <a class="btn btn-success pull-right" href="/record"><i class="fa fa-plus"></i> 新增患者档案</a>
            <h4>就诊列表</h4>
        </div>
        <div class="box-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>科室</th>
                        <th>病种</th>
                        <th>初步诊断</th>
                        <th>状态</th>
                        <th>创建时间</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${page.items}" var="record">
                        <tr>
                            <td>${record.patient.patientname}</td>
                            <td>${record.patient.sex.sexname}</td>
                            <td>${record.office.officename}</td>
                            <td>${record.ill.illname}</td>
                            <td>${record.initlog}</td>
                            <td>${record.patient.state.statename}</td>
                            <td><fmt:formatDate value="${record.creattime}" pattern="y-MM-dd"></fmt:formatDate></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <span id="page" class="pull-right"></span>
        </div>
    </div>

    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script src="/static/js/moment.min.js"></script>
    <script src="/static/js/moment-with-locales.js"></script>
    <script src="/static/js/daterangepicker.js"></script>
    <script>
        $(function () {
            moment.locale("zh-cn");
            $("#page").twbsPagination({
                totalPages:${page.totalPage == 0?page.totalPage:1},
                visiblePages: 5,
                first : '首页',
                prev : '上一页',
                next: '下一页',
                last : '末页',
                href:'?p={{number}}'
            });
            $("#time").daterangepicker({
                format: 'YYYY-MM-DD',
                ranges:{
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '最近七天': [moment().subtract(6, 'days'), moment()],
                    '最近三十天': [moment().subtract(29, 'days'), moment()],
                    '本月': [moment().startOf('month'), moment().endOf('month')],
                    '上一个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                autoUpdateInput:false
            },
            function(start, end) {
                var starttime = start.format('YYYY-MM-DD');
                var endtime = end.format('YYYY-MM-DD');
                $("#time").attr("placeholder",starttime+"到"+endtime)
                $("#start").val(starttime);
                $("#end").val(endtime);
            });

        })
    </script>

    </body>
</html>
