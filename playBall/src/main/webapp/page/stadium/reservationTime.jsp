<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form id='timeForm' name='timeForm'>
		<input type="hidden" name="reservationMonth" id="reservationMonth" value='${param.reservationMonth }'>
		<input type='hidden' name='serial' value='${param.serial }'>
		<input type='hidden' name="stadiumName" value='${param.stadiumName }'>
	    <input type="hidden" name="reservationDay" id="reservationDay" value='${param.reservationDay }'>
	    <input type="hidden" id="reservationTime" name="reservationTime">
	    <input type="hidden" id="price" name="price" value='${param.price}'>
        <input type="hidden" id="point" name="point" value="${param.point }">
        <input type="hidden" id="reservationId" name="reservationId" value="${sessionScope.mid }">
     </form>
<div class="selectTime">${vo.reservationTimeOne }</div>
<div class="selectTime">${vo.reservationTimeTwo }</div>
<div class="selectTime">${vo.reservationTimeThree }</div>
<div class="selectTime">${vo.reservationTimeFour }</div>
<div class="selectTime">${vo.reservationTimeFive }</div>
<div class="selectTime">${vo.reservationTimeSix }</div>
<div class="selectTime">${vo.reservationTimeSeven }</div>
<div class="selectTime">${vo.reservationTimeEight }</div>
<div class="selectTime">${vo.reservationTimeNine }</div>
<div class="selectTime">${vo.reservationTimeTen }</div>
<div class="selectTime">${vo.reservationTimeEleven }</div>
<div class="selectTime">${vo.reservationTimeTwelve }</div>
<div class = 'btn'>
	<span id = "myPoint">내 포인트 : ${param.point }</span>
	<input type="button" id="reservationBtn" class="reservationBtn" value="예약">
	<input type="button" id="backReservationDay" value="뒤로가기">
</div>
<script src='./lib/jq.js'></script>
<script src="./js/stadium/reservation.js"></script>
<script>
reservationTime.init();
</script>
</body>
</html>