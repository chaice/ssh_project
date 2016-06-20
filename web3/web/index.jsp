
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>title</title>
  </head>
  <body>
  <input type="text" id="in">&nbsp;<span id="sp"></span>
  <script>
    (function(){
      document.querySelector("#in").onchange = function(){
        var xml = createXML();//获得xmlHttpRequest
        var name = this.value;
        xml.open("get","/index?name="+name,true);//将文本框内的值传入到servlet中
        xml.onreadystatechange = function(){//设置回调函数
          var state = xml.readyState;//获取服务器状态码：1.已初始化 2.发送请求 3.响应数据传输中 4.相应完成 不同的浏览器顺序可能不同
          if(state == 4){
            var status = xml.status;//获取http状态码，当为200时成功
            if(status == 200){
              var result = xml.responseText;//获取返回的值
              if(result == "yes"){
                document.querySelector("#sp").innerText = "√";
              }else {
                document.querySelector("#sp").innerText = "用户名已存在";
              }
            }
          }
        }
        xml.send();
      }





      function createXML(){
        //创建XMLHttpRequest>-Ajax引擎
        var xml = null;
        if(window.ActiveXObject){
          xml = new ActiveXObject("Microsoft.XMLHTTP");
        }else {
          xml =  new XMLHttpRequest;
        }
        return xml;
      }
      /*
      ajax Asynchronous JavaScript and XML 核心：异步，无刷新
      ajax引擎？
      1.创建ajax引擎
      2.设置请求的方式、路径、是否为异步请求。xml.open();
      3.设置回调函数
        3.1 获取服务器状态码。1>已初始化 2>发送请求数据 3>响应数据传输中 4>响应完成
        xml.onreadstatechange = function(){
          var state = xml.readState;//当state为4时执行下一步
          if(state == 4){
          var httpstate = xml.status;//获取http状态码，httpstate为200时成功
           if(httpstate == 200){
           var txt = xml.responseText//获取服务器的响应数据，根据数据判断是否通过
           if(txt == "yes"){

            }else{

            }
          }
        }
       */
    })();
  </script>
  </body>
</html>
