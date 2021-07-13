/**
 * 
 */




$("#mat").click(function() {
	$("#pagebox").load("./page/match/match_view.jsp");
})	
	
/* 수정 버튼 클릭시 게시글 수정 페이지로 */
$(".matUpdate").click(function() {
	$("#pagebox").load("./page/match/match_update.jsp");
})
	

//뒤로가기
$("#backHistory").click(function() {
	const form = document.matchDetail;
	const data = $(form).serialize();
	console.log(data);
	$("#pageBox").load("matchingView.matching",data);
})


/* 삭제 버튼 클릭식 알림창 */
$("#matDelete").click(function() {
      const yn = confirm("정말로 삭제하시겠습니가?");
	  const form = document.matchDetail;
	  const data = $(form).serialize();
      
      if( !yn ) {
	    return;
      } else {
         $.ajax({
	        type : "POST",
            data : data,
            dataType : "json",
            url : "delete.matching",
            success : function(resp) {
	          if ( resp.result == true ) {  
		         alert("삭제되었습니다");
                 $("#pageBox").load("matchingView.matching");
	          } else {
		         alert("삭제 오류");
	          }
            }  
         })
      }
})

/* 댓글 입력시 등록 버튼 색상 활성화 */
$(document).ready(function(){
	    $('textarea').keyup(function() {
			var inputLength = $(this).val().length;
			var remain = 0 + inputLength;
			$('#insertReplBtn').html(remain);
			
			if( remain==0 ) {
				$('#insertReplBtn').css('background-color', 'white');
			} else {
				$('#insertReplBtn').css('background-color', 'green');
			}
	    })
})

//수정페이지로 넘어가기
$("#matUpdate").click(function() {
	const form = document.matchDetail;
	const data = $(form).serialize();
	$("#pageBox").load("moveBoardModify.matching",data);
})

//댓글 입력하기
$("#insertReplBtn").click(function() {
	
	let dDate = $(".dDate").text();
    dDate = dDate.split("-");
	const nowDate = new Date();
	dDate = new Date(nowDate.getFullYear(),dDate[1]-1,dDate[2]);
     
    if(nowDate > dDate) {
	  alert("이미 만료된 게시물입니다.");
    } else {
	    const form = document.matchDetail;
		$(form.replDoc).val($("#repl_text").val());
		if( $(form.replNowPage).val()=="" ) {
			$(form.replNowPage).val(1);
		}
		const data = $(form).serialize();
		$.ajax({
			type : "POST",
			data : data,
			url :  "insertRepl.matching",
			success : function() {
				$("#pageBox").load("matchingDetail.matching",data);
			} 
		})
    }
})



$("#repl_text").keydown(function(event){
	if( event.shiftKey && event.keyCode == 13 ) {
	    	let dDate = $(".dDate").text();
		    dDate = dDate.split("-");
			const nowDate = new Date();
			dDate = new Date(nowDate.getFullYear(),dDate[1]-1,dDate[2]);
		     
		    if(nowDate > dDate) {
			  alert("이미 만료된 게시물입니다.");
		    } else {
			    const form = document.matchDetail;
				$(form.replDoc).val($("#repl_text").val());
				if( $(form.replNowPage).val()=="" ) {
					$(form.replNowPage).val(1);
				}
				const data = $(form).serialize();
				$.ajax({
					type : "POST",
					data : data,
					url :  "insertRepl.matching",
					success : function() {
						$("#pageBox").load("matchingDetail.matching",data);
					} 
				})
		    }	
	}
})


















//댓글 삭제하기
function deleteRepl(replSerial) {
	const form = document.matchDetail;
	if( $(form.replNowPage).val()=="" ) {
		$(form.replNowPage).val(1);
	}
	$(form.replSerial).val(replSerial)
	const data = $(form).serialize();
	$.ajax({
		type : "POST",
		data : data,
		url : "deleteRepl.matching",
		success : function() {
			$("#pageBox").load("matchingDetail.matching",data);
		}		
	})
}


//댓글 페이지 넘기기
function replPageMove(nowPage) {
	const form = document.matchDetail;
	$(form.replNowPage).val(nowPage);
	const data = $(form).serialize();
	$("#pageBox").load("matchingDetail.matching",data);
}

//매칭신청하기
function matchApply(sessionMid,mid,replWriterMid,replSerial,replVs) {
	if( sessionMid == mid ){
		const form = document.matchDetail;
		$(form.replSerial).val(replSerial);
		$(form.replWriterMid).val(replWriterMid);
		if( $(form.replNowPage).val()=="" ) {
			$(form.replNowPage).val(1);
		}
		const data = $(form).serialize();
		if( replVs == '매칭신청' ) {
		      $.ajax({
				type : "POST",
				data : data,
				dataType : "json",
				url : "checkVs.matching",
				success : function(resp) {
					if( resp.result == true ) {
						 $.ajax({
			               type : "POST",
			               data : data,
			               dataType : "json",
			               url : "matchApply.matching",
			               success : function(resp){
					           if( resp.result == true ) {
					               alert("신청완료되었습니다!");
					               $("#pageBox").load("matchingDetail.matching",data);
					           } else {
				                  alert("신청 실패!");
					           }
			               } 
		                 })
					} else {
						alert("이미 매칭신청된 게시판입니다!");
					}		
				}
			  })
		} else if ( replVs == '신청완료' ) {
			$.ajax({
				type : "POST",
				data : data,
				dataType : "json",
				url : "replCancelMatch.matching",
				success : function(resp) {
				  if( resp.result == true ) {
					alert("신청취소되었습니다!");
					$("#pageBox").load("matchingDetail.matching",data);
				  }	else {
					alert("취소 실패!");
				  }
				}
			})
		} 
  } else {
	alert("작성자만 신청가능합니다~");
  }
}