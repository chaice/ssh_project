<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<input type="text" id="in">&nbsp;<span id="sp"></span>
<script>
    (function(){
        function createXML(){
            var xml = null;
            if(window.ActiveXObject){
                xml = new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xml = new XMLHttpRequest();
            }
            return xml;
        }
        document.querySelector("#in").onchange = function(){
            var xml = createXML();
            var name = this.value;
            xml.open("get","/index?name="+name);
            xml.onreadystatechange = function(){
                var state = xml.readyState;
                if(state == 4){
                    var httpstate = xml.status;
                    if(httpstate == 200){
                        var txt = xml.responseText;
                        if(txt == "yes"){
                            document.querySelector("#sp").innerText = "√";
                        }else{
                            document.querySelector("#sp").innerText = "用户名已存在！"
                        }
                    }
                }
            }
            xml.send();
        }
    })();

</script>
</body>
</html>
