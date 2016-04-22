package kr.nomad.mars.dto;

public class Notice {
	int noticeSeq = 0;
	String userId = "";
	int notiType = 0;
	int sendPush = 0;
	String title = "";
	String contentsHtml = "";
	String contentsText = "";
	String linkUrl = "";
	String startDate = "";
	String endDate = "";
	int visible = 1;
	String files = "";
	int viewCount = 0;
	String regDate = "";
	int sort=0;
	int type=0;
	
	
	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}

	public static int OS_ANDROID = 21;
	public static int OS_IOS = 22;

	public int getNoticeSeq() {
		return noticeSeq;
	}

	
	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}


	public void setNoticeSeq(int noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getNotiType() {
		return notiType;
	}

	public void setNotiType(int notiType) {
		this.notiType = notiType;
	}

	public int getSendPush() {
		return sendPush;
	}

	public void setSendPush(int sendPush) {
		this.sendPush = sendPush;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentsHtml() {
		return contentsHtml;
	}

	public void setContentsHtml(String contentsHtml) {
		this.contentsHtml = contentsHtml;
	}

	public String getContentsText() {
		return contentsText;
	}

	public void setContentsText(String contentsText) {
		this.contentsText = contentsText;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
