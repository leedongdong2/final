/**
 * 
 */





$("#confirmCertification").click(function(){
	if( $("#receiveCertificationCode").val() == $("#certificationCode").val() ) {
		 
	     const form = document.confirmCertificationPwdForm;
	     const data = $(form).serialize();

	        $.ajax ({
			  type : "POST",
	          data : data,
	          dataType : "json",
			  url : "issuePwd.member",
	          success : function(resp) {
		          $("#issuePwd").text(resp.issuePwd);
	          }
	        })
	} else {
		alert("인증코드가 다릅니다");
	}
})