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
<style>

#header {
	padding: 5px;
	margin-bottom: 5px;
}
#sidebar {
	width: "15%";
	padding: 5px;
	float: left;
	padding-top: 5px;
}

#content {
	width: "75%";
	padding: 5px;
	margin-right: 5px;
}
#footer {
        clear: both;
        padding: 5px;
      }
</style>


<title><tiles:insertAttribute name="title"/></title>
</head>
<body>
	<div>
		<div id="header"><tiles:insertAttribute name="header"/></div>
		<div id="sidebar"><tiles:insertAttribute name="side"/></div>
		<div id="content"><tiles:insertAttribute name="body"/></div>
		<div id="footer"><tiles:insertAttribute name="footer"/></div>
	</div>
</body>
</html>