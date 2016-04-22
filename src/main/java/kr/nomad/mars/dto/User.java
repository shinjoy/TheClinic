package kr.nomad.mars.dto;

import kr.nomad.util.T;

public class User {
	
	String userId = "";
	String password = "";
	String userType = "";
	String userName = "";
	String phoneNumber = "";
	int latitude = 0;
	int longitude = 0;
	String birthday = "";
	int gender = 0;
	String regDate = "";
	String lastLogindate = "";
	int loginFacebook = 0;
	int loginGoogle = 0;
	String osType = "";
	String osVersion = "";
	String appVersion = "";
	String deviceName = "";
	String deviceId = "";
	String pushkey = "";
	int usePushservice = 0;
	int status = 0;
	int loginStatus = 0;
	int userMed = 0;
	String photo="";
	String gendertxt ="";
	int userAge=0;
	public static int MAN = 1;
	public static int WOMAN = 2;
	String token="";
	String area ="";
	double height=0.0;
	double weight=0.0;
	String bloodGroups="";
	//view
	
	int missionSeq=0;
	int bbsSeq=0;
	
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getBbsSeq() {
		return bbsSeq;
	}

	public void setBbsSeq(int bbsSeq) {
		this.bbsSeq = bbsSeq;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getBloodGroups() {
		return bloodGroups;
	}

	public void setBloodGroups(String bloodGroups) {
		this.bloodGroups = bloodGroups;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getMissionSeq() {
		return missionSeq;
	}

	public void setMissionSeq(int missionSeq) {
		this.missionSeq = missionSeq;
	}

	public String getGenderText() {

		if (this.gender == MAN) {
			return "Man";
		} else if (this.gender == WOMAN) {
			return "Woman";
		} else {
			return "";
		}
	}

	public int getUserAge() {
		int thisYear = Integer.parseInt(T.getTodayYear());
		if (this.birthday == "") {
			return 0;
		} else {
			return thisYear - Integer.parseInt(this.birthday)  ;
		}
	}
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getLastLogindate() {
		return lastLogindate;
	}
	public void setLastLogindate(String lastLogindate) {
		this.lastLogindate = lastLogindate;
	}

	
	public int getLoginFacebook() {
		return loginFacebook;
	}

	public void setLoginFacebook(int loginFacebook) {
		this.loginFacebook = loginFacebook;
	}

	public int getLoginGoogle() {
		return loginGoogle;
	}

	public void setLoginGoogle(int loginGoogle) {
		this.loginGoogle = loginGoogle;
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
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	public int getUserMed() {
		return userMed;
	}
	public void setUserMed(int userMed) {
		this.userMed = userMed;
	}

	
	

}