<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${sales.name}</title>
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
        <jsp:param name="menu" value="sales"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content">
            <div class="box box-success">
                <div class="box-header">
                   <h3><i class="fa fa-tag"></i> ${sales.name}</h3>
                </div>
                <div class="box-body">
                   <table class="table">
                       <tr class="info">
                           <th>价值:<i class="fa fa-cny"></i> ${sales.value}</th>
                           <th>创建人:${sales.creatuser}</th>
                           <th>创建时间:<fmt:formatDate value="${sales.creattime}" pattern="y-M-d H:m"></fmt:formatDate></th>
                       </tr>
                       <tr class="active">
                           <th>关联客户:<a href="/customer/view/${sales.customerid}">${sales.customername}</a></th>
                           <th>最后跟进时间:<fmt:formatDate value="${sales.lasttime}" pattern="y-M-d H:m"></fmt:formatDate></th>
                           <th>完成时间:<fmt:formatDate value="${sales.completetime}" pattern="y-M-d H:m"></fmt:formatDate></th>
                       </tr>
                       <tr>
                           <th colspan="3">当前进度:${sales.progress}</th>
                       </tr>
                   </table>
                </div>
            </div>
            <section class="col-lg-7">
                <div class="box box-info">
                    <div class="box-header">
                        <h4>跟进记录 <button class="btn btn-warning pull-right"><i class="fa fa-plus"></i> 增加记录</button></h4>
                    </div>
                    <div class="box-body">
                        <ul class="timeline">
                            <c:forEach items="${salesLogs}" var="log">
                               <c:choose>
                                   <c:when test="${log.type == 'auto'}">
                                       <li class="time-label">
                                          <span class="bg-green">
                                             <fmt:formatDate value="${log.followtime}" pattern="y-M-d"></fmt:formatDate>
                                          </span>
                                       </li>
                                       <li>
                                           <i class="fa fa-user bg-blue"></i>
                                           <div class="timeline-item">
                                               <!--显示时间-->
                                               <span class="time"><i class="fa fa-clock-o"></i> 相对时间</span>
                                               <h3 class="timeline-header bg-info">${log.context}</h3>
                                           </div>
                                       </li>
                                   </c:when>
                                   <c:otherwise>
                                       <li class="time-label">
                                          <span class="bg-maroon">
                                             <fmt:formatDate value="${log.followtime}" pattern="y-M-d"></fmt:formatDate>
                                          </span>
                                       </li>
                                       <li>
                                           <i class="fa fa-hand-paper-o bg-aqua"></i>
                                           <div class="timeline-item">
                                               <!--显示时间-->
                                               <span class="time"><i class="fa fa-clock-o"></i> 相对时间</span>
                                               <h3 class="timeline-header bg-teal">${log.context}</h3>
                                           </div>
                                       </li>
                                   </c:otherwise>
                               </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </section>
            <section class="col-lg-5">
                <div class="box box-comment">
                    <div class="box-header">
                        <h4>相关文件<button class="btn btn-info pull-right"><i class="fa fa-upload"></i></button></h4>
                    </div>
                    <div class="box-body">
                        <table class="table">
                            <c:forEach items="${salesFile}" var="file">
                                <tr>
                                    <a href="">${file.name}</a> <i class="fa fa-download pull-right"></i>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="box-footer no-border">
                    </div>
                </div>
                <div class="box box-warning">
                    <div class="box-header">
                        <h4>待办事项</h4>
                    </div>
                    <div class="box-body">
                    </div>
                    <div class="box-footer no-border">
                    </div>
                </div>
            </section>
        </section>
    </div>
</div>
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/highlight.pack.js"></script>
</body>
</html>
