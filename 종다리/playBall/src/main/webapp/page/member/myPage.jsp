<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/member/myPage.css'>
</head>
<body>
	<div id="myPage">
		<input type="hidden" id="mid" value="${sessionScope.mid }"/>
		
		<div class="title">
			<h2>회원정보</h2>
		</div>
		
		<div id="info">
			
			<div>
				<span class="label">아이디</span><span>${sessionScope.mid }</span>
			</div>
			
			<div>
				<span class="label">이름</span>
				<span>${vo.name }</span>
			</div>
			
			<div>
				<span class="label">포인트</span>
				<span>${vo.point }</span>
			</div>
			
			<div>
				<span class="label">신고횟수</span>
				<span>${vo.reports }</span>
			</div>
			
			<div>
				<span class="label">번호</span>
				<span>${vo.phone }</span>
			</div>
			
			<div>
				<span class="label">이메일</span>
				<span>${vo.email }</span>
			</div>
			
			<div>
				<span class="label">주소</span>
				<span>${vo.post }</span>
				<span class="block">${vo.address }</span>
				<span class="block">${vo.address2 }</span>
			</div>	
			
			<div>
			   <span class="label">팀이름</span>
			   <span>${vo.tid }</span>
			</div>
		</div>
		
		<div class="btnZone">
			<input type="button" id="moveModifyPage" class="signBtn" value="수정">
			<input type="button" class="signBtn" value="뒤로가기">
		</div>
	</div>
<script src="./lib/jq.js"></script>
<script src="./js/member/myPage.js"></script>
</body>
</html>