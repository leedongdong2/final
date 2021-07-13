<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/member/signUp.css'>
</head>
<body>
	<div id="signUp">
		
		<div class="title">
			<h2>회원가입</h2>
		</div>
		
		<div id="signBox">
				<form name="singUpFrm" method="post">
				
					<label class="block botMargin">아이디</label> 
						<input type="text" name="mid" class="input" placeholder = "영문자,숫자 포함 6~12자"> 
						<input type="button" id="duplicatedId"class="btn" value="중복확인"> 
					
					<label class="block botMargin">비밀번호</label>
						<input type="password" name="password" id="pwd" class="input" placeholder = "영문자,숫자,특수문자 포함 8자리 이상">
						<img src="./page/member/img/eye.jpeg" class="eyeImg" id="eyeImg">
					
					<label class="topMargin botMargin">비밀번호확인</label>
						<input type="password" id="checkPwd" class="input">
						<span class="block" id="confirmPwd"></span> 
					
					<label class="block">이름</label>
						<input type="text" name="name" class="block input"> 
					
					<label class="block">휴대폰</label> 
						<input type="text" name="phone" class="block input"> 
					
					<label class="block">주소</label> 
						<input type="text" name="post" class="post"> 
						<input type="button" class="btn" id="postBtn" value="우편번호" /> 
						<input type="text" name="address" class="block input"> 
						<input type="text" name="address2" class="block input"> 
					
					<label class="block">이메일</label>
						<input type="text" name="email" class="block input"> 
					
					<span class="posi">선호 포지션</span>
					<div class="imgBox">
						<div class="position">
							<img src="./page/member/img/G.jpg" id="guard">
						</div>
						<div class="position">
							<img src="./page/member/img/C.jpg" id="center">
						</div>
						<div class="position">
							<img src="./page/member/img/F.jpg" id="foward">
						</div>
					</div>
					<input type="hidden" id="posit" name="posit" class="block" value="gard">
					
					<div class="btnZone">
						<input type="button" id="signBtn" class="signBtn" value="가입">
						<input type="button" id="cancleBtn" class="signBtn" value="취소">
					</div>
					
				</form>
		</div>
	</div>
<script src="./lib/jq.js"></script>
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/member/signUp.js"></script>
</body>
</html>