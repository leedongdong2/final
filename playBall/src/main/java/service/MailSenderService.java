package service;

import vo.MatchVo;
import vo.MemberVo;

public interface MailSenderService {
	public String getKey(int size);
	public String getAuthCode();
	public String sendAuthMail(String email);
	public boolean sendMatchApplyMail(MemberVo replVo,MemberVo writerVo, MatchVo vo);
}
