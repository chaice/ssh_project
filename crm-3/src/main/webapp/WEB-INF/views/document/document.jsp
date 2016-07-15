<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>文件管理</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/webuploader/webuploader.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="../include/head.jsp"%>
    <jsp:include page="../include/sidebar.jsp">
        <jsp:param name="menu" value="document"/>
    </jsp:include>
    <div class="content-wrapper">
        <section class="content-header">

        </section>
        <section class="content">
            <div class="box box-success">
                <div class="box-header">
                    <div class="btns pull-right">
                        <div id="picker"><i class="fa fa- fa-cloud-upload"></i> 上传文件</div>
                        <button class="btn btn-success" id="new_dir"><i class="fa fa-plus"></i> 新建文件夹</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>文件名
                                    <c:if test="${fid != 0 and fid != null}">
                                        <a class="pull-right" href="/document?fid=${pFid}"><i class="fa fa-mail-reply text-aqua"></i> 返回上一级</a>
                                    </c:if>
                                </th>
                                <th>大小</th>
                                <th>上传时间</th>
                                <th>上传人</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach items="${documentlist}" var="doc">
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${doc.type == 'dir'}">
                                                <i class="fa fa-folder-o"></i><a href="/document?fid=${doc.id}"> ${doc.name}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa fa-file-text-o"></i><a href="/document/download/${doc.id}"> ${doc.name}</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${doc.size}</td>
                                    <td>
                                        <fmt:formatDate value="${doc.creattime}" pattern="y-M-d H:m"></fmt:formatDate>
                                    </td>
                                    <td>${doc.creatuser}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <form action="/document/newdir/${fid}" method="post" id="new_dir_form">
                                <input type="hidden" name="fid" value="${fid}">
                                <div class="form-group">
                                    <label class="control-label">文件夹名</label>
                                    <input type="text" class="form-control" name="dirname" id="dirname">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="save">保存</button>
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
<script src="/static/highlight.pack.js"></script>
<script src="/static/webuploader/webuploader.js"></script>
<script>
   $(function(){
       var uploader =WebUploader.create({
           swf:"/static/webuploader/Uploader.swf",
           server:"/document/upload",
           pick:"#picker",
           auto:true,
           fileVal:"file",
           formData:{"fid":${fid}}
       });
       $("#new_dir").click(function(){
           $("#modal").modal({
               show:true,
               backdrop:"static",
               keyBoard:true
           })
       });
       $("#save").click(function(){
           if(!$("#dirname").val()){
               $("#dirname").focus();
               return;
           }
           $("#new_dir_form").submit();
       });
       uploader.on("uploadProgress",function(){

       });
       uploader.on("uploadSuccess",function(file){
            location.reload();
        });
       uploader.on("uploadError",function(file){
           alert("文件上传失败!")
       });
       uploader.on("uploadComplete",function(file){

       })


   })
</script>
</body>
</html>
