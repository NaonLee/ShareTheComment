<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript">
	
	window.onload = popup;
	
	function popup(){
		notShowPop = getCookieValue();
		if(notShowPop != "true"){
			window.open("${contextPath}/popUp.do", "pop", "width=350px, height=400px, history=no, resizable=no, status=no, scrollbars=no, menubar=no");
		}
	}
	
	function getCookieValue(){
		var result="false";
		if(document.cookie != ""){
			cookie = document.cookie.split(";");
			for(var i=0; i<cookie.length; i++){
				element=cookie[i].split("=");
				value=element[0];
				value=value.replace(/^\s*/,'');
				if(value=="notShowPop"){
					result = element[1];
				}
			}
		}
		return result;
	}
	
	function deleteCookie(){
		document.cookie = "notShowPop=" + "false" + ";path=/; expires=-1";
	}
	
</script>

</head>
<body>
<h1 align="center">Main Page</h1>
	<form>
		<button type="button" onClick="deleteCookie()">Delete Cookie</button>
	</form>
</body>
</html>