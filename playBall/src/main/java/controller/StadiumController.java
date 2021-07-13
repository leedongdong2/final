package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import service.StadiumServiceImpl;
import vo.Page;
import vo.StadiumVo;
import vo.ReservationPageVo;
import vo.ReservationVo;

@RestController
public class StadiumController {
  @Autowired
  StadiumServiceImpl stadiumService;
  @Autowired
  Page page;
  @Autowired
  JsonObject json;
  int r;
  
  //스타디움 검색하기
  @RequestMapping(value="search.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView search(Page page,ModelAndView mv) {
	  
	  List<StadiumVo> list = new ArrayList<StadiumVo>();
	  list = stadiumService.search(page);
	  
	  mv.addObject("page", page);
	  mv.addObject("list", list);
	  mv.setViewName("View");
	  
	  return mv;
  }
  
  //구장 상세정보보기
  @RequestMapping(value="detail.stadium", method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView detail(StadiumVo vo,ModelAndView mv) {
	  
	  vo=stadiumService.detail(vo.getSerial());
	  mv.addObject("vo", vo);
	  mv.setViewName("DetailView");
	  
	  return mv;
  }
  
  
  //구장게시판생성
  @RequestMapping(value="create.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public String create(StadiumVo vo) {
	  
	  r = stadiumService.create(vo);
      
	  if(r<0) {
    	  json.addProperty("result", true);
      } else { 
    	  json.addProperty("result", false);
      }
      
	  String result = json.toString();
      
	  return result;
  }
  
  //구장 수정하기
  @RequestMapping(value="update.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public void update(StadiumVo vo) {
	  stadiumService.update(vo); 
  
  }
  
  //구장 수정창으로 이동하기
  @RequestMapping(value="modify.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView modify(StadiumVo vo,ModelAndView mv) { 
	  
	  vo = stadiumService.modify(vo.getSerial());
	  
	  mv.addObject("vo", vo);
	  mv.setViewName("Update");
	  
	  return mv;
  }
  
  //구장삭제하기
  @RequestMapping(value="delete.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public String delete(StadiumVo vo) {
	  
	  r = stadiumService.findRecord(vo.getSerial());
	  
	  if(r>0) {
		  json.addProperty("findRecord", false);
	  } else {
		  r = stadiumService.delete(vo.getSerial());	  
		      if(r>0) {
				  json.addProperty("findRecord", true);
		    	  json.addProperty("result", true);
		      } else {
		    	  json.addProperty("result", false);
		      }
	  }
	  
	  String result = json.toString();
	  
	  return result;
  }
  
  //예약 날 목록보기
  @RequestMapping(value="viewRvationDay.stadium",method= {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView viewRvationMonth(ReservationVo vo,ModelAndView mv,@RequestParam("reservationId")String mid) {
      
	  List<String> rvationDay = stadiumService.viewRvationDay(vo);
	  String point = stadiumService.findUserPoint(mid);
      
	  mv.addObject("point",point);
	  mv.addObject("rvationDay",rvationDay);
      mv.setViewName("reservationDay");
	  
      return mv;
  }
  
  //예약 시간 목록보기
  @RequestMapping(value="viewRvationTime.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView viewRevationTiem(ReservationVo vo,ModelAndView mv) {
      
	  ReservationVo sendVo = stadiumService.viewReservationTime(vo);
      
	  mv.addObject("vo",sendVo);
	  mv.setViewName("reservationTime");
	  
	  return mv;
  }
  
  //예약하기
  @RequestMapping(value="reservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public String reservation(ReservationVo vo) {

	  r = stadiumService.reservation(vo);
	    
	  if( r>0 ) {
		 json.addProperty("result", true);
	  } else {
		  json.addProperty("resutl", false);
	  }
	  
	 String result = json.toString();
	 
	 return result;
  }
   
  //유저예약 현황 보기
  @RequestMapping(value="moveMyReservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView moveMyReservation(ModelAndView mv,ReservationPageVo page) {
	  
	  List<ReservationVo> list = stadiumService.moveMyReservation(page);
	  
	  mv.addObject("list",list);
	  mv.addObject("page",page);
	  mv.setViewName("myReservation");
	  
	  return mv;
  }
  
  //유저예약 삭제
  @RequestMapping(value="cancelReservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public String cancelReservation(ReservationVo vo) {
	  
	  r = stadiumService.cancelReservation(vo);
	  
	  if(r>0) {
		  json.addProperty("result", true);
	  } else {
		  json.addProperty("result", false);
	  }
	  
	  String result = json.toString();
	  
	  return result;
  }

}
