/**
 * 구장 목록
 */

var brd={};
brd.home=function() {
	
	//구장 작성창으로 이동
	$("#btn_create").click(function() {
		$("#pageBox").load("./page/stadium/Create.jsp");
	})
	
	
	//검색기능
	$('#btn_findstr').on('click', function(){
		var frm = $('#frm_find')[0];
		frm.nowPage.value = 1;
		var param = $(frm).serialize();
		$("#pageBox").load("./search.stadium",param);
		
	})
	
    //구장 디테일창으로 이동
	brd.detail = function(serial) {
		var frm = $('#frm_find')[0];
		frm.serial.value = serial;
		var param = $(frm).serialize();
		$("#pageBox").load("./detail.stadium",param);
	}
	 
     //페이지 이동
	 brd.move = function(nowPage) {
		var frm = $('#frm_find')[0];
		frm.nowPage.value = nowPage;
		var param = $(frm).serialize();
		
		$('#pageBox').load("./search.stadium", param);
	}
	
	//유저가 구장예약현황보기
	$("#myReservation").click(function() {
		const form  = document.moveMyReservationFrom;
		const data = $(form).serialize();
		$("#pageBox").load("moveMyReservation.stadium",data);
		
	})
	
	//관리자가 구장예약현황보기
	$("#reservationStatus").click(function() {
		const form  = document.moveMyReservationFrom;
		const data = $(form).serialize();
		$("#pageBox").load("moveMyReservation.stadium",data);
	})
	
	$("#findstr").keydown(function(event){
	    if( event.keyCode == 13 ) {
			var frm = $('#frm_find')[0];
			frm.nowPage.value = 1;
			var param = $(frm).serialize();
			$("#pageBox").load("./search.stadium",param);
			event.preventDefault();
	   }	
	})

}