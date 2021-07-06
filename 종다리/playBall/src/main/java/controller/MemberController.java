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
 MemberServiceImpl dao;
 @Autowired
 MailSenderServiceImpl mailDao;
 int r;
 
 @RequestMapping(value="insert.member",method = {RequestMethod.GET,RequestMethod.POST})
 public String insertM(MemberVo vo) {
	 JsonObject json = new JsonObject();
	 r = dao.insert(vo);
	 if(r>0) {
			json.addProperty("result","성공");
		}else {
			json.addProperty("result", "실패");
		}
	 String result = json.toString();
	 return result;
 }
 
 @RequestMapping(value="duplicatedId.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String  idCheck(@RequestParam("mid")String mid) {
	 JsonObject json = new JsonObject();
	     r = dao.duplicatedId(mid);
		 if(r>0) {
				json.addProperty("result", false);
			}else{
				json.addProperty("result", true);
			}
		String result = json.toString();
	 return result;
 }
 
 @RequestMapping(value="logIn.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String login(MemberVo vo,HttpServletRequest req) {
	 String result = dao.logIn(vo,req);
	 return result;
 }
 
 @RequestMapping(value="myPage.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView moveMyPage(MemberVo vo) {
	 ModelAndView mv = new ModelAndView();
	 vo = dao.myPageInfo(vo);
	 mv.addObject("vo",vo);
	 mv.setViewName("myPage");
	 return mv;
 }
 
 @RequestMapping(value="modifyPage.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView moveModifyPage(MemberVo vo) {
	 ModelAndView mv = new ModelAndView();
	 vo = dao.modifyPageInfo(vo);
	 mv.addObject("vo",vo);
	 mv.setViewName("signModify");
	 return mv;
 }
 
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
 
 @RequestMapping(value="logOut.member",method= {RequestMethod.GET,RequestMethod.POST})
 public void logOut(HttpServletRequest req) {
	 dao.logOut(req);
 }
 
 @RequestMapping(value="sendCertifyMailId.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView sendCertifyMailId(@RequestParam("email")String email) {
       ModelAndView mv = new ModelAndView();
	   String authKey = mailDao.sendAuthMail(email);
	   mv.addObject("authKey",authKey);
	   mv.setViewName("confirmCertificationId");
	   return mv;
 }
 
 @RequestMapping(value="sendCertifyMailPwd.member",method= {RequestMethod.GET,RequestMethod.POST})
 public ModelAndView sendCertifyMailPwd(@RequestParam("email")String email) {
	ModelAndView mv = new ModelAndView();
	String authKey = mailDao.sendAuthMail(email);
	mv.addObject("authKey",authKey);
	mv.setViewName("confirmCertificationPwd");
	return mv;
 }
 
 @RequestMapping(value="confirmUser.member",method= {RequestMethod.GET,RequestMethod.POST})
 public String confirmUser(MemberVo vo) {
	 JsonObject json = new JsonObject();
	 r = dao.confirmUser(vo); 
	 if(r>0) {
		 json.addProperty("result", true);
	 } else {
		 json.addProperty("result", false);
	 }
	 String result = json.toString();
	 return result;
 }
 
 @RequestMapping(value="findId.member",method= {RequestMethod.GET,RequestMethod.POST})
   public String findId(MemberVo vo) {
	 
	 JsonObject json = new JsonObject();
	 String mid = dao.findId(vo);
	 json.addProperty("mid", mid);
	 String result = json.toString();
	 return result;
 }
 
 @RequestMapping(value="issuePwd.member",method= {RequestMethod.GET,RequestMethod.POST})
  public String issuePwd(MemberVo vo) {
	 JsonObject json = new JsonObject();
	 String issuePwd = dao.issuePwd(vo);
	 json.addProperty("issuePwd", issuePwd);
	 String result = json.toString();
	 return result;
 }


}
