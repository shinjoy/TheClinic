package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class MissionComment {
	
	int missionCommentSeq  = 0;
	int missionSeq = 0;
	String bbsCategory = "";
	String bbsHeader = "";
	String userId = "";
	String missionTitle = "";
	String missionContents = "";
	String files = "";
	String linkUrl = "";
	String extraContents = "";
	int viewCount = 0;
	int likeCount = 0;
	int dislikeCount = 0;
	int reportCount = 0;
	int fileCount = 0;
	int commentCount = 0;
	String regDate = "";
	int RLevel = 0;
	int RCommentSeq = 0;
	String RRegDate = "";
	
	//List
	List commentList = new ArrayList();
	List fileList = new ArrayList();
	
	// View

	String userName = "";

	String birthday = "";
	int gender = 0;
	String photo="";
	String osType = "";
	String osVersion = "";
	String appVersion = "";
	String deviceName = "";
	String deviceId = "";
	String pushkey = "";
	int usePushservice = 0;
	
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getRLevel() {
		return RLevel;
	}
	public void setRLevel(int RLevel) {
		this.RLevel = RLevel;
	}
	public int getRCommentSeq() {
		return RCommentSeq;
	}
	public void setRCommentSeq(int RCommentSeq) {
		this.RCommentSeq = RCommentSeq;
	}
	public String getRRegDate() {
		return RRegDate;
	}
	public void setRRegDate(String RRegDate) {
		this.RRegDate = RRegDate;
	}
	public List getFileList() {
		return fileList;
	}
	public void setFileList(List fileList) {
		this.fileList = fileList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPushkey() {
		return pushkey;
	}
	public void setPushkey(String pushkey) {
		this.pushkey = pushkey;
	}
	public int getUsePushservice() {
		return usePushservice;
	}
	public void setUsePushservice(int usePushservice) {
		this.usePushservice = usePushservice;
	}
	public List getCommentList() {
		return commentList;
	}
	public void setCommentList(List commentList) {
		this.commentList = commentList;
	}
	public int getMissionCommentSeq() {
		return missionCommentSeq;
	}
	public void setMissionCommentSeq(int missionCommentSeq) {
		this.missionCommentSeq = missionCommentSeq;
	}
	public int getMissionSeq() {
		return missionSeq;
	}
	public void setMissionSeq(int missionSeq) {
		this.missionSeq = missionSeq;
	}
	public String getMissionTitle() {
		return missionTitle;
	}
	public void setMissionTitle(String missionTitle) {
		this.missionTitle = missionTitle;
	}
	public String getMissionContents() {
		return missionContents;
	}
	public void setMissionContents(String missionContents) {
		this.missionContents = missionContents;
	}
	public String getBbsCategory() {
		return bbsCategory;
	}
	public void setBbsCategory(String bbsCategory) {
		this.bbsCategory = bbsCategory;
	}
	public String getBbsHeader() {
		return bbsHeader;
	}
	public void setBbsHeader(String bbsHeader) {
		this.bbsHeader = bbsHeader;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getExtraContents() {
		return extraContents;
	}
	public void setExtraContents(String extraContents) {
		this.extraContents = extraContents;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	public int getReportCount() {
		return reportCount;
	}
	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	

	

}