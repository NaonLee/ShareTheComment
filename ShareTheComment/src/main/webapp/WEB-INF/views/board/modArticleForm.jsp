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
		obj.action="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}";
		obj.submit();
	}
	
	function readURL(input){
		if(input.files && input.files[0]){
			var reader = new FileReader();
			reader.onload = function(e){
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	var cnt = 1;
	function fn_addFile(){
		$("#d_file").append("<br>" + "<input type='file' name='file" + cnt + '/>");
		cnt++;
	}
	
</script>

<title>Modify article</title>
</head>
<body>
	<div class="contentForm" align="center">
   		<div class="row">
       	 	<div class="col-md-10">
	       	 	<h2>Modify article</h2>
	       	 	<form action="${contextPath}/board/modArticle.do"method="post" enctype="multipart/form-data">
	     			<div class="row">
		        		 <div class="col-md-12">
							<table class="table table-striped"> 
								<thead> 
									<tr> 
										<th width="30%">Writer</th> 
										<td width="70%">${article.id}</td> 
										<td><input type="text" name="articleNO" value="${article.articleNO}" hidden="hidden"/></td>
									</tr> 
									<tr> 
										<th style="padding-top: 15px">Title</th> 
										<td><input type="text" name="title" value="${article.title}" class="form-control" ></td> 
									</tr> 
								</thead> 
								<tbody> 
									<tr> 
										<td colspan="2"> 
										<textarea class="form-control" rows="20" name="content" >${article.content}</textarea> 
										</td> 
									</tr> 
									<tr> 
										<th style="padding-top: 15px">Attached file</th> 
										<td><input type="file" class="btn btn-default" name="imageFileName" onchange="readURL(this);"></td> 
									</tr> 	
								</tbody> 
							</table>		
            			 </div>  
           			</div>
           		
           		<!-- Button -->
	           		<div class="row">
	             		<div class="col-md-10">
		              		 <div class="well well-sm well-primary">
								<div class="form-group" id="btn">
		                  			<input type="submit"  class="btn btn-success btn-sm" value="Save">
									<input type="button"  class="btn btn-success btn-sm" value="Back" onClick="back(this.form)">
		                		 </div>
	               			</div>
	              		</div>
	          		 </div>
	          	</form>	<!-- End form -->
       		 </div>
  		</div>
	</div>

</body>
</html>