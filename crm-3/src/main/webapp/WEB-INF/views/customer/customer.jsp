<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户管理</title>
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
        <jsp:param name="menu" value="customer"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户列表
            </h1>
        </section>
        <section class="content">
            <div class="box box-success">
                <div class="box-header">
                    <button class="btn btn-success pull-right" id="new-customer"><i class="fa fa-plus"></i> 新增客户</button>
                </div>
                <div class="box-body">
                    <table id="customer_list" class="table">
                        <thead>
                           <tr>
                               <th>客户名</th>
                               <th>电话</th>
                               <th>电子邮件</th>
                               <th>微信号</th>
                               <th>地址</th>
                               <th>客户等级</th>
                               <th>创建时间</th>
                               <th>操作</th>
                           </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="updateMoadl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h3 class="modal-title">新增客户</h3>
                        </div>
                        <div class="modal-body">
                            <form id="updateForm">
                                <input type="hidden" id="id" name="id">
                                <input type="hidden" id="type" name="type">
                                <div class="form-group">
                                    <label class="control-label">客户名</label>
                                    <input type="text" class="form-control" name="name" id="name">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">电话</label>
                                    <input type="text" class="form-control" name="tel" id="tel">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">email</label>
                                    <input type="email" class="form-control" name="email" id="email">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">微信号</label>
                                    <input type="text" class="form-control" name="weixin" id="weixin">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">客户等级</label>
                                    <div>
                                        <select name="rank" class="form-control" id="rank">
                                            <option value=""></option>
                                            <option value="★">★</option>
                                            <option value="★★">★★</option>
                                            <option value="★★★">★★★</option>
                                            <option value="★★★★">★★★★</option>
                                            <option value="★★★★★">★★★★★</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">地址</label>
                                    <input type="text" class="form-control" name="address" id="address">
                                </div>
                                <div class="form-group" id="blongto" style="visibility: hidden;">
                                    <label class="control-label">所属公司</label>
                                    <select name="companyid" class="form-control" id="companyid">
                                        <option value=""></option>
                                        <c:forEach items="${companyList}" var="company">
                                            <option value="${company.id}" ${companid == company.id?'select':''}>${company.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="update-btn">保存</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="customerMoadl" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h3 class="modal-title">新增客户</h3>
                        </div>
                        <div class="modal-body">
                            <form id="newForm">
                                <div class="form-group">
                                    <label class="control-label">客户类型</label>
                                    <div>
                                        <input type="radio" value="person" name="type" id="person" checked>个人
                                        <input type="radio" value="company" name="type" id="company">公司
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">客户名</label>
                                    <input type="text" class="form-control" name="name">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">电话</label>
                                    <input type="text" class="form-control" name="tel">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">email</label>
                                    <input type="email" class="form-control" name="email">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">微信号</label>
                                    <input type="text" class="form-control" name="weixin">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">客户等级</label>
                                    <div>
                                        <select name="rank" class="form-control">
                                            <option value=""></option>
                                            <option value="★">★</option>
                                            <option value="★★">★★</option>
                                            <option value="★★★">★★★</option>
                                            <option value="★★★★">★★★★</option>
                                            <option value="★★★★★">★★★★★</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">地址</label>
                                    <input type="text" class="form-control" name="address">
                                </div>
                                <div class="form-group" id="belong">
                                    <label class="control-label">所属公司</label>
                                    <select name="companyid" class="form-control">
                                        <option value=""></option>
                                        <c:forEach items="${companyList}" var="company">
                                            <option value="${company.id}">${company.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="save-btn">保存</button>
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
<script src="/static/bootstrap/js/moment.js"></script>
<script>
    $(function(){
        var dataTable =$("#customer_list").DataTable({
           "serverSide":true,
            "ajax":"/customer/list",
            "ordering":false,
            "columns":[
                {"data":function(row){
                    if(row.type =="company"){
                       return "<i class='fa fa-building-o'></i> <a href='/customer/view/"+row.id+"'>"+row.name+"</a>"
                    }else{
                        if(row.companyid == null){
                            return "<i class='fa fa-user'></i> <a href='/customer/view/"+row.id+"'>"+row.name+"</a>"
                        }else{
                            return "<i class='fa fa-user'></i> <a href='/customer/view/"+row.id+"'>"+row.name+"</a>"+ "&nbsp;"+row.companyname
                        }
                    }
                }},
                {"data":"tel"},
                {"data":"email"},
                {"data":"weixin"},
                {"data":"address"},
                {"data":function(row){
                   return "<span style='color: darkorange'>"+row.rank+"<span>"
                }},
                {"data":function(row){
                    var time = moment(row.creattime).format("YYYY-MM-DD HH:mm");
                    return time;
                }},
                {"data":function(row){
                    return "<button class='update btn btn-warning' rel='"+row.id+"'>修改</button> " <shiro:hasRole name="经理">+"<button class='delete btn btn-danger' rel='"+row.id+"'>删除</button>"</shiro:hasRole>
                }}],
            "lengthMenu":[5,10,15,20],
            "autoWidth":false,
            "language":{
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
        $("#new-customer").click(function(){
            $("#newForm")[0].reset();
            $("#customerMoadl").modal({
                "show":true,
                "keyBoard":false,
                "backdrop":"static"
            });
        });
        $("#company").click(function(){
            $(this)[0].check;
            $("#belong")[0].setAttribute("style","visibility:hidden");
        });
        $("#person").click(function(){
            $(this)[0].check;
            $("#belong")[0].setAttribute("style","visibility:visible");
        });
        $("#save-btn").click(function(){
            $("#newForm").submit();
        });
        $("#newForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                "name":{
                    required:true
                },
                "tel":{
                    required:true
                }
            },
            messages:{
                "name":{
                    required:"请输入客户名!"
                },
                "tel":{
                    required:"请输入联系电话!"
                }
            },
            submitHandler:function(form){
                $.post("/customer/new",$(form).serialize()).done(function(data){
                        if(data == "success"){
                            $("#customerMoadl").modal("hide");
                            location.reload();
                        }
                }).fail(function(){
                    alert("数据异常!")
                })
            }
        });
        $(document).delegate(".delete","click",function () {
            var id = $(this).attr("rel");
            if(confirm("是否要删除?")){
                location.href = "/customer/delete/"+id;
            }
            dataTable.ajax.reload();
        })
        $(document).delegate(".update","click",function () {
            var id = $(this).attr("rel");
            $("#updateMoadl").modal({
                show:true,
                backdrop:"static",
                keyBoard:false
            });
            $.get("/customer/update/"+id).done(function (data) {
                $("#id").val(data.id);
                $("#name").val(data.name);
                $("#tel").val(data.tel);
                $("#email").val(data.email);
                $("#weixin").val(data.weixin);
                $("#rank").val(data.rank);
                $("#address").val(data.address);
                $("#companyid").val(data.companyid);
                $("#type").val(data.type);
                if(data.type == "person"){
                    $("#blongto")[0].removeAttribute("style");
                }else{
                    $("#blongto")[0].setAttribute("style","visibility: hidden");
                }
            })
        })
        $("#update-btn").click(function () {
            $("#updateForm").submit();
        });
        $("#updateForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                "name":{
                    required:true
                },
                "tel":{
                    required:true
                }
            },
            messages:{
                "name":{
                    required:"请输入客户名!"
                },
                "tel":{
                    required:"请输入联系电话!"
                }
            },
            submitHandler:function(form){
                $.post("/customer/update",$(form).serialize()).done(function(data){
                    if(data == "success"){
                        $("#updateMoadl").modal("hide");
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("数据异常!")
                })
            }
        })


    })
</script>
</body>
</html>
