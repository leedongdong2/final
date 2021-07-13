package service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import vo.MatchVo;
import vo.StadiumVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;

public interface HomeService {
	public List<TeamVo> findTeamRank();
	public List<StadiumVo> recentStadium();//스타디움 최신
    public List<MatchVo> matchingView();//매칭게시판 최신
}
