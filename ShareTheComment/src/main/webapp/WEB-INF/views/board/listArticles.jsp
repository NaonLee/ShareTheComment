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
<title>Articles</title>
</head>
<body>
	<c:choose>
		<c:when test="${isLogOn==true}">
			<h4>Hello ${logMember.id}</h4>
			<a href="${contextPath}/member/logout.do">Logout</a>
		</c:when>
		<c:otherwise>
			<a href="${contextPath}/member/loginForm.do">Login</a>
		</c:otherwise>
	</c:choose>
	<h2>Article list</h2>
	<table border="1" align="center">
		<tr align="center">
			<td width="10%">Article No</td>
			<td width="40%">Title</td>
			<td width="10%">ID</td>
			<td width="10%">WrittenDate</td>
		</tr>
		<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.articleNO}</td>
				<td><a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td>
				<td>${article.id}</td>
				<td>${article.writtenDate}</td>
			</tr>
		</c:forEach>
	</table>
	<c:choose>
		<c:when test="${isLogOn==true}">
			<a href="${contextPath}/board/articleForm.do">Write</a>
		</c:when>
	</c:choose>
</body>
</html>