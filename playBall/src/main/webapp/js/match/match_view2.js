/**
 * 
 */

/**
 * 
 */


	
/* 글쓰기 버튼 클릭시 게시글 작성 페이지로 */
$("#btn_write").click(function() {
	$("#pageBox").load("./page/match/match_register.jsp");
})

	
//검색하기	
$("#search").click(function() {
	const form = document.matchingViewForm;
	const nowPage = form.nowPage;
	$(nowPage).val("1");
	const data = $(form).serialize();
	$("#pageBox").load("matchingView.matching",data);
})

//버튼색깔 바꾸기
if($(".btnMatch").val() == '매칭중'){
   $(".btnMatch").css('background-color','rgba(240, 130, 71, 0.3)');
}


//페이지이동
function pageMove(nowPage) {
	const form = document.matchingViewForm
	$(form.nowPage).val(nowPage);
	const data = $(form).serialize();
	$("#pageBox").load("matchingView.matching",data);
}


//디테일창으로 이동
function moveDetail(serial) {
	const form = document.matchingViewForm
	$(form.serial).val(serial);
	const data = $(form).serialize();
	$("#pageBox").load("matchingDetail.matching",data);
}

//매칭취소하기
function matchCancle(sessionMid,mid,serial,vs) {
	if( sessionMid == mid ) {
		const form = document.matchingViewForm
		$(form.serial).val(serial);
		const data = $(form).serialize();
		if ( vs == '매칭완료') {
			$.ajax({
				type : "POST",
				data :  data,
				dataType : "json",
				url : "cancelMatch.matching",
				success : function() {
				 $("#pageBox").load("matchingView.matching");
				}	
			})
		} else if ( vs == '매칭중' ) {
			alert("댓글에서 매칭할 팀을 선정해주세요");
		}
  
    } else {
	   alert("작성자만 변경 가능합니다");
    }
}



$("#find").keydown(function(event) {
	   
        if(event.keyCode == 13){
			const form = document.matchingViewForm;
			const nowPage = form.nowPage;
			$(nowPage).val("1");
			const data = $(form).serialize();
			$("#pageBox").load("matchingView.matching",data);
			event.preventDefault();
	   }	
})










/*셀렉트 버튼 체크 */
 function checkLoca(loca){
	const form = document.matchingViewForm;
	switch(loca){
		case "all" : 
		   form.loca[0].selected = true;
            break;
		case "서울" : 
		   form.loca[1].selected = true;
            break;
        case "경기" : 
           form.loca[2].selected = true;
             break;
    }
}

 function checkTag(tag){
	const form = document.matchingViewForm;
	switch(tag){
		case "all" : 
		   form.tag[0].selected = true;
            break;
		case "2:2" : 
		   form.tag[1].selected = true;
            break;
        case "3:3" : 
           form.tag[2].selected = true;
             break;
    }
}

 function checkTier(tier){
	const form = document.matchingViewForm;
	switch(tier){
		case "all" : 
		   form.tier[0].selected = true;
            break;
		case "상" : 
		   form.tier[1].selected = true;
            break;
        case "중" : 
           form.tier[2].selected = true;
             break;
        case "하" :
           form.tier[3].selected = true;
           break;
      }
}