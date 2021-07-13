<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='ko'>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<script src='./lib/jq.js'></script>
<script src="./js/stadium/Create.js"></script>
<link rel='stylesheet' type='text/css' href='./css/stadium/Create.css'>
<title>구장 게시글 작성</title>
</head>
<body>
	<div id='main'>
		<form name='frm_upload' id='frm_upload' method='post'>
			<div id='image_preview'>
				<img id='img' src="http://placehold.it/500x380">
			</div>
			<input type='file' id='attfile' name='attfile'>
			<div id='map'></div>	
		</form>
		<form id='frm_create' name='frm_create' method='POST'>
			<div id='doc' >
			    <input type="hidden" name="pic">
				<input type='text' name='name' placeholder='구장 이름' id='title'><br/><br/>
				<textarea cols="103" rows="30" name='doc'></textarea><br/>
				<label>주소 : <input type='text'  name='address' id = 'address' placeholder='주소'></label><br/>
				<label>운영시간 : <input type='text'  name='tim' placeholder='운영시간'></label><br/>
				<label>이용가격 : <input type='text' name='price' placeholder='이용가격'></label><br/>
				<input type='hidden' id='place1' name='place1' value='' placeholder='경도'><br/>
				<input type='hidden' id='place2' name='place2' value='' placeholder='위도'><br/>
				<input type='hidden' placeholder='예약'><br/>
			</div>
		</form>
	</div>
	<div id='btn_zone'>
				<input type='button' value='작성' id='btn_create' >
				<input type='button' value='취소' id='btn_cancle' >
	</div>
	<script>
	brd.home();
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d41718592a8c34d83227765457f5cdf1&autoload=false "></script>
<script>
kakao.maps.load(function() {
	kakao.maps.load(function() {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(37.567788882807314, 126.9823760012043), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	//지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	// 지도에 마커를 표시합니다
	marker.setMap(map);

	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
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

});

</script>

</body>
</html>