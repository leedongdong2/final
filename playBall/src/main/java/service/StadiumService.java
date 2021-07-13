package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import vo.Page;
import vo.StadiumVo;
import vo.ReservationPageVo;
import vo.ReservationVo;

public interface StadiumService {
	public List<StadiumVo> search(Page page);
	public int create(StadiumVo vo);
	public void update(StadiumVo vo);
	public StadiumVo detail(String serial);
	public StadiumVo modify(String serial);
	public int findRecord(String serial);
	public int delete(String serial);
	public String findUserPoint(String mid);
	public List<String> viewRvationDay(ReservationVo vo);
	public ReservationVo viewReservationTime(ReservationVo vo);
	public int reservation(ReservationVo vo);
	public List<ReservationVo> moveMyReservation(ReservationPageVo page);
	public int cancelReservation(ReservationVo vo);
}
