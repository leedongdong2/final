package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@RestController
public class FileUpLoadController {

	@RequestMapping(value="fileUpLoad.upload",method= {RequestMethod.GET,RequestMethod.POST} )                                                 //파일을 받는 부분 클래스
	public String fileUpLoad(@RequestParam("file") MultipartFile mf) {
		JsonObject jo = new JsonObject(); 
		 String uploadPath = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		 String now = sdf.format(new Date());
		 String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\matchBoard\\";
		 String saveFileName = now + mf.getOriginalFilename();

		 uploadPath = fileRoot + saveFileName;
		 try {
			 if(mf.getSize()>0) {
				 mf.transferTo(new File(uploadPath));
			 }
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
		 jo.addProperty("id", "./img/matchBoard/"+saveFileName);
		 String a = jo.toString();
		 return a;
	}
	
	@RequestMapping(value="uploadStadium.upload",method= {RequestMethod.POST})
	public String uploadStadium(@RequestParam("attfile") MultipartFile mf) {
		JsonObject jo=new JsonObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSS");
		String uploadPath = "";
		String now = sdf.format(new Date());
		String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\stadiumBoard\\";
		String saveFileName = now + mf.getOriginalFilename();
		uploadPath = fileRoot + saveFileName;
		
		uploadPath = fileRoot + saveFileName;
		try { 
			if(mf.getSize()>0) {
				mf.transferTo(new File(uploadPath));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		jo.addProperty("saveFileName", saveFileName);
		String result = jo.toString();
		return result;
	}
	

}
