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
	<c:if test="${isLogOn == false || isLogOn == null}">
	<script type="text/javascript">
		window.onload=function(){
			alert("Please login to write an article!");
			location.href="${contextPath}/member/loginForm.do";
		}
	</script>
	</c:if>

<meta charset="UTF-8">
<title>Articles</title>
</head>
<body>

	<div class="container" align="center">
    	<div class="row">
       	 <div class="col-md-10">
       	 	<h2>Article form</h2>
			<form action="${contextPath}/board/addArticle.do"method="post" >
     		<div class="row">
	         <div class="col-md-12">
				
					<div hidden="hidden"><input type="text" name="id" value="${logMember.id}"></div>
                   	<div class="form-group">
                       <input type="text" class="form-control" placeholder="Title" name="title" required />
                   	</div>
                   	<div class="form-group">
                       <textarea class="form-control" placeholder="Content" rows="5" name="content" required></textarea>
                   	</div>
                 
             </div>  
           </div>
           <div class="row">
             <div class="col-md-10">
               <div class="well well-sm well-primary">
                 <div class="form-group">
                  	<button type="submit" class="btn btn-success btn-sm">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save</button>
                  	<button type="button" class="btn btn-default btn-sm">
                	<span class="glyphicon glyphicon-eye-open"></span>Preview</button>
                 </div>
               </div>
              </div>
           </div>
          </form>
        </div>
 	  </div>
	</div>

</body>
</html>