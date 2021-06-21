/**
 * 
 */

  $("#sendMail").click(function(){
	$.ajax({
		type : "POST",
		url : "sendCertifyMail.member"
		})
})