<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
   <div class="col-lg-5 container">
       <form action="/file" method="post" enctype="multipart/form-data">
           <div class="form-group">
               <label>文件描述</label>
               <input type="text" name="text">
               <label >选择要上传的文件</label>
               <input type="file" name="file">
               <button>提交</button>
           </div>
       </form>
   </div>
</body>
</html>
