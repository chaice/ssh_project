var Ajax = (function(){
    function creatXML(){
        var xml = null;
        if(window.ActiveXObject){
            xml = new ActiveXObject("Microsoft.XMLHTTP");
        }else{
            xml = new XMLHttpRequest();
        }
        return xml;
    }
    function buildObject(isPost,object){
        if(isPost){
            var param ="";
            for( var index in object){
                var key = index;
                var value = object[index];
                param += key + "=" + value + "&";
            }
            if(param.lastIndexOf("&") == param.length-1){
                param.substr(0,param.length-1);
            }
            return param;
        }else{
            var param ="?";
            for( var index in object){
                var key = index;
                var value = object[index];
                param += key + "=" + value + "&";
            }
            param += "_=" + new Date().getTime();
            return param;
        }
    }

    function doGet(url,object,fn){
        var ar = arguments;
        var xml = creatXML();
        xml.open("get",url+buildObject(false,object),true);
        xml.onreadystatechange = function(){
            var state = xml.readyState;
            if(state == 4){
                var status = xml.status;
                if(status == 200){
                    var result = xml.responseText;
                    if(ar.length == 2){
                        ar[1](result);
                    }else{
                        fn(result);
                    }
                }else{
                    alert("");
                }
            }
        }
        xml.send();
    }

    function doPost(url,object,fn){
        var ar = arguments;
        var xml = creatXML();
        xml.open("post",url,"true");
        xml.onreadystatechange = function(){
            var state = xml.readyState;
            if(state == 4){
                var status = xml.status;
                if( status == 200){
                    var result = xml.responseText;
                    if(ar.length == 2){
                        ar[1](result);
                    }else{
                        fn(result);
                    }
                }else{
                    alert("");
                }
            }
        }
        xml.send(buildObject(true,object));
    }
    return {creatxml:creatXML,
            doget:doGet,
            dopost:doPost}
})();
