<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball</title>
<link rel = 'stylesheet' type = 'text/css' href = './index.css'>
<script src = './lib/jq.js'></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src = './index.js'></script>
</head>
<body>
<div id="index">
  	<div>
   		<jsp:include page="./page/headFoot/header.jsp"/>
  	</div>
  	<div id="pageBox"></div>
  	<div>
    	<jsp:include page="./page/headFoot/footer.jsp"/>
  	</div>
</div>
<a id = "toTop">▲<br/>Top</a>
</body>
</html>