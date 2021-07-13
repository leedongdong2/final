package service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;

public interface TeamService {
	public String teamfileUpload(MultipartFile mf); //파일업로드
	public boolean registerTeam(TeamVo vo);	//팀등록
	public boolean bringUserInfo(String mid);//유저데이터 가져오기
	public int insertTeamMember(TeamMemberVo tVo); // 팀멤버테이블 등록
	public boolean checkTid(String tid); //팀아이디중복확인
	public List<TeamVo> selectTeam(TeamPage page); //페이징처리
	public boolean checkJoin(String mid, int serial);//가입전 중복확인
	public boolean bringJoinUserInfo(int serial, String mid); //가입신청
	public List<TeamMemberVo> selectJoinMember(Page page, String tid); //가입리스트
	public boolean acceptJoin(String mid, int serial);//가입자수락
	public TeamVo countMember(String tid); //멤버수 세기
	public int cntMemberUpdate(TeamVo vo); //멤버수 업데이트
	public String bringTid(int serial);//시리얼로 팀이름 찾기
	public int rejectJoinMember(String mid, String tid);//teammember테이블에서 삭제
	public TeamVo selectLeader(String tid);//주장 선택
	public int updateLeader(TeamVo vo);//주장 업데이트
}
