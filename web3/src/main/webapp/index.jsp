<html>
<body>
<h2>Hello World!</h2>
<input type="text">
<script>
    (function(){
        var xml = null;
        if(window.ActiveXObject()){
             xml = new ActiveXObject("Microsoft.XMLHTTP");
        }else {
             xml = XMLHttpRequest;
        }
        console.log(xml);
    })();
</script>
</body>
</html>
