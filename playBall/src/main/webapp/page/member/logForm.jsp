<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/member/logForm.css'>
</head>
<body>
	<div class="logForm">
		<div class="logBox">  
			
			<h2 class="title">PLAY BALL</h2>
			
			<form name="logForm" method="post">
				<input type="text" name="mid" class="inputLog"  placeholder="아이디">
				<br/>
				<input type="password" id="password" name="password" class="inputLog" placeholder="비밀번호">
				<br/>
				<input type="button" id="logBtn" class="logBtn" value="LOGIN">
			</form>
			
			<div class="logService">
				<span id="moveIdSearch" class="service">아이디찾기</span><span class="i">｜</span><span id="movePwdSearch" class="service">비밀번호찾기</span><span class="i">｜</span><span id="moveSingUp" class="service">회원가입</span>
			</div>
			
		</div>
	</div>
<script src="./lib/jq.js"></script>
<script src="./js/member/logForm.js"></script>
</body>
</html>