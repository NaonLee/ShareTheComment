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
	<div class="btn-toolbar">
	    <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/board/articleForm.do'">Write</button>
	</div>
	
	
	<div class="row">
	<table class="table table-striped">
		<thead>
			<tr align="center">
				<th width="10%">Article No</th>
				<th width="20%">Title</th>
				<th width="10%">ID</th>
				<th width="5%">Like</th>
				<th width="10%">WrittenDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td align="center">${article.articleNO}</td>
					<td style="padding-left: 20px">
					<c:if test="${article.level > 1}">
						<c:forEach begin="1" end="${article.level}" step="1">
							<span style="padding-left: 20px"></span>
						</c:forEach>
						<span>[Answer]</span>
					</c:if>
					<a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a></td>
					<td align="center">${article.id}</td>
					<td align="center">${article.likeCount}</td>
					<td align="center">${article.writtenDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="pagination">
	    <ul>
	        <li><a href="#">Prev</a></li>
	        <li><a href="#">1</a></li>
	        <li><a href="#">2</a></li>
	        <li><a href="#">3</a></li>
	        <li><a href="#">4</a></li>
	        <li><a href="#">Next</a></li>
	    </ul>
	</div>
	<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	        <h3 id="myModalLabel">Delete Confirmation</h3>
	    </div>
	    <div class="modal-body">
	        <p class="error-text">Are you sure you want to delete the user?</p>
	    </div>
	    <div class="modal-footer">
	        <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
	        <button class="btn btn-danger" data-dismiss="modal">Delete</button>
	    </div>
	</div>
	
			

</body>
</html>