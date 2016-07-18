<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>待办事项</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/colorpicker/dist/css/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/fullcalendar.css">
    <link rel="stylesheet" href="/static/datetimepicker/css/bootstrap-datetimepicker.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="task"/>
    </jsp:include>
    <div class="content-wrapper container-fluid">
        <section class="content col-lg-8 col-md-7">
            <div class="box box-warning">
                <div class="box-body">
                    <div id="calendar"></div>
                </div>
            </div>
            <div class="modal fade" id="taskmodal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增事项</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/task/add_event" id="addform">
                                <div class="form-group">
                                    <label class="form-group">主题</label>
                                    <input type="text" class="form-control" name="title" id="title">
                                </div>
                                <div class="form-group">
                                    <label class="form-group">开始时间</label>
                                    <div class="date form_datetime">
                                        <input class="form-control" type="text" id="start" name="start">
                                        <span class="add-on"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="form-group">结束时间</label>
                                    <div class="input-append date form_datetime">
                                        <input class="form-control" type="text" id="end" name="end">
                                        <span class="add-on"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="form-group">颜色</label>
                                    <input class="form-control colorpicker-element" type="text" id="color" name="color">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="savebtn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="updatemodal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">确认事项</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/task/add_event" id="updateform">
                                <input type="hidden" id="id" name="id">
                                <div class="form-group">
                                    <label class="form-group">主题</label>
                                    <div id="task_title"></div>
                                </div>
                                <div class="form-group">
                                    <label class="form-group">时间</label>
                                    <div id="task_time"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-danger" id="delete">删除</button>
                            <button type="button" class="btn btn-primary" id="finish">完成</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="content col-lg-4">
            <div class="box box-default">
                <div class="box-header">
                    <h4>延期事物</h4>
                </div>
                <div class="box-body">

                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/moment.js"></script>
<script src="/static/bootstrap/js/fullcalendar.min.js"></script>
<script src="/static/bootstrap/js/daterangepicker.js"></script>
<script src="/static/colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<script src="/static/bootstrap/js/zh-cn.js"></script>
<script src="/static/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    $(function () {
        var _event = null;
        $("#color").colorpicker();
        var calendar = $("#calendar");
        $(".form_datetime").datetimepicker({
            format:"yyyy-mm-dd",
            autoclose: true,
            minView:"month"
        });
        calendar.fullCalendar({
            dayClick:function (date) {
                $("#addform")[0].reset();
                $("#taskmodal").modal({
                    show:true,
                    backdrop:"static",
                    keyBroad:false
                });
                $("#start").val(date.format());
                $("#end").val(date.format());
            },
            header:{
                left:"prev,next today",
                center:"title,timelineFourDays",
                right:"month,agendaWeek,agendaDay"
            },
               events:"/task/event",
               aspectRatio: 1.47,
               eventLimit: true,
               views: {
                   agenda:{
                       eventLimit: 5
                    }
               },
            eventClick:function (event) {
                _event = event;
              $("#updatemodal").modal({
                  show:true,
                  backdrop:"static",
                  keyBorad:false
              });
               $.get("/task/"+event.id).done(function (result) {
                   $("#id").val(result.id);
                   $("#task_title").text(result.title)
                   $("#task_time").text(result.start+"--"+result.end);
               }).fail(function () {
                   alert("数据请求异常!")
               });
            }
        });
        $("#savebtn").click(function () {
            if(!$("#title").val()){
                $("#title").focus();
                return;
            }
            if(moment($("#start").val()).isAfter(moment($("#end").val()))){
                alert("结束时间必须大于开始时间!");
                return;
            }
            $.post("/task/add_event",$("#addform").serialize()).done(function (result) {
                calendar.fullCalendar("renderEvent",result);
                $("#taskmodal").modal("hide");
            }).fail(function () {
                alert("数据异常!");
            })
        });
        $("#delete").click(function () {
            if(confirm("确认删除该事项?")){
                var id = $("#id").val();
               $.get("/task/delete/"+id).done(function (result) {
                   if(result == "success"){
                       calendar.fullCalendar("removeEvents",id);
                       $("#updatemodal").modal("hide");
                   }
               }).fail(function () {
                   alert("数据异常!")
               })
            }
        });
        $("#finish").click(function () {
            var id = $("#id").val();
            $.get("/task/finish/"+id).done(function (result) {
                if(result == "success"){
                    $("#updateMoadl").modal("hide");
                    _event.color="#cccccc";
                    calendar.fullCalendar("updateEvent",_event);
                    $("#updatemodal").modal("hide");
                }
            }).fail(function () {
                alert("数据异常!")
            })
        })
    })
</script>
</body>
</html>
