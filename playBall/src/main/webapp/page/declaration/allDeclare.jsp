<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/declaration/allDeclare.css'>
</head>
<body>
<div class="allDeclare">
   <div id="DtList">
       <div class="title">신고리스트
           <form class="searchFrm">
               <input type="search">
               <input type="button" value="검색">
            </form>
       </div> 
         
       <div id="items">
         <div class="itemTitle">
             <span class="mid">아이디</span><span class="mname">이름</span><span class="dtDoc">신고내용</span><span class="nal">날짜</span><span class="dtCount">신고횟수</span><span class="fromUser">신고자 아이디</span><span class="boardDoc">게시글</span>
         </div>
         <div class="item">
             <span class="mid">hong</span><span class="mname">길동아</span><span class="dtDoc">아 저그거 완죤 이상한 아이엿네요</span><span class="nal">날짜</span><span class="dtCount">3</span><span class="fromUser">hong2</span>
               <form class="goBoard">
                  <input type="hidden">
                  <input type="submit" value="게시글보기"> 
               </form>
         </div>


       </div>
   </div>
  </div>
</body>
</html>