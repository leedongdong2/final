<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>Play ball - Team Search</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/team/team.css'>
</head>
<body>
<div id = 'team'>
	<h2> 팀 목록 </h2>
	
	<div class = 'content-grid'>
		<c:forEach var='vo' items="${list}">
			<figure class="grid-item">
			  <img src = "./img/${vo.pic }"/>
			  <figcaption class = 'grid-item_txt'>
			    <h3>${vo.tid }</h3>
			    <p><span>주장 : ${vo.mid }</span><br/><span>팀원수 : ${vo.cntMember}/5</span></p>
			  </figcaption>
			  <a href="#" onclick = 'team.view(${vo.serial})'></a>
			</figure>
		</c:forEach>
	</div>
	<div id='pageT'>
		<c:if test="${page.startPage>1 }">
			<input type='button' class='btn_find1' value='&lt&lt' onclick='team.move(1)'/>
			<input type='button' class='btn_find1' value='&lt' onclick='team.move(${page.startPage-1 })'/>
		</c:if>
		<c:forEach var='p' begin='${page.startPage }' end='${page.endPage }'>
			<input type='button' class='btn_find' value='${p }' onclick ='team.move(${p})' />
		</c:forEach>
		
		<c:if test="${page.endPage<page.totPage }">
			<input type='button' class='btn_find1' value='&gt' onclick ='team.move(${page.endPage+1})'/>
			<input type='button' class='btn_find1' value='&gt&gt' onclick ='team.move(${page.totPage})'/>
		</c:if>			
	</div>
	<form name = 'frm_team' id = 'frm_team'>
		<div id = 'findT'>
			<c:if test ="${sessionScope.tid eq null and sessionScope.mid ne null}">
				<input type = 'button' id = 'btnRegister' value = '팀 등록'/>
			</c:if>
			<input type = 'search' name ='findStr' id = 'findStr' value="${param.findStr }"/>
			<input type = 'hidden' name = 'nowPage' value='${param.nowPage }'/>
			<input type = 'hidden' name = 'serial' value='${param.serial }'/>
			<input type = 'button' id = 'btnSearch' value = '검색' style = "float: right;"/>
		</div>
	</form>
</div>
<script src = './lib/jq.js'></script>
<script src = './js/team/team.js'></script>
</body>
</html>