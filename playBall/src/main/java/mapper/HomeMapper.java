package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.MatchVo;
import vo.StadiumVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;


@Mapper
public interface HomeMapper {
     
     public List<TeamVo> findTeamRank();// 팀랭크
     public List<StadiumVo> recentStadium();//스타디움 최신
     public List<MatchVo> matchingView();//매칭게시판 최신
}
