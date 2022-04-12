package dto;

public class MemberDTO {
	private int mNum;
	private String mID;
	private String mPW;
	
	public MemberDTO() {
		System.out.println("MemberDTO() called");
		
		this.mNum = -1;
		this.mID = "test";
		this.mPW = "test";
	}
	
	public MemberDTO(int mNum, String mID, String mPW) {
		this.mNum = mNum;
		this.mID = mID;
		this.mPW = mPW;
	}
	
	public MemberDTO(String mID, String mPW) {
		this.mNum = -1;
		this.mID = mID;
		this.mPW = mPW;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [mNum=" + mNum + ", mID=" + mID + ", mPW=" + mPW + "]";
	}
	

	public int getmNum() {
		return mNum;
	}
	public void setmNum(int mNum) {
		this.mNum = mNum;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getmPW() {
		return mPW;
	}
	public void setmPW(String mPW) {
		this.mPW = mPW;
	}
	
}
