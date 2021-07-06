/**
 * 
 */

//메일보내기
$("#sendMail").click(function() {
	const form = document.idSearchForm;
	const data = $(form).serialize();
	$.ajax({
		type : "POST",
	    data : data,
        dataType : "json",
        url : "confirmUser.member",
        success : function(resp) {
          if( resp.result == false ){
		     alert("회원가입시 이름과 메일을 적어주세요");
	      } else if ( resp.result == true ) {
		     $("#pageBox").load("sendCertifyMailId.member",data);
	      }
        }
	})
})

//패스워드찾기창으로 이동
$("#movePwdSearch").click(function() {
	$("#pageBox").load("./page/member/pwdSearch.jsp")
})
	     


