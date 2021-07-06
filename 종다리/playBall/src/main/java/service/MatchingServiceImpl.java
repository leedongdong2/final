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




@Transactional(rollbackFor = Exception.class)
public int boardRegister(MatchVo vo,List<String> imageList) {
     String saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
     int r = 0;
     try {
     int boardResult = mMapper.boardRegister(vo);
     if(boardResult>0) {
    	 r = 1;
     }
     if(imageList.size()>0) {
     int serial = mMapper.findSerial();	 
     if(boardResult>0) {
	    for(int i=0;i<imageList.size();i++) {
	       imgVo.setSerial(serial);
	       imgVo.setSaveFileName(imageList.get(i));
	        r = mMapper.saveFileName(imgVo);
	       if(r<0) {
	    	   throw new Exception();
	       }
	    }           
     } else {
    		 throw new Exception();    	    
            }
     }
     }catch(Exception ex) {
    	 ex.printStackTrace();
    	 for(int i = 0; i<imageList.size();i++) {
 			File file = new File(saveDir+imageList.get(i));
 	        file.delete(); 
    	 }
    	 }
	return r;
}





@Transactional(rollbackFor = Exception.class)
public int updateBoard(MatchVo vo,List<String> imageList) throws Exception {
	String saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	int r = 0;
	try {
		int updateResult = mMapper.updateBoard(vo);

		if(updateResult>0) {
			if(imageList.size()>0) {
			  for(int i=0;i<imageList.size();i++) {
				  imgVo.setSerial(vo.getSerial());
				  imgVo.setSaveFileName(imageList.get(i));
				  r = mMapper.saveFileName(imgVo);
			 }
		   }
					r = confirmLeftImg(vo);
					if(r<0) {
						throw new Exception();	
					}
				} else {
					throw new Exception();
				}
			
	
	}catch(Exception ex) {
		ex.printStackTrace();
		for(int i = 0; i<imageList.size();i++) {
	 	    File file = new File(saveDir+imageList.get(i));
	 	    file.delete(); 
	 	    throw ex;
	    }
	}
	
	return r;
}















@Transactional
public List<String> theoremFile(MatchVo vo,String image) {
	  List<String> imageList = new ArrayList<String>(); 
	  if(!image.equals("")) {
	  String doc = vo.getDoc();
	  String savedir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	  String[] imageArray = image.split(",");       //배열을 리스트로 바꿔주는 메소드 타입이 같아야함W                                          //래퍼타입 자동 형변환안되서 int integer안되고 integer integer 식으로 같게 처음부터 만들어야함
	  imageList = new ArrayList<String>(Arrays.asList(imageArray));
	  
	  
		for(int i = imageList.size()-1;i>-1;i--) {
			boolean a = doc.contains(imageList.get(i));
			if(a == false) {
				    File file = new File( savedir +imageList.get(i));
				    file.delete();
				    imageList.remove(i);
			}
		}
	  }
	return imageList;
}


public void cancelFile(String image) {
   List<String> imageList = new ArrayList<String>();
   String savedir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
   String[] imageArray = image.split(",");                                    
   imageList = new ArrayList<String>(Arrays.asList(imageArray));
   for(int i = imageList.size()-1;i>-1;i--) {
	   File file = new File(savedir + imageList.get(i));
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

@Transactional
public List<MatchVo> matchingView(MatchingPage page){
       List<MatchVo> list = new ArrayList<MatchVo>();
       int totList = mMapper.totList(page);
       page.setTotList(totList);
       page.compute();
       list = mMapper.matchingView(page);
	return list;
}

@Transactional
public MatchVo matchingDetail(int serial) {
	MatchVo vo = mMapper.matchingDetail(serial);
	return vo;
}

@Transactional
public MatchVo matchingModify(int serial) {
	MatchVo vo = mMapper.matchingModify(serial);
	return vo;
}

@Transactional
private int confirmLeftImg(MatchVo vo) {
	String doc = vo.getDoc();
	int serial = vo.getSerial();
	int r = 0;
	String savedir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	List<String> saveFileName = mMapper.findFileName(serial);
	for(int i=0; i<saveFileName.size();i++) {
		boolean confirmFile = doc.contains(saveFileName.get(i));
		if(confirmFile == false) {
			imgVo.setSerial(serial);
			imgVo.setSaveFileName(saveFileName.get(i));
			r = mMapper.deleteLeftFile(imgVo);
			File file = new File( savedir + saveFileName.get(i));
		    file.delete();
		}
	}
	return r;
}

@Transactional
public List<String> findFileName(int serial) {
	List<String> saveFileName = mMapper.findFileName(serial);
	return saveFileName;
}

@Transactional
public int deleteBoard(int serial,List<String> saveFileName) {
	int r = 0;
	String saveDir = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
	r = mMapper.deleteBoard(serial);
	r = mMapper.deleteBoardImg(serial);
	r = mMapper.deleteBoardRepl(serial);
	if(r>0) {
		for(int i = 0; i<saveFileName.size();i++) {
	 	    File file = new File(saveDir+saveFileName.get(i));
	 	    file.delete(); 
	    }
	} 
	return r;
}

@Transactional
public void insertRepl(MatchingReplVo vo) {
	int r = mMapper.insertRepl(vo);
}
public void deleteRepl(int replSerial) {
	int r = mMapper.deleteRepl(replSerial);
}

@Transactional
public List<MatchingReplVo> matchingReplView(MatchingReplPage replPage){
	int serial = replPage.getSerial();
	int totList = mMapper.replTotList(serial);
	replPage.setTotList(totList);
	replPage.compute();
	List<MatchingReplVo> list = mMapper.matchingReplView(replPage);
	return list;
}

@Transactional
public MemberVo findReplWriterVo(String replWriterMid) {
	 MemberVo replWriterVo = mMapper.findReplWriterVo(replWriterMid); 
	return replWriterVo;
}
@Transactional
public MemberVo findBoardWriterVo(String mid) {
	MemberVo boardWriterVo = mMapper.findBoardWriterVo(mid);
	return boardWriterVo;
}

@Transactional
public MatchVo findMatchBoard(int serial) {
	MatchVo vo = mMapper.findMatchBoard(serial);
	String removeImgTag = removeImgTag(vo.getDoc());
	vo.setDoc(removeImgTag);
	return vo;
}

private String removeImgTag(String html) {
	html = html.replaceAll("/<?IMG(.*?)/>", "");
	return html; 
}
@Transactional
public int recordMatch(String myTeam,String yourTeam,MatchVo vo) {
	recordVo.setMyTeam(myTeam);
	recordVo.setYourTeam(yourTeam);
	recordVo.setBoardSerial(vo.getSerial());
	recordVo.setdDate(vo.getdDate());
	int r = mMapper.insertRecord(recordVo);
	return r;
}


@Transactional
public int updateVs(int serial,int replSerial) {
	int r = 0;
	HashMap<String, Integer> serials = new HashMap<String, Integer>(); 
	serials.put("serial", serial );
	serials.put("replSerial", replSerial);
	r = mMapper.updateReplVs(replSerial);
	if(r>0) {
	  r = mMapper.updateVs(serials);
	} 
	return r;
}

@Transactional
public int removeRecordMatch(int serial) {
	int r =	mMapper.removeRecordMatch(serial);
	return r;
}

@Transactional
public int updateCancelVs(int serial,int replSerial) {
	int r = 0;
	r = mMapper.updateCancelReplVs(replSerial);
	if(r>0) {
		r = mMapper.updateCancel(serial);
	}
	System.out.println(r);
	return r;
}

@Transactional
public int updateCntMatch(String myTeam,String yourTeam) {
	HashMap<String, String> updateCntMatch = new HashMap<String, String>();
	updateCntMatch.put("myTeam", myTeam);
	updateCntMatch.put("yourTeam", yourTeam);
	int r = mMapper.updateCntMatch(updateCntMatch);
	System.out.println(r);
	return r;
}

@Transactional
public int updateCntMatchM(String myTeam,String yourTeam) {
	HashMap<String, String> updateCntMatchM = new HashMap<String, String>();
	updateCntMatchM.put("myTeam", myTeam);
	updateCntMatchM.put("yourTeam", yourTeam);
	int r = mMapper.updateCntMatchM(updateCntMatchM);
	return r;
}


@Transactional
public int findReplSerial(int serial) {
	int replSerial = mMapper.findReplSerial(serial); 
	return replSerial;
}

@Transactional
public String checkVs(int serial) {
	String checkVs = mMapper.checkVs(serial);
	return checkVs;
}

@Transactional
public String findMyTeam(int serial) {
	String myTeam = mMapper.findMyTeam(serial);
	return myTeam;
}

@Transactional
public String findYourTeam(int replSerial) {
	String yourTeam = mMapper.findYourTeam(replSerial);
	return yourTeam;
}


}

