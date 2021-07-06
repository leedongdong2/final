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
import vo.MatchVo;
import vo.MatchingPage;
import vo.MatchingReplPage;
import vo.MatchingReplVo;
import vo.MemberVo;

@RestController
public class MatchingController {
@Autowired 
MatchingServiceImpl MatchingService;
@Autowired
MailSenderServiceImpl mailDao;
int r;

@RequestMapping(value="register.matching",method= {RequestMethod.GET,RequestMethod.POST})
public String register(ModelAndView mv,MatchVo vo,@RequestParam("image")String image) {
	    JsonObject json = new JsonObject();
	    List<String> imageList = MatchingService.theoremFile(vo, image);
	    r = MatchingService.boardRegister(vo, imageList);
	    if(r>0) {
	    	json.addProperty("result", true);
	    } else {
	    	json.addProperty("result", false);
	    }
	    String result = json.toString();
return result;
}

@RequestMapping(value="cancelRegister.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void cancelRegister (@RequestParam("image")String image) {
	  MatchingService.cancelFile(image);
}

@RequestMapping(value="matchingView.matching",method = {RequestMethod.GET,RequestMethod.POST})
public ModelAndView matchingView(ModelAndView mv,MatchingPage page) {
	List<MatchVo> matchingList = MatchingService.matchingView(page);
	mv.addObject("list",matchingList);
	mv.addObject("page",page);
	mv.setViewName("match_view");
	return mv;
}

@RequestMapping(value="matchingDetail.matching",method = {RequestMethod.GET,RequestMethod.POST})
public ModelAndView matchingDetail(ModelAndView mv,@RequestParam("serial")int serial,MatchingReplPage replPage) {
	MatchVo vo = MatchingService.matchingDetail(serial);
	List<MatchingReplVo> list = MatchingService.matchingReplView(replPage);
	mv.addObject("replList",list);
	mv.addObject("replPage",replPage);
	mv.addObject("vo",vo);
	mv.setViewName("match_detail");
	return mv;
}

@RequestMapping(value="moveBoardModify.matching",method= {RequestMethod.GET,RequestMethod.POST})
public ModelAndView moveBoardMoidfy(ModelAndView mv,@RequestParam("serial")int serial) {
	MatchVo vo = MatchingService.matchingModify(serial);
	mv.addObject("vo",vo);
	mv.setViewName("match_update");
	return mv;
}

@RequestMapping(value="update.matching",method={RequestMethod.GET,RequestMethod.POST})
public String update(MatchVo vo,@RequestParam("image")String image) {
	      JsonObject json = new JsonObject();    
	      List<String> imageList = MatchingService.theoremFile(image);
    	  int updateR = 0;
		try {
			updateR = MatchingService.updateBoard(vo,imageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  if(updateR>0) {
    		  json.addProperty("result", true);
    	  } else {
    		  json.addProperty("result", false);  
    	  }  
    String result = json.toString();
	return result;
}


@RequestMapping(value="delete.matching",method= {RequestMethod.GET,RequestMethod.POST})
public String delete(@RequestParam("serial")int serial) {
	JsonObject json = new JsonObject(); 
	List<String> saveFileName = MatchingService.findFileName(serial);
    r = MatchingService.deleteBoard(serial,saveFileName);
	if(r>0) {
		json.addProperty("result", true);
	} else {
		json.addProperty("result", false);
	}
	String result = json.toString();
	return result;
}

@RequestMapping(value="insertRepl.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void insertRepl(MatchingReplVo vo) {
       MatchingService.insertRepl(vo);
}

@RequestMapping(value="deleteRepl.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void deleteRepl(@RequestParam("replSerial")int replSerial) {
   MatchingService.deleteRepl(replSerial);
}





@RequestMapping(value="checkVs.matching",method= {RequestMethod.GET,RequestMethod.POST})
public String checkVs(@RequestParam("serial")int serial) {
	JsonObject json = new JsonObject();
	String checkVs = MatchingService.checkVs(serial);
	if(checkVs != null) {
		json.addProperty("result", false);
	} else {
		json.addProperty("result", true);
	}
	String result = json.toString();
	return result;
}







@RequestMapping(value="matchApply.matching",method={RequestMethod.GET,RequestMethod.POST})
public String matchApply(@RequestParam("replWriterMid")String replWriterMid,@RequestParam("mid")String mid,@RequestParam("serial")int serial,@RequestParam("replSerial")int replSerial ) {
	JsonObject json = new JsonObject();
	MemberVo replVo = MatchingService.findReplWriterVo(replWriterMid);
    MemberVo writerVo = MatchingService.findBoardWriterVo(mid);
    MatchVo vo = MatchingService.findMatchBoard(serial);
    String myTeam = writerVo.getTid();
    String yourTeam = replVo.getTid();
    
    boolean mailResult = mailDao.sendMatchApplyMail(replVo, writerVo, vo);
    if(mailResult == true) {
    	 r = MatchingService.recordMatch(myTeam,yourTeam,vo);
    	 r = MatchingService.updateCntMatch(myTeam,yourTeam);
    	if(r>0) {
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



@RequestMapping(value="replCancelMatch.matching",method={RequestMethod.GET,RequestMethod.POST})
public String replCancelMatch(@RequestParam("replWriterMid")String replWriterMid,@RequestParam("mid")String mid,@RequestParam("serial")int serial,@RequestParam("replSerial")int replSerial) {
	JsonObject json = new JsonObject();
	MemberVo replVo = MatchingService.findReplWriterVo(replWriterMid);
    MemberVo writerVo = MatchingService.findBoardWriterVo(mid);
    String myTeam = writerVo.getTid();
    String yourTeam = replVo.getTid();
	
   r = MatchingService.removeRecordMatch(serial);
   r = MatchingService.updateCntMatchM(myTeam,yourTeam);

   if(r>0) {
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


@RequestMapping(value="cancelMatch.matching",method= {RequestMethod.GET,RequestMethod.POST})
public void cancelMatch(@RequestParam("serial")int serial) {

	 int replSerial = MatchingService.findReplSerial(serial);
	 String myTeam = MatchingService.findMyTeam(serial);
	 String yourTeam = MatchingService.findYourTeam(replSerial);
	 r = MatchingService.removeRecordMatch(serial);
	 r = MatchingService.updateCntMatchM(myTeam,yourTeam);
	 if(r>0) {
		 r = MatchingService.updateCancelVs(serial, replSerial);
	 }
	 
}




}
