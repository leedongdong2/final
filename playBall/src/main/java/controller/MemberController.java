package controller;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import service.MailSenderServiceImpl;
import service.MemberServiceImpl;
import vo.MemberVo;

@RestController
public class MemberController {
 @Autowired
 MemberServiceImpl dao; //멤버 서비스단 클래스
 @Autowired
 MailSenderServiceImpl mailDao; //메일 서비스단 클래스
 @Autowired
 JsonObject json; //gson 라이브러리
 
 int r;//결과 반환
 
 
 
 
 
 
 //회원가입
 @RequestMapping(value="insert.member",method = {RequestMethod.GET,RequestMethod.POST})
 public String insertM(MemberVo vo) {
	 
	 r = dao.insert(vo);
	 
	 if(r>0) {
			json.addProperty("result","성공");
		}else {
			json.addProperty("result", "실패");
		}
	 
	 String result = json.toString();
	 
	 return result;
 }
 
 
 
 
 //아이디 중복체크
 @RequestMapping(value="duplicatedId.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String  idCheck(@RequestParam("mid")String mid) {
	    
	     r = dao.duplicatedId(mid);
		 
	     if(r>0) {
				json.addProperty("result", false);
			}else{
				json.addProperty("result", true);
			}
		
	     String result = json.toString();
	 
	return result;
 }
 
 //로그인
 @RequestMapping(value="logIn.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String login(MemberVo vo,HttpServletRequest req) {
	 
	 String result = dao.logIn(vo,req);
	 
	 return result;
 }
 
 //내 정보보기
 @RequestMapping(value="myPage.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView moveMyPage(MemberVo vo,ModelAndView mv) {
	 
	 vo = dao.myPageInfo(vo);
	 mv.addObject("vo",vo);
	 mv.setViewName("myPage");
	 
	 return mv;
 }

 //내 정보 수정창 이동
 @RequestMapping(value="modifyPage.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView moveModifyPage(MemberVo vo,ModelAndView mv) {
	 
	 vo = dao.modifyPageInfo(vo);
	 mv.addObject("vo",vo);
	 mv.setViewName("signModify");
	 
	 return mv;
 }
 
 //내 정보 수정하기
 @RequestMapping(value="update.member",method= {RequestMethod.GET,RequestMethod.POST})
  public String update(MemberVo vo,@RequestParam("oriPassword")String oriPassword) {
	 
	 String confirmResult = null;
	 String result = null;
     
	 confirmResult = dao.confirmPassword(vo, oriPassword);
	 
	 if(confirmResult == null) {
		 result = dao.update(vo);
	 } else {
		 result = confirmResult; 
	 }
	 return result;
 }
 
 
 //회원 탈퇴
 @RequestMapping(value="delete.member",method= {RequestMethod.GET,RequestMethod.POST})
   public String delete(MemberVo vo,@RequestParam("oriPassword")String oriPassword,HttpServletRequest req) {
	 
	 String confirmResult = null;
	 String result = null;
	 
	 confirmResult = dao.confirmPassword(vo, oriPassword);
	 
	 if(confirmResult == null) {
		 result = dao.delete(vo,req);
	 } else {
		 result = confirmResult; 
	 }
	 
	 return result;
 }
 
 //로그아웃
 @RequestMapping(value="logOut.member",method= {RequestMethod.GET,RequestMethod.POST})
 public void logOut(HttpServletRequest req) {
	 dao.logOut(req);
 }
 
	 //아이디 찾기 이메일 인증
	 @RequestMapping(value="sendCertifyMailId.member",method= {RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView sendCertifyMailId(@RequestParam("email")String email,ModelAndView mv) {
	       
		   String authKey = mailDao.sendAuthMail(email);
		   mv.addObject("authKey",authKey);
		   mv.setViewName("confirmCertificationId");
		   
		   return mv;
	 }
	 
	 //비밀번호 찾기 이메일 인증
	 @RequestMapping(value="sendCertifyMailPwd.member",method= {RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView sendCertifyMailPwd(@RequestParam("email")String email,ModelAndView mv) {
		
		String authKey = mailDao.sendAuthMail(email);
		mv.addObject("authKey",authKey);
		mv.setViewName("confirmCertificationPwd");
		
		return mv;
	 }
 
 //인증 확인하기
 @RequestMapping(value="confirmUser.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String confirmUser(MemberVo vo) {
	 
	 r = dao.confirmUser(vo); 
	 
	 if(r>0) {
		 json.addProperty("result", true);
	 } else {
		 json.addProperty("result", false);
	 }
	 
	 String result = json.toString();
	 
	 return result;
 }
 
 //회원 아이디 찾아주기
 @RequestMapping(value="findId.member",method= {RequestMethod.GET,RequestMethod.POST})
   public String findId(MemberVo vo) {

	 String mid = dao.findId(vo);
	 json.addProperty("mid", mid);
	 String result = json.toString();
	 
	 return result;
 
 }
 
 //새로운 비밀번호 발급
 @RequestMapping(value="issuePwd.member",method= {RequestMethod.GET,RequestMethod.POST})
  public String issuePwd(MemberVo vo) {
	 
	 String issuePwd = dao.issuePwd(vo);
	 json.addProperty("issuePwd", issuePwd);
	 String result = json.toString();
	 
	 return result;
 }


}
