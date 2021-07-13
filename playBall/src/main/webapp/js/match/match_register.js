/**/


//매치게시판 만들기
$("#saveBtn").click(function() {
	$("#image").val(imageList);
	const form = document.matchingRegisterForm;
	const data = $(form).serialize();
    const date = $(form.dDate).val();
    const nowDate = new Date();
    const dDate = new Date(date); 
    
    if( date == "" ) {
	  alert("대결일은 필수 입니다.");
	} else if(dDate < nowDate) {
		alert("대결일은 현재일 전으로 하실수 없습니다.")
	} else {
	$.ajax({
		type : "POST",
		data : data,
		dataType : "json",
		url : "register.matching",
		success : function(resp) {
			if( resp.result == true ) {
				alert("정상 등록 되었습니다.");
				$("#pageBox").load("matchingView.matching");
			} else {
				alert("실패 새로고침후 다시 해주세요");
			}
		}
	});
	}
})

$("#btnCancle").click(function() {
	$("#image").val(imageList);
	const form = document.matchingRegisterForm;
	const data = $(form).serialize();
	
	if( $("#image").val() != "" ) {
	$.ajax({
		type : "POST",
		data : data,
		url : "cancelRegister.matching",
		success : function(resp) { 
		  $("#pageBox").load("./matchingView.matching");
		}	
	})
   } else {
	 $("#pageBox").load("./matchingView.matching");
   }
})











/*썸머노트 이미지 업로드하는 부부운~~ */ 
 /*변수 */
let imageList = [];


$(document).ready(function(){
	$('#summernote').summernote({
		  height: 600,                 // 에디터 높이
		  minHeight: 600,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '장소와 시간을 적어주세요(최대 2048자)' , //placeholder 설정
          toolbar: [
			    // [groupName, [list of button]]
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			  callbacks : {
        	  onImageUpload : function(files){
        		 for(var i = files.length-1;i>=0;i--){
        			 uploadSummernoteImageFile(files[i],this);
        		 } 
        	  }
          }
	        });
	});
	

	
	
	$("#summernote").on('drop',function(e){
    for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
    	uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summer")[0]);
    }
   e.preventDefault();
})

function uploadSummernoteImageFile(file){
	var data = new FormData();
	data.append("file",file);
	$.ajax({
		data : data,
		dataType : "json",
		type : "POST",
		url : "fileUpLoad.upload",
		contentType : false,
		processData : false,
		enctype:"multpart/form-data",
		success : function(data){
			 var image = $('<img>').attr('src', data.id);
             $('#summernote').summernote("insertNode", image[0]);
             const saveList = data.id.replace("./img/matchBoard/","");
             imageList.push(saveList);
		}
	});
}

/*이미지 업로드 끝이요 */