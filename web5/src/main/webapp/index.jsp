<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        ul{
            list-style: none;
        }
    </style>
</head>
<body>
<h2>Hello World!</h2>
<button id="btn">查看</button>
<table id="table" style="visibility: hidden">
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>国籍</th>
    </tr>
</table>
</div>
<script src="/static/js/Ajax.js"></script>
<script>
    (function(){
     document.querySelector("#btn").onclick = function(){
         var xml = Ajax.creatxml();
         xml.open("get","/ajax.xml",true);
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
          var userArray = result.getElementsByTagName("user");
          var object = null;
          for( var i = 0;i<userArray.length-1;i++) {
              var user = userArray[i];
              var id = user.getElementsByTagName("id")[0].childNodes[0].nodeValue;
              var name = user.getElementsByTagName("name")[0].childNodes[0].nodeValue;
              var address = user.getElementsByTagName("address")[0].childNodes[0].nodeValue;
              var ul = document.createElement("tr");
              var l1 = document.createElement("td");
              var l2 = document.createElement("td");
              var l3 = document.createElement("td");
              var id = document.createTextNode(id);
              var name = document.createTextNode(name);
              var address = document.createTextNode(address);
              l1.appendChild(id);
              l2.appendChild(name);
              l3.appendChild(address);
              ul.appendChild(l1);
              ul.appendChild(l2);
              ul.appendChild(l3);
              document.querySelector("#table").appendChild(ul);
              document.querySelector("#table").setAttribute("style","visibility: visible")
          }
      }

    })();
</script>
</body>
</html>
