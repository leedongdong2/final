/**
 * 
 */
var brd={};
brd.home=function() {
	
	
	$("#btn_cancle").click(function() {
		$("#pageBox").load("./search.stadium");
	})


    //구장 작성하기
	$("#btn_create").click(function() {
		let form = document.frm_upload;
		let data = new FormData(form);
		
		$.ajax({
			type : "POST",
			data : data,
			dataType : "json",
			enctype : "multipart/form-data",
			contentType : false,
			processData : false,
			url : "uploadStadium.upload",
			success : function(resp) {
				form = document.frm_create;
		        $(form.pic).val(resp.saveFileName);
		        data = $(form).serialize();
		        $.ajax({
			      type : "POST",
			      data : data,
			      dataType : "json",
			      url : "create.stadium",
			      success : function(resp) {
				      if( resp.result == true ) {
					     alert("정상적으로 등록되었습니다");
					     $("#pageBox").load("search.stadium");
				      } else { 
					     alert("등록 실패");
				      }
			      }
		        })
		        
			}
		})
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







