<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Hello World!</h2>
<button id="btn">查看</button>
<input type="text"id="input" placeholder="请输入!">
<ul id="ul"></ul>
<script>
    (function(){
        function createXMl(){
            var xml = null;
            if(window.ActiveXObject){
                xml = new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xml = new XMLHttpRequest();
            }
            return xml;
        }
        document.querySelector("#input").onkeyup = function(event){
            document.querySelector("#ul").innerHTML = "";
            if(event.keyCode == 13){
                var xml = createXMl();
                xml.open("get","/index_find?word="+encodeURIComponent(this.value));
                xml.onreadystatechange = function(){
                    var state = xml.readyState;
                    if(state == 4){
                        var status = xml.status;
                        if(status == 200){
                            var result = xml.responseXML;
                            buildFind(result);
                        }else{
                            alert("");
                        }
                    }
                }
                xml.send();
                this.value = "";
            }

        }
        function buildFind(result){
            var explain = result.getElementsByTagName("explains")[0];
            var exs = explain.getElementsByTagName("ex");
            for(var i = 0 ; i<exs.length;i++){
                var ex = exs[i];
                var text = document.createTextNode(ex.childNodes[0].nodeValue)
                var li = document.createElement("li");
                li.appendChild(text);
                document.querySelector("#ul").appendChild(li);
            }

        }
        document.querySelector("#btn").onclick = function(){
            var xml = createXMl();
            xml.open("get","/index",true);
            xml.onreadystatechange = function(){
                var state = xml.readyState;
                if(state == 4){
                    var status = xml.status;
                    if(status == 200){
                        var result = xml.responseXML;
                        buildXML(result);
                    }else{
                        alert("");
                    }
                }
            }

            xml.send();
        }
       function buildXML(result){
           document.querySelector("#ul").innerHTML = "";
           var items = result.getElementsByTagName("item");
           for(var i = 0;i<items.length;i++){
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
    })();
</script>
</body>
</html>
