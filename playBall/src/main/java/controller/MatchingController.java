package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.catalina.filters.ExpiresFilter.XServletOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import service.MailSenderServiceImpl;
import service.MatchingServiceImpl;
import service.PointServiceImpl;
import vo.MatchVo;
import vo.MatchingPage;
import vo.MatchingReplPage;
import vo.MatchingReplVo;
import vo.MemberVo;

@RestController
public class MatchingController {
@Autowired 
MatchingServiceImpl MatchingService;//매칭서비스클래스
@Autowired
MailSenderServiceImpl mailDao; //메일 보내는 서비스단 클래스
@Autowired
PointServiceImpl point; //포인트 처리 서비스단 클래스
@Autowired
JsonObject json; //gson 라이브러리

int r; // 결과 반환받기

	//매칭 게시판 작성
	@RequestMapping(value="register.matching",method= {RequestMethod.GET,RequestMethod.POST})
	public String register(ModelAndView mv,MatchVo vo,@RequestParam("image")String image) {
		    //썸머노트에서 업로드된 파일명은 받아와서 내용과 다르면 파일 삭제
		    List<String> imageList = MatchingService.theoremFile(vo, image);
		    r = MatchingService.boardRegister(vo, imageList);
		    
		    if(r>0) {
		    	json.addProperty("result", true);
		    	point.earnPoints(vo.getMid());
		    } else {
		    	json.addProperty("result", false);
		    }
		    
		    String result = json.toString();
	return result;
	}

//매칭 게시판에서 뒤로가기
@RequestMapping(value="cancelRegister.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void cancelRegister (@RequestParam("image")String image) {
	 //이미지파일명이 들어올경우 업로드된파일삭제  
	 MatchingService.cancelFile(image);
}

//매칭게시판 목록 보여주기
@RequestMapping(value="matchingView.matching",method = {RequestMethod.GET,RequestMethod.POST})
public ModelAndView matchingView(ModelAndView mv,MatchingPage page) {
	List<MatchVo> matchingList = MatchingService.matchingView(page);
	mv.addObject("list",matchingList);
	mv.addObject("page",page);
	mv.setViewName("match_view");
	return mv;
}

//매칭게시판 게시판상세보기
@RequestMapping(value="matchingDetail.matching",method = {RequestMethod.GET,RequestMethod.POST})
public ModelAndView matchingDetail(ModelAndView mv,@RequestParam("serial")int serial,MatchingReplPage replPage) {
	//게시판상세보기
	MatchVo vo = MatchingService.matchingDetail(serial);
	//게시판 댓글 리스트
	List<MatchingReplVo> list = MatchingService.matchingReplView(replPage);
	//조회수 업
	MatchingService.updateHit(serial);
	
	mv.addObject("replList",list);
	mv.addObject("replPage",replPage);
	mv.addObject("vo",vo);
	mv.setViewName("match_detail");
	return mv;
}

//게시판 상세보기 수정페이지 이동
@RequestMapping(value="moveBoardModify.matching",method= {RequestMethod.GET,RequestMethod.POST})
public ModelAndView moveBoardMoidfy(ModelAndView mv,@RequestParam("serial")int serial) {
	MatchVo vo = MatchingService.matchingModify(serial);
	mv.addObject("vo",vo);
	mv.setViewName("match_update");
	return mv;
}

//게시판 수정
@RequestMapping(value="update.matching",method={RequestMethod.GET,RequestMethod.POST})
public String update(MatchVo vo,@RequestParam("image")String image) {    
	    //이미지 파일명을 받아서 정리해준다
	    List<String> imageList = MatchingService.theoremFile(image);
    	int updateR = 0;
		//업로드서비스
		updateR = MatchingService.updateBoard(vo,imageList);
	
	    if(updateR>0) {
	  	    json.addProperty("result", true);
	    } else {
		    json.addProperty("result", false);  
	    }  
	    
    String result = json.toString();
	return result;
}

//게시판 지우기
@RequestMapping(value="delete.matching",method= {RequestMethod.GET,RequestMethod.POST})
public String delete(@RequestParam("serial")int serial) { 
	//게시판에 등록된 이미지명을 모두 찾는다
	List<String> saveFileName = MatchingService.findFileName(serial);
    //삭제서비스
	r = MatchingService.deleteBoard(serial,saveFileName);
	
    if(r>0) {
		json.addProperty("result", true);
	} else {
		json.addProperty("result", false);
	}
    
	String result = json.toString();
	return result;
}

//댓글달기
@RequestMapping(value="insertRepl.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void insertRepl(MatchingReplVo vo) {
       MatchingService.insertRepl(vo);
       point.earnPoints(vo.getReplMid());
}

//댓글삭제하기
@RequestMapping(value="deleteRepl.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void deleteRepl(@RequestParam("replSerial")int replSerial) {
   MatchingService.deleteRepl(replSerial);
}


//매칭확인
@RequestMapping(value="checkVs.matching",method= {RequestMethod.GET,RequestMethod.POST})
public String checkVs(@RequestParam("serial")int serial) {

	String checkVs = MatchingService.checkVs(serial);
	
	if(checkVs != null) {
		json.addProperty("result", false);
	} else {
		json.addProperty("result", true);
	}
	
	String result = json.toString();
	return result;
}






//매칭신청하기
@RequestMapping(value="matchApply.matching",method={RequestMethod.GET,RequestMethod.POST})
public String matchApply(@RequestParam("replWriterMid")String replWriterMid,@RequestParam("mid")String mid,@RequestParam("serial")int serial,@RequestParam("replSerial")int replSerial ) {
	//댓글작성자아이디와 이메일을 찾는다
	MemberVo replVo = MatchingService.findReplWriterVo(replWriterMid);
    //작성자아이디와이메일을 찾는다
	MemberVo writerVo = MatchingService.findBoardWriterVo(mid);
    //이메일에 넣어줄 게시판 내용찾기
	MatchVo vo = MatchingService.findMatchBoard(serial);
    
	//팀이 없을떄 무소속 넣어주기 팀이 없어도 매칭기능 이용가능
	String myTeam = writerVo.getTid();
    if(myTeam == null) {
    	myTeam = "무소속";
    }
    
    String yourTeam = replVo.getTid();
    if(yourTeam == null) {
    	yourTeam = "무소속";
    }
    
    //게시판의 내용을 바탕으로 신청자에게 메일을 보낸다
    boolean mailResult = mailDao.sendMatchApplyMail(replVo, writerVo, vo);
    
    if(mailResult == true) {
    	 //매칭기록
    	 r = MatchingService.recordMatch(myTeam,yourTeam,vo);
    	 r = MatchingService.updateCntMatch(myTeam,yourTeam);
    	  if(r>0) {
    		  //매칭신청을 매칭중으로 바꾼다
    	     r = MatchingService.updateVs(serial,replSerial);
    	  }
    }
    
    if(r>0) {
    	json.addProperty("result", true);
    } else {
    	json.addProperty("result", false);
    }
    
   String result = json.toString();
return result;    
}


//댓글에서 매칭삭제하기
@RequestMapping(value="replCancelMatch.matching",method={RequestMethod.GET,RequestMethod.POST})
public String replCancelMatch(@RequestParam("replWriterMid")String replWriterMid,@RequestParam("mid")String mid,@RequestParam("serial")int serial,@RequestParam("replSerial")int replSerial) {
	
	//댓글작성자 정보 찾기
	MemberVo replVo = MatchingService.findReplWriterVo(replWriterMid);
    //게시판작성자 정보 찾기
	MemberVo writerVo = MatchingService.findBoardWriterVo(mid);
    
	//소속이 비어잇을떄
	String myTeam = writerVo.getTid();
    if(myTeam == null) {
		 myTeam = "무소속";
	 }
    String yourTeam = replVo.getTid();
    if(yourTeam == null) {
		 yourTeam = "무소속";
	 }
    
    // 매칭기록에서 삭제한다
   r = MatchingService.removeRecordMatch(serial);
   r = MatchingService.updateCntMatchM(myTeam,yourTeam);

   if(r>0) {
	   //매칭중을 신청으로 바꿈
	   r = MatchingService.updateCancelVs(serial, replSerial);
   }
   
   if(r>0) {
	   json.addProperty("result", true);
   } else {
	   json.addProperty("result", false);
   }
   
   String result = json.toString();
   return result;
}


//게시판 목록에서 매칭취소를 할때 댓글에서 취소와 동일
@RequestMapping(value="cancelMatch.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void cancelMatch(@RequestParam("serial")int serial) {

	 int replSerial = MatchingService.findReplSerial(serial);
	 //소속이 비어잇을떄
	 String myTeam = MatchingService.findMyTeam(serial);
	 if(myTeam == null) {
		 myTeam = "무소속";
	 }
	 String yourTeam = MatchingService.findYourTeam(replSerial);
	 if(yourTeam == null) {
		 yourTeam = "무소속";
	 }
	 
	 r = MatchingService.removeRecordMatch(serial);
	 r = MatchingService.updateCntMatchM(myTeam,yourTeam);
	 if(r>0) {
		 r = MatchingService.updateCancelVs(serial, replSerial);
	 }
	 
}




}
