package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import vo.Page;
import vo.StadiumVo;
import vo.reservationPageVo;
import vo.reservationVo;

@Mapper
public interface StadiumMapper {
	public List<StadiumVo> search(Page page);
	public int totList(Page page);
	public int create(StadiumVo vo);
	public void update(StadiumVo vo);
	public StadiumVo detail(String serial);
	public StadiumVo modify(String serial);
	public int delete(String serial);
	public String findSaveFile(String serial);
	public List<String> viewRvationDay(reservationVo vo);
	public reservationVo viewReservationTime(reservationVo vo);
	public String findUserPoint(String mid);
    public String getStadiumSerial();
    public int reservation(reservationVo vo);
    public int reservationRecord(reservationVo vo);
    public int payment(reservationVo vo);
    public int myReservationTotList(reservationPageVo page);
    public List<reservationVo> moveMyReservation(reservationPageVo page);
    public int cancelPay(reservationVo vo);
    public int cancelReservationRecord(reservationVo vo);
    public int cancelReservation(reservationVo vo);
    public int createMonth(String serial);
    public int createDay(String serial);
    public int findRecord(String serial);
    public int deleteReservationTable(String serial);
}
