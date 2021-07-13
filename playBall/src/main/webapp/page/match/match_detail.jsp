<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link href="./css/match/match_detail.css" type='text/css' rel="stylesheet"/>

</head>
<body>
<div id='match_detail'>
	<form name="matchDetail" id="matchDetail">
		<input type="hidden" name="loca" value="${param.loca }">
		<input type="hidden" name="tag" value="${param.tag }">
		<input type="hidden" name="tier" value="${param.tier }">
		<input type="hidden" name="findStr" value="${param.findStr }">
		<input type="hidden" name="nowPage" value="${param.nowPage == null ? 1 : param.nowPage }" >
		<input type="hidden" name="serial" value="${param.serial }">
		<input type="hidden" name="mid" value="${sessionScope.mid }">
		<input type="hidden" name="replMid" value="${sessionScope.mid }">
		<input type="hidden" name="replNowPage" value="${param.replNowPage == null ? 1 : param.replNowPage }">
		<input type="hidden" name="replDoc">
		<input type="hidden" name="replSerial" value="1">
		<input type="hidden" name="replWriterMid">
	</form>
	<label id='mat'>Match</label>
	<input type="button" value="뒤로가기" id="backHistory">
	<div id='line'></div>
	<br/>
	
	<div id='title_zone'>
		<label id='title'>${vo.title }</label>
		<br>
		
		<span class='mid'>${vo.mid }</span>
		
		<div id='sel'>
			<label>지역 : <span class='loc'>${vo.loca }</span></label>
			<label>종류 : <span class='tag'>${vo.tag }</span></label>
			<label>실력 : <span class='tier'>${vo.tier }</span></label>
			<label>약속기한 : <span class='dDate'>${vo.dDate }</span></label>
		</div>
		
		<div class='nal_hit'>
			<span class='nal'>${vo.mDate }</span>
			<label>
				조회수 <span class='hit'>${vo.hit }</span>
			</label>
		</div>
	</div>
	
	<div id='line1'></div>
	
	<div id='text-view'>
		${vo.doc }
	</div>
	
	<div class='SUD'>
		
		<c:if test="${sessionScope.mid eq vo.mid }">
			<input type='button' class='matUpdate' id="matUpdate" value='수정'/>
			<input type='button' class='matDelete' id="matDelete" value='삭제'/>
		</c:if>
		
	</div>
	
	<div id='line1'></div>
	
	<div id=replZone>
		<label id=repl>댓글</label>
		
		<c:forEach var="replVo" items="${replList }" >
			<div class='reList'>
				<span class='re_mid'>${replVo.replMid }</span>
				<br>
				<span class='items'>${replVo.replDoc }</span>
				<br>
				<span class='replnal'>${replVo.replNal }</span>
				<c:if test="${sessionScope.mid eq replVo.replMid  }">
					<input type='button' class='replDelete' value='삭제' onclick="deleteRepl(${replVo.replSerial})"/>	
				</c:if>
				<input type='button' value="${replVo.replVs }" class='vs' onclick="matchApply('${sessionScope.mid }','${vo.mid }','${replVo.replMid }',${replVo.replSerial},'${replVo.replVs }')"/>
			</div>
		</c:forEach>
		
		<div id='btn_zone'>
			
			<c:if test="${replPage.startPage>1 }">
				<input type='button' value='≪' class='btn_find1' onclick="replPageMove(1)">
				<input type='button' value='이전' class='btn_find1' onclick="replPageMove(${replPage.startPage-1})">
			</c:if>
			
			<c:forEach var ="p" begin="${replPage.startPage }" end="${replPage.endPage }">
				<input type='button' value="${p }" class='btn_find' onclick="replPageMove(${p})">
			</c:forEach>
			
			<c:if test="${page.endPage<page.totPage }">
				<input type='button' value='다음' class='btn_find1' onclick="replPageMove(${replPage.endPage+1})">
				<input type='button' value='≫' class='btn_find1' onclick="replPageMove(${replPage.totPage})">
			</c:if>
			
		</div>
			
	
	</div>
	
	<div class=replWrite>
		<textarea rows="5" cols="131"  id=repl_text placeholder="쉬프트 엔터로 바로 등록하실수 있습니다"></textarea>
		<input type="button" value='등록' id='insertReplBtn'/>
	</div>
</div>
</body>
<script src="./lib/jq.js"></script>
<script src="./js/match/match_detail.js"></script>
</html>