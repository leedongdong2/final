<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<input type="button" id="backHistory" value="뒤로가기">
<c:forEach var="list" items="${list }">
<div>
<form class="myReservationForm" name ="myReservationForm">
<input type="hidden" name="serial" value="${list.serial }">
<input type="text" name="reservationId" value="${list.reservationId }">
<input type="text" name="stadiumName" value="${list.stadiumName }"><input type="text" name="price" value="${list.price }"><input type="text" name="reservedDate" value="${list.reservedDate}"><input type="text" name="reservationMonth" value="${list.reservationMonth}"><input type="text" name="reservationDay" value="${list.reservationDay}"><input type="text" name="reservationTime" value="${list.reservationTime}"><input type="button" class="cancelReservation" value="예약취소">
</form>	
</div>
</c:forEach>
</div>
<script src='./lib/jq.js'></script>
<script>

$(".cancelReservation").each(function(index){
	$(this).click(function(){
	   let form = document.myReservationForm[index];
	   let data = $(form).serialize();
	   $.ajax({
		type : "POST",
		data : data,
		dataType : "json",
		url : "cancelReservation.stadium",
		sussecc : function(){
			
		}
		   
	   })
	 
	});
     })
     
  $("#backHistory").click(function(){
	  $('#pageBox').load('search.stadium');
  })
</script>
</body>
</html>