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
		$(this).click(function() {
			$("#reservationTime").val($(this).text());
		   $(".selectTime").css("background-color","#ffffff");
           $(this).css("background-color","#FF7F00");
		})
	});

    //예약버튼
	$("#reservationBtn").click(function() {
		const date  = new Date();
		const reservationMonth = $("#reservationMonth").val();
		const reservationDay = $("#reservationDay").val();
		let reservationTime = $("#reservationTime").val();
		reservationTime = reservationTime.substr(0,2);
		const reservationDate = new Date(date.getFullYear(),reservationMonth-1,reservationDay,reservationTime,00);
		
		const point = Number($("#point").val());
		const price = Number($("#price").val());
		if( date > reservationDate ) {
			alert("예약은 현재 시간 이후로 가능하십니다.");
		} else {
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
									$('#pageBox').load('search.stadium');
								} else {
									alert("예약 불가");
								}
							}
						})
				   }
				}
	         }
	})
	
	$("#backReservationDay").click(function(){
		const form = document.timeForm;
	    const data = $(form).serialize();
        $("#reserv").load("viewRvationDay.stadium",data);
	})

}

let myReservation = {};

myReservation.init = function() {
	
	
	$(".cancelReservation").each(function(index) {
			$(this).click(function(){
			   let form = $(".myReservationForm")[index];
			   let data = $(form).serialize();
		       console.log(data);
			   $.ajax({
				type : "POST",
				data : data,
				dataType : "json",
				url : "cancelReservation.stadium",
				success : function(resp){
					if(resp.result == true) {
				      $("#pageBox").load("moveMyReservation.stadium",data);
		            } else {
			         alert("취소실패");
		            } 
				}
				   
			   })
			 
			});
       })
     
  $("#backHistory").click(function(){
	  $('#pageBox').load('search.stadium');
  })
	
}
	
	