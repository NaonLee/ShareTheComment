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
<title>listMember</title>
</head>
<body>
	<h2 align="center">ListMember</h2>
	<table border="1" align="center">
		<tr>
			<td align="center">ID</td>
			<td align="center">Password</td>
			<td align="center">Name</td>
			<td align="center">Email</td>
			<td align="center">Joined date</td>
			<td align="center">Manage</td>
		</tr>
		
			<c:forEach var="member" items="${membersList}">
			<tr align="center">
				<td width=100 align="center">${member.id}</td>
				<td width=100 align="center">${member.pwd}</td>
				<td width=200 align="center">${member.name}</td>
				<td width=300 align="center">${member.email}</td>
				<td width=300 align="center">${member.joinDate}</td>
				<td><a href="${contextPath}/member/removeMember.do?id=${member.id}">Delete</a>
				<a href="${contextPath}/member/modMember.do?id=${member.id}">Modify</a></td>
				</tr>
			</c:forEach>
		
	</table>
	<h2><a href="${contextPath}/member/memberForm.do">Add Member</a></h2>
</body>
</html>