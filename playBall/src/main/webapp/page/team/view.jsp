<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball - Team view</title>
<link rel='stylesheet' type='text/css' href='./css/team/team.css'>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</head>
<body>
<div id = 'team'>
	<form name = 'frm_team' id = 'frm_team'>
		<input type="hidden" name="recordNowPage" value="${param.recordNowPage == null ? 1 : param.recordNowPage}"/>
		
		<label> 팀명 : 
			<input type = 'text' id= 'tid' name = 'tid' value = '${tvo.tid }' readOnly/>
		</label>
		
		<label>주장 : 
			<input type = 'text' id = 'lmid' name = 'lmid' value = '${tvo.mid }' readOnly/>
		</label>
		
		<label id = 'hit_label'>조회수 :
			<input type = 'text' name = 'hit' id = 'hit' value = '${tvo.hit }' readOnly/>
		</label>
		<br/>
		<div class='img'>
		<img src = './img/${tvo.pic}' class = 'photo' id = 'photo' width='120px' height='150px'/>
		<br/>
		<textarea class='textarea' name = 'intro' cols='60px' rows='10px' readOnly>${tvo.intro }</textarea>
		<br/>
		</div>
		
		<h2>팀원 목록</h2>
		<div class = 'tmTitle'>
			<span class = 'mid'>팀원명</span>
			<span class = 'posit'>포지션</span>
			<span class = 'email'>이메일</span>
			<span class = 'joinDate'>가입날짜</span>
		</div>
		<div class = 'tmItems'>
			<c:forEach var='tVo' items="${list}">
				<div class = 'tmItem'>
					<span class = 'mid'>${tVo.mid }</span>
					<span class = 'posit'>${tVo.posit }</span>
					<c:choose>
						<c:when test="${sessionScope.tid eq tvo.tid}" >
							<span class = 'email'>${tVo.email }</span>
						</c:when>
						<c:otherwise>
							<span class = 'email'>*****@*****.***</span>
						</c:otherwise>
					</c:choose>
					<span class = 'joinDate'>${tVo.jDate }</span>
				</div>
			</c:forEach>
			
			<input type='hidden' name='nowPage' value='${param.nowPage == null ? 1 : param.nowPage}'/>
			<input type='hidden' name='findStr' value='${param.findStr }'/>
			<input type='hidden' name='serial' value='${param.serial }'/>
			<input type='hidden' name='mid' value = '${sessionScope.mid }'/>
		</div>
		
		<div id = "matching">
			<h2>매칭 기록</h2>
			<section id = "matchingTitle">
				<span class = 'myteam'>현재팀</span>
				<span class = 'yourteam'>상대팀</span>
				<span class = 'ddate'>대결일</span>
				<span class = 'applydate'>수락일</span>
			</section>
			<section id = 'matchingItems'>
				 <c:forEach var="recordList" items="${recordList }">
					<section class = 'item'>
						<span class = 'myteam'>${recordList.myTeam }</span>
						<span class = 'yourteam'>${recordList.yourTeam }</span>
						<span class = 'ddate'>${recordList.dDate }</span>
						<span class = 'applydate'>${recordList.applyDate }</span>
					</section>
				</c:forEach>
			</section>
			<div id='pageMatch'>
				<c:if test="${page.startPage>1 }">
					<input type='button' class='btn_find1' value='&lt&lt' onclick='team.recordMove(1)'/>
					<input type='button' class='btn_find1'  value='&lt' onclick='team.recordMove(${page.startPage-1 })'/>
				</c:if>
				
				<c:forEach var='p' begin='${page.startPage }' end='${page.endPage }'>
					<input type='button' class='btn_find' value='${p }' onclick ='team.recordMove(${p})' />
				</c:forEach>
				
				<c:if test="${page.endPage<page.totPage }">
					<input type='button' value='&gt'  class='btn_find1' onclick ='team.recordMove(${page.endPage+1})'/>
					<input type='button' value='&gt&gt' class='btn_find1' onclick ='team.recordMove(${page.totPage})'/>
				</c:if>			
			</div>
		</div>
		
		<div id = 'btn_zone'>
			<br/>
				
			<!-- 주장일경우 -->
			<c:choose>
				<c:when test="${sessionScope.tid eq tvo.tid}">
					<c:if test = "${sessionScope.mid eq tvo.mid }">
						<input type = 'button' id = 'btnModify' value = '수정'/>
						<input type = 'button' id = 'btnDelete' value = '팀 해체'/>
					</c:if>
					<input type = 'button' id = 'btnBye' value = '팀 탈퇴'/>
					<input type = 'button' id = 'btnJoinList' onclick = "funcJoinList('${param.serial}','${tvo.tid }')" value = '신청목록'/>
				</c:when>
				<c:when test="${sessionScope.tid eq null }">
					<input type = 'button' id = 'btnJoin' value = '가입신청'/>
				</c:when>
			</c:choose>
			<input type = 'button' id = 'btnBackToList' value = '목록'/>
		</div>
	</form>
</div>
</body>
</html>