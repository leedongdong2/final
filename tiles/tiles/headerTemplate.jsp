<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<link href="${path}/resources/css/header.css" rel="stylesheet" >
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span id="menuBar" class="menuBar">menu</span>
	<nav id="topMenu">
		<ul>
			<li><a class="menuLink" href="#">About</a></li>
			<li><a class="menuLink" href="#">MusicVideo</a></li>
			<li><a class="menuLink" href="#">Branded</a></li>
			<li><a class="menuLink" href="#">contact</a></li>
		</ul>
	</nav>
	<span class="title">Here To Film</span>
</body>
<!-- 스크립트 -->
<script src = '${path}/resources/js/header.js'></script>
<script src = '${path}/resources/js/lib/jq.js'></script>
</html>