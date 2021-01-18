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
<c:choose>
	<c:when test="${isLogOn == true && logMember.id == 'admin'}">
		<script>
			window.onload=function(){
				alert("admin");
			}
		</script>
	</c:when>
</c:choose>
</head>
<body>
	
	<h2 align="center">ListMember</h2>
	
	<div class="btn-toolbar">
	    <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/member/memberForm.do'">Add member</button>
	</div>
	
	<div class="row">
	<table class="table table-striped">
		<thead align="center">
			<tr>
				<th width="10%">ID</th>
				<th width="10%">Password</th>
				<th width="10%">Name</th>
				<th width="15%">Email</th>
				<th width="20%">Joined date</th>
				<th width="20%">Manage</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach var="member" items="${membersList}">
			<tr align="center">
				<td>${member.id}</td>
				<td>${member.pwd}</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.joinDate}</td>
				<td><a href="${contextPath}/member/removeMember.do?id=${member.id}">Delete </a>|
				<a href="${contextPath}/member/modForm.do?id=${member.id}">Modify</a></td>
				<td hidden="true" value="${member.id}"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>