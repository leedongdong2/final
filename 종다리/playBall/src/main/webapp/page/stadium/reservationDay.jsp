<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.hover {
cursor:pointer;
}
</style>
</head>
<body>
크크크 여긴 월이 나온다아!!
	<form id='dayForm' name='dayForm'>
				<input type="text" name="reservationMonth" value='${param.reservationMonth }'>
				<input type='text' name='serial' value='${param.serial }'>
				<input type='text' name="stadiumName" value='${param.stadiumName }'>
			    <input type="text" name="reservationDay">
			    <input type="text" name="price" value='${param.price}'>
			    <input type="text" name="point" value="${point }">
			    </form>
<c:forEach var="rvationDay" items="${rvationDay }">
 <div class="hover" onclick="reservationDay.moveTime('${rvationDay }')">${rvationDay }</div>
</c:forEach>
내 포인트 : ${point }
<script src='./lib/jq.js'></script>
<script src="./js/stadium/reservation.js"></script>
<script>
reservationDay.init();
</script>
</body>
</html>