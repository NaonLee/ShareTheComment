<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
 

</head>
<body>
	<!-- Server responses get written here -->

		<div id="messages" style="padding-left:10px; width:100%; height: 80%; border:1px solid #C0C0C0; background-color: #DCDCDC;"></div>
		<div>
	        <input type="text" id="sender" value="${sessionScope.member.m_id}" style="display: none;">
	        <input type="text" id="messageinput" style="margin-top:5px; height: 5%; width: 80%;" placeholder="Type..." autofocus>
	    	<button class="btn btn-primary btn-sm" onclick="send();">Send</button>
	    </div>

</body>

    <!-- web socket javascript -->
    <script type="text/javascript">
        var ws;
        var messages=document.getElementById("messages");
        
        
        window.onload=function openSocket(){
            if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //Create web socket object
            ws=new WebSocket("ws://${contextPath}/echo.do");
            
            ws.onopen=function(event){
                if(event.data===undefined) return;
                
                writeResponse(event.data);
            };
            ws.onmessage=function(event){
                writeResponse(event.data);
            };
            ws.onclose=function(event){
                writeResponse("Connection closed");
            }
        }
        
        function send(){
            var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            ws.send(text);
            text="";
        }
        
        function closeSocket(){
            ws.close();
        }
        function writeResponse(text){
            messages.innerHTML+="<br/>"+text;
        }
  </script>

</html>