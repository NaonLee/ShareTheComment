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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

<title>header</title>
</head>
<body>
<!-- Navigation -->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	   <div class="container">
	      <a class="navbar-brand" href="${contextPath}/main.do">ShareTheComment</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        		<span class="navbar-toggler-icon"></span>
      	  </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
        
            <li class="nav-item active">
              <a class="nav-link" href="${contextPath}/main.do">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="${contextPath}/board/listArticles.do">Board</a>
            </li>
            <c:choose>
				<c:when test="${isLogOn==true}">
					<li class="nav-item dropdown">
		              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		               	${logMember.id}
		              </a>
		              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
		                <a class="dropdown-item" href="#">information</a>
		                <a class="dropdown-item" href="/board/mailForm.do">Contact us</a>
		                <div class="dropdown-divider"></div>
		                <a class="dropdown-item" href="${contextPath}/member/logout.do">Logout</a>
		              </div>
		            </li>
				</c:when>
				<c:otherwise>
					<a class="nav-link active" href="${contextPath}/member/loginForm.do">Sign in</a>
				</c:otherwise>
			</c:choose>
              <li class="nav-item">
              <a class="nav-link active" href="${contextPath}/board/mailForm.do">Contact US</a>
            </li>
          </ul>
        </div>
      </div>
  </nav>
  


	  <!-- Menu Toggle Script -->
	<script>
	    $("#menu-toggle").click(function(e) {
	      e.preventDefault();
	      $("#wrapper").toggleClass("toggled");
	    });
 	 </script>
</body>
</html>