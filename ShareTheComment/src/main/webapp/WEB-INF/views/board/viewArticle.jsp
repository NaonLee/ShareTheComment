<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
	#img-btn {
		background: url("${contextPath}/resources/images/like_2.jpg") no-repeat;
		border: none;
		cursor: pointer;
		width:70px;
		height:32px;
	}
</style>

<script type="text/javascript">
	function back(obj){
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	//move to modify page (only writer can see the button)
	function modify_article(action, articleNO){
		var form = document.createElement("form");	//create the form for modArticleForm
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
	
	//delete current article (only writer can see the button)
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
	
	//reply to article
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
	
	//Like button
	function fn_like(){
		var articleNO = ${article.articleNO};
		
		$.ajax({
			type: "post",
			async: false,
			url: "${contextPath}/like",
			data: {
				articleNO: articleNO,
			},
			success: function(data){
				alert(data);
			},
			error: function(){
				alert("error");
			},
			complete: function(){}	
		});
		fn_likeCount();
	}
	window.onload= fn_likeCount();
	function fn_likeCount(){
		$.ajax({
			type: "post",
			async: false,
			url: "${contextPath}/likeCount",
			dataType: "text",
			data: {
				articleNO: ${article.articleNO}
			},
			success: function(data){
				$(likeCount).text(data);
			}
		});
	}
</script>



<title>View article</title>
</head>
<body>
	<div class="contentForm" align="center" style="width: 85%;">
   		<div class="row">
       	 	<div class="col-md-10">
	       	 	<h2>Article</h2>
	     		<div class="row">
		         <div class="col-md-12">
		         
		         <div style="clear:both; height:30px;">
					<div style="float:left; margin-top:6px;">
						<span>Written Date: <fmt:formatDate pattern="yy-MM-dd HH:mm" value="${article.writtenDate}"/></span>
					</div>
					<div style="float:right; padding-left: 10px;"><input type="button" id="img-btn" onClick="fn_like()"></div>
					<div style="float:right;"><button type="button" class="btn btn-dark btn-sm" onClick="location.href='${contextPath}/board/listArticles.do'">Back to list</button></div>
				</div>

			<table class="table"> 
				
				<thead> 
					<tr> 
						<td colspan="2" style="padding: 8px;">
							<div style="color:#505050; font-size:18px; font-weight:bold; word-break:break-all;">${article.title}</div>
						</td> 
					</tr> 
					<tr> 
						<td>Writer: <span style="font-weight:bold; padding-left: 3px">${article.id}</span></td>
						<td style="float: right;">Like: <span style="font-weight:bold;" id="likeCount">${article.likeCount}</span></td>
					</tr> 
					
				</thead> 
				<tbody> 
					<tr> 
						<td colspan="2" height="400" style="word-break:break-all; padding: 3%;"> 
						${article.content}
						</td> 
					</tr> 
					<tr> 
						<th style="padding-top: 15px">Attached file</th> 
						<td>file name</td> 
					</tr> 	
				</tbody> 
			</table>		
            	 </div>  
           		</div>
           		
           		<!-- Button -->
           		<div class="row">
             		<div class="col-md-10">
	              		 <div class="well well-sm well-primary">
							<div class="form-group" id="btn" style="clear:both;">
								<div style="float: left;">
									<button type="button" class="btn btn-success btn-sm" onClick="location.href='${contextPath}/board/listArticles.do'">Back</button>
									<button type="button" class="btn btn-success btn-sm" onClick="location.href='${contextPath}/board/articleForm.do'">Write</button>
								</div>
								<div style="float: right;">
									<c:if test="${logMember.id == article.id}">	<!-- only writer can see these buttons -->
										<input type="button" class="btn btn-success btn-sm" value="Modify" onclick="modify_article('${contextPath}/board/modArticleForm.do', ${article.articleNO})">
										<input type="button" class="btn btn-success btn-sm" value="Delete" onclick="remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
									</c:if>
									<button type="button" class="btn btn-success btn-sm" onClick="reply_article('${contextPath}/board/replyForm.do', ${article.articleNO})">Reply</button>
								</div>
	                		 </div>
               			</div>
              		</div>
          		 </div>
          		
       		 </div>
  		</div>
	</div>

	

</body>
</html>