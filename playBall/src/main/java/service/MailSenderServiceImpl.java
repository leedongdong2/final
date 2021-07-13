package service;


import java.util.Random;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import vo.MatchVo;
import vo.MemberVo;

@Service
public class MailSenderServiceImpl {
	@Autowired
    private JavaMailSenderImpl mailSender;
	int size = 0;
	
	//인증키 사이즈 정하기
	public String getKey(int size) {
	        
		this.size = size;
	    
		return getAuthCode();
	}
	
	//인증키 생성
	public String getAuthCode() {
        
		Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }
	 
	 //인증번호메일보내기
	  public String sendAuthMail(String email) {
		  
		  String authKey = getKey(6);
		  
		  MimeMessage mail = mailSender.createMimeMessage();
		  MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
		  
		  String mailContent = "안녕하세요~ palyball입니다" 
		                     + "인증코드 : " + authKey ;

		  try {
			  mailHelper.setFrom("dongwon1923@gmail.com");
			  mailHelper.setTo(email);
			  mailHelper.setSubject("이메일 인증 입니다");
			  mailHelper.setText(mailContent);
			  
			  mailSender.send(mail);
		  } catch (Exception ex) {
			  ex.printStackTrace();
		  }
		 
		  return authKey;
    }
	  
	  
	  //매칭신청메일보내기
	 public boolean sendMatchApplyMail(MemberVo replVo,MemberVo writerVo, MatchVo vo) {
		  
		 boolean result = false;
		 
		 MimeMessage mail = mailSender.createMimeMessage();
		 MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
		  
		  String mailContent = "안녕하세요~ palyball입니다" 
		                     + "<br>"
		                     + "게시물시리얼번호 :" + vo.getSerial() 
                             + "<br>"
                             + "작성자 번호 : " +  writerVo.getPhone()
                             + "제목 : " + vo.getTitle() + " 대결날짜 : "+ vo.getdDate() + "<br>"
                             + "지역 : " + vo.getLoca() + " 대결 : " + vo.getTag() + " 실력 : " + vo.getTier()   
		                     + " 내용 : " + vo.getDoc()
                             + "작성자분과 연락후 착오없는 재밋는 시합 되세요~~";
           
              try {            
            	  mailHelper.setFrom("dongwon1923@gmail.com");
            	  mailHelper.setTo(replVo.getEmail());
            	  mailHelper.setSubject("매칭완료된 게시판입니다~");
            	  mailHelper.setText(mailContent,true);
			  
            	  mailSender.send(mail);
            	  result = true;
              } catch(Exception ex) {
            	  ex.printStackTrace();
            	  result = false;
              }
              
              return result;
	 }
	 
}
