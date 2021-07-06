package service;

import java.util.List;

import vo.Page;
import vo.StadiumVo;

public interface StadiumService {
	public List<StadiumVo> search(Page page);
	public int create(StadiumVo vo);
	public void update(StadiumVo vo);
	public StadiumVo detail(String serial);
	public StadiumVo modify(String serial);
	public int delete(String serial);
}
