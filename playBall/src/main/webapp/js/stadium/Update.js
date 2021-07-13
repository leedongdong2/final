/**
 * 
 */
 
 brd={};
 brd.home=function() {
	
	//구장 소개 업데이트
	brd.update=function() {
	
	let frm = $('#frm_upload')[0];
	let param = new FormData(frm);
	console.log(param);	
		$.ajax({
			type : "POST",
			data : param,
			dataType : "json",
			enctype : "multipart/form-data",
			contentType : false,
			processData : false,
			url : "uploadStadium.upload",
			success : function(resp) {
				 frm = $('#frm_update')[0];
		         $(frm.pic).val(resp.saveFileName);
		         param = $(frm).serialize();
				$.ajax({
					type : 'POST',
					url : './update.stadium',
					data : param,
					success : function(resp) {
						alert("수정이 완료되었습니다");
						$("#pageBox").load("search.stadium");
					}
				})
	       }	
	})
}

//파일태그 열기
$("#image_preview").click(function() {
	$("#attfile").click();
});


var pic=document.getElementById("attfile");

//이미지 프리뷰하기
function preview(ev) {
	var files = ev.srcElement.files;
	var reader = new FileReader();
	reader.readAsDataURL(files[0]);
	reader.onload = function(ev2) {
		var img = new Image();
		img.src = ev2.target.result;
		$('#img')[0].src=img.src;
	}
}

pic.addEventListener("change",preview);

}