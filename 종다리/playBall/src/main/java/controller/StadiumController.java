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
import vo.reservationPageVo;
import vo.reservationVo;

@RestController
public class StadiumController {
  @Autowired
  StadiumServiceImpl stadiumService;
  @Autowired
  Page page;
  int r;
  
  @RequestMapping(value="search.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView search(Page page) {
	  ModelAndView mv=new ModelAndView();
	  List<StadiumVo> list=new ArrayList<StadiumVo>();
	  list=stadiumService.search(page);
	  mv.addObject("page", page);
	  mv.addObject("list", list);
	  mv.setViewName("View");
	  return mv;
  }
  
  @RequestMapping(value="detail.stadium", method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView detail(StadiumVo vo) {
	  ModelAndView mv=new ModelAndView();
	  vo=stadiumService.detail(vo.getSerial());
	  mv.addObject("vo", vo);
	  mv.setViewName("DetailView");
	  return mv;
  }
  
  @RequestMapping(value="create.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public String create(StadiumVo vo) {
	  JsonObject json = new JsonObject();
	  int r = stadiumService.create(vo);
      if(r>0) {
    	  json.addProperty("result", true);
      } else { 
    	  json.addProperty("result", false);
      }
      String result = json.toString();
       return result;
  }
  
  @RequestMapping(value="update.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public void update(StadiumVo vo) {
	  stadiumService.update(vo);
	  
  }
  
  @RequestMapping(value="modify.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView modify(StadiumVo vo) {
	  ModelAndView mv=new ModelAndView(); 
	  vo=stadiumService.modify(vo.getSerial());
	  mv.addObject("vo", vo);
	  mv.setViewName("Update");
	  return mv;
  }
  
  @RequestMapping(value="delete.stadium", method= {RequestMethod.GET, RequestMethod.POST})
  public String delete(StadiumVo vo) {
	  JsonObject json = new JsonObject();
	  int r = 0;
	  
	  r = stadiumService.findRecord(vo.getSerial());
	  System.out.println(r);
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
  
  @RequestMapping(value="viewRvationDay.stadium",method= {RequestMethod.GET, RequestMethod.POST})
  public ModelAndView viewRvationMonth(reservationVo vo,ModelAndView mv,@RequestParam("mid")String mid) {
      List<String> rvationDay = stadiumService.viewRvationDay(vo);
      String point = stadiumService.findUserPoint(mid);
      mv.addObject("point",point);
	  mv.addObject("rvationDay",rvationDay);
      mv.setViewName("reservationDay");
	  return mv;
  }
  
  @RequestMapping(value="viewRvationTime.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView viewRevationTiem(reservationVo vo,ModelAndView mv) {
      reservationVo sendVo = stadiumService.viewReservationTime(vo);
      mv.addObject("vo",sendVo);
	  mv.setViewName("reservationTime");
	  return mv;
  }
  
  @RequestMapping(value="reservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public String reservation(reservationVo vo) {
	  JsonObject json = new JsonObject();
	  int r = stadiumService.reservation(vo);
	    
	  if( r>0 ) {
		 json.addProperty("result", true);
	  } else {
		  json.addProperty("resutl", false);
	  }
	  
	 String result = json.toString();
	  return result;
  }

  @RequestMapping(value="moveMyReservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public ModelAndView moveMyReservation(ModelAndView mv,reservationPageVo page) {
	  List<reservationVo> list = stadiumService.moveMyReservation(page);
	  System.out.println(page.getReservationId());
	  mv.addObject("list",list);
	  mv.addObject("page",page);
	  mv.setViewName("myReservation");
	  return mv;
  }
  
  @RequestMapping(value="cancelReservation.stadium",method= {RequestMethod.GET,RequestMethod.POST})
  public String cancelReservation(reservationVo vo) {
	  JsonObject json = new JsonObject();
	  int r = stadiumService.cancelReservation(vo);
	  if(r>0) {
		  json.addProperty("result", true);
	  } else {
		  json.addProperty("result", false);
	  }
	  String result = json.toString();
	  return result;
  }

}
