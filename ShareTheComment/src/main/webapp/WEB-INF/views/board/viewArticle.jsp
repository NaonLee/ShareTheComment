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
	
	function modify_article(obj){
		document.getElementById("id_title").disabled=false;
		document.getElementById("id_content").disabled=false;
		document.getElementById("modify_btn").style.display="block";
		document.getElementById("btn").style.display="none";
	}
	
	function save_change(obj){
		obj.action="${contextPath}/board/modArticle.do";
		obj.submit();
	}
	
	function remove_article(action, articleNO){
		var form = document.createElement("form");	//create the form for removeArticle
		form.setAttribute("method", "post");		//set form method 'post'
		form.setAttribute("action", action);		//set url
		
		var article = document.createElement("input");	//create input tag
		article.setAttribute("type", "hidden");			//type
		article.setAttribute("name", "articleNO");		//name
		article.setAttribute("value", articleNO)		//value
		
		form.appendChild(article);						//put input into form
		document.body.appendChild(form);				//put form into body
		form.submit();
	}
	
	function reply_article(action, parentNO){
		var form = document.createElement("form");	//create the form for reply
		form.setAttribute("method", "post");		//set form method post
		form.setAttribute("action", action);		//set ulr(*Form)
		form.setAttribute("hidden", "hidden");
		
		var article = document.createElement("input");	//create input
		article.setAttribute("type", "text");			//type
		article.setAttribute("name", "parentNO");		//name (current article number will be the parent number)
		article.setAttribute("value", parentNO);		//value
		
		
		form.appendChild(article);
		document.body.appendChild(form);
		form.submit();
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
		<tr id="modify_btn" style="display: none">
			<td><input type="button" value="Save" onclick="save_change(this.form)"><input type="button" value="Cancel" onclick="back(this.form)"></td>
		</tr>
		<tr id="btn">
			<td>&nbsp;</td>
			<td><input type="button" value="Reply" onClick="reply_article('${contextPath}/board/replyForm.do', ${article.articleNO})">
				<input type="button" value="Back" onClick="back(this.form)">
				<c:if test="${logMember.id == article.id}">
					<input type="button" value="Modify" onclick="modify_article(this.form)">
					<input type="button" value="Delete" onclick="remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
				</c:if>
			</td>
		</tr>
		</table>	
	</form>
	

</body>
</html>