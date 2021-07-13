package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.MatchingMapper;
import vo.MatchImgVo;
import vo.MatchRecordVo;
import vo.MatchVo;
import vo.MatchingPage;
import vo.MatchingReplPage;
import vo.MatchingReplVo;
import vo.MemberVo;

@Service
public class MatchingServiceImpl implements MatchingService {
@Autowired
MatchingMapper mMapper;
@Autowired
MatchImgVo imgVo;
@Autowired
MatchRecordVo recordVo;

int r;
String saveDir;


//매칭 게시판 등록하기
@Transactional(rollbackFor = Exception.class)
public int boardRegister(MatchVo vo,List<String> imageList) {
     
	saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
     
     try {
    	 
	     int boardResult = mMapper.boardRegister(vo);
	     
	     if(boardResult>0) {
	    	 r = boardResult;
	     }
	     //만약에 이미지가 업로드 되어 잇다면
	     if(imageList.size()>0) {
	    	 
	     int serial = mMapper.findSerial();	 
	     //게시판 시리얼을 찾아서 게시판 이미지db에 저장시켜준다
	     if(boardResult>0) {
		    for(int i=0;i<imageList.size();i++) {
		       imgVo.setSerial(serial);
		       imgVo.setSaveFileName(imageList.get(i));
		        r = mMapper.saveFileName(imgVo);
		       if(r<0) {
		    	    new Exception();
		       }
		    }           
	     } else {
	    		  new Exception();    	    
	            }
	     }
	     //에러가 난다면 이미지 파일을 모두 삭제
     } catch(Exception ex) {
    	 ex.printStackTrace();
    	 for(int i = 0; i<imageList.size();i++) {
 			File file = new File(saveDir+imageList.get(i));
 	        file.delete(); 
    	 }
    	 //트랙잭션을 위해 오류던지기
    	 throw ex;
    	 }
	
     return r;
}





	//게시판 수정
	@SuppressWarnings("unused")
	@Transactional(rollbackFor = Exception.class)
	public int updateBoard(MatchVo vo,List<String> imageList)  {
	
		 saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
		 
		 try {
			int updateResult = mMapper.updateBoard(vo);
			if(updateResult>0) {
				//이미지가 있다면 매칭게시판 이미지db에 저장
				if(imageList.size()>0) {
				  for(int i=0;i<imageList.size();i++) {
					  imgVo.setSerial(vo.getSerial());
					  imgVo.setSaveFileName(imageList.get(i));
					  r = mMapper.saveFileName(imgVo);
				 }
			   }
				     //게시판 내용에 남은 이미지 외 이미지 삭제
						r = confirmLeftImg(vo);
						if(r<0) {
							new Exception();	
						}
					} else {
						new Exception();
					}
		 } catch(Exception ex) {
			ex.printStackTrace();
			for(int j = 0; j<imageList.size(); j++) {
		 	    File file = new File(saveDir+imageList.get(j));
		 	    file.delete(); 
		 	    //트랙잭션을 위해 오류 던지기
		 	    throw ex;
		    }
		}
		 
	
		return r;
}


//조회수 업
@Transactional
public void updateHit(int serial) {
	
	mMapper.updateHit(serial);

}


	//썸머노트 이미지 업로드 정리
	@Transactional
	public List<String> theoremFile(MatchVo vo,String image) {
		  
		  List<String> imageList = new ArrayList<String>(); 
		  
		  if(!image.equals("")) {
		  String doc = vo.getDoc();
		  saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
		  String[] imageArray = image.split(",");       //배열을 리스트로 바꿔주는 메소드 타입이 같아야함                                          //래퍼타입 자동 형변환안되서 int integer안되고 integer integer 식으로 같게 처음부터 만들어야함
		  imageList = new ArrayList<String>(Arrays.asList(imageArray));
		  
		  //썸머노트로 업로드된 이미지명 중 게시판 내용에 있는 이미지 외의 이미지 제거
			for(int i = imageList.size()-1;i>-1;i--) {
				boolean a = doc.contains(imageList.get(i));
				if(a == false) {
					    File file = new File( saveDir +imageList.get(i));
					    file.delete();
					    imageList.remove(i);
				}
			}
		  }
		  //정리된 이미지명 반환
		return imageList;
	}


//매칭 게시판 취소햇을떄 파일삭제기능
public void cancelFile(String image) {
	
   List<String> imageList = new ArrayList<String>();
   
   saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
   
   String[] imageArray = image.split(",");                                    
   imageList = new ArrayList<String>(Arrays.asList(imageArray));
   
   //받아온 이미지명으로 파일 전부 삭제
   for(int i = imageList.size()-1;i>-1;i--) {
	   File file = new File(saveDir + imageList.get(i));
	   file.delete();
	   imageList.remove(i);
   }

}



public List<String> theoremFile(String image) {
	
	 List<String> imageList = new ArrayList<String>(); 
	 
	 if(!image.equals("")) {
	  String[] imageArray = image.split(",");       //배열을 리스트로 바꿔주는 메소드 타입이 같아야함                                               //래퍼타입 자동 형변환안되서 int integer안되고 integer integer 식으로 같게 처음부터 만들어야함
	  imageList = new ArrayList<String>(Arrays.asList(imageArray));
	 }
	
	 return imageList;
}


//매칭게시판 목록 찾기
@Transactional
public List<MatchVo> matchingView(MatchingPage page) {
       
	   int totList = mMapper.totList(page);
       page.setTotList(totList);
       page.compute();
       
       List<MatchVo> list = mMapper.matchingView(page);
	   
       return list;
}

//매칭게시판 상세보기
@Transactional
public MatchVo matchingDetail(int serial) {
	
	MatchVo vo = mMapper.matchingDetail(serial);
	
	return vo;
}

//매칭게시판 수정페이지 이동
@Transactional
public MatchVo matchingModify(int serial) {
	
	MatchVo vo = mMapper.matchingModify(serial);
	
	return vo;
}

//매칭게시판 수정시 남겨진 파일 조회
@Transactional
private int confirmLeftImg(MatchVo vo) {
	
	String doc = vo.getDoc();
	int serial = vo.getSerial();
	saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	
	//수정시 매칭게시판에 잇는 모든 이미지명을 찾음
	List<String> saveFileName = mMapper.findFileName(serial);
	
	//게시판 내용에 잇는 이미지명 외의 이미지 제거
	for(int i=0; i<saveFileName.size();i++) {
		boolean confirmFile = doc.contains(saveFileName.get(i));
		
		if(confirmFile == false) {
			imgVo.setSerial(serial);
			imgVo.setSaveFileName(saveFileName.get(i));
			//db에서 없는 이미지명 삭제
			r = mMapper.deleteLeftFile(imgVo);
			File file = new File( saveDir + saveFileName.get(i));
		    file.delete();
		}
	
	}
	
	//이미지수정이 없을 경우
	if( r == 0 ) {
		r = 1;
	}
	
	return r;
}

//이미지명 찾기
@Transactional
public List<String> findFileName(int serial) {
	
	List<String> saveFileName = mMapper.findFileName(serial);
	
	return saveFileName;
}

//게시판 삭제
@Transactional
public int deleteBoard(int serial,List<String> saveFileName) {

	saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	
	//게시판 정보 삭제
	r = mMapper.deleteBoard(serial);
	//게시판 댓글 삭제
    mMapper.deleteBoardRepl(serial);
	
    //저장된 이미지명이 있다면 삭제해줌
    if(saveFileName != null) {
		 mMapper.deleteBoardImg(serial);
		if(r>0) {
			for(int i = 0; i<saveFileName.size();i++) {
		 	    File file = new File(saveDir+saveFileName.get(i));
		 	    file.delete(); 
		    }
		} 
	}
    
	return r;
}


//댓글 작성
@Transactional
public void insertRepl(MatchingReplVo vo) {
	
	mMapper.insertRepl(vo);

}

//댓글 삭제
public void deleteRepl(int replSerial) {

	mMapper.deleteRepl(replSerial);

}

//댓글목록보기
@Transactional
public List<MatchingReplVo> matchingReplView(MatchingReplPage replPage){
	
	int serial = replPage.getSerial();
	int totList = mMapper.replTotList(serial);
	
	replPage.setTotList(totList);
	replPage.compute();
	
	List<MatchingReplVo> list = mMapper.matchingReplView(replPage);
	
	return list;
}

//댓글 작성자 정보 찾기
@Transactional
public MemberVo findReplWriterVo(String replWriterMid) {
	
	MemberVo replWriterVo = mMapper.findReplWriterVo(replWriterMid); 
	
	return replWriterVo;
}

//게시판 작성자 정보 찾기
@Transactional
public MemberVo findBoardWriterVo(String mid) {
	
	MemberVo boardWriterVo = mMapper.findBoardWriterVo(mid);
	
	return boardWriterVo;
}

//이메일에 넣을 게시판 정보 찾기
@Transactional
public MatchVo findMatchBoard(int serial) {
	
	MatchVo vo = mMapper.findMatchBoard(serial);
	
	String removeImgTag = removeImgTag(vo.getDoc());
	vo.setDoc(removeImgTag);
	
	return vo;
}

//img src html 태그 없애기 // 안됨..
private String removeImgTag(String html) {
	html = html.replaceAll("/<?IMG(.*?)/>", "");
	return html; 
}

//매칭기록작성 
@Transactional
public int recordMatch(String myTeam,String yourTeam,MatchVo vo) {
	
	recordVo.setMyTeam(myTeam);
	recordVo.setYourTeam(yourTeam);
	recordVo.setBoardSerial(vo.getSerial());
	recordVo.setdDate(vo.getdDate());
	
	r = mMapper.insertRecord(recordVo);
	
	return r;
}


//매칭신청 버튼 값 변경 매칭중 => 매칭신청
@Transactional
public int updateVs(int serial,int replSerial) {

	HashMap<String, Integer> serials = new HashMap<String, Integer>(); 
	serials.put("serial", serial );
	serials.put("replSerial", replSerial);
	//댓글
	r = mMapper.updateReplVs(replSerial);
	
	if(r>0) {
		//게시물
	  r = mMapper.updateVs(serials);
	} 
	
	return r;
}

//매칭기록 삭제
@Transactional
public int removeRecordMatch(int serial) {
	
	r =	mMapper.removeRecordMatch(serial);
	
	return r;
}

//매칭신청  버튼값 변경 매칭완료 => 매칭중
@Transactional
public int updateCancelVs(int serial,int replSerial) {
	//댓글
	r = mMapper.updateCancelReplVs(replSerial);
	if(r>0) {
		//게시판
		r = mMapper.updateCancel(serial);
	}
	System.out.println(r);
	return r;
}

//매칭횟수 올리기
@Transactional
public int updateCntMatch(String myTeam,String yourTeam) {
	
	HashMap<String, String> updateCntMatch = new HashMap<String, String>();
	updateCntMatch.put("myTeam", myTeam);
	updateCntMatch.put("yourTeam", yourTeam);
	
	r = mMapper.updateCntMatch(updateCntMatch);
	
	return r;
}

//매칭횟수 내리기
@Transactional
public int updateCntMatchM(String myTeam,String yourTeam) {
	
	HashMap<String, String> updateCntMatchM = new HashMap<String, String>();
	
	updateCntMatchM.put("myTeam", myTeam);
	updateCntMatchM.put("yourTeam", yourTeam);
	
	r = mMapper.updateCntMatchM(updateCntMatchM);
	
	return r;
}


//댓글시리얼 찾기
@Transactional
public int findReplSerial(int serial) {
	
	int replSerial = mMapper.findReplSerial(serial); 

	return replSerial;
}

//?
@Transactional
public String checkVs(int serial) {
	
	String checkVs = mMapper.checkVs(serial);
	
	return checkVs;
}

//내팀명찾기
@Transactional
public String findMyTeam(int serial) {
	String myTeam = mMapper.findMyTeam(serial);
	return myTeam;
}

//상대팀명 찾기
@Transactional
public String findYourTeam(int replSerial) {
	String yourTeam = mMapper.findYourTeam(replSerial);
	return yourTeam;
}


}

