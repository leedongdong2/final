package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

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
	TeamServiceImpl TeamService;
	
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
	
	@RequestMapping(value="register.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView goToRegister(ModelAndView mv) {//등록페이지 이동
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value="insert.upload",method = {RequestMethod.GET,RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile mf) {	//파일업로드
		JsonObject json = new JsonObject();
		String fileName = TeamService.teamfileUpload(mf);
		json.addProperty("fileName", fileName);
		String result = json.toString();
		return result;
	}
	
	@RequestMapping(value="insertTeam.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String insertTeam(TeamVo vo,HttpServletRequest req) { //팀등록
		HttpSession session = req.getSession();
		String tid = vo.getTid();
		
		JsonObject json = new JsonObject();
		boolean resultInsert = TeamService.registerTeam(vo);
	    if(resultInsert == true) {
	    	session.setAttribute("tid",tid);
	    	System.out.println("세션 tid : "+ tid);
	    	json.addProperty("result", true);
	    } else {
	    	json.addProperty("result", false);
	    }
	    String result = json.toString();
	    System.out.println("팀등록 : "+result);
		return result;
	}
	
	@RequestMapping(value = "insertTeamMember.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String bringUserInfo(@RequestParam("mid")String mid){
		JsonObject json = new JsonObject();
		boolean resultTeamMember = TeamService.bringUserInfo(mid);
		if(resultTeamMember == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		String result = json.toString();
		System.out.println("팀 멤버등록 : " + result);
		return result;
	}
	
	
	@RequestMapping(value ="deleteTeamMember.team",method= {RequestMethod.GET,RequestMethod.POST})
	public String deleteTeamMember(TeamMemberVo vo,HttpServletRequest req) {
	    System.out.println(vo.getMid());
		HttpSession session = req.getSession();
		JsonObject json = new JsonObject();
		int r = TeamService.deleteMember(vo);
		if(r>0) {
			json.addProperty("result", true);
			session.removeAttribute("tid");
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();
		return result;
	}
	
	@RequestMapping(value="dismantleTeam.team",method= {RequestMethod.GET,RequestMethod.POST})
	public String dismantleTeam(@RequestParam("tid")String tid) {
		int r = TeamService.dismantleTeam(tid);
		JsonObject json = new JsonObject();
		System.out.println(r);
		if(r>0) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		String result = json.toString();
		return result;
	}
	

	
	@RequestMapping(value="checkTid.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String checkTid(@RequestParam("tid")String tid) {//팀아이디중복확인
		JsonObject json = new JsonObject();
		boolean checkTid = TeamService.checkTid(tid);
		 if(checkTid == true) {
		    	json.addProperty("result", true);
		    } else {
		    	json.addProperty("result", false);
		    }
		 String result = json.toString();
		return result;
	}
	
	
	
	//READ
	
	@RequestMapping(value="list.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView search(TeamPage page) {//페이징
		ModelAndView mv = new ModelAndView();
		List<TeamVo> list = TeamService.selectTeam(page);
		mv.addObject("list",list);
		mv.addObject("page",page);
		mv.setViewName("search");
		return mv;
	}
	
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
	
	@RequestMapping(value="join.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String join(ModelAndView mv, @RequestParam("mid")String mid, @RequestParam("serial")int serial) {//가입
		JsonObject json = new JsonObject();
		boolean resultJoinMember = TeamService.bringJoinUserInfo(serial, mid);
		
		if(resultJoinMember == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		
		String result = json.toString();

		return result;
	}
	
	@RequestMapping(value="joinlist.team",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView joinlist(ModelAndView mv, @SessionAttribute("tid")String tid, Page page) {
		System.out.println("tid : "+ tid);
		List<TeamMemberVo> list = TeamService.selectJoinMember(page, tid);
		mv.addObject("list",list);
		mv.addObject("page",page);
		mv.setViewName("JoinList");
		return mv;
	}
	
	@RequestMapping(value="checkJoin.team",method = {RequestMethod.GET,RequestMethod.POST})//가입중복확인
	public String checkJoin(ModelAndView mv, @RequestParam("mid")String mid, @RequestParam("serial")int serial) {
		JsonObject json = new JsonObject();
		boolean resultCheck = TeamService.checkJoin(mid, serial);
		if(resultCheck == true) {
			json.addProperty("result", true);
		} else {
			json.addProperty("result", false);
		}
		String result = json.toString();
		return result;
	}
	
	@RequestMapping(value="checkCount.team",method = {RequestMethod.GET,RequestMethod.POST})//가입중복확인
	public String checkCount(@RequestParam("tid")String tid) {
		JsonObject json = new JsonObject();
		int cntMember = 0;
		TeamVo vo = TeamService.countMember(tid);
		try {
			int update = TeamService.cntMemberUpdate(vo);
			if(update>0) {
				cntMember = vo.getCntMember();
				if(cntMember>=5) {
					System.out.println("멤버수 : "+cntMember);
					json.addProperty("result", false);
				}else {
					System.out.println("멤버수 : "+cntMember);
					json.addProperty("result", true);
				}
			}else {
				json.addProperty("result", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		String result = json.toString();
		return result;
	}
	
	@RequestMapping(value="acceptJoin.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String acceptJoin(@RequestParam("mid")String mid, @RequestParam("serial")int serial){
		System.out.println("데이터받아옴");
		JsonObject json = new JsonObject();
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
	
	@RequestMapping(value = "rejectJoin.team",method = {RequestMethod.GET,RequestMethod.POST})
	public String rejectJoin(@RequestParam("mid")String mid, @RequestParam("serial")int serial) {
		System.out.println("가입리스트 삭제데이터 받음");
		JsonObject json = new JsonObject();
		String tid = TeamService.bringTid(serial);
		int r= TeamService.rejectJoinMember(mid, tid);
		if(r>0) {
			json.addProperty("result", true);
		}else {
			json.addProperty("result", false);
		}
		String result = json.toString();
		System.out.println("가입신청자 삭제 결과 : "+result);
		return result;
	}
	
	
}
