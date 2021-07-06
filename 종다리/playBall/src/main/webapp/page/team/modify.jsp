<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball - Team Modify</title>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</head>
<body>
<div id = 'team'>
	<h2> 팀정보 수정 </h2>
	<form name = 'frm_team' id = 'frm_team'>
		<label> 팀명 : 
			<input type = 'text' name = 'tid' value = '${tvo.tid }' readOnly/>
		</label>
		<label>주장 : 
			<input type = 'text' name = 'lmid' value = '${tvo.mid }' readOnly/>
		</label>
		<br/>
		<img src = './img/${tvo.pic}' class = 'photo' id = 'photo' width='120px' height='150px'/>
		<br/>
		<input type= 'file' name = 'file' id = 'image'/>
		<input type="hidden" name="pic" id="fileName">
		<br/>
		
		<textarea name = 'intro'>${tvo.intro }</textarea>
		
		<div id = 'btn_zone'>
			<input type = 'button' id = 'btnModifyR' value = '수정'/>
			<input type = 'button' id = 'btnSelect' value = '취소'/>
		</div>
		
		<input type='hidden' name='nowPage' value='${param.nowPage }'/>
		<input type='hidden' name='findStr' value='${param.findStr }'/>
		<input type='hidden' name='serial' value='${param.serial }'/>
		<input type='hidden' name='mid' value = '${sessionScope.mid }'/>
	</form>
</div>
</body>
</html>