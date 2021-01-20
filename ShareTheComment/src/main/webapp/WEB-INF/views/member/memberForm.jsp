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
	//When user click 'sign up'
	
	function btn_signUp(obj){
		var pwdConfirm = $("#i_pwdConfirm").val();
		var pwd = $("#i_pwd").val();
		var idCheck= $("#idCheck").val();
		
		if(pwdConfirm == pwd){
			$("#confirm").empty();
			if(idCheck == 'true'){			//if id, password are both ok, proceed sign-up
				obj.submit();
			} else {						//if id 
				alert("ID check must to be done");
			}
		}  else{
			$("#confirm").text("Please confirm the password")	//if password and password aren't in match
		}
	}
	
	//Check if ID is already in use
	function btn_check(obj){
		var id = $("#i_id").val();
		
		$.ajax({
			type: "post",
			async: false,
			url: "${contextPath}/member/idCheck",
			data: {
				id: id
			},
			success: function(data){
				if(data == 'exist'){	//There is same id in DB
					$("#checkResult").text("ID is already in use");
					$("#idCheck").val('false');
				} else if(data == 'empty'){			//user didn't enter the id
					$("#checkResult").text("Please enter the ID");
					$("#idCheck").val('false');
				}
				else{
					$("#checkResult").text("Good to go!");
					$("#idCheck").val('true');
				}
			},
			error: function(){
				alert("error");
			},
			complete: function(){}	
		});
	}
</script>

<title>MemberForm</title>
</head>
<body>
	<div class="view full-page-intro">
        <div class="mask rgba-black-light d-flex justify-content-center align-items-center">
          <div class="container">
            <div class="row wow fadeIn">
               <div class="col-md-6 mb-4 white-text text-center text-md-left">
                <h1 class="display-4 font-weight-bold">Sign UP</h1>
                <hr class="hr-light">
                <p>
                  <strong>For the more information</strong>
                </p>
                <p class="mb-4 d-none d-md-block">
                  <strong>BlahBlah</strong>
                </p>
              </div>
              <div class="col-md-6 col-xl-5 mb-4">
                <div class="card">
                  <div class="card-body">
                    <form action="${contextPath}/member/addMember.do" method="post">
                      <p class="h4 text-center mb-4">Sign up</p>
                      <div class="md-form">
                         <label for="i_names">Your name</label>
                         <input type="text" id="i_name" name="name" class="form-control"> 
                      </div>
                      <div class="md-form">
                         <label for="i_email">Your email</label>
                         <input type="text" id="i_email" name="email" class="form-control">
                      </div>
                      <div class="md-form">
                         <label for="i_id">Your ID</label>
                         <input type="text" id="i_id" name="id" class="form-control">
                         <input type="button" id="id_check" onclick="btn_check(this.form)" value="Check">
                         <div id="checkResult"></div>				<!-- Show user the check result -->
                         <div id="idCheck" hidden="hidden"></div>	<!-- id check value (true or false) -->
                      </div>
                      <div class="md-form">
                        <label for="i_pwd">Your password</label>
                        <input type="password" id="i_pwd" name="pwd" class="form-control">
                      </div>
                      <div class="md-form mb-3">
                            <label for="i_pwdConfirm">Confirm Your password</label>
                            <input type="password" id="i_pwdConfirm" class="form-control"> <div id="confirm" style="color:red;"></div>
                      </div>
                      <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="defaultCheck12">
                            <label for="defaultCheck12" class="grey-text">Accept the
                                <a href="#" class="blue-text"> Terms and Conditions</a>
                            </label>
                        </div>
                      <div class="text-center mt-4">
                       <button class="btn btn-primary" type="button" onclick="btn_signUp(this.form)">SignUp</button>
                    	<button class="btn btn-primary" type="button" onclick="location.href='${contextPath}/main.do'">Cancel</button>
                    </div>
                   </form> 
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
</body>
</html>