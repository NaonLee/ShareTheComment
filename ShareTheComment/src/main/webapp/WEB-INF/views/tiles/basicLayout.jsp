<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>

#header {
	padding: 5px;
	margin-bottom: 5px;
}
#sidebar {
	width: 20%;
	height: 500px;
	padding: 5px;
	margin-left: 15px;
	margin-right: 5px;
	margin-bottom: 5px;
	float: left;
}

#content {
	width: 70%;
	padding: 5px;
	margin-right: 5px;
	margin-left: 5%;
	float:left;
}
#footer {
        clear: both;
        padding: 5px;
      }
</style>


<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<div id="wrapper">
		<div id="header"><tiles:insertAttribute name="header"/></div>
		<div id="sidebar"><tiles:insertAttribute name="side"/></div>
		<div id="content"><tiles:insertAttribute name="body"/></div>
		<div id="footer"><tiles:insertAttribute name="footer"/></div>
	</div>
</body>
</html>