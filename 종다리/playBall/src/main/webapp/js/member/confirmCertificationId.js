/**
 * 
 */




//중복아이디 체크
$("#confirmCertification").click(function(){
	if( $("#receiveCertificationCode").val() == $("#certificationCode").val() ) {
		 const form = document.confirmCertificationIdForm;
	     const data = $(form).serialize()
		     $.ajax ({
				  type : "POST",
		          data : data,
		          dataType : "json",
				  url : "findId.member",
		          success : function(resp) {
			          $("#findId").text(resp.mid);
		          }
		     })
	} else {
		alert("인증코드가 다릅니다");
	}
})