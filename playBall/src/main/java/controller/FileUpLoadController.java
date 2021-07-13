package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@RestController
public class FileUpLoadController {
	 @Autowired
	 JsonObject json; //gson 라이브러리
	 String fileRoot; //파일 업로드 경로
	 String saveFileName; //폴더업로드,데이터베이스저장 될떄 저장될 파일네임
	 SimpleDateFormat sdf; // 데이트 포맷
	 String now; // 현재시간
	 String uploadPath;  //경로 + 이름 
	 
	 //썸머노트 온이미지 이벤트시 파일 업로드 메소드
	@RequestMapping(value="fileUpLoad.upload",method= {RequestMethod.GET,RequestMethod.POST} )                                                 //파일을 받는 부분 클래스
	public String fileUpLoad(@RequestParam("file") MultipartFile mf) {
	 

		 sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		 now = sdf.format(new Date());
		 fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
		 saveFileName = now + mf.getOriginalFilename();
		 uploadPath = fileRoot + saveFileName;
		 
		 try {
			   if(mf.getSize()>0) {
				 mf.transferTo(new File(uploadPath));
		       }
		 } catch(Exception ex) {
			 ex.printStackTrace();
		 }
		 
		 json.addProperty("id", "./img/matchBoard/"+saveFileName);
		 String result = json.toString();
		 return result;
	}
	
	
	
	//구장 이미지 업로드 
	@RequestMapping(value="uploadStadium.upload",method= {RequestMethod.GET,RequestMethod.POST})
	public String uploadStadium(@RequestParam("attfile") MultipartFile mf) {
		
		sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		now = sdf.format(new Date());
		fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\stadiumBoard\\";
		saveFileName = now + mf.getOriginalFilename();
		uploadPath = fileRoot + saveFileName;
		
		try { 
				if(mf.getSize()>0) {
					mf.transferTo(new File(uploadPath));	
				}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		json.addProperty("saveFileName", saveFileName);
		String result = json.toString();
		return result;
	}
	
 
	
	
	
	
	
	
	
	
}
