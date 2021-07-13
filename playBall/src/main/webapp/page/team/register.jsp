<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball - Team Register</title>
<link rel='stylesheet' type='text/css' href='./css/team/register.css'>
</head>
<body>

<div id = 'team'>
	<h2> 팀 등록 </h2>
	<form name = 'frm_team' id = 'frm_team'>
		<div class='img'>
		<img src = 'http://placehold.it/606x366' class = 'photo' id = 'photo'/><br/>
		<input type= 'file' name = 'file' id = 'image'/>
		<input type="hidden" name="pic" id="fileName">
		</div>
		
		<div class='doc'>
		<label> 작성자 : </label>
			<input type = 'text' class='mid' name = 'mid' value = '${sessionScope.mid }' readOnly/>
		
		<br/><br/>
		<label> 팀명 : </label>
			<input type = 'text' class='tid' name = 'tid'/>
	
		<input type = 'button' id = 'checkTid' value = '중복확인'/>
		<br/>
		<br/>
		<textarea name = 'intro' placeholder= '팀 소개를 적어주세요' cols="40" rows="13"></textarea>
		</div>
		
		
		<input type = 'hidden' name = 'nowPage' value = '${param.nowPage }'/>
	</form>
		<div id = 'btn_zone'>
			<input type = 'button' id = 'btnTRegR' value = '등록'/>
			<input type = 'button' id = 'btnSelect' value = '취소' onclick = 'team.list()'/>
		</div>
</div>
<script src="./lib/jq.js"></script>
<script src="./js/team/register.js"></script>
</body>
</html>