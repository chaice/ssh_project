<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>销售机会</title>
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
        <jsp:param name="menu" value="sales"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">
           <h1>机会列表</h1>
        </section>
        <section class="content">
            <div class="box box-warning collapsed-box">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-hand-o-down"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form action="">
                        <span class="col-lg-2">
                            <label class="control-label">请选择查询条件  <i class="fa fa-arrow-right"></i></label>
                        </span>
                        <span class="col-lg-2">
                            <input type="text" name="name" placeholder="请输入机会名!" class="form-control">
                        </span>
                        <span class="col-lg-2">
                            <select name="progress" class="form-control">
                                <option value="">请选择进度</option>
                                <option value="初次接触">初次接触</option>
                                <option value="确认意向">确认意向</option>
                                <option value="提供合同">提供合同</option>
                                <option value="交易搁浅">交易搁浅</option>
                                <option value="交易完成">交易完成</option>
                                <option value="交易失败">交易失败</option>
                            </select>
                        </span>
                        <span class="col-lg-3">
                            <button type="button" class="btn btn-default pull-left form-control">
                                <span>
                                    <i class="fa fa-calendar"></i> 请选择日期
                                </span>
                                    <i class="fa fa-caret-down  pull-right"></i>
                            </button>
                        </span>
                        <button class="btn btn-info pull-right"><i class="fa fa-search"></i> 查找</button>
                    </form>
                </div>
            </div>
            <div class="box box-success">
                <div class="box-header">
                    <button class="btn btn-primary pull-right" id="newChance"><i class="fa fa-plus"></i> 新增机会</button>
                </div>
                <div class="box-body">
                    <table class="table" id="salestable">
                        <thead>
                           <tr>
                               <th>名字</th>
                               <th>关联客户</th>
                               <th>价值</th>
                               <th>进度</th>
                               <th>最后跟进时间</th>
                               <th>操作</th>
                           </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="addmoadl">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增机会</h4>
                        </div>
                        <div class="modal-body">
                            <form id="addform">
                                <div class="form-group">
                                    <label class="control-label">机会名</label>
                                    <input type="text" class="form-control" name="name">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">交易价值</label>
                                    <input class="form-control" type="text" name="value">
                                </div>
                                <div class="form-group">
                                    <label >关联客户</label>
                                    <select name="customerid" id="select" class="form-control">
                                        <c:forEach items="${cuslist}" var="cus">
                                            <option value="${cus.id}">${cus.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">进度</label>
                                    <select name="progress" class="form-control">
                                        <option value="初次接触">初次接触</option>
                                        <option value="确认意向">确认意向</option>
                                        <option value="提供合同">提供合同</option>
                                        <option value="交易搁浅">交易搁浅</option>
                                        <option value="交易完成">交易完成</option>
                                        <option value="交易失败">交易失败</option>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="saveadd">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/bootstrap/js/jquery.validate.min.js"></script>
<script src="/static/bootstrap/js/jquery.dataTables.min.js"></script>
<script src="/static/bootstrap/js/dataTables.bootstrap.min.js"></script>
<script>
    $(function () {
       var dataTable = $("#salestable").DataTable({
              serverSide:true,
              ajax:"/sales/saleslist",
              columns:[
                  {"data":function (row) {
                      return "<i class='fa fa-server'></i> <a href='/sales/view/"+row.id+"'>"+row.name+"</a>";
                  }},
                  {"data":function (row) {
                      return "<a href='/customer/view/"+row.customerid+"'>"+row.customername+"</a>";
                  }},
                  {"data":function (row) {
                      return "<b style='color: #e08e0b'><i class='fa fa-cny'></i></b>"+row.value
                  }},
                  {"data":function (row) {
                      if(row.progress == "交易失败"){
                          return "<a class='btn btn-danger'>"+row.progress+"</a>"
                      }else if(row.progress == "交易成功"){
                          return "<a class='btn btn-success'>"+row.progress+"</a>"
                      }else if(row.progress == "初次接触"){
                          return "<a class='btn btn-default'>"+row.progress+"</a>"
                      }else if(row.progress == "交易搁浅"){
                          return "<a class='btn btn-warning'>"+row.progress+"</a>"
                      }else if(row.progress == "确认意向") {
                          return "<a class='btn btn-primary'>"+row.progress+"</a>"
                      }else if(row.progress == "提供合同") {
                          return "<a class='btn btn-github'>"+row.progress+"</a>"
                      } else{
                          return "<a class='btn btn-info'>"+row.progress+"</a>"
                      }
                  }},
                  {"data":"lasttime"},
                  {"data":function (row) {
                      return "<button class='btn btn-warning' rel='"+row.id+"'>编辑</button>"<shiro:hasRole name="经理">+"&nbsp;<button class='btn btn-danger' rel='"+row.id+"'>删除</button>"</shiro:hasRole>
                  }}
              ],
           searching:false,
           ordering:false,
           lengthMenu:[5,10,15,20],
           autoWidth:false,
           language:{
               "search":"搜索",
               "lengthMenu":"每页显示_MENU_条数据",
               "emptyTable":   "没有数据!",
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
        });
        $("#newChance").click(function () {
            $("#addform")[0].reset();
            $("#addmoadl").modal({
                show:true,
                backdrop:"static",
                keyBroad:false
            })
        });
        $("#saveadd").click(function () {
            $("#addform").submit();
        });
        $("#addform").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                name:{required:true},
                value:{required:true}
            },
            messages:{
                name:{required:"请输入机会名!"},
                value:{required:"请输入价值!"}
            },
            submitHandler:function (form) {
                $.post("/sales/new",$(form).serialize()).done(function (data) {
                    if(data == "success"){
                        $("#addmoadl").modal("hide");
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("数据异常!")
                })
            }
        });
        $(document).delegate(".btn-danger","click",function () {
            var id = $(this).attr("rel");
            if(confirm("是否删除该机会?")){
                $.get("/sales/delete/"+id).done(function (data) {
                    if(data == "success"){
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("数据异常!")
                })
            }
        });
    })
</script>
</body>
</html>
