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
<title>Login form</title>
<c:choose>
	<c:when test="${result=='loginFailed'}">
	<script>
		window.onload=function(){
			alert("ID or password is not correct. Plese retry");
		}
	</script>
	</c:when>
</c:choose>
</head>
<body>
	<h1 align="center">Login</h1>
	<form name="frmLogin" action="${contextPath}/member/login.do" method="post">
		<table border="0" align="center">
			<tr>
				<td>ID: </td>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<td>Password: </td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input type="submit" value="login">
					<input type="reset" value="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>