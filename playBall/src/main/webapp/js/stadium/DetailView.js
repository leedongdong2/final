/**
 * 
 */
var brd={};
brd.home=function() {
	$("#back").click(function(){
		const form = document.frm_back;
	  	const data = $(form).serialize();
		$("#pageBox").load("./search.stadium",data);
	})
	
	//수정페이지로 넘어가기
	brd.modify = function(serial) {
		var frm = $('#frm_update')[0];
		frm.serial.value = serial;
		var param = $(frm).serialize();
		$('#pageBox').load('./modify.stadium', param);
	}
	
	//구장 삭제하기
	brd.delete = function(serial) {
		var frm = $('#frm_update')[0];
		frm.serial.value = serial;
		var param = $(frm).serialize();
		$.ajax({
		type : 'post',
		url : './delete.stadium',
		data : param,
		dataType : "json",
		success : function(resp) {
			if( resp.findRecord == false ) {
			  alert ("예약중인 구장입니다 예약취소를 해주세요");      
			} else if( resp.result == false) {
				alert ("삭제 취소");
			} else if( resp.findRecord == true && resp.result == true ) {
				alert("삭제 되었습니다");
				$("#pageBox").load("./page/stadium/View.jsp");
			}
		}
	   })
	}
	
	//달 왼쪽으로 넘기기
	$("#leftMonth").click(function() {
		let month = $("#reservationMonth");
	    let monthNum = Number(month.val());
	    if( monthNum != 1 ) {
	    	month.val(monthNum - 1);
	        const form = document.frm_update;
	        $(form.reservationMonth).val(monthNum -1 );
		    const data = $(form).serialize();
			$("#reserv").load("viewRvationDay.stadium",data);
         }
        
	
	});	
	
	//달 오른쪽으로 넘기기
	$("#rightMonth").click(function() {
		let month = $("#reservationMonth");
	    let monthNum = Number(month.val());
	    if( monthNum != 12 ) {
	    	month.val(monthNum + 1);
		    const form = document.frm_update;
	        $(form.reservationMonth).val(monthNum + 1);
		    const data = $(form).serialize();
			$("#reserv").load("viewRvationDay.stadium",data);
        }
        
	
	});	
	
	//도큐먼트 레디될떄 달 예약 페이지 나오게 하기
	$(function() {
		let date = new Date();
		let nowMonth = date.getMonth();
		$("#reservationMonth").val(nowMonth+1);
		const form = document.frm_update;
		$(form.reservationMonth).val(nowMonth+1);
	    const data = $(form).serialize();
		$("#reserv").load("viewRvationDay.stadium",data);	
	})


 

}