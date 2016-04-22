package kr.nomad.mars.dto;

import java.util.ArrayList;
import java.util.List;

public class BbsFnc {
	public static final int FNC_TYPE_LIKE = 1;
	public static final int FNC_TYPE_DISLIKE = 2;
	public static final int FNC_TYPE_REPORT = 3;
	public static final int FNC_TYPE_SHARE_LIKE=4;
	int bbsFncSeq = 0;
	int bbsSeq = 0;
	int bbsCommentSeq = 0;
	int fncType = 0;
	int fncValue = 0;
	String userId = "";
	String regDate = "";
	String contents = "";
	String photo = "";
	int fncReason = 0;
	String userName="";
	int type =0;
	
	String fncReasontxt="";
	Bbs bbs ;
	BbsComment bbsComment;
	
	
	
	public BbsComment getBbsComment() {
		return bbsComment;
	}
	public void setBbsComment(BbsComment bbsComment) {
		this.bbsComment = bbsComment;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public Bbs getBbs() {
		return bbs;
	}
	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}
	public String getFncReasontxt() {
		String str="";
		if(this.fncReason==1){
			str="음란성 게시물";
			
		}else if(this.fncReason==2){
			str="욕설";
			
		}else if(this.fncReason==3){
			str="타인도용/사기";
			
		}else if(this.fncReason==4){
			str="기타";
			
		}
		
		return str;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getFncReason() {
		return fncReason;
	}
	public void setFncReason(int fncReason) {
		this.fncReason = fncReason;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getBbsFncSeq() {
		return bbsFncSeq;
	}
	public void setBbsFncSeq(int bbsFncSeq) {
		this.bbsFncSeq = bbsFncSeq;
	}
	public int getBbsSeq() {
		return bbsSeq;
	}
	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
	}
	public int getBbsCommentSeq() {
		return bbsCommentSeq;
	}
	public void setBbsCommentSeq(int bbsCommentSeq) {
		this.bbsCommentSeq = bbsCommentSeq;
	}
	public int getFncType() {
		return fncType;
	}
	public void setFncType(int fncType) {
		this.fncType = fncType;
	}
	public int getFncValue() {
		return fncValue;
	}
	public void setFncValue(int fncValue) {
		this.fncValue = fncValue;
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
