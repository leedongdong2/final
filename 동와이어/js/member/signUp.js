/**
 * 
 */
/*비밀번호 TEXT로 변경해서 확인 */
let duplicatedId = false; 
let confirmPwd = false;

//비밀번호 타입 변경
$("#eyeImg").click(function(){
	if ( $("#pwd").attr("type") == "password" ) {
		$("#pwd").attr("type","text");
		$("#checkPwd").attr("type","text");
	} else {
		$("#pwd").attr("type","password");
		$("#checkPwd").attr("type","password");
	}
})


/*우편번호 검색하기 */
$('#postBtn').on('click',function(){
	const form = document.singUpFrm;
	
	new daum.Postcode ({
		//검색하고 더블클릭하면 매개변수를 타고 데이터가 들어온다
		oncomplete : function(data){
			//우편번호
			form.post.value = data.zonecode;
			//주소
			form.address.value = data.address;
		}
	}).open();
})

/*회원가입 */
$("#signBtn").click(function(){
	const form = document.singUpFrm;
	const data = $(form).serialize();
	
	if ( duplicatedId == false ) {
		alert ( "아이디중복체크를 해주세요~" );
	} else if ( confirmPwd == false ) {
		alert ( "비밀번호가 다릅니다!" )
	} else {
		$.ajax ({
		  type : 'POST',
		  data : data,
		  url : "insert.member",
		  dataType : "json",
		  success : function(resp) {
		  $("#pageBox").load("./page/member/logForm.jsp");
		  }
	    });
	}
});

/*중복 아이디 확인 */
$("#duplicatedId").click(function() {
	const form = document.singUpFrm;
	const data = $(form).serialize();
	
	$.ajax({
		type : 'POST',
		data : data,
		dataType : "json",
		url : "duplicatedId.member",
		success : function(resp){
			
			switch(resp.result){
				case false : 
				alert ( "중복된 아이디 입니다." );
				duplicatedId = false;
				break;
				
				case true :
				alert ( "사용가능한 아이디 입니다." ); 
				duplicatedId = true;
				break;
			}	
		}
	})
})

/*비밀번호 확인 */
$("#checkPwd").keyup(function(){
	if( $("#checkPwd").val() == $("#pwd").val() ) {
		$("#confirmPwd").text("비밀번호가 같습니다.");
		confirmPwd = true;
	} else {
		$("#confirmPwd").text("비밀번호가 다릅니다.");
		confirmPwd = false;
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