
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <label>验证码:</label><input type="text" name="txt">
    <a href="javascript:;" id="a"><img src="/pic.png" alt="这是验证码" id="img"></a>
    <button>submit</button>
</form>

<script src="/static/js/jquery-3.0.0.min.js"></script>
<script>
    $(function(){
        $("#a").click(function(){
            $("#img").attr("src","/pic.png?t="+ new Date().getTime());
        })
    });
</script>
</body>
</html>
