package vo;

public class MatchingReplPage {
	private int totList;
	private int listSize=10;
	private int blockSize=5;
	private int totPage;
	private int startNo;
	private int endNo;
	private int startPage;
	private int endPage;
	private int replNowPage=1;
    private int serial;
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public void Page() {}
	  public void Page(int totList,int replNowPage) {
		  this.totList = totList;
		  this.replNowPage = replNowPage;
		  //페이징 연산;
		  compute();
	  }
	  
	  
	  public void compute() {
		  totPage = (int)Math.ceil(totList/(double)listSize);
		  endNo = replNowPage*listSize;
		  startNo = endNo-listSize+1;
		  if(endNo>totList) {endNo = totList;}
		  
		  endPage = ((int)Math.ceil(replNowPage/(double)blockSize))*blockSize;
		  startPage = endPage-blockSize+1;
		  if(endPage>totPage) { endPage = totPage;}
	      
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
		public int getReplNowPage() {
			return replNowPage;
		}
		public void setReplNowPage(int replNowPage) {
			this.replNowPage = replNowPage;
		}
}
	
