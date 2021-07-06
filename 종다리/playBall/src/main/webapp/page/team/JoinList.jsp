<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Play ball - Team Join List</title>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</head>
<body>
<div id = 'team'>
<form name = 'frm_team' id = 'frm_team'>
	<h2>${sessionScope.tid }</h2>
	<h3>가입신청목록</h3>
		<section class = 'jTitle'>
			<span class = 'mid'>가입자명</span>
			<span class = 'posit'>포지션</span>
			<span class = 'email'>이메일</span>
			<span class = 'joinDate'>신청날짜</span>
		</section>
		<div class = 'jItems'>
			<c:forEach var='jVo' items="${list}">
				<div class = 'jItem'>
					<span class = 'mid'>${jVo.mid }</span>
					<span class = 'posit'>${jVo.posit }</span>
					<span class = 'email'>${jVo.email }</span>
					<span class = 'joinDate'>${jVo.jDate }</span>
					<input type = 'button' value = '수락' onclick = "team.acceptJoin('${jVo.mid}')">
					<input type = 'button' value = '거절' onclick = "team.rejectJoin('${jVo.mid}')">
				</div>
			</c:forEach>
		</div>
		<div id='pageJ'>
		<c:if test="${page.startPage>1 }">
			<input type='button' value='&lt&lt' onclick='team.jmove(1)'/>
			<input type='button' value='&lt' onclick='team.jmove(${page.startPage-1 })'/>
		</c:if>
		<c:forEach var='p' begin='${page.startPage }' end='${page.endPage }'>
			<input type='button' value='${p }' onclick ='team.jmove(${p})' />
		</c:forEach>
	
		<c:if test="${page.endPage<page.totPage }">
			<input type='button' value='&gt' onclick ='team.jmove(${page.endPage+1})'/>
			<input type='button' value='&gt&gt' onclick ='team.jmove(${page.totPage})'/>
		</c:if>			
		</div>
		<input type='hidden' name='nowPage' value='${param.nowPage }'/>
		<input type='hidden' name='serial' value='${param.serial }'/>
		<input type = "hidden" name = "mid" value = "${sessionScope.mid }"/>
		<input type = "hidden" name = "tid" value = "${sessionScope.tid }"/>
		<!-- <input type = 'button' id = 'btnClose' value = "닫기"/> -->
</form>
</div>
</body>
</html>