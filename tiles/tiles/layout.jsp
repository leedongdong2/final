<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/css/layout.css" rel="stylesheet" >
</head>
<body>
<div class="wrap">
	<div class="header">
 		<tiles:insertAttribute name="header" />
 	</div>
 	<div class="body">
 		<tiles:insertAttribute name="body" />
 	</div>
 	<div class="footer">
 		<tiles:insertAttribute name="foot" />
 	</div>
 	</div>
</body>
</html>