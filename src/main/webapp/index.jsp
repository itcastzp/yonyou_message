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
                url: "/message/getHistoryMessages",
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
            param.pkdjbh = "1";
            param.senderId = "1234";
            param.senderName = "123";
            param.touser = "3,8";
            param.content = "wqasdfwe";
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
        function getMessageMembers(){
            var param = {};
            param.messageId = "8";
            $.ajax({
                type: "POST",
                url: "/message/getMessageMembers",
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
        function getfiles(){
            var param = {};
            param.messageid = "1564647";
            $.ajax({
                type: "POST",
                url: "/message/getMessageFile?messageid=1564647",
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

        function selectByUserPkdjh(){
            var param = {};
            param.userid = "1001WW10000000000121";
            param.dj_id = "asdf";
            $.ajax({
                type: "POST",
                url: "/message/selectByUserPkdjh",
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
<body >
<h2>Hello World!</h2>
    <input type="button" value="getHistorymessage" onclick="getHistorymessage()">
    <input type="button" value="sendMessage" onclick="sendMessage()">
    <input type="button" value="callBackMessage" onclick="callBackMessage()">
    <input type="button" value="getMessageMembers" onclick="getMessageMembers()">
    <input type="button" value="getMessageFile" onclick="getfiles()">
    <input type="button" value="selectByUserPkdjh" onclick="selectByUserPkdjh()">
    <form action="http://127.0.0.1:8080/message/message/imageUpload?messageid=1564647&uploader=3123212" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传">
        <a href="/message/fileDownload?fileid=7" >patch_ssss.zip</a>
    </form>
    <ul>
        <li>

            1.  接口名称：http://ip:port/message/getHistoryMessages<br/>
            参数：pk_djh //单据号<br/>
            返回：code //0 成功，1失败<br/>
                history //消息历史<br/>
                message //提示信息<br/>
        </li>
        <li>2.  接口名称：http://ip:port/message/sendMessage<br/>
        参数： pkdjbh //单据号<br/>
        senderId //用户编码<br/>
        senderName //用户名称<br/>
        touser = //接收者,以","分隔<br/>
        content = //消息内容<br/>
        返回：  code //0 成功，1失败<br/>
        message //提示信息<br/>
        data    //发送成功直接返回<br/>

        </li>
        <li>

            3.  接口名称：http://ip:port/message/callBackMessage<br/>
            参数：messageId //消息id<br/>
            返回：  code //0 成功，1失败<br/>
                message //提示信息
        </li>
        <li>
            4. getMessageMembers<br/>
                参数：objectId<br/>
                返回json<br/>
        </li>
        <li>
            5.文件上传 http://ip:port/message/fileUpload
            参数：messageid   //消息id
                uploader   //上传者

        </li>
        <li>
            6.接收成员用户：http://ip:port/message/members
            参数：users
        </li>
    </ul>



</body>
</html>
