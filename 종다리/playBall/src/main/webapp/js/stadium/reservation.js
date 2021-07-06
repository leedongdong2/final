/**
 * 
 */

let reservationDay = {};
reservationDay.init = function(){

   //구장시간보기
	reservationDay.moveTime = function(rvationMonth) {
		const form = document.dayForm;
		$(form.reservationDay).val(rvationMonth);
		const data = $(form).serialize();
		$("#reserv").load("viewRvationTime.stadium",data);
	}

}


let reservationTime = {};
reservationTime.init =function() { 	
	
	//예약시간정하기
	$(".selectTime").each(function() {
		$(this).click(function(event) {
			event.preventDefault();
			$("#reservationTime").val($(this).text());
		})
	});

    //예약버튼
	$("#reservationBtn").click(function() {
		const point = Number($("#point").val());
		const price = Number($("#price").val());
		if( point < price ) {
		   alert("포인트가 모자랍니다");	
		} else if ( point >= price ) {
		   const reservationConfirm = confirm("정말 예약하시겠습니까?");
		   if ( !reservationConfirm ) {
			  return;
		   } else { 
				const form = document.timeForm;
				const data = $(form).serialize();
				$.ajax({
					type : "POST",
					data : data,
					dataType : "json",
					url : "reservation.stadium",
					success : function(resp) {
						if( resp.result  == true ) {
							alert("정삭적으로 예약되었습니다");
						} else {
							alert("예약 불가");
						}
					}
				})
		   }
		}
	})

}

let myReservation = {};

myReservation.init = function() {
	//예약 취소하기
	 $(".cancelReservation").each(function(index) {
	 $(this).click(function() {
		let form = document.myReservationForm[index];
		let data = $(form).serialize();
		console.log(data);
	 });
     })
	
}
	
	