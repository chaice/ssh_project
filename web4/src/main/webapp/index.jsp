<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<input type="text" id="in">&nbsp;<span id="sp"></span>
<button id="btn">post提交</button>&nbsp;<span id="sp1"></span>
<script src="/static/js/Ajax.js"></script>
<script>
    (function(){
//        function createXML(){
//            var xml = null;
//            if(window.ActiveXObject){
//                xml = new ActiveXObject("Microsoft.XMLHTTP");
//            }else{
//                xml = new XMLHttpRequest();
//            }
//            return xml;
//        }
        document.querySelector("#in").onchange = function(){
            var object = {name:"tom",address:"china"};
            Ajax.doget("/index",object,function(result){
                if(result == "yes"){
                    document.querySelector("#sp").innerText = "√";
                }else {
                    document.querySelector("#sp").innerText = "用户名已存在！";
                }
            });

            /*
            针对Ajax中get请求方式的中文乱码问题
             encodeURIComponent(name);客户端
             服务器端 new String(request.getParameter("").getBytes("ISO-8859-1"),"UTF-8");
             */
//            var object = {name:"tom",address:"china"};
//            var queryparam = "";
//            for(var index in object){
//                var key = index;
//                var value = object[index];
//                    queryparam += key +"="+value+"&";
//            }
//            if(queryparam.lastIndexOf("&") == queryparam.length-1){
//               queryparam= queryparam.substr(0,queryparam.length-1);
//            }
//            queryparam += "_="+new Date().getTime();
//            console.log(queryparam);


//            Ajax.doget(name,function(result){
//                if(result == "yes"){
//                    document.querySelector("#sp").innerText = "√";
//                }else {
//                    document.querySelector("#sp").innerText = "用户名已存在！";
//                }
//            })
//            var xml = createXML();
//            var name = this.value;
//            xml.open("get","/index?name="+name);
//            xml.onreadystatechange = function(){
//                var state = xml.readyState;
//                if(state == 4){
//                    var httpstate = xml.status;
//                    if(httpstate == 200){
//                        var txt = xml.responseText;
//                        if(txt == "yes"){
//                            document.querySelector("#sp").innerText = "√";
//                        }else{
//                            document.querySelector("#sp").innerText = "用户名已存在！"
//                        }
//                    }
//                }
//            }
//            xml.send();
        }
        document.querySelector("#btn").onclick = function(){
            var object = {name:"tom",address:"china"};
            console.log(object);
            Ajax.dopost("/index",object,function(result){
                if(result == "yes"){
                    document.querySelector("#sp1").innerText = "√";
                }else {
                    document.querySelector("#sp1").innerText = "用户名已存在！";
                }
            })
        }
    })();

</script>
</body>
</html>
