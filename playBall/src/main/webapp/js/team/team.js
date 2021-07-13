/**
 * 팀관련 스크립트
	김하람
 */
var team = {};

team.list = function() {
	$('#pageBox').load('list.team');
}

team.view = function(serial) {
	var frm = $('#frm_team')[0];
	frm.serial.value = serial;
	if( frm.nowPage.value == '' ) {
		frm.nowPage.value = 1;
	}

	var param = $(frm).serialize();

	$('#pageBox').load('view.team', param);
}

team.move = function(nowPage) {
	var frm = $('#frm_team')[0];
	frm.nowPage.value = nowPage;
	var param = $(frm).serialize();
	$('#pageBox').load('list.team', param);
}

team.myTeamInfo = function(serial){
	const data = "serial=" + serial
	$("#pageBox").load("view.team",data);
}

$("#btnSearch").click( function() {
	var frm = $('#frm_team')[0];
	frm.nowPage.value = 1;
	var param = $(frm).serialize();
	$('#pageBox').load('list.team',param);
})

$("#btnBackToList").click( function() {
	var frm = $('#frm_team')[0];
	var param = $(frm).serialize();
	$('#pageBox').load('list.team',param);
})

$("#frm_team #btnJoin").click( function() { //가입신청
	var form = $('#frm_team')[0];
	var id = form.mid.value;

	if( id == "" ) {
		alert ("로그인이 필요합니다.");
		$('#pageBox').load('./page/member/logForm.jsp');
	} else {
	 	var yn = confirm("가입을 신청하시겠습니까?");
		if( !yn ) {
			return;
		} else {
			var frm = $('#frm_team')[0];
			var param = $(frm).serialize();
	
			$.ajax({
				type : "POST",
				data : param,
				dataType : "json",
				url : "checkCount.team",
				success : function(resp) {
					if( resp.result == true ) {
						var form = $('#frm_team');
						var data = new FormData(form[0]);
						$.ajax({
							type : "POST",
							data : data,
							dataType : "json",
							enctype : "multipart/form-data",
							contentType : false,
							processData : false,
							url : "checkJoin.team",
							success : function(resp) {
								if( resp.result == true ) {
									$.ajax ({
										type : "POST",
										data : data,
										dataType : "json",
										enctype : "multipart/form-data",
										contentType:false,
										processData:false,
										url : "join.team",
										success : function(resp) {
											if( resp.result == true ) {
												alert("가입신청이 정상적으로 완료되었습니다.");
												$('#pageBox').load('view.team');
											} else {
												alert("가입신청 도중 오류 발생하였습니다.");
											}
										}
									})
								} else {
									alert("현 팀에 이미 가입신청완료하였습니다.");
								}
							}
						})
								} else {
									alert("팀 인원수의 초과로 가입신청이 불가합니다.");
					}
				}
			})
		}
	}
})

$("#frm_team #btnRegister").click(function() {//등록페이지 이동
	var frm = $('#frm_team')[0];
	frm.nowPage.value = 1;
	var param = $(frm).serialize();
	$('#pageBox').load('register.team',param);
})

$("#btnTeamList").click(function() { //팀리스트페이지 이동
	$('#pageBox').load('list.team');
})

$("#btnBye").click(function() {
	const frm = $('#frm_team')[0];
	const param = $(frm).serialize();
	$.ajax({
		type : "POST",
		data : param,
		dataType : "json",
		url : "deleteTeamMember.team",
		success : function(resp) {
			if( resp.result == true ) {
				alert("탈퇴되셨습니다")
				location.href = "./index.jsp";
			} else {
				alert("탈퇴 실패")
			}
		}
	})
});


$("#btnDelete").click(function(){
	let yn = confirm("팀을 해체하면 포인트가 차감됩니다. 진행 하시겠습니가?")
	
	if( !yn ) {
		return;
	} else {
	
	const frm = $('#frm_team')[0];
	const param = $(frm).serialize();
	$.ajax({
		type : "POST",
		data : param,
		dataType : "json",
		url : "dismantleTeam.team",
		success : function(resp){
			if(resp.result == true){
				alert("팀이 해체되었습니다");
				location.href = "./index.jsp" ;
			} else {
				alert("해체 실패");
			}
		}
	})
  }
})


$("#btnModify").click(function(){
	const frm = $('#frm_team')[0];
	const param = $(frm).serialize();
	$("#pageBox").load("moveModifyTeam.team",param);
})


$("#btnModifyR").click(function(){
	const frm = $('#frm_team');
	let data = new FormData(frm[0]);
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
           data = $(frm).serialize();
           $.ajax({
	           type : "POST",
               data : data,
               dataType : "json",
               url :"modifyTeam.team",
               success : function(resp){
	            if( resp.result == true ) {
		              alert("수정되었습니다!");
                    $("#pageBox").load("view.team",data);
	            } else {
		            alert("수정 실패");
	            }
               }
            })
        }
     })
})


$("#findStr").keydown(function(event){
		 if( event.keyCode == 13 ) {
			  	var frm = $('#frm_team')[0];
				frm.nowPage.value = 1;
				var param = $(frm).serialize();
				$('#pageBox').load('list.team',param);
				event.preventDefault();  	
		 }
})








function funcJoinList(serial,tid){
	var win = window.open('joinlist.team','win','width=1000px, height=830px');
	
	win.onload = function(){
		win.frm_team.serial.value = serial;
		win.frm_team.nowPage.value = 1;
	    win.move_teamFrm.tid.value = tid; 
	
	win.onbeforeunload = function () { 
	   const form = win.move_teamFrm;
       const data = $(form).serialize();
       $("#pageBox").load("./main.team",data);
	};
	
	}
}

   

team.acceptJoin = function(mid){
	var frm = $('#frm_team')[0];
	frm.mid.value = mid;
	var param = $(frm).serialize();
	console.log(param);
	$.ajax({
			type : "POST",
			data : param,
			dataType : "json",
			url : "checkCount.team",
			success : function(resp){
				if(resp.result == true){
					console.log("가입멤버수 확인완료");
						$.ajax({
						type : "POST",
						data : param,
						dataType : "json",
						url : "acceptJoin.team",
						success : function(resp){
							if(resp.result == true){
								console.log("가입신청자 수락 완료");
								alert("가입수락이 정상적으로 완료되었습니다.");
								$('body').load('joinlist.team',param);
							}else{
								alert("가입신청 도중 오류 발생하였습니다.");
								$('body').load('joinlist.team',param);
							}
						}
					})
				}else{
					alert("가입수락 오류발생. 멤버수를 확인해주세요.");
					$('body').load('joinlist.team',param);
				}
			}
		})
}



$("#photo").click(function(){
	$("#image").click();
})

var pic=document.getElementById("image");

//이미지 프리뷰하기
function preview(ev) {
	var files = ev.srcElement.files;
	var reader = new FileReader();
	reader.readAsDataURL(files[0]);
	reader.onload = function(ev2) {
		var img = new Image();
		img.src = ev2.target.result;
		$('#photo')[0].src=img.src;
	}
}

pic.addEventListener("change",preview);


team.rejectJoin = function(mid){
	var frm = $('#frm_team')[0];
	frm.mid.value = mid;
	var param = $(frm).serialize();
	console.log(param);
	$.ajax({
		type : "POST",
		data : param,
		dataType : "json",
		url : "rejectJoin.team",
		success : function(resp){
			if(resp.result == true){
					console.log("현신청자 삭제 완료");
					alert("선택한 신청자를 삭제완료하였습니다.");
					$('body').load('joinlist.team',param);
				}else{
					alert("삭제 도중 오류 발생하였습니다.");
					$('body').load('joinlist.team',param);
				}
			}
	})
}

team.jmove = function(nowPage){
	var frm = $('#frm_team')[0];
	frm.nowPage.value = nowPage;
	var param = $(frm).serialize();
	$('body').load('joinlist.team', param);
}

team.recordMove = function(nowPage){
	var frm = $('#frm_team')[0];
	frm.recordNowPage.value = nowPage;
	var param = $(frm).serialize();
	$("#pageBox").load('view.team', param);
}






