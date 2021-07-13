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
	const newPwd = $(form.password).val();
	const phone = $(form.phone).val();
	const post = $(form.post).val();
	const email = $(form.email).val();
	
	let newPasswordConfirmResult = confirmPasswordRegex(newPwd);
	let phoneConfirmResult = phoneRegexConfirm(phone);
	let emailConfirmResult = confirmEmailRegex(email);
	
	if(  newPwd != "" ) { 
	   if( confirmPwd == false ) {
		alert ( "새로운 비밀번호가 다릅니다" );
	   } else if( newPasswordConfirmResult == false ) {
		 alert( "비밀번호 형식을 맞춰주세요." );
	      $(form.password).focus();
	   } else if( phoneConfirmResult == false ) {
		 alert("핸드폰 형식을 맞춰주세요.");
	     $(form.phone).focus();
       } else if( emailConfirmResult == false ) {
		 alert("이메일 형식을 맞춰주세요.");
	     $(form.email).focus();
	   } else if( post == "" ) {
		 alert("주소를 입력해주세요.");
	     $(form.post).focus();
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
	} else if( phoneConfirmResult == false ) {
		 alert("핸드폰 형식을 맞춰주세요.");
	     $(form.phone).focus();
       } else if( emailConfirmResult == false ) {
		 alert("이메일 형식을 맞춰주세요~~");
	     $(form.email).focus();
	   } else if( post == "" ) {
		 alert("주소를 입력해주세요~~");
	     $(form.post).focus();
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




//정규식 패턴
function phoneRegexConfirm(phone) {
const phoneRegex = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;
let phoneRegexConfirm = phoneRegex.test(phone);
return   phoneRegexConfirm;
}

function confirmEmailRegex(email) {
	const emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
	let confirmEmailRegex = emailRegex.test(email);
	return confirmEmailRegex;
}

function confirmIdRegex(id) {
	const idRegex = /^[a-z]+[a-z0-9]{5,11}$/;
	let confirmIdRegex = idRegex.test(id);
	return confirmIdRegex;
}

function confirmPasswordRegex(password) {
	const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
    let confirmPasswordRegex = passwordRegex.test(password);
    return confirmPasswordRegex;
}
