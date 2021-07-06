/**
 * header 메뉴에 대한 스크립트
	현재 장터,회원가입 연결 - hr (2021.)
 */

var main = {};

main.init = function(){
	
}


main.menu = function(menu,tid){
	switch(menu){
		case 'login' :
		$('#pageBox').load('./page/member/logForm.jsp');
		break;
		
		case 'match':
		$('#pageBox').load('./matchingView.matching');
		break;
		
		case 'comu':
		$('#pageBox').load('./page/comu/freegesi_test1.jsp');
		//시윤씨 오시면 변경
		break;
		
		case 'stadium':
		$('#pageBox').load('search.stadium');
		break;
		
		case 'team' :
		const param = "tid=" + tid;
   		$("#pageBox").load("./main.team",param);
		break;
		
		case 'video' :
		$('#pageBox').load('./page/video/Video.jsp');
		break;
		
		case 'home' :
		window.location.href = "./index.jsp";
		break; 

		case 'logout' :
		$.ajax({
		url : "./logOut.member",
		success : function(){
			location.href = "./index.jsp"
				}
		})
		break;
		
		case 'myPage' :
		const url = "./myPage.member"
   		const data = "mid=" + $("#moveMyPage").val();
   		$("#pageBox").load( url,data );
		break;
	
	}
}

main.init();