<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello,spring mvc</h1>
<form action="/home1" method="get">
    username:<input type="text" name="username" id="username"></br></br>
    password:<input type="text" name="password"></br></br>
    &nbsp;&nbsp;<button>submit</button>
</form>
<script>
    (function(){
        document.querySelector("#username").onblur = function(){
            console.log(this);
            console.log(this.value);
        }
    })();
</script>
</body>
</html>
