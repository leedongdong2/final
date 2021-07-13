package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import service.PointServiceImpl;
import service.TeamServiceImpl;
import vo.MatchRecordVo;
import vo.MatchingRecordVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;

@RestController
@SessionAttributes("tid")
public class Team {
	@Autowired
	TeamServiceImpl TeamService; //팀 서비스단 클래스
	@Autowired
	PointServiceImpl point; // 포인트처리 서비스단 클래스
	@Autowired
	JsonObject json; //gson 라이브러리
	int r;//결과 반환 
	
	//팀 메인창 이동
	@RequestMapping(value="main.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView goToMain(ModelAndView mv,@RequestParam("tid")String tid) {//등록페이지 이동
		
		TeamVo tVo = TeamService.findTeamInfo(tid);
		
		List<TeamMemberVo> mVoList = TeamService.selectTeamMember(tid);
		List<TeamVo> teamRank = TeamService.findTeamRank();
		List<TeamVo> newTeam = TeamService.newTeam();
		
		mv.addObject("newTeam",newTeam);
		mv.addObject("teamRank",teamRank);
		mv.addObject("mVoList",mVoList);
		mv.addObject("tVo",tVo);
		mv.setViewName("main");
		
		return mv;
	}
	
	//팀등록 페이지 이동
	@RequestMapping(value="register.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView goToRegister(ModelAndView mv) {//등록페이지 이동
		
		mv.setViewName("register");
		
		return mv;
	}
	
    //파일 업로드
	@RequestMapping(value="insert.upload",method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile mf) {	//파일업로드
		
		String fileName = TeamService.teamfileUpload(mf);
		json.addProperty("fileName", fileName);
		
		String result = json.toString();
		
		return result;
	}
	
	//팀 등록하기
	@RequestMapping(value="insertTeam.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertTeam(TeamVo vo,HttpServletRequest req) { //팀등록
		
		HttpSession session = req.getSession();
		String tid = vo.getTid();
		
		boolean resultInsert = TeamService.registerTeam(vo);
	    
		if(resultInsert == true) {
	    	session.setAttribute("tid",tid);
	    	json.addProperty("result", true);
	    	point.earnPoints(vo.getMid());
	    } else {
	    	json.addProperty("result", false);
	    }
	    
		String result = json.toString();
		
		return result;
	}
	
	//팀멤버추가하기
	@RequestMapping(value = "insertTeamMember.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String bringUserInfo(@RequestParam("mid")String mid){
		
		boolean resultTeamMember = TeamService.bringUserInfo(mid);
		
		if(resultTeamMember == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//팀 탈퇴하기
	@RequestMapping(value ="deleteTeamMember.team",method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteTeamMember(TeamMemberVo vo,HttpSession session,@ModelAttribute("tid")String tid,SessionStatus sessionStatus,ModelAndView mv) {
		
		r = TeamService.deleteMember(vo,session,tid,sessionStatus,mv);	
		
		if(r>0) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//팀해체하기
	@RequestMapping(value="dismantleTeam.team",method= {RequestMethod.GET,RequestMethod.POST})
	public String dismantleTeam(@RequestParam("tid")String tid,@RequestParam("lmid")String mid,@ModelAttribute("tid")String teamId,SessionStatus sessionStatus) {
		
		r = TeamService.dismantleTeam(tid,mid);

		if(r>0) {
			json.addProperty("result", true);
            sessionStatus.setComplete();			
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	

	//팀아이디 중복체크하기
	@RequestMapping(value="checkTid.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String checkTid(@RequestParam("tid")String tid) {//팀아이디중복확인

		boolean checkTid = TeamService.checkTid(tid);
		
		if(checkTid == true) {
	    	json.addProperty("result", true);
	    } else {
	    	json.addProperty("result", false);
	    }
		
		String result = json.toString();
		
		return result;
	}
	
	//팀 수정창으로 이동하기
	@RequestMapping(value="moveModifyTeam.team",method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView moveModifyTeam(ModelAndView mv,@RequestParam("tid")String tid) {
		
		TeamVo tVo = TeamService.findTeamInfo(tid);
		
		mv.addObject("tVo",tVo);
		mv.setViewName("modify");
		
		return mv;
	}
	
	//팀정보 수정하기
	@RequestMapping(value="modifyTeam.team",method= {RequestMethod.GET,RequestMethod.POST})
	public String modifyTeam(TeamVo vo) {
		
		r = TeamService.modifyTeam(vo);
		
		if(r>0) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	
	//READ
	
	//팀 목록보기
	@RequestMapping(value="list.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView search(TeamPage page,ModelAndView mv) {//페이징
		
		List<TeamVo> list = TeamService.selectTeam(page);
		
		mv.addObject("list",list);
		mv.addObject("page",page);
		mv.setViewName("search");
		
		return mv;
	}
	
	//팀 상세보기
	@RequestMapping(value="view.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView view(ModelAndView mv,@RequestParam("serial")int serial, TeamPage page) {
		
		TeamVo tvo = TeamService.viewTeam(serial);
		String tid = tvo.getTid();
		
		List<TeamMemberVo> list = TeamService.selectTeamMember(tid);
		List<MatchRecordVo> recordList = TeamService.viewRecord(tid,page); 
		
		mv.addObject("recordList",recordList);
		mv.addObject("page",page);
		mv.addObject("list",list);
		mv.addObject("tvo",tvo);
		mv.setViewName("view");
		
		return mv;
	}
	
	//가입신청하기
	@RequestMapping(value="join.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String join(ModelAndView mv, @RequestParam("mid")String mid, @RequestParam("serial")int serial) {//가입
		
		boolean resultJoinMember = TeamService.bringJoinUserInfo(serial, mid);
		
		if(resultJoinMember == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();

		return result;
	}
	
	//가입신청목록보기
	@RequestMapping(value="joinlist.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView joinlist(ModelAndView mv, @SessionAttribute("tid")String tid, Page page) {
		
		List<TeamMemberVo> list = TeamService.selectJoinMember(page, tid);
		
		mv.addObject("list",list);
		mv.addObject("page",page);
		mv.setViewName("JoinList");
		
		return mv;
	}
	
	//가입중복확인
	@RequestMapping(value="checkJoin.team",method = {RequestMethod.GET,RequestMethod.POST})//가입중복확인
	public String checkJoin(ModelAndView mv, @RequestParam("mid")String mid, @RequestParam("serial")int serial) {
	
		boolean resultCheck = TeamService.checkJoin(mid, serial);
		
		if(resultCheck == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//가입중복확인
	@RequestMapping(value="checkCount.team",method = {RequestMethod.GET,RequestMethod.POST})//가입중복확인
	public String checkCount(@RequestParam("tid")String tid) {
	
		int cntMember = 0;
		
		TeamVo vo = TeamService.countMember(tid);
		
		try {
			int update = TeamService.cntMemberUpdate(vo);
			
			if(update>0) {
				cntMember = vo.getCntMember();
				if(cntMember>=5) {
					json.addProperty("result", false);
				} else {
					json.addProperty("result", true);
				}
			}else {
				json.addProperty("result", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//팀가입시키기
	@RequestMapping(value="acceptJoin.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String acceptJoin(@RequestParam("mid")String mid, @RequestParam("serial")int serial){
	
		boolean updateCheck = TeamService.acceptJoin(mid, serial);
		
		if(updateCheck == true) {
			String tid = TeamService.bringTid(serial);
			TeamVo vo = TeamService.selectLeader(tid);
			int r = TeamService.updateLeader(vo);
			
			if(r>0) {
				json.addProperty("result", true);
			} else {
				json.addProperty("result", false);
			}
			
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//팀멤버로 받지 않기
	@RequestMapping(value = "rejectJoin.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String rejectJoin(@RequestParam("mid")String mid, @RequestParam("serial")int serial) {
		
		String tid = TeamService.bringTid(serial);
		r = TeamService.rejectJoinMember(mid, tid);
		
		if(r>0) {
			json.addProperty("result", true);
		}else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		
		return result;
	}
	
	
}
