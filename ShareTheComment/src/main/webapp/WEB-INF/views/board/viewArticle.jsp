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
<script type="text/javascript">
	function back(obj){
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	function modify(obj){
		document.getElementById("id_title").disabled=false;
		document.getElementById("id_content").disabled=false;
		document.getElementById("modify_button").style.display="block";
		document.getElementById("btn").style.display="none";
	}
	
	function save(obj){
		obj.action="${contextPath}/board/modArticle.do";
		obj.submit();
	}
</script>


<title>View article</title>
</head>
<body>
	<h2 align="center">Article</h2>
	
	<form action="${contextPath}/board/modArticle.do" method="post" name="artForm">
		<table width="80%">
		<tr>
			<td>Article No.</td>
			<td><input type="text" name="articleNO" value="${article.articleNO}" disabled></td>
			<td><input name="articleNO" value="${article.articleNO}" hidden="hidden"></td>
		</tr>
		<tr>
			<td>title</td>
			<td><input type="text" name="title" id="id_title" value="${article.title}" disabled></td>
		</tr>
		<tr>
			<td>Content</td>
			<td><textarea rows="20" cols="20" name="content" id="id_content" disabled>${article.content}</textarea></td>
		</tr>
		<tr id="modify_button" style="display: none">
			<td><input type="button" value="Save" onclick="save(this.form)"><input type="button" value="Cancel" onclick="back(this.form)"></td>
		</tr>
		<tr id="btn">
			<td>&nbsp;</td>
			<td><input type="button" value="Back" onClick="back(this.form)">
				<c:if test="${logMember.id == article.id}">
					<input type="button" value="Modify" onclick="modify(this.form)"><input type="button" value="Delete">
				</c:if>
			</td>
		</tr>
		</table>	
	</form>
	

</body>
</html>