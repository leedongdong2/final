/**
 * 
 */

  $("#sendMail").click(function(){
	const form = document.passSearchForm;
	const data = $(form).serialize();
	$.ajax({
		type : "POST",
	    data : data,
        dataType : "json",
        url : "confirmUser.member",
        success : function(resp) {
	      
          if( resp.result == false ){
		     alert("회원가입시 아이디와 메일을 적어주세요");
	      } else if ( resp.result == true ) {
		console.log(resp.result);
		     $("#pageBox").load("sendCertifyMailPwd.member",data);
	      }
        
       }
	})
})

 $("#movePwdSearch").click(function(){
	$("#pageBox").load("./page/member/pwdSearch.jsp")
})
	     


