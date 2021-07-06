package vo;

import org.springframework.stereotype.Repository;

@Repository
public class TeamVo {
	private int serial;
	private String tid;
	private String pic;
	private String intro;
	private int rno;
	private int cntMember;
	private int hit;
	private String mid;
	private int cntMatch;
	

	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public int getCntMember() {
		return cntMember;
	}
	public void setCntMember(int cntMember) {
		this.cntMember = cntMember;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getCntMatch() {
		return cntMatch;
	}
	public void setCntMatch(int cntMatch) {
		this.cntMatch = cntMatch;
	}
}
