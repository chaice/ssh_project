<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/fullcalendar.css">
    <link rel="stylesheet" href="/static/datepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/colorpicker/dist/css/bootstrap-colorpicker.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="task"/>
    </jsp:include>
    <div class="content-wrapper">

        <section class="content-header">
            <h1>

            </h1>
        </section>
        <section class="content col-md-8">
            <div class="box box-success">
                <div class="box-body">
                    <div id="calender"></div>
                </div>
            </div>
            <div class="modal fade" id="add_modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增事项</h4>
                        </div>
                        <div class="modal-body">
                            <form id="add_form">
                                <div class="form-group">
                                    <label class="form-group">内容</label>
                                    <input type="text" class="form-control" name="title" id="add_title">
                                </div>
                                <div class="form-group">
                                    <label class="form-group">开始日期</label>
                                    <input type="text" class="form-control datetimepicker" name="start" id="start">
                                </div>
                                <div class="form-group">
                                    <label class="form-group">结束日期</label>
                                    <input type="text" class="form-control datetimepicker" name="end" id="end">
                                </div>
                                <div class="form-group">
                                        <label class="form-group">提醒时间&nbsp;</label>
                                        <select name="hours" id="hours" style="width: 110px;height: 35px">
                                        </select>&nbsp;点&nbsp;
                                        <select name="mint" id="mint" style="width: 110px;height: 35px">
                                        </select>&nbsp;分
                                </div>
                                <div class="form-group">
                                    <label class="form-group">选择显示颜色</label>
                                    <input type="text" class="form-control" name="color" id="color">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="add_event">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="deal_modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增事项</h4>
                        </div>
                        <div class="modal-body">
                            <form id="deal-form">
                                <input type="hidden" id="id" name="id">
                                <div class="form-group">
                                    <label class="form-group">内容</label>
                                    <div id="deal_title"></div>
                                </div>
                                <div class="form-group">
                                    <label class="form-group">日期</label>
                                    <div id="deal_day"></div>
                                </div>
                                <div class="form-group">
                                    <label class="form-group">提醒时间&nbsp;</label>
                                    <div id="deal_time"></div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer" id="button">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-warning" id="delete">删除</button>
                            <button type='button' class='btn btn-success' id='finish'>完成</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="content col-md-4">
            <div class="box box-default">
                <div class="box-header">
                    <h3>已延期事项</h3><hr>
                </div>
                <div class="box-body">
                    <ul style="list-style: none" id="list">

                    </ul>
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
<script src="/static/bootstrap/js/zh-cn.js"></script>
<script src="/static/datepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/static/colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<script>
    $(function () {
        $("#color").colorpicker();
        var calender = $("#calender");
        var eve = null;
        $(".datetimepicker").datetimepicker({
            minView:"month",
            format:"yyyy-mm-dd",
            todayBtn:true,
            autoclose:true,
            language:"zh-CN",
            showMeridian:true
        });
        calender.fullCalendar({
            header:{
                left:"prev,next today",
                center:"title",
                right:""
            },
            events:"/task/event",
            dayClick:function (date) {
                $("#add_form")[0].reset();
                $("#add_modal").modal({
                    show:true,
                    backdrop:"static",
                    keyBroad:false
                })
               $("#start").val(date.format())
            },
            eventClick:function (event,view) {
                eve = event;
                var id = event.id;
                $("#deal_modal").modal({
                    show:true,
                    backdrop:"static",
                    keyBoard:false
                })
                $.get("/task/"+id).done(function (data) {
                    if(data.message == "success"){
                        $("#id").val(data.data.id);
                        $("#deal_title").text(data.data.title);
                        $("#deal_day").text(data.data.start+"---"+data.data.end);
                        $("#deal_time").text(data.data.remindtime);
                        if(data.data.done){
                            $("#finish").remove();
                        }
                    }
                }).fail(function () {
                    alert("数据获取异常!")
                })
            }
        });
        for(var i = 0;i<24;i++){
            $("#hours").html($("#hours").html()+"<option value='"+i+"'>"+i+"</option>");
        };
        for(var i = 0;i<60;i++){
            $("#mint").html($("#mint").html()+"<option value='"+i+"'>"+i+"</option>");
        };
        $("#add_event").click(function () {
            if(!$("#add_title").val()){
                $("#add_title").focus();
                return;
            }
            if(moment($("#start").val()).isAfter(moment($("#end").val()))){
                alert("结束时间必须大于结束时间!");
                return;
            }
            $.post("/task/add_event",$("#add_form").serialize()).done(function(data) {
                if(data.message == "success"){
                    $("#add_modal").modal("hide");
                    calender.fullCalendar("renderEvent",data.data);
                }
            }).fail(function () {
                alert("数据异常!")
            })
        });
        $("#delete").click(function () {
            var id = $("#id").val();
            if(confirm("是否删除该事项?")){
                $.get("/task/delete/"+id).done(function (data) {
                    if(data == "success"){
                        $("#deal_modal").modal("hide");
                        calender.fullCalendar("removeEvents",id)
                    }
                }).fail(function () {
                    alert("数据获取异常!")
                })
            }
        });
        $("#finish").click(function () {
            var id = $("#id").val();
            $.get("/task/finish/"+id).done(function (data) {
                if(data == "success"){
                    $("#deal_modal").modal("hide");
                    console.log(eve);
                    eve.color = "#cccccc";
                    eve.title = eve.title +"(已完成)"
                    calender.fullCalendar("updateEvent",eve);
                }
            }).fail(function () {
                alert("数据异常!")
            })
        });
          var finished = $.get("/task/finished").done(function (data) {
                if(data.message == "success"){
                    var list = data.list;
                        for(var i = 0;i<list.length;i++){
                            $("#list").append("<li>"+list[i].title+"<a href='/task/dele/"+list[i].id+"'><i class='fa fa-trash pull-right'></i></a></li>");
                        }
                }
            }).fail(function () {
               alert("数据获取异常!")
            })
    })
</script>
</body>
</html>
