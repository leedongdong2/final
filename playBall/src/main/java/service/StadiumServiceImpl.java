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
import vo.ReservationPageVo;
import vo.ReservationVo;



@Service
public class StadiumServiceImpl implements StadiumService{

	@Autowired
	StadiumMapper sm;
    int r;
    
	@Override
	@Transactional
	public List<StadiumVo> search(Page page) {
		List<StadiumVo> list=new ArrayList<StadiumVo>();
		int totList=sm.totList(page);
		page.setTotList(totList);
		page.compute();
		list=sm.search(page);
		return list;
	}
    
	
    @Override
    @Transactional
	public int create(StadiumVo vo) {
		 r = sm.create(vo);
		 String serial = sm.getStadiumSerial();
		 if(r>0) {
		 r = sm.createMonth(serial);
		 r = sm.createDay(serial);
		 }
	    return r;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(StadiumVo vo) {
		String fileRoot = "C:\\eclipes\\playBall\\src\\main\\webapp\\img\\stadiumBoard\\";
		String saveFileName = sm.findSaveFile(vo.getSerial());
		try {
			r = sm.update(vo);
			if( r>0 ) {
				File file = new File(fileRoot + saveFileName);
				file.delete();
			}
		} catch(Exception ex) {
			File file = new File(fileRoot + vo.getPic());
			file.delete();
			throw ex;
		}
	}

	@Override
	@Transactional
	public StadiumVo detail(String serial) {
		StadiumVo vo = sm.detail(serial);
		return vo;
	}

	@Override
	@Transactional
	public StadiumVo modify(String serial) {
		StadiumVo vo=sm.modify(serial);
		return vo;
	}
           
	@Transactional
	public int findRecord(String serial) {
		r = sm.findRecord(serial);		
		return r;
	}
	
	
	@Override
	@Transactional
	public int delete(String serial) {
		r = 0;
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
     
	@Transactional
	public String findUserPoint(String mid) {
		String point = sm.findUserPoint(mid);
		return point;
	}
	
	@Transactional
	public List<String> viewRvationDay(ReservationVo vo){
		List<String> viewRvationDay = sm.viewRvationDay(vo);
		return viewRvationDay;
	}
	
	@Transactional
	public ReservationVo viewReservationTime(ReservationVo vo) {
		ReservationVo sendVo = sm.viewReservationTime(vo);
		return sendVo;
	}
	
	@Transactional
	public int reservation(ReservationVo vo) {
		r = sm.reservation(vo);
		r = sm.reservationRecord(vo);
		r = sm.payment(vo);
		return r;
	}
	
	@Transactional
	public List<ReservationVo> moveMyReservation(ReservationPageVo page) {
		int totList = sm.myReservationTotList(page);
		page.setTotList(totList);
		page.compute();
		List<ReservationVo> list = sm.moveMyReservation(page);
		return list;
	}
	
	@Transactional
	public int cancelReservation(ReservationVo vo) {
		r = sm.cancelPay(vo);
		r = sm.cancelReservationRecord(vo);
		r = sm.cancelReservation(vo);
		return r;
	}
}

