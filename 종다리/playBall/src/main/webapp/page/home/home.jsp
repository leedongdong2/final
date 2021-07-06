<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Play ball</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<link rel = 'stylesheet' type = 'text/css' href = './css/home/home.css'>
	 <script src="./lib/jq.js"></script>
	 <script src="./js/home/home.js"></script>
</head>
<body>
<div id = 'main'>

	<!-- 배너 시작 -->
	<div id='banner'>
		<div class="swiper-container mySwiper2">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
        	<a href = 'http://www.jumpball.co.kr/news/newsview.php?ncode=1065607146775940' class = 'goMarket'>
          		<img src="./img/news1.jpg" />
          		<article class="Bigtxt">
            		<h2>대구시, “한국가스공사 10월 개막 경기 가능토록 노력 중”</h2>
          		</article>
        	 </a>
        </div>
        <div class="swiper-slide">
        	<a href = 'http://www.jumpball.co.kr/news/newsview.php?ncode=1065560518032434' class = 'goMarket'>
          		<img src="./img/news2.jpg" />
          		<article class="Bigtxt">
            		<h2>LG 이광진, 변기훈에게 폴더 인사를 한 이유는?</h2>
          		</article>
        	 </a>
        </div>
        <div class="swiper-slide">
        	<a href = 'http://www.jumpball.co.kr/news/newsview.php?ncode=1065608126363174' class = 'goMarket'>
          		<img src="./img/news3.jpg" />
          		<article class="Bigtxt">
            		<h2>니콜슨 선택한 유도훈 감독 “국내선수 살려줄 능력 봤다”</h2>
          		</article>
        	 </a>
        </div>
        <div class="swiper-slide">
          <a href = 'http://www.jumpball.co.kr/news/newsview.php?ncode=1065611544538344' class = 'goMarket'>
          		<img src="./img/news4.jpg" />
          		<article class="Bigtxt">
            		<h2>[NBA PO] '60년만의 파이널 진출' 노리는 애틀랜타의 기적은?</h2>
          		</article>
        	 </a>
        </div>
        <div class="swiper-slide">
          <a href = 'http://www.jumpball.co.kr/news/newsview.php?ncode=1065540582386417' class = 'goMarket'>
          		<img src="./img/news5.jpg" />
          		<article class="Bigtxt">
            		<h2>[NBA PO] '이젠 아데토쿤보까지', 부상으로 얼룩진 2020-2021 플레이오프</h2>
          		</article>
        	 </a>
        </div>
      </div>
    </div>
    <div thumbsSlider="" class="swiper-container mySwiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <img src="./img/news1.jpg" />
          <div class="Smalltxt">
            <h2>대구시, “한국가스공사 10월 개막 경기 가능토록 노력 중”</h2>
          </div>
        </div>
        <div class="swiper-slide">
          <img src="./img/news2.jpg" />
          <div class="Smalltxt">
            <h2>LG 이광진, 변기훈에게 폴더 인사를 한 이유는?</h2>
          </div>
        </div>
        <div class="swiper-slide">
          <img src="./img/news3.jpg" />
          <div class="Smalltxt">
            <h2>니콜슨 선택한 유도훈 감독 “국내선수 살려줄 능력 봤다”</h2>
          </div>
        </div>
        <div class="swiper-slide">
          <img src="./img/news4.jpg" />
          <div class="Smalltxt">
            <h2>[NBA PO] '60년만의 파이널 진출' 노리는 애틀랜타의 기적은?</h2>
          </div>
        </div>
        <div class="swiper-slide">
          <img src="./img/news5.jpg" />
          <div class="Smalltxt">
            <h2>[NBA PO] '이젠 아데토쿤보까지', 부상으로 얼룩진 2020-2021 플레이오프</h2>
          </div>
        </div>
      </div>
    </div>
	</div>
	<!-- 배너 끝 -->
	
	<div id="latest_team">
	<div class = 'borderName'>
			<p>최신 팀</p>
			<a href = "javascript:void(0);" onclick="teamlist();" >더보기 ></a>
			</div>
			<div id="latest_all">
				<div id="latest">
					<div id="latest_bast">
						<img src="./img/logo5.jpg">
					</div>
					<div id="latest_name">
						<h2>팀명 : team1</h2>
						<h2>주장 : user1</h2>
					</div>
				</div>
				<div id="latest">
					<div id="latest_bast">
						<img src="./img/logo6.jpg">
					</div>
					<div id="latest_name">
					<h2>팀명 : team2</h2>
						<h2>주장 : user2</h2>
					</div>
				</div>
				<div id="latest">
					<div id="latest_bast">
						<img src="./img/logo7.jpg">
					</div>
					<div id="latest_name">
					<h2>팀명 : team3</h2>
						<h2>주장 : user3</h2>
					</div>
				</div>
				<div id="latest">
					<div id="latest_bast">
						<img src="./img/logo8.jpg">
					</div>
					<div id="latest_name">
					<h2>팀명 : team4</h2>
						<h2>주장 : user4</h2>
					</div>
				</div>
			</div>
		</div>
	<div id = 'match'>
		<div class = 'borderName'>
			<p>매칭게시판</p>
			<a href = "javascript:void(0);" onclick="moveMatchBoard();" >더보기 ></a>
		</div>
		<div class = 'title'>
			<span class = 'rnum'>No</span>
			<span class = 'state'>매칭상태</span>
			<span class = 'region'>지역</span>
			<span class = 'sort'>종류</span>
			<span class = 'subject'>제목</span>
			<span class = 'mid'>작성자</span>
		</div>
		<div class = 'items'>
			<div class ='item'>
				<span class = 'rnum'>1</span>
				<span class = 'state'>매칭대기</span>
				<span class = 'region'>그외지역</span>
				<span class = 'sort'>구인/팀</span>
				<span class = 'subject'>초보들만 오세요</span>
				<span class = 'mid'>user1</span>
			</div>
			<div class ='item'>
				<span class = 'rnum'>2</span>
				<span class = 'state'>매칭대기</span>
				<span class = 'region'>인천</span>
				<span class = 'sort'>3:3</span>
				<span class = 'subject'>초보들만 오세요</span>
				<span class = 'mid'>user1</span>
			</div>
			<div class ='item'>
				<span class = 'rnum'>3</span>
				<span class = 'state'>매칭대기</span>
				<span class = 'region'>서울</span>
				<span class = 'sort'>2:2</span>
				<span class = 'subject'>초보들만 오세요</span>
				<span class = 'mid'>user1</span>
			</div>
			<div class ='item'>
				<span class = 'rnum'>4</span>
				<span class = 'state'>매칭완료</span>
				<span class = 'region'>서울</span>
				<span class = 'sort'>2:2</span>
				<span class = 'subject'>초보들만 오세요</span>
				<span class = 'mid'>user1</span>
			</div>
			<div class ='item'>
				<span class = 'rnum'>5</span>
				<span class = 'state'>매칭완료</span>
				<span class = 'region'>경기</span>
				<span class = 'sort'>2:2</span>
				<span class = 'subject'>초보들만 오세요</span>
				<span class = 'mid'>user1</span>
			</div>
		</div>
	</div>
</div>
</body>
</html>