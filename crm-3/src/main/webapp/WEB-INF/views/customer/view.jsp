<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${customer.name}</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/default.css">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="customer"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div>
                <div class="box box-success">
                    <div class="box-header">
                        <c:if test="${not empty customer.userid}">
                            <span class="pull-right">
                                <button class="btn btn-danger" id="public">转为公共</button>&nbsp;
                                <button class="btn btn-facebook" id="transfer">转让客户</button>
                            </span>
                        </c:if>
                        <h4>客户信息</h4>
                    </div>
                    <div class="box-body">
                       <table class="table table-responsive">
                           <c:choose>
                               <c:when test="${customer.type == 'person'}">
                                   <tr>
                                       <th rowspan="3" style="width: 100px;height:100px;padding: 0px "><img src="/customer/build/${customer.id}" title="客户信息二维码"></th>
                                       <th>名称:&nbsp;${customer.name}</th>
                                       <th>电话:&nbsp;${customer.tel}</th>
                                       <th>微信号:&nbsp;${customer.weixin}</th>
                                   </tr>
                                   <tr>
                                       <th colspan="2">地址:&nbsp;${customer.address}</th>
                                       <th>email:&nbsp;${customer.email}</th>
                                   </tr>
                                   <tr>
                                       <th colspan="2">所属公司:&nbsp;<a href="/customer/view/${customer.companyid}">${customer.companyname}</a></th>
                                       <th>级别:&nbsp;<span style="color: darkorange">${customer.rank}</span></th>
                                   </tr>
                               </c:when>
                               <c:otherwise>
                                   <tr>
                                       <th rowspan="3" style="width: 100px;height:100px;padding: 0px "><img src="/customer/build/${customer.id}" title="客户信息二维码"></th>
                                       <th colspan="2">名称:&nbsp;${customer.name}</th>
                                       <th colspan="2">电话:&nbsp;${customer.tel}</th>

                                   </tr>
                                   <tr>
                                       <th colspan="2">email:&nbsp;${customer.email}</th>
                                       <th colspan="2">地址:&nbsp;${customer.address}</th>
                                   </tr>
                                   <tr>
                                       <th colspan="2">微信号:&nbsp;${customer.weixin}</th>
                                       <th colspan="2">级别:&nbsp;<span style="color: darkorange">${customer.rank}</span></th>
                                   </tr>
                               </c:otherwise>
                           </c:choose>
                       </table>
                    </div>
                </div>
            </div>
            <div class="modal modal-info" id="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span></button>
                            <h4 class="modal-title">选择转入人</h4>
                        </div>
                        <div class="modal-body">
                            <form class="form-group" action="/customer/transformUser?id=${customer.id}" method="post">
                                <label class="form-group">转入人姓名</label>
                                <select name="userid" class="form-control">
                                    <c:forEach items="${userlist}" var="user">
                                        <option value="${user.id}">${user.name}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-outline">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--关联项目-->
            <div class="col-md-6">
                <div class="box box-info">
                    <div class="box-body">

                    </div>
                </div>
            </div>
            <!--待办事项-->
            <div class="col-md-6">
                <div class="box box-info">
                    <div class="box-body">

                    </div>
                </div>
            </div>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script>
    $(function () {
        $("#public").click(function () {
            var id = ${customer.id};
            if(confirm("是否确定将该客户变为公共?")){
                location.href = "/customer/public/"+id;
            }
        });
        $("#transfer").click(function () {
            $("#modal").modal({
                show:true,
                backdrop:"static",
                keyBroad:false
            })
        });
    })
</script>
</body>
</html>
