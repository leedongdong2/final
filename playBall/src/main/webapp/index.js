/**
 * index 관련 스크립트
 */
$(document).ready(function(){
	$("#pageBox").load('goHome.home');
})

$(function(){
		//TOP 버튼
		// 보이기 | 숨기기 
		
	   $(window).scroll(function() {
       if ($(this).scrollTop() > 100) { //150 넘으면 버튼이 보여집니다.
    	$('#toTop').fadeIn(); 
   	 	//$('#toTop').css('left', $('#sidebar').offset().left); 
         // #sidebar left:0 죄표 
         } else { 
       	$('#toTop').fadeOut(); 
     	} });
      	// 버튼 클릭시 
		$("#toTop").click(function() { 
		   $('html, body').animate({ 
		      scrollTop : 0  // 0 까지 animation 이동합니다. 
		      }, 400); // 속도 400 
		      return false;
		   });
})