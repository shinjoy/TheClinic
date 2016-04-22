package kr.nomad.mars.dto;

public class MissionUser {
	
	int missionSeq = 0;
	String userId = "";
	String regDate = "";
	public int getMissionSeq() {
		return missionSeq;
	}
	public void setMissionSeq(int missionSeq) {
		this.missionSeq = missionSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	

}
