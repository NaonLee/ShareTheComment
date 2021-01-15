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

	<h2>Article list</h2>
	<table width="80%" border="1" align="center">
		<thead>
			<tr align="center">
				<th width="10%">Article No</td>
				<th width="30%">Title</td>
				<th width="10%">ID</td>
				<th width="10%">WrittenDate</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td>${article.articleNO}</td>
					<td style="padding-left: 10px"/>
					<c:if test="${article.level > 1}">
						<c:forEach begin="1" end="${article.level}" step="1">
							<span style="padding-left: 20px"></span>
						</c:forEach>
						<span>[Answer]</span>
					</c:if>
					<a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td>
					<td>${article.id}</td>
					<td>${article.writtenDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
			<a href="${contextPath}/board/articleForm.do">Write</a>

</body>
</html>