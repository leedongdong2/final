<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<script src='./lib/jq.js'></script>
<link rel='stylesheet' type='text/css' href='./css/stadium/Update.css'>
<script src="./js/stadium/Update.js"></script>
<title>구장 게시글 수정</title>
</head>
<body>
<div id='main'>
	<form name='frm_upload' id='frm_upload' method='POST'>
		<div id='image_preview'>
			<img id='img' src="./img/stadiumBoard/${vo.pic }">
		</div>
		<input type='file' id='attfile' name='attfile'>
		<div id='map'></div>	
	</form>
		
	<form id='frm_update' name='frm_update' method='POST'>
		<div id='doc' >
			<label>구장명 : </label>
		    <input type='text' name='name' value='${vo.name }' id='title'><br/><br/>
			<textarea cols="103" rows="30" name='doc'>${vo.doc }</textarea><br/>
			<label>주소 : <input type='text'  name='address' id = 'address' placeholder='주소' value='${vo.address }'></label><br/>
			<label>운영시간 : <input type='text'  name='tim' placeholder='운영시간' value='${vo.tim }'></label><br/>
			<label>이용가격 : <input type='text' name='price' placeholder='이용가격' value='${vo.price }'></label><br/><br/>
			<input type='hidden' id='place1' name='place1' placeholder='경도' value='${vo.place1 }'>
			<input type='hidden' id='place2' name='place2' placeholder='위도' value='${vo.place2 }'><br/>
			<input type='hidden' name='serial' value='${vo.serial }'>
			<input type='hidden' name='pic'>
		</div>
	</form>
</div>
	<div id='btn_zone'>
		<input type='button' value='작성' id='btn_create' onclick='brd.update()'>
		<input type='button' value='취소' id='btn_cancle' onclick='brd.back'>
	</div>
	<script>brd.home();</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d41718592a8c34d83227765457f5cdf1&autoload=false "></script>
<script>
var x=document.getElementById('place1').value;
var y=document.getElementById('place2').value;
var n=document.getElementById('title').value;

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

//지도에 클릭 이벤트를 등록합니다
//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {    
	
 // 클릭한 위도, 경도 정보를 가져옵니다 
 var latlng = mouseEvent.latLng;
 
	  // 마커 위치를 클릭한 위치로 옮깁니다
 marker.setPosition(latlng);
 
 var xmessage = latlng.getLat();
 var ymessage = latlng.getLng();
 
 $("#place1").val(xmessage);
 $("#place2").val(ymessage);
 
	});

});

</script>
</body>
</html>