/**
 * 메인화면 관련 스크립트
	김하람
	2021-06-20
 */

	/* 배너 */
     var swiper = new Swiper(".mySwiper", {
        direction: "vertical",
        spaceBetween: 10,
        slidesPerView: 5,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true
      });
      var swiper2 = new Swiper(".mySwiper2", {
		initialSlide: 0,
		observer: true,
      	observeParents: true,
        spaceBetween: 10,
        effect: "fade",
        loop: true,
        thumbs: {
          swiper: swiper
        },
        autoplay: {
          delay: 2500,
          disableOnInteraction: false
        }
      });

function moveMatchBoard(){
	$("#pageBox").load("matchingView.matching");
}

teamlist = function(){
	$('#pageBox').load('list.team');
}