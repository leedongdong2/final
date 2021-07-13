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

import service.HomeServiceImpl;
import service.TeamServiceImpl;
import vo.MatchVo;
import vo.StadiumVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;

@RestController
@SessionAttributes("tid")
public class HomeController {
	@Autowired
	HomeServiceImpl HomeService;
	
	@RequestMapping(value = "goHome.home",method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView rankTeam(ModelAndView mv) {
		System.out.println("팀랭킹 홈 들어옴");
		List<TeamVo> rank = HomeService.findTeamRank();
		System.out.println(rank.size());
		List<StadiumVo> stadiumList = HomeService.recentStadium();
		List<MatchVo> matchList = HomeService.matchingView();
		mv.addObject("rank",rank);
		mv.addObject("stadiumList",stadiumList);
		mv.addObject("matchList",matchList);
		mv.setViewName("home2");
		return mv;
	}
	
	
}
