
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <button id="btn">查看</button>
    <ul id="ul"></ul>
    <label>在线中英文词典</label>
    <input type="text" id="input" placeholder="请输入！">
    <ul id="u"></ul>
</div>
<script src="/static/js/Ajax.js"></script>
<script>
    (function(){
        document.querySelector("#input").onkeyup = function(event){
            if(event.keyCode == 13){
                var xml = Ajax.creatxml();
                xml.open("get","/test?p="+encodeURIComponent(this.value),true);
                xml.onreadystatechange = function(){
                    var state = xml.readyState;
                    if(state == 4){
                        var status = xml.status;
                        if(status == 200){
                          var result = xml.responseXML;
                            buildKey(result);
                        }else {
                            alert("");
                        }
                    }
                }
                this.value = "";
                xml.send();
            }
        }


        document.querySelector("#btn").onclick = function(){

                var xml = Ajax.creatxml();
                xml.open("get","/test");
                xml.onreadystatechange = function(){
                    var state = xml.readyState;
                    if(state == 4){
                        var status = xml.status;
                        if(status == 200){
                            var result = xml.responseXML;
                             buildXML(result);
                        }
                    }
                }
            xml.send();
        }
        function  buildKey(result){
            var explains = result.getElementsByTagName("explains")[0];
            var exs = explains.getElementsByTagName("ex");
            for(var i = 0;i<exs.length;i++ ){
                var ex = exs[i];
                var text = document.createTextNode(ex.childNodes[0].nodeValue);
                var li = document.createElement("li");
                li.appendChild(text);
                document.querySelector("#u").appendChild(li);

            }
        }
        function buildXML(result){
            var items = result.getElementsByTagName("item");
            for(var i = 0 ;i<items.length-1;i++){
                var item = items[i];
                var title = item.getElementsByTagName("title")[0].childNodes[0].nodeValue;
                var link = item.getElementsByTagName("link")[0].childNodes[0].nodeValue;
                var text = document.createTextNode(title);
                var a = document.createElement("a");
                var li = document.createElement("li");
                a.appendChild(text);
                a.setAttribute("href",link);
                a.setAttribute("target","_blank");
                li.appendChild(a);
                document.querySelector("#ul").appendChild(li);
            }
        }
    })()
</script>
</body>
</html>
