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

	function setPopUp(obj){
		if(obj.checked == true){
			var expireDate = new Date();
			expireDate.setMonth(expireDate.getDay() + 1);
			document.cookie = "notShowPop=" + "true" + ";path=/; expires=" + expireDate.toGMTString();
			window.close();
		}
	}

</script>

<title>Insert title here</title>
</head>
<body>
<div class="contentForm" style="position: relative; width: 300px; height: 350px;">
	<div align="center" id="head">
		<h3>
			Hello This is pop up test! 
		</h3><br><br>
		<div id="body">
			<div class="row">
				<h5>If you want to check out my career, please see the links below!</h5> <br><br>
			</div>
			<div class="row">
				<a href="linkedin.com/in/naon-lee-931028/">Linked In</a>
				<a href="github.com/NaonLee">Github</a>
			</div>
		</div>
	</div>
	
	<div id="foot" style="position:absolute; width:100%; bottom: 0; float: right;">
	<form>
		<input type="checkbox" onClick="setPopUp(this)" class="btn btn-dark btn-sm">Don't want to show for a day
	</form>
	</div>
</div>
</body>
</html>