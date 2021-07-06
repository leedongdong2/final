package mapper;

import org.apache.ibatis.annotations.Mapper;

import vo.MemberVo;

@Mapper
public interface MemberMapper {

public int insert(MemberVo vo);
public int duplicatedId(String mid);
public String findSalt(String mid);
public int findUser(MemberVo vo);
public MemberVo myPageInfo(String mid);
public MemberVo modifyPageInfo(String mid);
public int confirmPassword(String password);
public int confirmUser(MemberVo vo);
public String findId(MemberVo vo);
public int update(MemberVo vo);
public int issuePwd(MemberVo vo);
public int delete(String mid);
public String findUserTeam(String mid);
public int deleteTeamMember(String mid);
public int updateCntMemberM(String tid);
}
