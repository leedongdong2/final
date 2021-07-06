<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src='./lib/jq.js'></script>
</head>
<body>
<input type='button' id='btn'>
<!-- 이미지 지도를 표시할 div 입니다 -->
<div id="map"></div>

<script>
$("#btn").click(function(){
	$("#pageBox").load("./page/stadium/View.jsp");
})
</script>
</body>
</html>