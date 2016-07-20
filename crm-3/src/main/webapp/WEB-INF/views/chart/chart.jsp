<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>统计</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="chart"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                业绩统计
            </h1>
        </section>
        <section class="content">
            <div class="box box-default">
                <div class="box-body">
                    <div class="col-lg-4 col-xs-7">
                        <div class="small-box bg-yellow">
                            <div class="inner">
                                <h3>${add}</h3>
                                <p>新增客户数量</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-person-add"></i>
                            </div>
                            <a href="/customer" class="small-box-footer">更多信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xs-7">
                        <div class="small-box bg-aqua">
                            <div class="inner">
                                <h3>${salesed}</h3>
                                <p>交易完成的次数</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-pie-graph"></i>
                            </div>
                            <a href="/sales" class="small-box-footer">更多信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xs-7">
                        <div class="small-box bg-green">
                            <div class="inner">
                                <h3>${total.intValue()}</h3>
                                <p>交易金额</p>
                            </div>
                            <div class="icon">
                                <i class="ion ion-stats-bars"></i>
                            </div>
                            <a href="/sales" class="small-box-footer">更多信息 <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="box box-warning">
                <div class="box-body">
                    <div class="col-md-3">
                        <h3>客户状态表</h3>
                    </div>
                    <div id="main" style="width: 600px;height:400px;" class="col-md-9"></div>
                </div>
            </div>
            <div class="box box-warning">
                <div class="box-body">
                    <div class="col-md-3">
                        <h3>员工业绩表</h3>
                    </div>
                    <div id="chart" style="width: 600px;height:400px;" class="col-md-9"></div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/echarts.min.js"></script>
<script>
    $(function () {
        var myChart = echarts.init( $("#main")[0]);
        var userchart = echarts.init( $("#chart")[0]);
        userchart.setOption({
            tooltip: {},
            xAxis: {
                data: []
            },
            yAxis: {
            type : 'value'
        },
            series: [{
                name: '销售额',
                type: 'bar',
                data: []
            }]
        });
        $.get("/chart/user").done(function (data) {
            userchart.setOption({
                xAxis: {
                    data:data.listname
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '销售额',
                    data: data.listvalue
                }]
            });
        })
        myChart.setOption({
            tooltip: {},
            series : [
                {   name: '客户状态',
                    type: 'pie',
                    data:[],
                }
            ]
        });
        $.get("/chart/progress").done(function (data) {
            myChart.setOption({
                series:[{
                    name:"客户状态",
                    data:data
                }]
            })
        })
    })
</script>
</body>
</html>
