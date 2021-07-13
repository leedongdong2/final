<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/member/confirmCertificationPwd.css'>
</head>
<body>
	<div id="confirmCertificationPwd">
		
		<p class="title">비밀번호찾기</p>
		
		<div>
			
			<form name="confirmCertificationPwdForm" method="post">
			
				<div>
				   <input type="text" name="mid" value="${param.mid }">
				   <input type="text" name="email" value="${param.email }">
				   <input type="text" id="receiveCertificationCode" value="${authKey }">
			 	   <input type="text" id="certificationCode" value=""/>
			  	   <input type="button" id="confirmCertification" value="인증하기" />
				</div>	
				
			</form>
			
		
			<span class="notice">고객님의 비밀번호는 <span id="issuePwd">     </span>입니다</span>
			
			<input type="button" value="로그인">
			<span class="goPwd">혹시 비밀번호를 잊어버리셧나요?</span>
			
		</div>
	</div>
	<script src="./lib/jq.js"></script>
<script src="./js/member/confirmCertificationPwd.js"></script>
</body>
</html>