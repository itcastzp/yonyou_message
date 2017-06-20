<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="/js/jquery-1.11.2.js"></script>
    <script type="text/javascript">
        function getHistorymessage(){
            var param = {};
            param.pk_djh = "1";
            $.ajax({
                type: "POST",
                url: "/message/getHistorymessage",
                data:JSON.stringify(param),
                async : false,
                dataType: "json" ,
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                success: function(result) {
                    if(result["success"] == "true"){

                    }else{

                    }
                },
                error:function(result){

                }
            });
        }

        function sendMessage(){
            var param = {};
            param.pkDjbh = "1";
            param.usercode = "1234";
            param.username = "123";
            param.touser = "sadfa";
            param.mesg = "wqasdfwe";
            $.ajax({
                type: "POST",
                url: "/message/sendMessage",
                data:JSON.stringify(param),
                async : false,
                dataType: "json" ,
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                success: function(result) {
                    if(result["success"] == "true"){

                    }else{

                    }
                },
                error:function(result){

                }
            });
        }
        function callBackMessage(){
            var param = {};
            param.messageId = "8";
            $.ajax({
                type: "POST",
                url: "/message/callBackMessage",
                data:JSON.stringify(param),
                async : false,
                dataType: "json" ,
                headers: {
                    "Content-type": "application/json; charset=utf-8"
                },
                success: function(result) {
                    if(result["success"] == "true"){

                    }else{

                    }
                },
                error:function(result){

                }
            });
        }

    </script>
</head>
<body>
<h2>Hello World!</h2>
    <input type="button" value="getHistorymessage" onclick="getHistorymessage()">
    <input type="button" value="sendMessage" onclick="sendMessage()">
    <input type="button" value="callBackMessage" onclick="callBackMessage()">
</body>
</html>
