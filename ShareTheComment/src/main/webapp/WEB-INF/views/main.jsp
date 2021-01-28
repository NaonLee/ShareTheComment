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
	
	window.onload = popup;
	
	function popup(){
		notShowPop = getCookieValue();
		if(notShowPop != "true"){
			window.open("${contextPath}/popUp.do", "pop", "width=350px, height=400px, history=no, resizable=no, status=no, scrollbars=no, menubar=no");
		}
	}
	
	function getCookieValue(){
		var result="false";
		if(document.cookie != ""){
			cookie = document.cookie.split(";");
			for(var i=0; i<cookie.length; i++){
				element=cookie[i].split("=");
				value=element[0];
				value=value.replace(/^\s*/,'');
				if(value=="notShowPop"){
					result = element[1];
				}
			}
		}
		return result;
	}
	
	function deleteCookie(){
		document.cookie = "notShowPop=" + "false" + ";path=/; expires=-1";
	}
	
</script>

</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div style="position: relative; background-color: #E6E6FA; height: 30%; padding-top: 5%; padding-bottom: 10%; margin-bottom: 2%;">
				<div align="center">
					<h1><a href="./" rel="home" >ShareCommentLike</a></h1>
					<p style="color: #696969;">Let's share thoughts!</p>
				</div>
			</div>
			<div style="float:left; width:40%; text-align:right; margin-right: 10%;">
				<h1> ShareCommentLike? </h1><hr/>
				<p style="font-style: italic; color: gray;">Share your opinion, thoughts, and feelings!<br><br>
				</p>
				<p><span style="font-weight: bold;">ShareCommentLike</span> provides you an awesome platform.<br>
					Write your own article on the board or leave comments after sign-in. (You can sign-in with Google or Naver)<br><br>
					Dive into our community, like other's articles and share your perspective! Every opinion is welcome, however do not forget to respect other people!<br><br>
					If you have any concern or suggestion, you can contact me with 'Contact US' in the top right corner of the page.</p>
			</div>
			
			<div class="col-md-6" style="float: left; width:45%; margin-left: 5%;">
				<a href="https://www.linkedin.com/in/naon-lee-931028/"><img  height="90%" width="90%" src="${contextPath}/resources/images/Profile.JPG"></a>
			</div>
		</div>
	</div>
</body>
</html>