<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/member/pwdSearch.css'>
</head>
<body>
	<div id="passSearch">
		<span class="title">비밀번호재발급-(임시비밀번호는 꼭 수정해주세요)</span>
		<div>
			<form name="passSearchForm"> 
	
				<label>아이디</label>
					<input type="text" name="mid">
				
				<label>이메일</label>
					<input type="text" name="email">
				
				<input type="button" id="sendMail" value="인증코드발송">
				
			</form>
		
		</div>
	</div>
	<script src="./lib/jq.js"></script>
<script src="./js/member/pwdSearch.js"></script>
</body>
</html>