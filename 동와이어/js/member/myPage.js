/**
 * 
 */

//수정페이지로 이동
$("#moveModifyPage").click(function(){
	const url = "./modifyPage.member"
	const data = "mid=" + $("#mid").val();
	$( "#pageBox" ).load( url,data );
})