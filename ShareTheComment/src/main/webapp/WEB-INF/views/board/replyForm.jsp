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
<c:if test="${isLogOn == false || isLogOn == null}">		<!-- if user isn't logged in -->
	<script type="text/javascript">
		window.onload=function(){
			alert("Please login to write an article!");
			location.href="${contextPath}/member/loginForm.do";
		}
	</script>
	</c:if>

<meta charset="UTF-8">
<title>Reply article</title>
</head>
<body>
	<h2 align="center">Article form</h2>
	
	<form action="${contextPath}/board/replyArticle.do"method="get">
		<table width="80%">
		<tr hidden="hidden"><td><input type="text" name="id" value="${logMember.id}"></td></tr>
		<tr hidden="hidden"><td><input type="text" name="parentNO" value="${parentNO}"></td></tr>
		<tr>
			<td>title</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>Content</td>
			<td><textarea rows="40" cols="40" name="content"></textarea></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Write"><input type="button" value="cancel" onClick=""></td>
		</tr>
		</table>	
	</form>
	

</body>
</html>