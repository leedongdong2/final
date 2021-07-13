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
	const phone = $(form.phone).val();
	const email = $(form.email).val();
	const password = $(form.password).val();
	const post = $(form.post).val();
	
	let phoneConfirmResult = phoneRegexConfirm(phone);
	let emailConfirmResult = confirmEmailRegex(email);
	let passwordConfirmResult = confirmPasswordRegex(password);
	console.log(phoneConfirmResult);
	
	if( passwordConfirmResult == false ) {
		alert ( "패스워드 형식을 맞춰주세요." );
		$(form.password).focus();	
	} else if( phoneConfirmResult == false ) {
		alert ( "핸드폰형형식을 맞춰주세요." );
		$(form.phone).focus();
	} else if( post == "") {
		alert ( "주소를 입력해주세요." );
		$(form.post).focus();
	} else if ( emailConfirmResult == false ) {
		alert ( "이메일 형식을 맞춰주세요." );
		$(form.email).focus();
	} else if ( duplicatedId == false ) {
		alert ( "아이디중복체크를 해주세요." );
	} else if ( confirmPwd == false ) {
		alert ( "비밀번호가 다릅니다!" )
	} else {
	    const data = $(form).serialize();
		$.ajax ({
		  type : 'POST',
		  data : data,
		  url : "insert.member",
		  dataType : "json",
		  success : function(resp) {
			duplicatedId = false;
		  $("#pageBox").load("./page/member/logForm.jsp");
		  }
	    });
	}
});

/*중복 아이디 확인 */
$("#duplicatedId").click(function() {
	const form = document.singUpFrm;
	const id = $(form.mid).val();
	let idConfirmResult = confirmIdRegex(id);
	if( idConfirmResult == false ){
		alert("아이디 형식에 맞춰주세요~~")
	} else {
		const data = $(form).serialize();
		$.ajax({
			type : 'POST',
			data : data,
			dataType : "json",
			url : "duplicatedId.member",
			success : function(resp){
				switch(resp.result){
					case false : 
					duplicatedId = false;
					alert ( "중복된 아이디 입니다." );
					break;
					case true :
					alert ( "사용가능한 아이디 입니다." ); 
					duplicatedId = true;
					break;
				}	
			}
		})
   }
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

//*정규식 */
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

