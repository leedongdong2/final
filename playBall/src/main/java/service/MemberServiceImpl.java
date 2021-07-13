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
	@Autowired
	PointServiceImpl point;
    @Autowired
    JsonObject json;
    int r;
    
    
    
    //회원가입
	@Transactional
	public int insert(MemberVo vo) {
		//솔트생성
		String salt = ec.makeSalt();
		//암호화하기
		String hex = ec.encryption(salt, vo.getPassword());
		vo.setPs(salt);
		vo.setPassword(hex);
		
		r = memberMapper.insert(vo);
		
		return r;
	}
	
	//아이디 중복체크
	@Transactional
	public int duplicatedId(String mid) {
		r = memberMapper.duplicatedId(mid);
	 return r;	
	}
	
	
	
	
	
	
	
	//로그인
	@Transactional
	public String logIn(MemberVo vo,HttpServletRequest req) {
		String mid = vo.getMid();
		HttpSession session = req.getSession();
		//아이디를 통해서 솔트를 찾음
		String salt = memberMapper.findSalt(mid);
		//솔트를 못찾으면
		if(salt == null) {
			//해당 아이디는 틀림
			json.addProperty("findId", false);
		} else {
			//솔트를 찾으면 암호화
			String hex = ec.encryption(salt, vo.getPassword());
			vo.setPassword(hex);
	        //아이디와 비밀번호가 맞는 회원을 찾음
			r = memberMapper.findUser(vo);
			
			if(r>0) {
				
				json.addProperty("findUser", true);
				//회원의 팀명 찾기
				String tid = memberMapper.findUserTeam(mid);
				//로그인시 포인트 적립
				point.loginPoint(mid);
				//세션값 넣기
				session.setAttribute("mid", mid);
				session.setAttribute("tid", tid);
			
			} else {
				json.addProperty("findUser", false);
			}
	    }
		
		String result = json.toString();
		
		return result;
	}
	
	//내정보 페이지로 이동
	@Transactional
	public MemberVo myPageInfo(MemberVo vo) {
		
		String mid = vo.getMid();
		
		vo = memberMapper.myPageInfo(mid);
		
		return vo;
	}
	
	//내 정보수정 창으로 이동
	@Transactional
	public MemberVo modifyPageInfo(MemberVo vo) {
		
		String mid = vo.getMid();
		
		vo = memberMapper.modifyPageInfo(mid);
		
		return vo;
	}
	
	//기존의 비밀번호가 맞는지 확인
	@Transactional
	public String confirmPassword(MemberVo vo,String oriPassword) {

		String confirmResult = null;
		String mid = vo.getMid();
		//아이디로 솔트를 찾음
		String salt = memberMapper.findSalt(mid);
		String hex = ec.encryption(salt,oriPassword);
		//비밀번호가 맞는지 확인한다
		 r = memberMapper.confirmPassword(hex);
		if(r>0) {
			//비밀번호가 맞을경우
			confirmResult = null;
		}else {
			//비밀번호가 틀릴경우
			json.addProperty("confirmResult", false);
			confirmResult = json.toString();
		}
		
		return confirmResult ;
	}
	
	
	//정보수정
	@Transactional
	public String update(MemberVo vo) {
	  
	  //새로운 비밀번호를 작성 햇을경우
	  if(!vo.getPassword().equals("")) {
	  
		  String salt = ec.makeSalt();
		  String hex = ec.encryption(salt, vo.getPassword());
		  vo.setPs(salt);
		  vo.setPassword(hex);
		  
	  } else if(vo.getPassword().equals("")) {
		  //새로운 비밀번호를 작성하지 않앗을떄
		  vo.setPassword(null);
	   }
	  
	  //업데이트
	  r = memberMapper.update(vo);
	  
	  if(r>0) {
	   json.addProperty("result", true);
	  } else {
	   json.addProperty("result", false);
	  }
	  
	  String result = json.toString(); 
	  
	  return result;
	}
	
	//회원 탈퇴
	@Transactional
	public String delete(MemberVo vo,HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		String mid = vo.getMid();
		
		//팀 관련 테이블 전부다 삭제 (팀멤버,팀인원)
		r = memberMapper.deleteTeamMember(mid);
		r = memberMapper.updateCntMemberM(vo.getTid());
		//멤버 삭제
		r = memberMapper.delete(mid);
		
		
		if(r>0) {
			//세션값 삭제
			json.addProperty("result", true );
			session.removeAttribute("mid");
			session.removeAttribute("tid");
		} else {
			json.addProperty("result", false );
		}
		
		String result = json.toString();
		
		return result;
	}
	
	//유저확인
	@Transactional
	public int confirmUser(MemberVo vo) {
		
		r = memberMapper.confirmUser(vo);
	    
		return r;
	}
	
	//로그아웃
	public void logOut(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("mid");
		session.removeAttribute("tid");
	}
    
		//아이디찾기
		@Transactional
		public String findId(MemberVo vo) { 
		    
			String mid = memberMapper.findId(vo);
			
			return mid ;
		}
		
		//비밀번호 재발급
		@Transactional
		public String issuePwd(MemberVo vo) {
			  
			 //랜덤으로 암호를 재발급해준다
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
			   
			   //발급된 비밀번호를 테이블에 저장
			   r = memberMapper.issuePwd(vo);
			  
			   if(r == 0 || r<0) {
				   issuePwd = null;
			   }
			   
			   
			return issuePwd;
		}
	
}
