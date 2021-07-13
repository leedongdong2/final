<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.day {
cursor:pointer;
}
</style>
</head>
<body>
	<form id='dayForm' name='dayForm'>
				<input type="hidden" name="reservationMonth" value='${param.reservationMonth }'>
				<input type='hidden' name='serial' value='${param.serial }'>
				<input type='hidden' name="stadiumName" value='${param.stadiumName }'>
			    <input type="hidden" name="reservationDay">
			    <input type="hidden" name="price" value='${param.price}'>
			    <input type="hidden" name="point" value="${point }">
			    </form>
<div id="days">			    
<c:forEach var="rvationDay" items="${rvationDay }">
 <div class="day" onclick="reservationDay.moveTime('${rvationDay }')">${rvationDay }</div>
</c:forEach>
</div>
<span id = "myPoint">내 포인트 : ${point }</span>
<script src='./lib/jq.js'></script>
<script src="./js/stadium/reservation.js"></script>
<script>
reservationDay.init();
</script>
</body>
</html>