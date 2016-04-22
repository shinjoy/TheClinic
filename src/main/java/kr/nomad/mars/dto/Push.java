package kr.nomad.mars.dto;

public class Push {
	int seq = 0;
	String os = "";
	String pushKey = "";
	String msg = "";
	String msgType = "";
	String msgKey = "";
	int pushType = 0;
	String userid = "";
	int badge = 0;
	int status = 0;
	String regDate = "";
	String serviceId = "";
	String sender = "";
	public static String MSG_TYPE_SEND_NOTICE = "1";	// 공지사항.
	public static String MSG_TYPE_SEND_MISSION = "2";	// 미션
	public static String MSG_TYPE_SEND_MISSION_COMMENT = "3";	// 미션댓글
	public static String MSG_TYPE_SEND_DIARY_COMMENT = "4";	// 미션댓글
	public static String MSG_TYPE_ALARM_MISSION = "5";	// 미션타임
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getPushKey() {
		return pushKey;
	}
	public void setPushKey(String pushKey) {
		this.pushKey = pushKey;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	public int getPushType() {
		return pushType;
	}
	public void setPushType(int pushType) {
		this.pushType = pushType;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBadge() {
		return badge;
	}
	public void setBadge(int badge) {
		this.badge = badge;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}