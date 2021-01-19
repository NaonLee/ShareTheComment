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
<c:if test="${isLogOn == false || isLogOn == null}">		<!-- if user isn't logged in, send user to login page -->
	<script type="text/javascript">
		window.onload=function(){
			alert("Please login to write an article!");
			location.href="${contextPath}/member/loginForm.do";
		}
	</script>
	</c:if>
	
<script type="text/javascript">
	function back(obj){
		obj.action="${contextPath}/board/viewArticle.do?articleNO=${parentNO}";
		obj.submit();
	}
</script>

<meta charset="UTF-8">
<title>Reply article</title>
</head>
<body>
	
	<div class="contentForm" align="center">
   		<div class="row">
       	 	<div class="col-md-10">
	       	 	<h2>Reply Form</h2>
	       	 	<form action="${contextPath}/board/replyArticle.do" method="post">
	     			<div class="row">
		        		 <div class="col-md-12">
							<table class="table table-striped table-bordered"> 
								<thead> 
									<tr> 
										<th width="30%">Writer</th> 
										<td width="70%">${logMember.id}</td> 
										<td hidden="hidden"><input type="text" name="id" value="${logMember.id}" /></td>
										<td hidden="hidden"><input type="text" name="parentNO" value="${parentNO}"></td>
									</tr> 
									<tr> 
										<th style="padding-top: 15px">Title</th> 
										<td><input type="text" class="form-control" placeholder="Title" name="title" required /></td> 
									</tr> 
								</thead> 
								<tbody> 
									<tr> 
										<td colspan="2"> 
										 <textarea class="form-control" placeholder="Content" rows="5" name="content" required></textarea> 
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
           		
           		
	           		<div class="row">
             <div class="col-md-10">
               <div class="well well-sm well-primary">
                 <div class="form-group">
                  	<button type="submit" class="btn btn-success btn-sm">
                    <span class="glyphicon glyphicon-floppy-disk"></span>Save</button>
                  	<button type="button" class="btn btn-dark btn-sm" onclick="back(this.form)">
                	<span class="glyphicon glyphicon-eye-open"></span>Cancel</button>
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