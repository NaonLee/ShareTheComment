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


<c:choose>
	<c:when test="${fail=='failed'}">	<!-- There is no member registered with id and name entered -->
	<script type="text/javascript">
		window.onload=function(){
			alert("ID or Name is not correct. Please retry.");
		}
	</script>
	</c:when>
	<c:when test="${fail=='suceed;}">	<!-- successfully sent email -->
		<script type="text/javascript">
			window.onload=function(){
				alert("Email has been sent");
			}
		</script>
	</c:when>
</c:choose>
	

<script type="text/javascript">
	function cancel_btn(obj){
		obj.action = "${contextPath}/member/loginForm.do";
		obj.submit();
	}
	
</script>
</head>
<body>
<div class="contentForm" align="center">
   		<div class="row">
       	 	<div class="col-md-10">
	       	 	<h2>Write article</h2>
		       	 	<h4>We will send your password to your email that was given when you sign-in</h4>
		       	 	<h5 style="color:red;">If the name and ID are not in match, email will not be sent</h5>
	       	 	<form action="${contextPath}/member/findPwd.do" method="post" >
	     			<div class="row">
		        		 <div class="col-md-10" align="center">
							<table class="table"> 
								<tr> 
									<th>ID</th> 
									<td><input type="text" class="form-control" name="id" required/></td>
								</tr> 
								<tr> 
									<th style="padding-top: 15px">Name</th> 
									<td><input type="text" class="form-control" name="name" required /></td> 
								</tr> 
							</table>		
            			 </div>  
           			</div>
           		
           		<!-- button -->
				     <div class="row">
			             <div class="col-md-10">
			               <div class="well well-sm well-primary">
			                 <div class="form-group">
			                  	<button type="submit" class="btn btn-success btn-sm">
			                    <span class="glyphicon glyphicon-floppy-disk"></span>Send</button>
			                  	<button type="button" class="btn btn-dark btn-sm" onclick="cancel_btn(this.form)">
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