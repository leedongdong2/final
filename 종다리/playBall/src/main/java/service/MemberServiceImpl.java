package service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;

import mapper.MemberMapper;
import vo.MemberVo;

public class MemberServiceImpl {
	@Autowired
	EncryptionServiceImpl ec;
	@Autowired
	MemberMapper memberMapper;
	
	@Transactional
	public int insert(MemberVo vo) {
		String salt = ec.makeSalt();
		String hex = ec.encryption(salt, vo.getPassword());
		vo.setPs(salt);
		vo.setPassword(hex);
		int r = memberMapper.insert(vo);
		return r;
	}
	
	@Transactional
	public int duplicatedId(String mid) {
		int r = memberMapper.duplicatedId(mid);
	 return r;	
	}
	
	@Transactional
	public String logIn(MemberVo vo,HttpServletRequest req) {
		String mid = vo.getMid();
		HttpSession session = req.getSession();
		JsonObject jo = new JsonObject();
		String salt = memberMapper.findSalt(mid);
		
		if(salt == null) {
			jo.addProperty("findId", false);
		} else {
			String hex = ec.encryption(salt, vo.getPassword());
			vo.setPassword(hex);
	
			int r = memberMapper.findUser(vo);
			
			if(r>0) {
				jo.addProperty("findUser", true);
				String tid = memberMapper.findUserTeam(mid);
				session.setAttribute("mid", mid);
				session.setAttribute("tid", tid);
			} else {
				jo.addProperty("findUser", false);
			}
	    }
		String result = jo.toString();
		
		return result;
	}
	
	@Transactional
	public MemberVo myPageInfo(MemberVo vo) {
		String mid = vo.getMid();
		vo = memberMapper.myPageInfo(mid);
		return vo;
	}
	
	@Transactional
	public MemberVo modifyPageInfo(MemberVo vo) {
		String mid = vo.getMid();
		vo = memberMapper.modifyPageInfo(mid);
		return vo;
	}
	
	@Transactional
	public String confirmPassword(MemberVo vo,String oriPassword) {
		JsonObject jo = new JsonObject();
		String confirmResult = null;
		String mid = vo.getMid();
		String salt = memberMapper.findSalt(mid);
		String hex = ec.encryption(salt,oriPassword);
		
		int r = memberMapper.confirmPassword(hex);
		if(r>0) {
			confirmResult = null;
		}else {
			jo.addProperty("confirmResult", false);
			confirmResult = jo.toString();
		}
		
		return confirmResult ;
	}
	
	@Transactional
	public String update(MemberVo vo) {
	  JsonObject jo = new JsonObject();
	  
	  if(!vo.getPassword().equals("")) {
	  
		  String salt = ec.makeSalt();
		  String hex = ec.encryption(salt, vo.getPassword());
		  vo.setPs(salt);
		  vo.setPassword(hex);
		  
	  } else if(vo.getPassword().equals("")) {
		  vo.setPassword(null);
	   }
	  int r = memberMapper.update(vo);
	  if(r>0) {
	   jo.addProperty("result", true);
	  } else {
	   jo.addProperty("result", false);
	  }
	  String result = jo.toString(); 
	  return result;
	}
	
	@Transactional
	public String delete(MemberVo vo,HttpServletRequest req) {
		JsonObject json = new JsonObject();
		HttpSession session = req.getSession();
		String mid = vo.getMid();
		int r = memberMapper.deleteTeamMember(mid);
		r = memberMapper.updateCntMemberM(vo.getTid());
		r = memberMapper.delete(mid);
		
		
		if(r>0) {
			json.addProperty("result", true );
			session.removeAttribute("mid");
			session.removeAttribute("tid");
		} else {
			json.addProperty("result", false );
		}
		String result = json.toString();
		return result;
	}
	
	@Transactional
	public int confirmUser(MemberVo vo) {
		int r = memberMapper.confirmUser(vo);
	    return r;
	}
	
	
	public void logOut(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("mid");
		session.removeAttribute("tid");
	}
    
	@Transactional
	public String findId(MemberVo vo) { 
	    String mid = memberMapper.findId(vo);
		return mid ;
	}
	
	@Transactional
	public String issuePwd(MemberVo vo) {
		  Random random = new Random();
	      StringBuffer buffer = new StringBuffer();
	      int num = 0;

	       while(buffer.length() < 6) {
	            num = random.nextInt(10);
	            buffer.append(num);
	        }

	       String issuePwd = buffer.toString();
	       String salt = ec.makeSalt();
		   String hex = ec.encryption(salt,issuePwd);
		   
		   vo.setPs(salt);
		   vo.setPassword(hex);
		   
		   int r = memberMapper.issuePwd(vo);
		   if(r == 0 || r<0) {
			   issuePwd = null;
		   }
		   
		   
		return issuePwd;
	}
	
}
