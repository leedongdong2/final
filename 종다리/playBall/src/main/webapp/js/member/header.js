/**
 * 
 */
//내정보 페이지로 이동
$("#moveMyPage").click(function() {
   const url = "./myPage.member"
   const data = "mid=" + $("#moveMyPage").val();
   $("#pageBox").load( url,data );
})

//로그아웃
$("#logOut").click(function(){
	$.ajax({		
		url : "./logOut.member",
		success : function(){
			location.href = "./index.jsp"
		}
	})
})