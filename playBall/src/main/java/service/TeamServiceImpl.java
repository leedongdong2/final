package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mapper.Team;
import vo.MatchRecordVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;


@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	Team teamMapper;
	@Autowired
	PointServiceImpl point;
    int r;
	
	@Override
	public String teamfileUpload(MultipartFile mf) {
		String uploadPath = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		String date = sdf.format(new Date());
		String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\";
        String uploadName = date + mf.getOriginalFilename();
        uploadPath = fileRoot + uploadName;
		
        try {
        	if(mf.getSize()>0) {
        		mf.transferTo(new File(uploadPath));
        	}
        }catch(Exception ex) {
        	ex.printStackTrace();
        }
    
		return uploadName;
	}

	@Override
	@Transactional
	public boolean registerTeam(TeamVo vo) {//팀등록
		boolean bool = false;
		r = teamMapper.registerTeam(vo);
		r = point.payPoints(vo.getMid());
		if(r>0) {
			bool = true;
		} else {
			bool = false;
		}
		return bool;
	}
	
	@Override
	@Transactional
	public boolean bringUserInfo(String mid){
		TeamMemberVo tVo = new TeamMemberVo();
		int insertTeam = 0;
		boolean bool = false;
		try {
			 int serial = teamMapper.bringSerial();
			 String tid = bringTid(serial);
			 tVo = teamMapper.bringUserInfo(mid);
			 tVo.setTid(tid);
			 insertTeam = teamMapper.insertTeamMember(tVo);
			 if(insertTeam> 0) {
				 insertTeam = 0;
				 insertTeam = teamMapper.updateTid(tVo);
				 int delete = teamMapper.deleteOtherJoin(mid);//
				 if(insertTeam > 0) {
					 TeamVo vo = countMember(tid);
					 int cntMemberResult = cntMemberUpdate(vo);
					 if(cntMemberResult >0) {
						 bool = true;
					 }else {
						 bool = false;
					 }
				 } else {
					 bool = false;
				 }
			 }else {
				 bool = false;
			 }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}
	
	@Override
	@Transactional
	public TeamVo countMember(String tid) {//멤버수 세기
		TeamVo vo = new TeamVo();
		vo = teamMapper.countMember(tid);
		return vo;
	}
	
	@Override
	@Transactional
	public int cntMemberUpdate(TeamVo vo) {//멤버수 업데이트
		int result = teamMapper.cntMemberUpdate(vo);
		return result;
	}
	
	
	@Override
	@Transactional
	public int insertTeamMember(TeamMemberVo tVo) {

		try {
			r = teamMapper.insertTeamMember(tVo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return r;
	}
	
	@Transactional
	public int deleteMember(TeamMemberVo vo,HttpSession session,@ModelAttribute("tid")String tid,SessionStatus sessionStatus,ModelAndView mv) {
		r = teamMapper.deleteMember(vo);
		r = teamMapper.deleteMyTeam(vo);
		r = teamMapper.cntMemberUpdateM(vo);
		
		if(r>0) {
			sessionStatus.setComplete();
		}
		TeamVo selectLeader = teamMapper.selectLeader(vo.getTid());
		r = teamMapper.updateLeader(selectLeader);
		
		return r;
	}
	
	@Transactional
	public int dismantleTeam(String tid,String mid) {
		
		r = teamMapper.dismantleTeam(tid);
		r = teamMapper.deleteAllMember(tid);
		r = teamMapper.deleteAllMyTeam(tid);
		r = point.payPoints(mid);
		
		return r;
	}
	
	@Override
	@Transactional
	public boolean checkTid(String tid) {
		boolean check = false;
		
	    r = teamMapper.checkTid(tid);
		
		
		if(r == 0) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	
	@Transactional
	public int modifyTeam(TeamVo vo) {
		String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\";
		String saveFileName = teamMapper.findSaveFileName(vo.getTid());

		try {
		    r = teamMapper.modifyTeam(vo);
			if(r>0) {
		    File file = new File(fileRoot + saveFileName);
			file.delete();
			} else {
				throw new Exception();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			File file = new File(vo.getPic() + saveFileName);
			file.delete();
		}
	
		return r;
	}
	
	
	//---------------------------조회------------------------------
	@Override
	@Transactional
	public List<TeamVo> selectTeam(TeamPage page){//페이징처리
		List<TeamVo> list = new ArrayList<TeamVo>();
		try {
			//검색어관련 총페이지 찾기
			int totList = teamMapper.totListTeam(page);
			page.setTotList(totList);
			page.compute();
			int totPage = page.getTotPage();
			list = teamMapper.selectTeam(page);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	@Transactional
	public TeamVo viewTeam(int serial) {
		TeamVo tvo = new TeamVo();
		int hit = teamMapper.teamHitUp(serial); //조회수증가
		if(hit>0) {
		tvo = teamMapper.viewTeam(serial);//팀상세창
		}
		return tvo;
	}
	
	@Transactional
	public List<TeamMemberVo> selectTeamMember(String tid){
		List<TeamMemberVo> list = new ArrayList<TeamMemberVo>();
		list = teamMapper.selectTeamMember(tid);//팀멤버보여주기
		return list;
	}
	
	@Transactional
	public boolean checkJoin(String mid, int serial){//가입전 중복확인
		boolean bool = false;
		TeamMemberVo jVo = new TeamMemberVo();
		String tid = bringTid(serial);
		jVo.setTid(tid);
		jVo.setMid(mid);
		r = teamMapper.checkJoinList(jVo);
		if(r> 0) {
			bool = false;
		}else {
			bool =true;
		}
		return bool;
	}
	
	@Override
	@Transactional
	public boolean bringJoinUserInfo(int serial, String mid){//가입신청
		TeamMemberVo tVo = new TeamMemberVo();
		int insertJoin = 0;
		boolean bool = false;
		try {
			 String tid = bringTid(serial);
			 tVo = teamMapper.bringUserInfo(mid);
			 tVo.setTid(tid);
			 insertJoin = teamMapper.insertJoinMember(tVo);
			 if(insertJoin> 0) {
				 bool = true;
			 }else {
				 bool = false;
			 }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}
	
	@Override
	@Transactional
	public List<TeamMemberVo> selectJoinMember(Page page, String tid){//페이징처리
		List<TeamMemberVo> list = new ArrayList<TeamMemberVo>();
		try {
			//검색어관련 총페이지 찾기
			int totList = teamMapper.totListJoin(tid);
			page.setTotList(totList);
			page.compute();
			int totPage = page.getTotPage();
			page.setFindStr(tid);
			list = teamMapper.selectJoinList(page);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	@Override
	@Transactional
	public boolean acceptJoin(String mid, int serial){
		boolean bool = false;
		TeamMemberVo tvo = new TeamMemberVo();
		String tid = bringTid(serial);
		tvo.setTid(tid);
		tvo.setMid(mid);
		int update = teamMapper.acceptMember(tvo);
		update = teamMapper.updateTid(tvo);
		if(update>0) {
			try {
				int delete = teamMapper.deleteOtherJoin(mid);
				try {
					TeamVo vo = countMember(tid);
					int cntMemberResult = cntMemberUpdate(vo);
					 if(cntMemberResult >0) {
						 bool = true;
					 }else {
						 bool = false;
					 }
				}catch(Exception e) {
					e.printStackTrace();
					bool = false;
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				bool = false;
			}
		}else {
			bool = false;
		}
		return bool;
	}//가입자수락
	
	@Override
	@Transactional
	public String bringTid(int serial) {
		String tid = teamMapper.bringTid(serial);
		return tid;
	}//serial로 tid가져오기
	
	@Override
	@Transactional
	public int rejectJoinMember(String mid, String tid) {
		int delete = 0;
		TeamMemberVo tvo = new TeamMemberVo();
		tvo.setTid(tid);
		tvo.setMid(mid);
		delete = teamMapper.rejectJoinMember(tvo);
		return delete;
	}//teammember테이블에서 삭제
	
	@Override
	@Transactional
	public TeamVo selectLeader(String tid) {//주장 선택
		TeamVo vo = teamMapper.selectLeader(tid);
		return vo;
	}
	
	@Override
	@Transactional
	public int updateLeader(TeamVo vo){//주장 업데이트
		r = teamMapper.updateLeader(vo);
		return r;
	}
	
	@Transactional
	public TeamVo findTeamInfo(String tid) {
		TeamVo tVo = teamMapper.findTeamInfo(tid);
		return tVo;
	}
	
	@Transactional
	public List<TeamVo> findTeamRank() {
		List<TeamVo> teamRank = teamMapper.findTeamRank();
	    return teamRank;
	}
	
	@Transactional
	public List<TeamVo> newTeam() {
		List<TeamVo> newTeam = teamMapper.newTeam();
		return newTeam;
	}
	
	@Transactional
	public List<MatchRecordVo> viewRecord(String tid,TeamPage page){
	    int totList = teamMapper.recordTotList(tid);
	    page.setTid(tid);
	    page.setTotList(totList);
	    page.recordCompute();
	    List<MatchRecordVo> recordList = teamMapper.recordList(page);
		return recordList;
	}
}
