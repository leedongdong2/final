package vo;

public class Page {
	int totList;
	int listSize=12;
	int blockSize=5;
	int totPage;
	int startNo;
	int endNo;
	int startPage;
	int endPage;
	int nowPage=1;
	String findstr="";
	String gubun="";
	String sel="전체";
	
	
	public Page() {}
	public Page(String gubun, String findstr,int totList, int nowPage, String sel) {
		this.gubun = gubun;
		this.findstr = findstr;
		this.totList = totList;
		this.nowPage = nowPage;
		this.sel= sel;
		
		// 페이징 연산
		compute();
	}
	public void compute() {
		totPage = (int)Math.ceil(totList/(double)listSize);
		endNo = nowPage*listSize;
		startNo = endNo-listSize+1;
		if(endNo>totList) endNo = totList;
		
		endPage = (int)Math.ceil(nowPage/(double)blockSize)*blockSize;
		startPage = endPage-blockSize+1;
		if(endPage>totPage) endPage = totPage;
	}
	
	
	public int getTotList() {
		return totList;
	}
	public void setTotList(int totList) {
		this.totList = totList;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getFindstr() {
		return findstr;
	}
	public void setFindstr(String findstr) {
		this.findstr = findstr;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	
	

}
