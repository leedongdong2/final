package vo;

public class MatchVo {
	int rno;
	int serial;
	String mid; // 아이디
	String mDate; //게시글 작성 날짜
	String dDate; //시합 날짜 선택 
	String title; //제목
	String tag; //2:2 or 3:3
	String loca; //지역
	String tier; // 상 중 하 등급
	String doc; // 본문
	String vs; // 매칭 상태
	int hit; // 조회수
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getmDate() {
		return mDate;
	}
	public void setmDate(String mDate) {
		this.mDate = mDate;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getLoca() {
		return loca;
	}
	public void setLoca(String loca) {
		this.loca = loca;
	}
	public String getTier() {
		return tier;
	}
	public void setTier(String tier) {
		this.tier = tier;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getVs() {
		return vs;
	}
	public void setVs(String vs) {
		this.vs = vs;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
	
	
}

