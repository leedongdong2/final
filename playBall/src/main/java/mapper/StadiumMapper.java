package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.Page;
import vo.StadiumVo;
import vo.ReservationPageVo;
import vo.ReservationVo;

@Mapper
public interface StadiumMapper {
	public List<StadiumVo> search(Page page);
	public int totList(Page page);
	public int create(StadiumVo vo);
	public int update(StadiumVo vo);
	public StadiumVo detail(String serial);
	public StadiumVo modify(String serial);
	public int delete(String serial);
	public String findSaveFile(String serial);
	public List<String> viewRvationDay(ReservationVo vo);
	public ReservationVo viewReservationTime(ReservationVo vo);
	public String findUserPoint(String mid);
    public String getStadiumSerial();
    public int reservation(ReservationVo vo);
    public int reservationRecord(ReservationVo vo);
    public int payment(ReservationVo vo);
    public int myReservationTotList(ReservationPageVo page);
    public List<ReservationVo> moveMyReservation(ReservationPageVo page);
    public int cancelPay(ReservationVo vo);
    public int cancelReservationRecord(ReservationVo vo);
    public int cancelReservation(ReservationVo vo);
    public int createMonth(String serial);
    public int createDay(String serial);
    public int findRecord(String serial);
    public int deleteReservationTable(String serial);
}
