<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/dataTables.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <small><i class="fa fa-comments-o"></i></small>&nbsp;
                公告列表
                <shiro:hasRole name="经理">
                <a type="button" class="btn btn-success pull-right" href="/notice/new"><i class="fa fa-plus"></i> 新增公告</a>
                </shiro:hasRole>
            </h1>
        </section>
        <section class="content">
            <div class="box box-success">
                <div class="box-body">
                     <table class="table table-bordered" id="noticeTable">
                         <thead>
                             <tr>
                                 <th>公告标题</th>
                                 <th>发表时间</th>
                                 <th>发表人</th>
                             </tr>
                         </thead>
                         <tbody>
                         </tbody>
                     </table>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/jquery.dataTables.min.js"></script>
<script src="/static/bootstrap/js/dataTables.bootstrap.min.js"></script>
<script src="/static/bootstrap/js/moment.js"></script>
<script>
    $(function(){
        $("#noticeTable").DataTable({
            "serverSide":true,
            "ajax":"/notice/data",
            "ordering":false,
            "searching":false,
            "columns":[
                {"data":function(row){
                    return "<a href='/notice/view/"+row.id+"'>"+row.title+"</a>"
                }},
                {"data":function(row){
                    var time = moment(row.creattime).format("YYYY-MM-DD HH:mm");
                    return time;
                }},
                {"data":"realname"}
            ],
            "lengthMenu":[5,10,15,20],
            "autoWidth":false,
            "language":{
                "search":"搜索",
                "lengthMenu":"每页显示_MENU_条数据",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                },
                "info": "显示了第 _START_  条到第  _END_  条共  _TOTAL_ 条数据",
                "infoEmpty":      "没有数据！",
                "infoFiltered":   "(从总共 _MAX_条数据查询得来 )"
            }
        })
    })
</script>
</body>
</html>