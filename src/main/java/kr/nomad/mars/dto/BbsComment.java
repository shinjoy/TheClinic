package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class BbsComment {
	
	int bbsCommentSeq  = 0;
	int bbsSeq = 0;
	String bbsCategory = "";
	String bbsHeader = "";
	String userId = "";
	String bbsTitle = "";
	String bbsContents = "";
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
	int rLevel = 0;
	int rCommentSeq = 0;
	String rRegDate = "";
	
	///view
	int gender =0;
	String birthday = "";
	int userType = 0;
	String userName = "";
	String photo="";
	//List
	List commentList = new ArrayList();
	
	
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List getCommentList() {
		return commentList;
	}
	public void setCommentList(List commentList) {
		this.commentList = commentList;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getBbsCommentSeq() {
		return bbsCommentSeq;
	}
	public void setBbsCommentSeq(int bbsCommentSeq) {
		this.bbsCommentSeq = bbsCommentSeq;
	}
	public int getBbsSeq() {
		return bbsSeq;
	}
	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
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
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContents() {
		return bbsContents;
	}
	public void setBbsContents(String bbsContents) {
		this.bbsContents = bbsContents;
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
	public int getRLevel() {
		return rLevel;
	}
	public void setRLevel(int rLevel) {
		this.rLevel = rLevel;
	}
	public int getRCommentSeq() {
		return rCommentSeq;
	}
	public void setRCommentSeq(int rCommentSeq) {
		this.rCommentSeq = rCommentSeq;
	}
	public String getRRegDate() {
		return rRegDate;
	}
	public void setRRegDate(String rRegDate) {
		this.rRegDate = rRegDate;
	}

	

}
