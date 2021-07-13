package mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.MatchImgVo;
import vo.MatchRecordVo;
import vo.MatchVo;
import vo.MatchingPage;
import vo.MatchingReplPage;
import vo.MatchingReplVo;
import vo.MemberVo;

@Mapper
public interface MatchingMapper {
public int boardRegister(MatchVo vo);
public int findSerial();
public int saveFileName(MatchImgVo imgVo);
public int totList(MatchingPage page);
public List<MatchVo> matchingView(MatchingPage page);
public MatchVo matchingDetail(int serial);
public MatchVo matchingModify(int serial);
public List<String> findFileName(int serial);
public int deleteLeftFile(MatchImgVo imgVo);
public int updateBoard(MatchVo vo);
public int deleteBoard(int serial);
public int deleteBoardImg(int serial);
public int deleteBoardRepl(int serial);
public int insertRepl(MatchingReplVo vo);
public int replTotList(int serial);
public int deleteRepl(int replSerial);
public List<MatchingReplVo> matchingReplView(MatchingReplPage replPage);
public MemberVo findReplWriterVo(String replWriterMid);
public MemberVo findBoardWriterVo(String mid);
public MatchVo findMatchBoard(int serial);
public int insertRecord(MatchRecordVo vo); 
public int updateReplVs(int replSerial);
public int updateVs(HashMap<String, Integer> serials);
public int removeRecordMatch(int serial);
public int updateCancelReplVs(int replSerial);
public int updateCancel(int serial);
public int findReplSerial(int serial);
public String checkVs(int serial);
public int updateCntMatch(HashMap<String, String> updateCntMatch);
public int updateCntMatchM(HashMap<String,String> updateCntMatchM);
public String findMyTeam(int serial);
public String findYourTeam(int serial);
public int updateHit(int serial);

}
