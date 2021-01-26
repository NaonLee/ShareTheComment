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
<script src="http://code.jquery.com/jquery-3.4.1.js"></script>


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

/* functions for view article */
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
	
	<!-- Comment -->

	<br><br>
	
	
	
	<div class="col-lg-9 box-body repliesDiv"> 
		<div class="card" style="width: 80%;"> 
			<div class="card-header with-border"> 
				<h3 class="card-title">Comments</h3> 
			</div> 
			<div class="card-body"> 
				<div class="row"> 
					<c:choose>
						<c:when test="${logMember.id == null || logMember.id == ''}">
							<div class="col-sm-12" align="center"><p style="font: bold;">Please login to leave comment</p></div>
						</c:when>
						<c:otherwise>
							<div class="form-group col-sm-10"> 
								<input class="form-control input-sm" id="newComment" type="text" placeholder="Type comment"> 
							</div>	 
							<div class="form-group col-sm-2" hidden="hidden"> 
								<input class="form-control input-sm" id="commentWriter" type="text" value="${logMember.id}" > 
							</div> 
							<div class="form-group col-sm-2"> 
								<button type="button" class="btn btn-primary btn-sm btn-block btn_addComment" > 
									<i class="fa fa-save"></i> Save 
								</button> 
							</div> 
						</c:otherwise>
					</c:choose>
			</div> 
		</div> 
		<div class="card-footer co">
			<ul id="comments"> 
				
			</ul>
		</div> 
	</div> 
	</div> 
	
</body>


<script>
	/* functions for comments */	
	var articleNO = ${article.articleNO}; 

	getReplies(); 
	
	//get comments by articleNO
	function getReplies() {
	$.getJSON("${contextPath}/comments/all/" + articleNO, function (data) { 
		var str = "";
		$(data).each(function () { 
			str += "<li class='commentList' reply_no='"+this.commentNO+"'>" 
			+ "<p id='m_id' class='id'>" + this.id+"</p>"
			+ "<p id='m_content' class='comment_content'>"+ this.comment_content + "</p>"
			+"<div id='test'>"
			+ "<textarea class='form-control input-sm' rows='2' cols='160%' style='display: none;' id='ch_content'>" + this.comment_content+ "</textarea>"
			+"</div>"
			
			/* button */
			+"<div align='right'>"
			
			+ "<button type='button' class='btn btn-sm btn-success' id='btn_mod' >Modify</button> "

			+ "<button type='button' class='btn btn-sm btn-success' id='btn_del' onclick='btn_DeleteComment(" + this.commentNO + ")'>Delete</button>"
			+ "<button type='button' class='btn btn-sm btn-success' id='btn_change' style='display:none; align:right;' onclick='btn_SaveChange("+ this.commentNO +")'>Save changes</button>"
			+ "</div>"
			+ "</li>" + "<hr/>"; 
			});
		$("#comments").html(str); 
		}); 
	}
	
	//enable to modify
	function btn_ModifyComment(){
	//Click modify button->text area to modify content and button for save changes
	
	/* 모디파이 누르면 텍스트에어리아 오픈, save버튼도 ->value는 commentNO로 가져온 commentContent, 고친후에 save하면 comment update 하고 textare, button 둘다 없어짐*/
	}
	
	
	//delete comments
	function btn_DeleteComment(commentNO){
	$.ajax({
		type: "delete",
		url: "${contextPath}/comments/" + commentNO,
		headers : { "Content-type" : "application/json", "X-HTTP-Method-Override" : "DELETE" },
		dataType: "text",
		success: function(data){
			if(data == "DelSuccess"){
				alert("successfully deleted");
			
			} 
			getReplies();
		}
	});
	}
	
	
	$(document).ready(function() {
	
	$("#comments").on("click", ".commentList #btn_mod", function(){
		var comment = $(this).parent().parent();
		var textarea = comment.find("#ch_content");
		var btn_del = comment.find("#btn_del");
		var btn_mod = comment.find("#btn_mod");
		var btn_change = comment.find("#btn_change");
		
		/* make delete, modify button invisible, and make text area and save changes button visible */
		btn_del.css("display", "none");
		btn_mod.css("display", "none");
		btn_change.css("display", "block");
		textarea.css("display", "block");
		
	});
	
	//save comment change
	$("#comments").on("click", ".commentList #btn_change", function(){
		var comment = $(this).parent().parent();
		var commentNO = comment.attr("reply_no");
		var comment_content = comment.find("#ch_content").val();
		
		$.ajax({
			type: "put",
			url: "${contextPath}/comments/" + commentNO,
			headers : {
				"Content-type" : "application/json",
				"X-HTTP-Method-Override" : "PUT" 
				},
	
			dataType: "text",
			data: JSON.stringify({
				comment_content: comment_content
			}),
			success: function(data){
				alert(data);
				getReplies();
			}
		});
		
	});

	//add comments
	$(".btn_addComment").on("click", function(){
		var content = $("#newComment").val();
		var id = $("#commentWriter").val();
		
		$.ajax({
			type: "post",
			url: "${contextPath}/comments",
			headers : {
				"Content-type" : "application/json",
				"X-HTTP-Method-Override" : "POST" 
				},
	
			dataType: "text",
			data: JSON.stringify({
				articleNO: articleNO,
				comment_content: content,
				id: id
			}),
			success: function(data){
				if(data == "Addsuccess"){
					alert("successfully added");
				}
				getReplies();
				content.val("");
				id.val("");
			}
		});
		
	});
	
	
	});
</script>

</html>