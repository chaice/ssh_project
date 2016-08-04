<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据统计</title>
    <link rel="stylesheet" href="/static/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/css/daterangepicker.css">
    <jsp:include page="../include/head.jsp">
        <jsp:param name="menu" value="data"/>
    </jsp:include>

    <div class="well well-sm container">
        <form class="form-inline" method="get">
            <input type="hidden" name="q_s_ge_creattime" id="start">
            <input type="hidden" name="q_s_le_creattime" id="end">
            <input type="text" class="form-control" placeholder="选择时间段" value="" id="time">
            <button class="btn btn-primary pull-right" type="submit"><i class="fa"></i> 搜索</button>
        </form>
    </div>

    <div class="box box-info container">
        <div class="box-header">
            <h4>疾病统计</h4>
        </div>
        <div class="box-body">
            <body>
                <div id="main1" style="width: 700px;height:400px;" class="container"></div>
            </body>
        </div>
    </div>
    <div class="box box-danger container">
        <div class="box-header">
            <h4>科室统计</h4>
        </div>
        <div class="box-body">
            <body>
                <div id="main2" style="width: 600px;height:400px;"></div>
            </body>
        </div>
    </div>

<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/moment.min.js"></script>
<script src="/static/js/moment-with-locales.js"></script>
<script src="/static/js/daterangepicker.js"></script>
<script src="/static/js/echarts.min.js"></script>
<script>
    $(function () {
        moment.locale("zh-cn");
        $("#start").val(moment().startOf('month').format('YYYY-MM-DD'));
        $("#end").val(moment().endOf('month').format('YYYY-MM-DD'));
        $("#time").daterangepicker({
                    format: 'YYYY-MM-DD',
                    ranges: {
                        '今天': [moment(), moment()],
                        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                        '最近七天': [moment().subtract(6, 'days'), moment()],
                        '最近三十天': [moment().subtract(29, 'days'), moment()],
                        '本月': [moment().startOf('month'), moment().endOf('month')],
                        '上一个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                    },
                    autoUpdateInput: false
                },
        function(start, end) {
              var starttime = start.format('YYYY-MM-DD');
              var endtime = end.format('YYYY-MM-DD');
              $("#time").attr("placeholder",starttime+"到"+endtime);
              $("#start").val(starttime);
              $("#end").val(endtime);
        });
        echarts.init($('#main1')[0]).setOption({
            color:["#dfsdjf"],
            tooltip: {},
            xAxis : [
                {
                  data : [],
                  axisTick: {
                  alignWithLabel: true
                  }
                }
            ],
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            yAxis :{},
            series : [
                {
                 name:'疾病数量',
                 type:'bar',
                 barWidth: '60%',
                 data:[]
                }
            ]
        });
        $.get("/data/ill.json").done(function (data) {
            myChart.setOption({
                xAxis: {
                    data: data.categories
                },
                series: [{
                    // 根据名字对应到相应的系列
                    name: '销量',
                    data: data.data
                }]
            });
        }).fail(function () {
            alert("数据获取异常!")
        })
        echarts.init($('#main2')[0]).setOption({

        });
    })
</script>
</body>
</html>
