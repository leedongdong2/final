/**
 * 
 */
//변수
var confirmPwd = false;

//비밀번호 타입 변경
$("#eyeImg").click(function(){
	if ( $("#oriPwd").attr("type") == "password" ) {
		$("#oriPwd").attr("type","text");
		$("#newPwd").attr("type","text");
		$("#checkNewPwd").attr("type","text");
	} else {
		$("#oriPwd").attr("type","password");
		$("#newPwd").attr("type","password");
		$("#checkNewPwd").attr("type","password");
	}
})


//우편번호 검색
$('#postBtn').on('click',function(){
	const form = document.updateMemberForm;
	
	new daum.Postcode({
		//검색하고 더블클릭하면 매개변수를 타고 데이터가 들어온다
		oncomplete: function(data){
			//우편번호
			form.post.value = data.zonecode;
			//주소
			form.address.value = data.address;
		}
	}).open();
})

//삭제창 열기
$("#showDelete").click(function(){
	$("#deleteZone").removeClass("hide");
})
//삭제창 닫기
$("#deleteCancel").click(function(){
	$("#deleteZone").addClass("hide");
})







//회원정보 수정
$("#updateSign").click(function(){
	const form = document.updateMemberForm;
	const data = $(form).serialize();
	
	if( $("#newPwd").val() != "" ) { 
	   if( confirmPwd == false ) {
		alert ( "새로운 비밀번호가 다릅니다" );
	   } else {
		
		$.ajax({
			type : "POST",
			data : data,
			dataType : "json",
			url : "update.member",
			success : function(resp) {
			   if ( resp.confirmResult == false ) {
			      alert ("비밀번호가 틀립니다");	
			   } else if ( resp.result == false ) {
				  alert ( "수정이 불가합니다");
			   } else if ( resp.result == true ) {
				  alert ("수정 완료");
		          $("#pageBox").load("./page/member/logForm.jsp");
			   }
	        }
	    })
	   }      	
	} else {
		
		$.ajax({
			type : "POST",
			data : data,
			dataType : "json",
			url : "update.member",
			success : function(resp){
			   if ( resp.confirmResult == false ) {
			      alert ("비밀번호가 틀립니다");	
			   } else if ( resp.result == false ) {
				  alert ( "수정이 불가합니다");
			   } else if ( resp.result == true ) {
				  alert ("수정 완료");
		          $("#pageBox").load("./page/member/logForm.jsp");
			   }  
		   }
	   })
	}

})

//회원정보 삭제
$("#deleteBtn").click(function(){
	const form = document.updateMemberForm;
	const data = $(form).serialize();
	
	$.ajax({
	  type : "POST",
      data : data,
      dataType : "json",
      url : "delete.member",
      success : function(resp) {
	    if ( resp.confirmResult == false ) {
		    alert ( "비밀번호가 틀립니다.");
	    } else if ( resp.result == false ) {
		    alert ( "삭제 중 오류" );
	    } else if ( resp.result == true ) {
		    alert ( "정삭적으로 삭제 되었습니다." );
            location.href = "./index.jsp";
       	}
      }
	})
})









//비밀번호 확인
$("#checkNewPwd").keyup(function(){
	
	if( $("#newPwd").val() != "" ) {
	
		if( $("#newPwd").val() == $("#checkNewPwd").val() ) {
			$("#confirmPwd").text("비밀번호가 같습니다.");
			confirmPwd = true;
		} else {
			$("#confirmPwd").text("비밀번호가 다릅니다.");
			confirmPwd = false;
		}
	
	}
})


/*포지션 선택 */
$("#guard").click(function(){
	$("#guard").addClass("click");
	$("#center").removeClass("click");
	$("#foward").removeClass("click");
	$("#posit").val("guard");
  })

$("#center").click(function(){
	$("#guard").removeClass("click");
	$("#center").addClass("click");
	$("#foward").removeClass("click");
	$("#posit").val("center");
  })

$("#foward").click(function(){
	$("#guard").removeClass("click");
	$("#center").removeClass("click");
	$("#foward").addClass("click");
	$("#posit").val("foward");
  })

