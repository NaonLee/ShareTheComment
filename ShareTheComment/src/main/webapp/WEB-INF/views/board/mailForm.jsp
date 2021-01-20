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

	<script type="text/javascript">
		function back(obj){
			obj.action="${contextPath}/main.do";
			obj.method="get";
			obj.submit();
		}
	</script>
	
<meta charset="UTF-8">
<title>Articles</title>
</head>
<body>
<div class="contentForm" align="center">
	<div class="row">
	<div class="col-md-10">
	   <h2>Contact Us</h2>
		<form action="${contextPath}/board/sendEmail.do" method="post">
			<table class="table">
				<tr>
					<td>Email</td>
					<td>
						<c:choose>
							<c:when test="${isLogOn == false || isLogOn == null}">
								<input type="text" name="email" class="form-control">
							</c:when>
							<c:otherwise>
								<input type="text" name="email" class="form-control" value="${logMember.email}" readonly>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" class="form-control" required></td>
				</tr>
				<tr>
					<td>Body</td>
					<td><textarea name="body" class="form-control" rows="5" required></textarea></td>
				</tr>
			</table>		
			 <div class="row">
	             <div class="col-md-10">
	               <div class="well well-sm well-primary">
	                 <div class="form-group">
	                  	<button type="submit" class="btn btn-success btn-sm">
	                    <span class="glyphicon glyphicon-floppy-disk"></span>Send</button>
	                  	<button type="button" class="btn btn-dark btn-sm" onclick="back(this.form)">
	                	<span class="glyphicon glyphicon-eye-open"></span>Cancel</button>
	                 </div>
	               </div>
	              </div>
	           </div>
		</form>
	</div></div>
</div>
</body>