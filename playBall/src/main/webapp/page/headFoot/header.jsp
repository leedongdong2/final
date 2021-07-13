<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang = 'ko'>
<head>
<meta charset="UTF-8">
<meta name = 'viewport' content = 'width= device-width, initial-scale=1.0'>
<title>header</title>
<link rel = 'stylesheet' type = 'text/css' href = './css/headFoot/header.css'>
<script src = './lib/jq.js'></script>
<script src = './js/headFoot/header.js'></script>
</head>
<body>
<div id = 'header'>
	<header>
		<section id="sectionLogin">
			<nav class="cl-effect-1" id="cl-effect-1">
		<c:choose>
		     <c:when test="${empty sessionScope.mid}">
		  		<a href = "#" class = "menu" id = "loginIcon" onclick="main.menu('login')">Login / SignUp</a>
        	</c:when>
        	<c:when test="${not empty sessionScope.mid and sessionScope.mid eq 'hong' }">
        	    <input type="hidden" value="${sessionScope.mid }" id="moveMyPage">
    			<a class = 'menu'  onclick="main.menu('myPage')">관리자</a>
    			<a class = 'menu' id ='logOut' onclick="main.menu('logout')">Logout</a>
            </c:when>
            <c:otherwise>
            	<input type = "hidden" value="${sessionScope.mid }" id="moveMyPage">
    			<a class = 'menu' onclick="main.menu('myPage')">${sessionScope.mid }</a>
   				<a class = 'menu' id = 'logOut' onclick="main.menu('logout')">Logout</a>
   		     </c:otherwise>
    	</c:choose>
   		
			</nav>
		</section>
	  	
	  	<nav>
			<div id = 'home'>
				<a href = "#" id = "logo" onclick="main.menu('home')">Play ball</a>
			</div>
		    <section id="sectionMenu">
		    	<nav class="cl-effect-20" id="cl-effect-20">
			    	<a href = "#" class = "menu" id = "notice" onclick="main.menu('notice')">
			    		<span data-hover="Notice">Notice</span>
			    	</a>
				    <a href = "#" class = "menu" id = "match" onclick="main.menu('match')">
				    	<span data-hover="Match">Match</span>
				    </a>                                                                 
				    <a href = "#" class = "menu" id = "Team" onclick="main.menu('team','${sessionScope.tid}')">
				    	<span data-hover="Team">Team</span>
				    </a>
				    <a href = "#" class = "menu" id = "stadium" onclick="main.menu('stadium')">
				    	<span data-hover="Stadium">Stadium</span>
				    </a>
				  </nav>
		    </section>
	  	</nav>
	  	
	</header>
</div>
</body>
</html>