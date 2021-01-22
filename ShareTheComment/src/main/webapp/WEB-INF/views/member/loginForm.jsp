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


<!-- CSS by amatellanes-->
<style>
body {
    padding-top: 50px;
    font-size: 12px
  }
  .main {
    max-width: 320px;
    margin: 0 auto;
  }
  .login-or {
    position: relative;
    font-size: 18px;
    color: #aaa;
    margin-top: 10px;
            margin-bottom: 10px;
    padding-top: 10px;
    padding-bottom: 10px;
  }
  .span-or {
    display: block;
    position: absolute;
    left: 50%;
    top: -2px;
    margin-left: -25px;
    background-color: #fff;
    width: 50px;
    text-align: center;
  }
  .hr-or {
    background-color: #cdcdcd;
    height: 1px;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
  }
  h3 {
 	 paddint-top: 100px;
    text-align: center;
    line-height: 300%;
  }
</style>

<!-- Login failed -->
<c:choose>
	<c:when test="${result=='loginFailed'}">
	<script>
		window.onload=function(){
			alert("ID or password is not correct. Plese retry");
		}
	</script>
	</c:when>
</c:choose>

<script type="text/javascript">
	function cancel_btn(obj){
		obj.action = "${contextPath}/main.do";
		obj.method="get";
		obj.submit();
	}
</script>
</head>
<body>
	<div class="container">
  <div class="row">

    <div class="main">

      <h3>Please Log In, or <a href="${contextPath}/member/memberForm.do">Sign Up</a></h3>
      <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-6">
          <a href="${NaverUrl}" class="btn btn-lg btn-success btn-block">Naver</a>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6">
          <a href="${GoogleUrl}" class="btn btn-lg btn-info btn-block">Google</a>
        </div>
      </div>
      <div class="login-or">
        <hr class="hr-or">
        <span class="span-or">or</span>
      </div>

      <form role="form" action="${contextPath}/member/login.do" method="post" name="frmLogin" >
        <div class="form-group">
          <label for="inputUsernameEmail">UserID</label>
          <input type="text" name="id" class="form-control" id="inputUsernameEmail">
        </div>
        <div class="form-group">

         
          <label for="inputPassword">Password</label>
          <a class="pull-right" style="padding-left: 10px;" href="${contextPath}/member/findPWForm.do">Forgot password?</a>
          <input type="password" name="pwd" class="form-control" id="inputPassword">
        </div>
        <div class="checkbox pull-right">
          <label>
            <input type="checkbox">
            Remember me </label>
        </div>
        <button type="submit" class="btn btn btn-primary">
          Log In
        </button>
         <button type="button" class="btn btn btn-dark" onclick="cancel_btn(this.form)">
        	 Cancel
        </button>
      </form>

    </div>
    
  </div>
</div>

	
</body>
</html>