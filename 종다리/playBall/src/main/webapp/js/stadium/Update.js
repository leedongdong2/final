/**
 * 
 */
 
 brd={};
 brd.home=function() {
	
	//구장 소개 업데이트
	brd.update=function() {
	var frm = $('#frm_update')[0];
	var param = $(frm).serialize();
	$.ajax({
		type : 'post',
		url : './update.stadium',
		data : param,
		success : function(resp) {
			alert("수정이 완료되었습니다");
			$("#pageBox").load("./page/stadium/View.jsp");
		}
	})
	}	

}