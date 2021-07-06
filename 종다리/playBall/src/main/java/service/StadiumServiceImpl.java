package service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mapper.StadiumMapper;
import vo.Page;
import vo.StadiumVo;
import vo.reservationPageVo;
import vo.reservationVo;



@Service
public class StadiumServiceImpl implements StadiumService{

	@Autowired
	StadiumMapper sm;

	@Override
	public List<StadiumVo> search(Page page) {
		List<StadiumVo> list=new ArrayList<StadiumVo>();
		int totList=sm.totList(page);
		page.setTotList(totList);
		page.compute();
		list=sm.search(page);
		return list;
	}
    
	@Override
	public int create(StadiumVo vo) {
		 int r = sm.create(vo);
		 String serial = sm.getStadiumSerial();
		 r = sm.createMonth(serial);
		 r = sm.createDay(serial);
	    return r;
	}

	@Override
	public void update(StadiumVo vo) {
		sm.update(vo);
		
	}

	@Override
	public StadiumVo detail(String serial) {
		StadiumVo vo = sm.detail(serial);
		return vo;
	}

	@Override
	public StadiumVo modify(String serial) {
		StadiumVo vo=sm.modify(serial);
		return vo;
	}
           
	public int findRecord(String serial) {
		int r = sm.findRecord(serial);		
		return r;
	}
	
	
	@Override
	public int delete(String serial) {
		int r = 0;
		String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\stadiumBoard\\";
		String findSaveFile = sm.findSaveFile(serial);
	    r = sm.deleteReservationTable(serial);
		r = sm.delete(serial);
		if(r>0) {
		  File file = new File(fileRoot+findSaveFile);
		  file.delete();
	    } 
		
		return r;
	}

	public String findUserPoint(String mid) {
		String point = sm.findUserPoint(mid);
		return point;
	}
	
	public List<String> viewRvationDay(reservationVo vo){
		List<String> viewRvationDay = sm.viewRvationDay(vo);
		return viewRvationDay;
	}
	
	public reservationVo viewReservationTime(reservationVo vo) {
		reservationVo sendVo = sm.viewReservationTime(vo);
		return sendVo;
	}
	
	public int reservation(reservationVo vo) {
		int r = 0;
		r = sm.reservation(vo);
		r = sm.reservationRecord(vo);
		r = sm.payment(vo);
		return r;
	}
	
	public List<reservationVo> moveMyReservation(reservationPageVo page) {
		int totList = sm.myReservationTotList(page);
		page.setTotList(totList);
		page.compute();
		List<reservationVo> list = sm.moveMyReservation(page);
		return list;
	}
	
	public int cancelReservation(reservationVo vo) {
		int r = 0;
		r = sm.cancelPay(vo);
		r = sm.cancelReservationRecord(vo);
		r = sm.cancelReservation(vo);
		return r;
	}
}

