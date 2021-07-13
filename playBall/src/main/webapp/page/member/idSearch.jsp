<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/member/idSearch.css'>
</head>
<body>
	<div id="idSearch">
		
		<p class="title">아이디찾기</p>
		
		<div>
			
		    <form name="idSearchForm" method="post">
				
				<label>이름</label>
					<input type="text" name="name">
				
				<label>이메일</label>
					<input type="text" name="email">
					<input type="button" id="sendMail" value="인증코드보내기">
			</form>
			
			<span id="movePwdSearch"class="goPwd">혹시 비밀번호를 잊어버리셧나요?</span>
			
		</div>
	</div>
<script src="./lib/jq.js"></script>
<script src="./js/member/idSearch.js"></script>
</body>
</html>