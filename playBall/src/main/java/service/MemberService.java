package service;

import javax.servlet.http.HttpServletRequest;

import vo.MemberVo;

public interface MemberService {
	public int insert(MemberVo vo);
	public int duplicatedId(String mid);
	public String logIn(MemberVo vo,HttpServletRequest req);
	public MemberVo myPageInfo(MemberVo vo);
	public MemberVo modifyPageInfo(MemberVo vo);
	public String confirmPassword(MemberVo vo,String oriPassword);
	public String update(MemberVo vo);
	public String delete(MemberVo vo,HttpServletRequest req);
	public int confirmUser(MemberVo vo);
	public void logOut(HttpServletRequest req);
	public String findId(MemberVo vo);
	public String issuePwd(MemberVo vo);
}
