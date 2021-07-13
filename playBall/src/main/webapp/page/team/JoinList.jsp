<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Play ball - Team Join List</title>
<style>
@font-face {
	font-family:'VITRO PRIDE TTF';
	src: url('../font/VITRO-PRIDE-TTF.woff2') format('woff2'),
		url('../font/VITRO-PRIDE-TTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}/* 비트로 프라이드 */

@font-face {
    font-family: 'twayair';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_tway@1.0/twayair.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}/*티웨이 항공*/

#frm_team{ 
	width:90%;
	margin:0 auto;
	font-family:'VITRO PRIDE TTF';
}


#team #frm_team h3 {
	margin: 10px 0;
	font-size: 20pt;
	font-family: 'twayair';
}

#frm_team .jTitle{ /* 맨 위 가입자,포지션,이메일, 날짜 설명칸? */
	width: 100%;
	border: 1px solid black;
    height: 40px;
	font-weight: bold;
	background-color: rgb(76, 76, 76);
	color: white;
	
	
}

#frm_team .jItems{
	width:100%;
}

/* 신청목록 라인 */
#frm_team .jItems .jItem{
	width:100%;
	border-bottom:1px solid black;
	height: 40px;
}


/* 정보 간격 */
.mid, .posit, .email, .joinDate { 
	width: 20%;
	text-align: center;
	float: left;	
	font-family:'VITRO PRIDE TTF';
	line-height: 35px;
}

/* 버튼 위치 */
.btnYn{ 
	width: 20%;
	text-align: center;
	float: left;
	line-height: 35px;
}

.btnYn input[type="button"]{
	cursor:pointer;
}

/* 페이징 처리 */
#pageJ{
	text-align: center;
	margin-top: 2%;
}

#pageJ input[type="button"]{ 
	cursor:pointer;
}

.btn_find{ 
    background-color: #ffffff;
    width:41px;
    height: 41px;
    color: rgb(80, 80, 80);
    font-family:'VITRO PRIDE TTF';
    border: 1px solid #aaa;
    font-size: 15px;
    font-weight: bold;
}

.btn_find:hover{
    background-color:  rgb(240, 130, 71);
    border:1px solid #000;
    color:white;
    cursor: pointer;
    transition: all 0.5s;
}

.btn_find2{
	margin-top:5px;
    background-color: #ffffff;
    padding: 4px 10px;
    color: rgb(80, 80, 80);
    font-family:'VITRO PRIDE TTF';
    border: 1px solid #aaa;
    font-size: 15px;
    font-weight: bold;
    margin-left:1.5%;
}

.btn_find2:hover{
    background-color:  rgb(240, 130, 71);
    border:1px solid #000;
    color:white;
    cursor: pointer;
    transition: all 0.5s;
}

.btn_find1{ 
    background-color: #ffffff;
    width: 40px;
    height: 40px;
    border:1px solid #aaa;
    color: rgb(80, 80, 80);
    font-family:'VITRO PRIDE TTF';
    font-weight: bold;
}

.btn_find1:hover{
    background-color:rgb(255, 255, 255);
    border:1px solid #fc5d3a;
    color:#fc5d3a;
    cursor: pointer;
    transition: all 0.5s;
}

</style>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</head>
<body>
<div id = 'team'>
<form name= 'move_teamFrm'>
   <input type="hidden" name="tid">
</form>
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
					<input type = 'button' value = '수락'  class = 'btn_find2' onclick = "team.acceptJoin('${jVo.mid}')">
					<input type = 'button' value = '거절'  class = 'btn_find2' onclick = "team.rejectJoin('${jVo.mid}')">
				</div>
			</c:forEach>
		</div>
		<div id='pageJ'>
		<c:if test="${page.startPage>1 }">
			<input type='button' value='&lt&lt' class = 'btn_find' onclick='team.jmove(1)'/>
			<input type='button' value='&lt' class = 'btn_find' onclick='team.jmove(${page.startPage-1 })'/>
		</c:if>
		<c:forEach var='p' begin='${page.startPage }' end='${page.endPage }'>
			<input type='button' value='${p }'  class = 'btn_find1' onclick ='team.jmove(${p})' />
		</c:forEach>
	
		<c:if test="${page.endPage<page.totPage }">
			<input type='button' value='&gt' class = 'btn_find' onclick ='team.jmove(${page.endPage+1})'/>
			<input type='button' value='&gt&gt'  class = 'btn_find' onclick ='team.jmove(${page.totPage})'/>
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