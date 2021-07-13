/**
 * 
 */


	
//게시물수정
$("#btnUpdate").click(function() {
	$("#image").val(imageList);
	const form = document.matchUpdateForm;
	const data = $(form).serialize();
	$.ajax({
		type : "POST",
		data : data,
		dataType : "json",
		url : "update.matching",
		success : function(resp) {
		     if( resp.result == true ) {
			     alert("수정되었습니다");
              $("#pageBox").load("matchingDetail.matching",data);
		     } else {
			     alert("수정 실패");
		     }
		}
	});
})

$("#btnCancle").click(function(){
	$("#image").val(imageList);
	const form = document.matchUpdateForm;
	const data = $(form).serialize();
	
	if( $("#image").val() != "" ) {
	$.ajax({
		type : "POST",
		data : data,
		url : "cancelRegister.matching",
		success : function(resp) { 
		  $("#pageBox").load("matchingDetail.matching",data);
		}	
	})
   } else {
	$("#pageBox").load("matchingDetail.matching",data);
   }
})

















/*썸머노트 이미지 업로드하는 부부운~~ */ 
 /*변수 */
let imageList = [];

$(document).ready(function(){
	$('#summernote').summernote({
		  height: 600,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다' , //placeholder 설정
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
             console.log(imageList);
		}
	});
}

/*이미지 업로드 끝이요 */


/*셀렉트 버튼 체크 */
 function checkLoca(loca){
	const form = document.matchUpdateForm;
	switch(loca){
		case "서울" : 
		   form.loca[0].selected = true;
            break;
        case "경기" : 
           form.loca[1].selected = true;
             break;
    }
}

 function checkTag(tag){
	const form = document.matchUpdateForm;
	switch(tag){
		case "2:2" : 
		   form.tag[0].selected = true;
            break;
        case "3:3" : 
           form.tag[1].selected = true;
             break;
    }
}

 function checkTier(tier){
	const form = document.matchUpdateForm;
	switch(tier){
		case "상" : 
		   form.tier[0].selected = true;
            break;
        case "중" : 
           form.tier[1].selected = true;
             break;
        case "하" :
           form.tier[2].selected = true;
           break;
      }
}



