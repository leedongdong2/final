<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/member/signModify.css'>
</head>
<body>
	<div id="signModify">
	
		<div class="title">
			<h2>회원수정</h2>
		</div>
		
		<div id="signBox">
			
			<form name="updateMemberForm" method="post">
				<input type="hidden" name="mid" value="${sessionScope.mid }"/>
				
				<label class="block botMargin">기존 비밀번호(필수)</label>
					<div>
						<input type="password" name="oriPassword" id="oriPwd" class="input"> 
						<img src="./page/member/img/eye.jpeg" class="eyeImg" id="eyeImg">
					</div>
				
				<label class="block botMargin">새 비밀번호</label>
					<div>
						<input type="password" name="password" id="newPwd" class="input">
					</div>
				<label class="topMargin botMargin">새 비밀번호확인</label>
					<div>
						<input type="password" id="checkNewPwd" class="input"> 
					</div>
				<span class="block" id="confirmPwd">비밀번호 체크하는 곳</span> 
				
				<label class="block">이름</label>
					<input type="text" name="name" class="block input" value="${vo.name }"> 
				
				<label class="block">휴대폰</label>
					<input type="text" name="phone" class="block input" value="${vo.phone }"> 
				
				<label class="block">주소</label>
					<input type="text" name="post" class="post" value="${vo.post }"> 
					<input type="button" class="btn" id="postBtn" value="우편번호" /> 
					<input type="text" name="address" class="block input" value="${vo.address }">
					<input type="text" name="address2" class="block input" value="${vo.address2 }"> 
				
				<label class="block">이메일</label>
					<input type="text" name="email" class="block input" value="${vo.email }"> 
				
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
				<input type="hidden" id="posit" name="posit" class="block" value="${vo.posit }">
				<input type="hidden" id="tid" name ="tid" value="${sessionScope.tid }">
				<div class="btnZone">
					<input type="button" id="updateSign" class="signBtn" value="확인"> 
					<input type="button" id="showDelete" class="signBtn" value="탈퇴">
					<input type="button" class="signBtn" value="취소">
				</div>
			</form>
			
			<div id="deleteZone" class="hide">
				<div id="confirmZone">
					<div id=confirmMsg>정말로 삭제하시겠습니까?</div>
					<input type="button" id="deleteBtn" value="삭제">
					<input type="button" id="deleteCancel"value="취소">
				</div>
			</div>
		
		</div>
	</div>
<script src="./lib/jq.js"></script>
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/member/signModify.js"></script>
</body>
</html>