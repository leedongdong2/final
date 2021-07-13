<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link href="./css/match/match_view.css" type='text/css' rel="stylesheet"/>
</head>
<body>

<div class='match_view'>
        
	<label id='mat'>Match</label>
	
	<div id='line'></div>
	
	<br/>
	
	<div id="find_box">
		<form name="matchingViewForm" id="matchingViewForm">
			<input type="hidden" name="nowPage" value="${param.nowPage == null ? 1 : param.nowPage}">
			<input type="hidden" name="serial">
			
			<div id='findZone'> <!-- 검색창 검색 버튼 -->
				<input type="text" name="findStr" id='find' value="${param.findStr }" placeholder="검색"/>
				<input type='button' id='search' value='검색'/>
			</div>
			
			<div id="find_loc">
				<select name="tag" id="tag">
					<option value="all">전체</option>
					<option value="2:2">2:2</option>
					<option value="3:3">3:3</option>
				</select>
				
				<select name="loca" id="loca">
					<option value="all">전체</option>
					<option value="서울">서울</option>
					<option value="경기">경기</option>
				</select>
				    
				<select name="tier" id="tier">
					<option value="all">전체</option>
					<option value="상">상</option>
					<option value="중">중</option>
					<option value="하">하</option>
				</select>
			</div>
		</form>
	</div>
	
	<div class="clear"></div>
	
	<div id='page'>
		<div class='main'>
			<span class='no'>번호</span>
			<span class='date'>날짜</span>
			<span class='team'>태그</span>
			<span class='local'>지역</span>
			<span class='tier'>등급</span>
			<span class='title'>제목</span>
			<span class='mid'>아이디</span>
			<span class='hit'>조회수</span>
			<span class='vs'>상태</span>
		</div>
			
			
		<div id='view'>
			
			<c:forEach var="vo" items="${list }">
			<div class='item' onclick="moveDetail(${vo.serial})">
				<span class='no'>${vo.rno }</span>
				<span class='date'>${vo.dDate }</span>
				<span class='team'>${vo.tag }</span>
				<span class='local'>${vo.loca }</span>
				<span class='tier'>${vo.tier }</span>
				<span class='title'>${vo.title }</span>
				<span class='mid'>${vo.mid }</span>
				<span class='hit'>${vo.hit }</span>
			</div>
			<input type='button' value="${vo.vs }" class = 'btnMatch' onclick="matchCancle('${sessionScope.mid }','${vo.mid }',${vo.serial},'${vo.vs }')"/>
			</c:forEach>
		     
		</div>
		
		<br>
		<c:if test="${sessionScope.mid ne null }">
			<input type='button' value='글쓰기' id="btn_write"/>
		</c:if>
		<div id='btn_zone'>
			<c:if test="${page.startPage>1 }">
				<input type='button' value='≪' class='btn_find1' onclick="pageMove(1)">
				<input type='button' value='이전' class='btn_find1' onclick="pageMove(${page.startPage-1})">
			</c:if>
			
			<c:forEach var ="p" begin="${page.startPage }" end="${page.endPage }">
				<input type='button' value="${p }" class='btn_find' onclick="pageMove(${p})">
			</c:forEach>
			
			<c:if test="${page.endPage<page.totPage }">
				<input type='button' value='다음' class='btn_find1' onclick="pageMove(${page.endPage+1})">
				<input type='button' value='≫' class='btn_find1' onclick="pageMove(${page.totPage})">
			</c:if>
		</div>
	</div>
</div>
<script src="./lib/jq.js"></script>
<script src="./js/match/match_view2.js"></script>
<script>
checkLoca("${param.loca}");
checkTag("${param.tag}");
checkTier("${param.tier}");

</script>
</body>


</html>