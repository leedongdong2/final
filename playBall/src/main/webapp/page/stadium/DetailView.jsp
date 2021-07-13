<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<title>구장 상세보기</title>
<script src='./lib/jq.js'></script>
<link rel='stylesheet' type='text/css' href='./css/stadium/DetailView.css'>
<script src="./js/stadium/DetailView.js"></script>
</head>
<body>
<form id='frm_back' name='frm_back'>
<input type='hidden' name='findstr'>
<input type='hidden' name='sel' value='${param.sel }'>
</form>
	<div id='main'>
		<input type='button' value='뒤로가기' id='back'>
		<div id='shell'>
			<div class='shell2'>
				<div class='top'>
					<img src="./img/stadiumBoard/${vo.pic }" id='stadiumimg'>
				</div>
			</div>
			<div id='title'>
				<span id='item_name' class='item_name'>${vo.name }</span>
			</div>
			<div id='item'>
				<h2>상세정보</h2>
				<br>
				<label>주소 : ${vo.address }</label><br/>
				<label>운영시간 : ${vo.tim }</label><br/>
				<label>구장소개 : ${vo.doc }</label><br/>
				<label>이용 포인트 : ${vo.price }</label><br/>
			</div>
				<span id = 'Rtitle'>예약 테이블</span>	
				 <div id="MonthSelector">
			     <span id="leftMonth"><</span><input id="reservationMonth" type="text" readonly><span id="rightMonth">></span>
			      </div>
				 <div id='reserv'>
				
				</div>
				<div id="map" style="width:73%;height:500px;margin:30px auto"></div>
				<div id='btn_zone'>
				<input type='button' value='수정' id='btn_modify' onclick='brd.modify(${vo.serial})'>
				<input type='button' value='삭제' id='btn_delete' onclick='brd.delete(${vo.serial})'>
				<input type='hidden' id='place1' value='${vo.place1 }'>
				<input type='hidden' id='place2' value='${vo.place2 }'>
				</div>
				<form id='frm_update' name='frm_update'>
				<input type="hidden" name="reservationId" value="${sessionScope.mid }">
				<input type="hidden" name="price" value='${vo.price }'>
				<input type="hidden" name="reservationMonth">
				<input type='hidden' name='serial' value='${param.serial }'>
				<input type='hidden' name="stadiumName" value='${vo.name }'>
			    </form>
				</div>
		
	</div>
	<script>brd.home();</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d41718592a8c34d83227765457f5cdf1&autoload=false "></script>
<script>
var x=document.getElementById('place1').value;
var y=document.getElementById('place2').value;
var n=document.getElementById('item_name').innerHTML;

kakao.maps.load(function() {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(x, y); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = n, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(x, y); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker); 


});

</script>
</body>
</html>