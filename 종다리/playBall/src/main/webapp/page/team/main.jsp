<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball - Team Main</title>
<link rel='stylesheet' type='text/css' href='./css/team/teamMain.css'>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</head>
<body>
<div id = 'team'>
	<label><b>Team Main</b></label>
	<hr>
	<div id="my_team">
			<p>내 팀</p>
			<c:choose>
			<c:when test ="${sessionScope.tid eq null }">
				<div id="team_thumb">
					<img src = "./img/1.png"/>
					<span class = "none"> 현재 가입하신 팀이 없습니다. </span>
					<br/>
					<span class = "none"> 팀에 가입하거나 등록해주세요. </span>
				</div>
			</c:when>
			<c:otherwise>
			<div>
				  주장 : ${tVo.mid }
				</div>
			<div id="honor_img" onclick="team.myTeamInfo(${tVo.serial})">
				<img src="./img/${tVo.pic }">
				<div class="clear"></div>
				<div id="teamName">
					<div id="name">팀 명 : ${tVo.tid }</div>
					<div id="team_number">매칭 횟수 : ${tVo.cntMatch }</div>
				</div>
				<div class="clear"></div>
				<div id="p">
					<p>${tVo.intro }</p>
				</div>
				<div id="list_team">
				<c:forEach var="mVo" items="${mVoList }">
					<div>팀원 : ${mVo.mid } <br> 이메일 : ${mVo.email }</div>
				</c:forEach>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</div>
		
		<div id="honor_bast">
			<p>명예의 전당</p>
			
			<c:forEach var="teamRank" items="${teamRank }" varStatus="status">
			<div id="number">
				<div id="bast">
					<p>${status.count }</p>
					<img src="./img/${teamRank.pic }">
				</div>
				<div id="team_name">
					<div>팀 명 : ${teamRank.tid }</div>
					<div>매칭 횟수 : ${teamRank.cntMatch }</div>
				</div>
			</div>
			<div class="clear"></div>
			</c:forEach>
			
		</div>
		<div class="clear"></div>
		<div id="latest_team">
			<label><b>최신 팀</b></label>
			<input type="button" value="더보기" id="V_more" onclick = "team.list()">
			<hr>
			<div id="latest_all">
			<c:forEach var="newTeam" items="${newTeam }">
				<div id="latest">
					<div id="latest_bast">
						<img src="./img/${newTeam.pic }">
					</div>
					<div id="latest_name">
						<div>팀명 : ${newTeam.tid }</div>
						<div>주장 : ${newTeam.mid }</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
</body>
</html>