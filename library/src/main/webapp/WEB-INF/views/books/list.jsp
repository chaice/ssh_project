<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/dataTables.bootstrap.min.css">
</head>
<body>
   <div class="container">
       <div class="container">
       <a type="button" class="btn btn-success" href="javascript:;" id="add" style="margin: 15px 0px">新增书籍</a>
           <div class="well well-sm">
               <form class="form-inline" method="get">
                   <div class="form-group">
                       <input type="text" placeholder="书籍名称" name="bookName" class="form-control" id="bookName">
                   </div>
                   <div class="form-group">
                       <select name="type" class="form-control" id="type">
                           <option value="">请选择类型</option>
                           <c:forEach items="${bookTypeList}" var="type">
                               <option value="${type.id}" ${typeid == type.id?'selected':''}>${type.booktype}</option>
                           </c:forEach>
                       </select>
                   </div>
                   <div class="form-group">
                       <select name="pub" class="form-control" id="pub">
                           <option value="">请选择出版社</option>
                           <c:forEach items="${publisherList}" var="pub">
                               <option value="${pub.id}" ${pubid == pub.id?'selected':''}>${pub.pubname}</option>
                           </c:forEach>
                       </select>
                   </div>
                   <button type="button" class="btn btn-default" id="searchButton">搜索</button>
               </form>
           </div>
       </div>
           <table id="dataTable" class="table">
           <thead>
           <tr>
               <th>id</th>
               <th>书名</th>
               <th>价格</th>
               <th>作者</th>
               <th>数量</th>
               <th>类型</th>
               <th>出版社</th>
               <th>操作</th>
           </tr>
           </thead>
           <tbody>

           </tbody>
       </table>
   </div>
   <div class="modal fade" id="modalAlter">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">修改书籍</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal" id="formAlter">
                       <input type="hidden" name="id" id="book_id">
                       <div class="form-group">
                           <label class="col-md-4 control-label">书名</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookname" id="book_name">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">价格</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookprice" id="book_price" >
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">作者</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookauthor" id="book_author" >
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">数量</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="booknum" id="book_num">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">类型</label>
                           <div class="col-md-6">
                               <select name="typeid" id="book_type">
                                   <c:forEach items="${bookTypeList}" var="type">
                                       <option value="${type.id}" ${typeid == type.id?'selected':''}>${type.booktype}</option>
                                   </c:forEach>
                               </select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">出版社</label>
                           <div class="col-md-6">
                               <select name="pubid" id="book_pubid">
                                   <c:forEach items="${publisherList}" var="pub">
                                       <option value="${pub.id}" ${pubid == pub.id?'selected':''}>${pub.pubname}</option>
                                   </c:forEach>
                               </select>
                           </div>
                       </div>
                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                   <button type="button" class="btn btn-primary" id="saveAlter">保存</button>
               </div>
           </div>
       </div>
   </div>

   <div class="modal fade" id="modal">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title">添加书籍</h4>
               </div>
               <div class="modal-body">
                   <form class="form-horizontal" id="form">
                       <div class="form-group">
                           <label class="col-md-4 control-label">书名</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookname" placeholder="" >
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">价格</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookprice" placeholder="" >
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">作者</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="bookauthor" placeholder="">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">数量</label>
                           <div class="col-md-6">
                               <input type="text" class="form-control" name="booknum" placeholder="">
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">类型</label>
                           <div class="col-md-6">
                               <select name="typeid">
                                   <option value="">请选择类型</option>
                                   <c:forEach items="${bookTypeList}" var="type">
                                       <option value="${type.id}">${type.booktype}</option>
                                   </c:forEach>
                               </select>
                           </div>
                       </div>
                       <div class="form-group">
                           <label class="col-md-4 control-label">出版社</label>
                           <div class="col-md-6">
                               <select name="pubid">
                                   <option value="">请选择出版社</option>
                                   <c:forEach items="${publisherList}" var="pub">
                                       <option value="${pub.id}">${pub.pubname}</option>
                                   </c:forEach>
                               </select>
                           </div>
                       </div>
                   </form>
               </div>
               <div class="modal-footer">
                   <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                   <button type="button" class="btn btn-primary" id="save">保存</button>
               </div>
           </div>
       </div>
   </div>


<script src="/static/js/jquery-3.0.0.min.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/jquery.dataTables.min.js"></script>
<script src="/static/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function(){
      var dataTable = $("#dataTable").DataTable({
            "serverSide":true,
            "ajax":{url:"/books/data.json",
                data:function(dataSource){
                    dataSource.bookname = $("#bookName").val();
                    dataSource.type = $("#type").val();
                    dataSource.pub = $("#pub").val();
                }},
          "searching":false,
            "lengthMenu":[5,10,15,20],
            "order": [0,'desc'],
            "columnDefs": [ {
                "targets": [0],
                "visible": false
            } ,{
                "targets":[1,2,3,4,6,7],
                "orderable":false
            }],
            "columns":[
                {"data":"id","name":"id"},
                {"data":"bookname"},
                {"data":"bookprice"},
                {"data":"bookauthor"},
                {"data":"booknum"},
                {"data":"bookType.booktype","name":"typeid"},
                {"data":"publisher.pubname","name":"pubid"},
                {"data":function(row){
                    return "<button class='alter' rel='"+row.id+"' type='button'>编辑</button>&nbsp;<button type='button' class='delete' rel='"+row.id+"'>删除</button>"
                }}
            ],
            "language":{
                "search":"搜索",
                "lengthMenu":"每页显示_MENU_条数据",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                },
                "zeroRecords":"没有找到你要查找的内容！",
                "info": "显示了第 _START_  条到第  _END_  条共  _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据查询)",
                "infoEmpty":      "没有数据！"
            }

        });
        $("#add").click(function(){
            $("#form").reset;
            $("#modal").modal({
                "show":true,
                "backdrop":"static",
                "keyboard":false
            })
        });
        $("#form").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{bookname:{required:true},
                bookprice:{required:true,number:true},
                bookauthor:{required:true},booknum:{required:true,digits:true},
                typeid:{required:true},pubid:{required:true}},
            messages:{bookname:{required:"请输入书籍姓名"},
                bookprice:{required:"请输入价格",number:"请输入正确的书籍价格"},
                bookauthor:{required:"请输入作者"},booknum:{required:"请输入数量",digits:"请输入正确的数量"},
                typeid:{required:"请选择类型"},pubid:{required:"请选择出版社"}
            },
            submitHandler:function(form){
                $.post("/books/new",$(form).serialize())
                        .done(function(data){
                       if(data == "success"){
                           $("#modal").modal("hide");
                           dataTable.ajax.reload();
                       }
                }).fail(function(){
                    alert("添加失败！");
                })
            }

        });
        $("#save").click(function(){
            $("#form").submit();
        });
        $(document).delegate(".delete","click",function(){
            var id =$(this).attr("rel");
            if(confirm("是否要删除？")){
                $.get("/books/delete/"+id).done(function(data){
                    if(data == "success"){
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("删除失败！")
                })
            }
        });
        $(document).delegate(".alter","click",function(){
            var id = $(this).attr("rel");
            $.get("/books/alter/"+id).done(function(data){
                $("#book_id").val(data.id);
                $("#book_name").val(data.bookname);
                $("#book_price").val(data.bookprice);
                $("#book_author").val(data.bookauthor);
                $("#book_num").val(data.booknum);
                $("#book_type").val(data.typeid);
                $("#book_pubid").val(data.pubid)
            }).fail(function(){
                alert("数据连接错误！")
            });
           $("#modalAlter").modal({
               show:"true",
               backdrop:"static",
               keyboard:"true"
           });

        });
        $("#saveAlter").click(function(){
            $("#formAlter").submit();
        })
        $("#formAlter").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{bookname:{required:true},
            bookprice:{required:true,number:true},
            bookauthor:{required:true},booknum:{required:true,digits:true},
            typeid:{required:true},pubid:{required:true}},
            messages:{bookname:{required:"请输入书名！"},
            bookprice:{required:"请输入价格！",number:"请输入正确的价格！"},
            bookauthor:{required:"请输入作者！"},booknum:{required:"请输入数量！",digits:"请输入正确的数量！"},
            typeid:{required:"请选择类型！"},pubid:{required:"请选择出版社！"}},
            submitHandler:function(form){
                $.post("/books/alter",$(form).serialize()).done(function(data){
                    if(data == "success"){
                        $("#modalAlter").modal("hide");
                        dataTable.ajax.reload();
                    }
                }).fail(function(){})
            }
        });
        $("#searchButton").click(function(){
           dataTable.ajax.reload();
        });



    })
</script>
</body>
</html>
