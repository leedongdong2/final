package service;

import java.util.List;

import vo.MatchVo;
import vo.MatchingPage;
import vo.MatchingReplPage;
import vo.MatchingReplVo;
import vo.MemberVo;

public interface MatchingService {
	public int boardRegister(MatchVo vo,List<String> imageList) ;
	public int updateBoard(MatchVo vo,List<String> imageList) ;
	public void updateHit(int serial);
	public List<String> theoremFile(MatchVo vo,String image);
	public void cancelFile(String image);
	public List<String> theoremFile(String image);
	public List<MatchVo> matchingView(MatchingPage page);
	public MatchVo matchingDetail(int serial);
	public MatchVo matchingModify(int serial);
	public List<String> findFileName(int serial);
	public int deleteBoard(int serial,List<String> saveFileName);
	public void insertRepl(MatchingReplVo vo);
	public void deleteRepl(int replSerial);
	public List<MatchingReplVo> matchingReplView(MatchingReplPage replPage);
	public MemberVo findReplWriterVo(String replWriterMid);
	public MemberVo findBoardWriterVo(String mid);
	public MatchVo findMatchBoard(int serial);
	public int recordMatch(String myTeam,String yourTeam,MatchVo vo);
	public int updateVs(int serial,int replSerial);
	public int removeRecordMatch(int serial);
	public int updateCancelVs(int serial,int replSerial);
	public int updateCntMatch(String myTeam,String yourTeam);
	public int updateCntMatchM(String myTeam,String yourTeam);
	public int findReplSerial(int serial);
	public String checkVs(int serial);
	public String findMyTeam(int serial);
	public String findYourTeam(int replSerial);
}
