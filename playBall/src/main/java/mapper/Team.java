package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.MatchRecordVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;


@Mapper
public interface Team {
	 public int registerTeam(TeamVo vo);
	 public int bringSerial();
	 public String bringTid(int serial);
	 public TeamMemberVo bringUserInfo(String mid);
	 public int insertTeamMember(TeamMemberVo tVo);
	 public int updateTid(TeamMemberVo tVo);
	 public TeamVo countMember(String tid);// 팀멤버 세기
	 public int cntMemberUpdate(TeamVo vo);//팀 테이블 안에 팀 멤버 업데이트
	 
     public int checkTid(String tid);
     
     public int totListTeam(TeamPage page);//R- 검색하기(페이지연산)
     public List<TeamVo> selectTeam(TeamPage page);//R- 팀리스트가져오기
     public TeamVo viewTeam(int serial);//R- 팀상세보기
     public List<TeamMemberVo> selectTeamMember(String tid);//R-팀멤버 상세보기
     public int checkJoinList(TeamMemberVo jVo);
     public int teamHitUp(int hit);//R-조회수증가
     public int insertJoinMember(TeamMemberVo tVo);//C- 가입신청시 가입멤버입력
     public int totListJoin(String tid);//R-가입리스트에서 totlist구하기
     public List<TeamMemberVo> selectJoinList(Page page);//R- 가입리스트 페이징처리
     public int acceptMember(TeamMemberVo tvo);//가입자 수락
     public int deleteOtherJoin(String mid);//가입자 수락후 대기목록 삭제
     public int rejectJoinMember(TeamMemberVo tvo);//teammember테이블에서 가입자 삭제
     public TeamVo selectLeader(String tid);//주장 선택
     public int updateLeader(TeamVo vo);//주장 업데이트
     public int deleteMember(TeamMemberVo tvo);//멤버 탈퇴
     public int deleteTeam(String tid);//팀 해체
     public int cleanMember(String tid);//팀원 청소
     public int deleteMyTeam(TeamMemberVo tvo);//내 정보에서 팀 삭제
     public int deleteAllMyTeam(String tid);
     public int cntMemberUpdateM(TeamMemberVo tvo);//팀카운트 뺴기
     public int dismantleTeam(String tid);//팀 해체
     public int deleteAllMember(String tid);// 팀멤버 모두 뺴기
     public TeamVo findTeamInfo(String tid);//팀정보 찾기
     public List<TeamVo> findTeamRank();// 랭크
     public List<TeamVo> newTeam();// 새로운 팀 나열
     public int recordTotList(String tid);
     public List<MatchRecordVo> recordList(TeamPage page);
     public String findSaveFileName(String tid);
     public int modifyTeam(TeamVo vo);
}
