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
<title>header</title>
</head>
<body>
<h1 align="center">Header</h1>
	<c:choose>
		<c:when test="${isLogOn==true}">
			<h4>Hello ${logMember.id}</h4>
			<a href="${contextPath}/member/logout.do">Logout</a>
		</c:when>
		<c:otherwise>
			<a href="${contextPath}/member/loginForm.do">Login</a>
		</c:otherwise>
	</c:choose>
</body>
</html>