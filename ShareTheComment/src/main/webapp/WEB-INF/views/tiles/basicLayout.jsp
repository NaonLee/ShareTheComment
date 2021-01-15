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
<title><tiles:insertAttribute name="title"/>
</head>
<body>
	<div>
		<div><tiles:insertAttribute name="header"/></div>
		<div width="15%"><tiles:insertAttribute name="side"/></div>
		<div width="75%"><tiles:insertAttribute name="body"/></div>
		<div><tiles:insertAttribute name="footer"/></div>
	</div>
</body>
</html>