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
<title>Modify member</title>
</head>
<body>

	
		<div class="view full-page-intro" align="center">
		
        <div class="mask rgba-black-light d-flex justify-content-center align-items-center">
        
          <div class="container">
              <div class="col-md-6 col-xl-5 mb-4">
                <div class="card">
                <h2 align="center">Modify Member</h2>
                  <div class="card-body">
                    <form action="${contextPath}/member/modMember.do" method="post">
                      <div class="md-form">
                         <label for="i_names">ID</label>
                         <input type="text" id="i_id" name="id" value="${member.id}" class="form-control" readonly="readonly"> 
                      </div>
						<div class="md-form">
                        <label for="i_pwd">Password</label>
                        <input type="password" id="i_pwd" name="pwd" class="form-control" value="${member.pwd}">
                      </div>
                      <div class="md-form">
                         <label for="i_id">Name</label>
                         <input type="text" id="i_name" name="name" class="form-control" value="${member.name}">
                      </div>
                       <div class="md-form">
                         <label for="i_email">Email</label>
                         <input type="text" id="i_email" name="email" class="form-control" value="${member.email}">
                      </div>
                      
                      <div class="text-center mt-4">
                       <button class="btn btn-primary" type="submit">Apply change</button>
                    	<button class="btn btn-primary" type="button">Cancel</button>
                    </div>
                   </form> 
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
	
</body>
</html>