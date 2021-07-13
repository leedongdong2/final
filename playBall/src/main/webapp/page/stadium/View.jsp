<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<script src='./lib/jq.js'></script>
<script src='./js/stadium/View.js'></script>
<link rel='stylesheet' type='text/css' href='./css/stadium/View.css'>
<title>구장 목록</title>
</head>
<body>
	<div id="map"></div>
	<div id='main'>
	     <c:if test="${sessionScope.mid eq 'hong'}">
		<input type='button' value='글쓰기' id='btn_create'>
		</c:if>
		<c:choose>
		<c:when test="${sessionScope.mid ne 'hong' and sessionScope.mid ne null }">
			<input type='button' value='내예약현황' id ="myReservation">
		</c:when>	
		<c:when test="${sessionScope.mid eq 'hong' }">
			<input type='button' value='예약현황' id ="reservationStatus">
		</c:when>
		</c:choose>
		<form id="moveMyReservationFrom" name="moveMyReservationFrom">
		<input type="hidden" name="reservationId" value="${sessionScope.mid }">
		</form>
		<div id='find_zone'>
			<form id='frm_find' name='frm_find' method='POST'>
			<select id='sel' name='sel'>
				<option value="전체" <c:if test="${page.sel =='전체'}">selected='selected'</c:if>>전체</option>
				<option value="서울" <c:if test="${page.sel =='서울'}">selected='selected'</c:if>>서울</option>
				<option value="경기" <c:if test="${page.sel =='경기'}">selected='selected'</c:if>>경기</option>
				<option value="인천" <c:if test="${page.sel =='인천'}">selected='selected'</c:if>>인천</option>
				<option value="대구" <c:if test="${page.sel =='대구'}">selected='selected'</c:if>>대구</option>
				<option value="부산" <c:if test="${page.sel =='부산'}">selected='selected'</c:if>>부산</option>
			</select>
				<input type='text' id='findstr' name='findstr' value='${param.findstr }'>
				<input type='button' value='검색' id='btn_findstr'>
				
				<input type='hidden' name='nowPage' value='${(empty param.nowPage)? 1 : param.nowPage }'/>
				<input type='hidden' name='serial' value='${param.serial }'>
				<input type='hidden' id='sele' name='sele' value='${param.sel }'>
			</form>
		</div>
			<div id='s'></div>
			<c:forEach var='vo' items='${list }' varStatus='status'>

			<figure class="grid-item">
			  <img src="./img/stadiumBoard/${vo.pic }" class='stadiumimg' />
			  <figcaption>
			    <p>이용 가격 : ${vo.price }<br/>
			    구장 소개 : ${vo.doc }</p>
			    <div class="list_items">
			      <h2>구장<span>${vo.name }</span></h2>
			    </div>
			  </figcaption>
			  <a href="#" onclick='brd.detail(${vo.serial})'></a>
			</figure>
			
			</c:forEach>
			<div class='btn_zone'>
				<c:if test="${page.startPage>1 }">
					<input type='button' value='맨처음' onclick='brd.move(1)'>
					<input type='button' value='이전' onclick='brd.move(${page.startPage-1})'>
				</c:if>
				
				<c:forEach var='p' begin="${page.startPage }" end="${page.endPage }">
					<input type='button' value='${p}' onclick='brd.move(${p})'>
				</c:forEach>
				
				<c:if test="${page.endPage<page.totPage }">
					<input type='button' value='다음' onclick='brd.move(${page.endPage+1})'>
					<input type='button' value='맨끝' onclick='brd.move(${page.totPage})'>
				</c:if>
			</div>
	</div>
	<script>brd.home();</script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d41718592a8c34d83227765457f5cdf1&autoload=false "></script>
<script>
kakao.maps.load(function() {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(37.567788882807314, 126.9823760012043), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
});

</script>
</body>
</html>