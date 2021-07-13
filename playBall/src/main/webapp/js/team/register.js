/**
 * team관련 스크립트
 */
	let check = false; 
	//사진 클릭시 
	$('#photo').on('click', function() {
		var frm = document.frm_team;
		frm.file.click();
	})
	
		// file tag의 내용이 변경된 경우
	var pic = $('#image')[0];
	if( pic != null ) {
		pic.onchange = function(ev) {
			var files = ev.srcElement.files;
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onload = function(ev2) {
				var img = new Image();
				img.src = ev2.target.result;
				$('#photo')[0].src = img.src;
			}
		}
	}

//팀게시물등록버튼
$("#btnTRegR").click(function(){
	const form = $("#frm_team");
	let data = new FormData(form[0]);
	
	let yn = confirm("팀생성은 포인트가 차감됩니다 진행하시겠습니가?")
	
	if( !yn ) {
		return;
	} else {
	
		if( check == false ) {
			alert("팀명 중복확인해주세요.");
		} else {
		
		$.ajax({
			type : "POST",
			data : data,
			dataType : "json",
			enctype : "multipart/form-data",
			contentType : false,
			processData : false,
			url : "insert.upload",
			success : function(resp) {//파일업로드 완료
			   $( "#fileName" ).val(resp.fileName);
	           data = $(form).serialize();
	           $.ajax({
		         type : "POST",
	             data : data,
	             dataType : "json",
	             url : "insertTeam.team",
	             success : function(resp){// 팀등록 완료
		          if( resp.result == true ) {
			        $.ajax({
						type : "POST",
						data : data,
						dataType : "json",
						url : "insertTeamMember.team",
						success : function(resp) {
							console.log("멤버 등록 완료");
						}
					})	   
					alert("등록되었습니다.");
					location.href = "./index.jsp";
		          } else {
			          alert("등록오류");
		          }
	             }
	           })
		   	}
		  })
		}
   }
})

//중복체크
$( "#checkTid" ).click( function() {
	const form = document.frm_team;
	const data = $(form).serialize();
	
	$.ajax({
		type : 'POST',
		data : data,
		dataType : "json",
		url : "checkTid.team",
		success : function(resp) {
			
			switch(resp.result) {
				case false : 
				alert ( "중복된 팀명 입니다." );
				check = false;
				break;
				
				case true :
				alert ( "사용가능한 팀명 입니다." ); 
				check = true;
				break;
			}	
		}
	})
})