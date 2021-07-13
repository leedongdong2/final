<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' type='text/css' href='./css/stadium/reservationRecord.css'>
</head>
<body>
<div id="reservaitonRecord">
<div id="titleBox">
<span class="title">예약 현황</span>
<input type="button" id="backHistory" value="뒤로가기">
</div>
<div id="center">
<div id="recordBox">
<span id="reservationId">예약자</span><span id="stadiumName">구장명</span><span id="price">가격</span><span id="reservedDate">예약신청일</span><span id="reservationDay">예약일</span>
</div>
<c:forEach var="list" items="${list }">
<div class="records">
<form class="myReservationForm" name ="myReservationForm">
<input type="hidden" name="serial" value="${list.serial }"/>
<input type="text" class="record" name="reservationId" value="${list.reservationId }"/>
<input type="text" class="record" name="stadiumName" value="${list.stadiumName }"/>
<input type="text" class="record" name="price" value="${list.price }"/>
<input type="text" class="record" name="reservedDate" value="${list.reservedDate}"/>
<input type="text" class="recordMonth" name="reservationMonth" value="${list.reservationMonth}"/>월
<input type="text" class="recordDay" name="reservationDay" value="${list.reservationDay}"/>일
<input type="text" name="reservationTime" class="recordTime" value="${list.reservationTime}">
<input type="button" class="cancelReservation" value="예약취소">
</form>	
</div>
</c:forEach>
</div>
</div>
<script src='./lib/jq.js'></script>
<script src='./js/stadium/reservation.js'></script>
<script>

myReservation.init();
</script>
</body>
</html>