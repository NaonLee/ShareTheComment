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
<title>MemberForm</title>
</head>
<body>
	<h1 align="center">Add Member</h1>
	<form action="${contextPath}/member/addMember.do" method="post">
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
				<td>Name: </td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>Email: </td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input type="submit" value="Add">
					<input type="reset" value="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>