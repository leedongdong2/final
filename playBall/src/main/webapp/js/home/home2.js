/**
 * 
 */
$(function(){
	var swiper = new Swiper(".mySwiper", {
        effect: "coverflow",
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: "auto",
        coverflowEffect: {
          rotate: 120,
          stretch: 0,
          depth: 300,
          modifier: 1,
          slideShadows: true
        },
        autoplay: {
          delay: 4000,
          disableOnInteraction: false
        },
        pagination: {
          el: ".swiper-pagination"
        }
      });
})

var home = {};

function moveMatchBoard(){
	$("#pageBox").load("matchingView.matching");
}

function moveStadium(){
	$('#pageBox').load('search.stadium');
}

	/* 배너 끝 */
home.goTeam= function(serial){
	const data = "serial=" + serial;
	$("#pageBox").load("view.team", data);
}

home.goMatch = function(serial){
	const data = "serial=" + serial;
	$("#pageBox").load("matchingDetail.matching", data);
}

home.goStadium= function(serial){
	const data = "serial=" + serial;
	$("#pageBox").load("detail.stadium", data);
}