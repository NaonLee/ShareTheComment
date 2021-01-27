<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Articles</title>
<style>

	#container {
		width: 75%;
		margin: 0 auto;
		padding-top: 10%;
	}
	.table > thead {
		background-color: #A9A9A9	
	}
	.table > thead > tr > th {
		text-align: center;
	}
	.table-hover > tbody > tr:hover {
		background-color: #e6ecff;
	}
	.table > tbody > tr > td {
		text-align: center;
	}
	.table > tbody > tr > #title {
		text-align: left;
	}
</style>

</head>
<body>
	
	<h3 align="center">Article board</h3>
	<div>
	<div class="row">
	<table class="table table-bordered table-hover">
		<thead>
		<tr>
			<th width="10%">Article No</th>
			<th width="20%">Title</th>
			<th width="10%">Writer</th>
			<th width="5%">Like</th>
			<th width="10%">WrittenDate</th>
		</tr>
		</thead>
		<tbody>	<!-- article list -->
		<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.articleNO}</td>
				<td style="padding-left: 20px" id="title">
				<c:if test="${article.level > 1}">		<!-- if article's level is higher than 1 == it's reply -->
					<c:forEach begin="1" end="${article.level}" step="1">	<!-- give space to specify the levels -->
						<span style="padding-left: 20px"></span>
					</c:forEach>
					<span>[RE]: </span>
				</c:if>
				<a href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a> <span style="color: grey;">[${article.commentCount}]</span></td>
				<td>${article.id}</td>
				<td>${article.likeCount}</td>
				<td><fmt:formatDate pattern="yy-MM-dd HH:mm" value="${article.writtenDate}"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
		<!-- Button -->
	
		<div id="contatiner" align="right">
		    <button type="button" class="btn btn-dark" onclick="location.href='${contextPath}/board/articleForm.do'">Write</button>
		</div>
	</div>
	
	<!-- Paging -->

	<div class="row">
	 <div class="col-md-12">
        <nav aria-label="Page navigation">
          <ul class="pagination justify-content-center">
          <c:if test="${pageMaker.prev}">		<!-- Previous page if there are more than 100 articles-->
            <li class="page-item">
               <a class="page-link" href="${ContextPath}/article/listPaging?page=${pageMaker.startPage - 1}" aria-label="Previous">
                   <span aria-hidden="true">«</span>
                   <span class="sr-only">Previous</span>
               </a>
             </li>
          </c:if>
          
          <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
          	<li class="page-item" <c:out value="${pageMaker.criteria.page == idx ? 'class=active' : ''}"/>>
          	 	<a class="page-link" href="${contextPath}/board/listArticles.do?page=${idx}">${idx}</a> 
          	 </li>
          </c:forEach>
             <c:if test="${pageMaker.next && pageMaker.endPage > 0}">		<!-- Next page if there are more than 100 articles-->
             	<li class="page-item">
            		 <a class="page-link" href="${contextPath}/article/listPaging?page=${pageMaker.endPage + 1}" aria-label="Next">
                	 <span aria-hidden="true">»</span>
                 	<span class="sr-only">Next</span>
            		 </a>
            	 </li>
             </c:if>
           </ul>
          </nav>
        </div></div>
</body>
</html>