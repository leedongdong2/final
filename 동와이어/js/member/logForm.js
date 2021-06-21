/**
 * 
 */
//로그인
$("#logBtn").click(function(){
	const form = document.logForm;
	const data = $(form).serialize();
	
	  $.ajax({
	      type : "POST",
	      data : data,
	      dataType : "json",
	      url : "logIn.member",
	      success : function(resp) {
		     if ( resp.findId == false ) {
			   alert ("없는 아이디 입니다.");
		     } else if ( resp.findUser == false ) {
			   alert ("아이디나 비밀번호가 맞지 않습니다");
		     } else if ( resp.findUser == true ) {
			   location.href = "./index.jsp" ;
		     }
	     
         }  		
		
	});

});

//회원가입창 이동
$("#moveSingUp").click(function(){
	$( "#pageBox" ).load( "./page/member/signUp.jsp" );
})




