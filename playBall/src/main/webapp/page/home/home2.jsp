<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play ball</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<link rel = 'stylesheet' type = 'text/css' href = './css/home/home2.css'>
<script src="./lib/jq.js"></script>
<script src = "./js/home/home2.js"></script>
</head>
<body>
<div id = 'main'>
	<!-- 배너 시작 -->
	<div id='banner'>
		<div class="swiper-container mySwiper">
	      <div class="swiper-wrapper">
	        <c:forEach var= "rank" items = "${rank}" varStatus="status">
	        <div class="swiper-slide">
	          <div class="in" onclick = "home.goTeam('${rank.serial}')">
	            <h2>No.${status.count }</h2>
	            <img src="./img/${rank.pic }" />
	            <h3>Team : ${rank.tid } | Leader : ${rank.mid }</h3>
	            <h4>Matching : ${rank.cntMatch }</h4>
	          </div>
	        </div>
	        </c:forEach>
	      </div>
	      <div class="swiper-pagination"></div>
	    </div>
	</div>
	<!-- 배너 끝 -->
	
	<div id = 'recentStadium'>
		<div class = 'borderName'>
			<p>최신 구장</p>
			<a href = "javascript:void(0);" onclick = "moveStadium();">더보기 ></a>
		</div>
		<div class = 'items'>
			<c:forEach var= 'stadiumList' items = "${stadiumList}">
				<div class = 'item' onclick = "home.goStadium('${stadiumList.serial}')">
					<div class = 'imgs'>
						<img src = "./img/stadiumBoard/${stadiumList.pic }"/>
					</div>
					<h2>구장명 : ${stadiumList.name }</h2>
					<h3>이용가격: ${stadiumList.price }</h3>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div id = 'match'>
		<div class = 'borderName'>
			<p>매칭게시판</p>
			<a href = "javascript:void(0);" onclick="moveMatchBoard();" >더보기 ></a>
		</div>
		<div class = 'title'>
			<span class = 'rnum'>No</span>
			<span class='date'>날짜</span>
			<span class='team'>태그</span>
			<span class = 'region'>지역</span>
			<span class='tier'>등급</span>
			<span class = 'subject'>제목</span>
			<span class = 'mid'>작성자</span>
		</div>
		<div class = 'items'>
			<c:forEach var= 'matchList' items = "${matchList}">
				<div class ='item' onclick = "home.goMatch('${matchList.serial}')">
					<span class = 'rnum'>${matchList.rno}</span>
					<span class='date'>${matchList.dDate}</span>
					<span class='team'>${matchList.tag}</span>
					<span class = 'region'>${matchList.loca}</span>
					<span class='tier'>${matchList.tier}</span>
					<span class = 'subject'>${matchList.title}</span>
					<span class = 'mid'>${matchList.mid}</span>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
</body>
</html>