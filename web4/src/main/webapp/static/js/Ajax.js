var Ajax = (function(){
    function creatAjax(){
        var xml = null;
        if(window.ActiveXObject){
            xml = new ActiveXObject("Microsoft.XMLHTTP");
        }else{
            xml = new XMLHttpRequest();
        }
        return xml;
    }
    function buildParam(isPost,object){
        var queryparam="";
        if(!isPost){
            queryparam = "?";
            for(var index in object){
                var key = index;
                var value = object[index];
                queryparam += key +"="+value+"&";
            }
            queryparam += "_="+new Date().getTime();
            return queryparam;
        } else{
            for(var index in object){
                var key = index;
                var value = object[index];
                queryparam += key +"="+value+"&";
            }
             if(queryparam.lastIndexOf("&") == queryparam.length-1){
                queryparam= queryparam.substr(0,queryparam.length-1);
            }
            return queryparam;
        }
    }
    function doGet(url,object,fn) {
        var xml = creatAjax();
        var ar = arguments;
        xml.open("get", url + buildParam(false, object), true);
        xml.onreadystatechange = function () {
            var state = xml.readyState;
            if (state == 4) {
                var status = xml.status;
                if (status == 200) {
                    var result = xml.responseText;
                    if(ar.length == 2){
                        ar[1](result);
                    }
                    fn(result);
                }
            }
        }
        xml.send();
    }
    function doPost(url,object,fn){
        var xml = creatAjax();
        xml.open("post",url,true);
        xml.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xml.onreadystatechange = function(){
            var state = xml.readyState;
            if( state == 4){
                var status = xml.status;
                if( status == 200){
                    var result = xml.responseText;
                    fn(result);
                }
            }
        }
        xml.send(buildParam(true,object));
    }

    return {doget:doGet,dopost:doPost}
})();
