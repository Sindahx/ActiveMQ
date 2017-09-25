<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    System.out.println(path);
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    System.out.println(basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>ActiveMQ Demo程序</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
    <style type="text/css">
        .h1 {
            margin: 0 auto;
        }

        #producer {
            width: 48%;
            border: 1px solid blue;
            height: 80%;
            align: center;
            margin: 0 auto;
        }

        body {
            text-align: center;
        }

        div {
            text-align: center;
        }

        textarea {
            width: 80%;
            height: 100px;
            border: 1px solid gray;
        }

        button {
            background-color: rgb(62, 156, 66);
            border: none;
            font-weight: bold;
            color: white;
            height: 30px;
        }
    </style>
    <script type="text/javascript">
        var t1 = null;
        function send(controller) {
            if ('' == $("#message").val()) {
                $("#message").css("border", "1px solid red");
                return;
            } else {
                $("#message").css("border", "1px solid gray");
            }


            var url = '<%=basePath%>activemq/' + controller;
            $.post(url, {"message": $("#message").val()}, function (data) {
                if (data.opt == "suc") {
                    if(data.flog == "1"){
                        var message = data.message;
                        $('#messageRes').val(message);
                    }else{
                        requestIndex = 0;
                        t1 =  setInterval(function(){
                            gatData(data.typeName, data.uuid);
                        },500);
                    }
                } else {
                    $("#status").html("<font color=red>" + data + "</font>");
                    setTimeout(clear, 5000);
                }
            }, "json");
        }

        var requestIndex=0;

        function gatData(typeName, uuid) {
            var url = '<%=basePath%>activemq/getData';
            var parme = {};
            parme.typeName = typeName;
            parme.uuid = uuid;
            $.post(url, parme, function (data) {
                requestIndex++;
                var message = data.message;
                $('#messageRes').val(message);

                if(data.flog == 1 || requestIndex == 60){
                    clearInterval(t1);
                }

            }, 'json');

        }

        function clear() {
            $("#status").html("");
        }

    </script>
</head>

<body>
<h1>Hello ActiveMQ</h1>
<div id="producer">
    <h2>Producer</h2>
    <textarea id="message"></textarea>
    <br>
    <button onclick="send('queueSender')">发送Queue消息</button>
    <%--<button onclick="send('topicSender')">发送Topic消息</button>--%>
    <br>
    <span id="status"></span>

    <h2>Response</h2>
    <textarea id="messageRes"></textarea>
</div>
</body>
</html>
