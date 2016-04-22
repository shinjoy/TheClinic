package kr.nomad.mars;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kr.nomad.util.F;
import kr.nomad.util.FileWrite;
import kr.nomad.util.ImageUtil;
import kr.nomad.util.Response;
import kr.nomad.util.T;
import kr.nomad.util.file.UniqFileRenamePolicy;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.util.ImageUtils;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;

import kr.nomad.mars.dao.BbsCommentDao;
import kr.nomad.mars.dao.BbsDao;
import kr.nomad.mars.dao.BbsFncDao;
import kr.nomad.mars.dao.DataDao;
import kr.nomad.mars.dao.FoodDao;
import kr.nomad.mars.dao.MissionCommentDao;
import kr.nomad.mars.dao.MissionDao;
import kr.nomad.mars.dao.MissionUserDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dao.UserAllergyDao;
import kr.nomad.mars.dao.UserDao;
import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.BbsComment;
import kr.nomad.mars.dto.BbsFnc;
import kr.nomad.mars.dto.Data;
import kr.nomad.mars.dto.Food;
import kr.nomad.mars.dto.Mission;
import kr.nomad.mars.dto.MissionComment;
import kr.nomad.mars.dto.MissionUser;
import kr.nomad.mars.dto.Push;
import kr.nomad.mars.dto.User;
import kr.nomad.mars.dto.UserAllergy;
import kr.nomad.mars.dto.Vfood;


@Controller
public class SeverController {
	
	private static final Logger logger = LoggerFactory.getLogger(SeverController.class);
	
	@Autowired private BbsCommentDao bbsCommentDao;
	@Autowired private BbsDao bbsDao;
	@Autowired private BbsFncDao bbsFncDao;
	@Autowired private UserDao userDao;
	@Autowired private MissionCommentDao missionCommentDao;
	@Autowired private MissionDao missionDao;
	@Autowired private MissionUserDao missionUserDao;
	@Autowired private FoodDao foodDao;
	@Autowired private DataDao dataDao;
	@Autowired private UserAllergyDao userAllergyDao;
	@Autowired private PushDao pushDao;
	// 페이지당 아이템 갯수
	@Value("#{config['page.itemCountPerPage']}")
	int ITEM_COUNT_PER_PAGE;

	// 페이징당 페이지 갯수
	@Value("#{config['page.pageCountPerPaging']}")
	int PAGE_COUNT_PER_PAGING;

	// 파일 루트
	@Value("#{config['file.root']}")
	String FILE_ROOT;

	String FILE_PATH = "";
	String FILE_LOCAL_PATH = "";

	// 파일 최대크기(Mb)
	@Value("#{config['file.maxSize']}")
	int FILE_MAX_SIZE;
	
	
	@RequestMapping("/api_view.go")
	public String ApiViewController(
	        Model model
	    ) {
	    return "/api_view";
	}
	
	//회원가입
	@RequestMapping("/m/join.go")
	public String joinController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="userName", required=false, defaultValue="") String userName,
			@RequestParam(value="birthday", required=false, defaultValue="") String birthday,
			@RequestParam(value="phoneNumber", required=false, defaultValue="0") String phoneNumber,
			@RequestParam(value="gender", required=false, defaultValue="0") int gender,
			@RequestParam(value="osType", required=false, defaultValue="") String osType,
			@RequestParam(value="deviceName", required=false, defaultValue="") String deviceName,
			@RequestParam(value="deviceId", required=false, defaultValue="") String deviceId,
			@RequestParam(value="pushKey", required=false, defaultValue="") String pushKey,
			@RequestParam(value="osVersion", required=false, defaultValue="") String osVersion,
			@RequestParam(value="appVersion", required=false, defaultValue="") String appVersion,
			@RequestParam(value="loginFacebook", required=false, defaultValue="1") int loginFacebook,
			@RequestParam(value="usePushservice", required=false, defaultValue="0") int usePushservice,
			@RequestParam(value="token", required=false, defaultValue="") String token,
			HttpServletResponse res
		) {
		Map<String, Object> map = new HashMap<String, Object>();
		String nowYear = T.getTodayYear();
		int chk = userDao.getUserchk(userId,false);
		int birthyear=Integer.parseInt(nowYear)-Integer.parseInt(birthday);	
		if(chk==0){
				User uu = new User();
				uu.setUserId(userId);
				uu.setUserName(userName);
				uu.setGender(gender);
				uu.setBirthday(Integer.toString(birthyear));
				uu.setOsType(osType);
				uu.setDeviceName(deviceName);
				uu.setDeviceId(deviceId);
				uu.setPushkey(pushKey);
				uu.setUsePushservice(usePushservice);
				uu.setOsVersion(osVersion);
				uu.setAppVersion(appVersion);
				uu.setUserType("3");
				uu.setStatus(1);
				uu.setPhoneNumber(phoneNumber);
				uu.setLoginFacebook(loginFacebook);
				uu.setToken(token);
				userDao.addUser(uu);
				map.put("result", true);
			//	map.put("User", userDao.getUser(userId));
				map.put("message", "complete");
		}else{
				
				map.put("result", false);
				map.put("message", "ID is being used");
		}


		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//프로필 이미지 수정 
	@RequestMapping("/m/user_profile.go")
	public String userProfileController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="photo", required=false, defaultValue="") String photo,
			@RequestParam(value="thumb", required=false, defaultValue="") String thumb,
			HttpServletResponse res
		) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		int chk = userDao.getUserchk(userId,false);
		try{
			if(chk!=0){
					User uu = userDao.getUser(userId);
					
					if(!uu.getPhoto().equals("")){
						filedelete(uu.getPhoto());
					}
					
					String photoString="";
					if(!photo.equals("")){
						photoString=fileJson(photo,thumb);
					}
					uu.setPhoto(photoString);
					userDao.updateUserphoto(uu);
					map.put("result", true);
					map.put("User", userDao.getUser(userId));
					map.put("userId", userId);
					map.put("message", "complete");
			}else{
					
					map.put("result", false);
					map.put("message","A non-registered users");
			}
		}catch(Exception e){
			map.put("result", false);
			map.put("message",e.getMessage());
		}


		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	

	//기초문진 등록 /수정 
	@RequestMapping("/m/user_info.go")
	public String userMedController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="userName", required=false, defaultValue="") String userName,
			@RequestParam(value="birthday", required=false, defaultValue="") String birthday,
			@RequestParam(value="area", required=false, defaultValue="") String area,
			@RequestParam(value="gender", required=false, defaultValue="0") int gender,
			@RequestParam(value="height", required=false, defaultValue="") Double height,
			@RequestParam(value="weight", required=false, defaultValue="") Double weight,
			@RequestParam(value="bloodGroups", required=false, defaultValue="") String bloodGroups,
		
			@RequestParam(value="usePushservice", required=false, defaultValue="0") int usePushservice,
			
			HttpServletResponse res
		) {
		Map<String, Object> map = new HashMap<String, Object>();
		String nowYear = T.getTodayYear();
		int chk = userDao.getUserchk(userId,false);
		int birthyear=Integer.parseInt(nowYear)-Integer.parseInt(birthday);	
		if(chk!=0){
				User uu = userDao.getUser(userId);
				
				uu.setUserName(userName);
				uu.setGender(gender);
				uu.setBirthday(Integer.toString(birthyear));
				uu.setHeight(height);
				uu.setWeight(weight);
				uu.setArea(area);
				uu.setBloodGroups(bloodGroups);
			
				uu.setUsePushservice(usePushservice);
				userDao.updateUser(uu);
				map.put("result", true);
				map.put("User", userDao.getUser(userId));
				map.put("userId", userId);
				map.put("message", "complete");
		}else{
				
				map.put("result", false);
				map.put("message","A non-registered users");
		}


		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	
	// 알러지 문진 등록 /수정
		@RequestMapping("/m/user_allergy.go")
		public String AllergyController(
			UserAllergy ua ,
			HttpServletResponse res) {
			Map<String, Object> map = new HashMap<String, Object>();
		
			
			try{
				int chk = userAllergyDao.getUserAllergyChk(ua.getUserId());
				
				
				if(chk==0){//등록
					
					userAllergyDao.addUserAllergy(ua);
					
				
					map.put("result",true);
					map.put("message","complete");
				}else{//수정
					userAllergyDao.updateUserAllergy(ua);
					map.put("result",true);
					map.put("message","complete");
				
				}
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
		

			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
	//////////////////////////////////

	/*	
		@RequestMapping("/user_login_facebook.go")
		public String userRegistFacebookController(
				@RequestParam(value="userId", required=false, defaultValue="") String userId,
				@RequestParam(value="password", required=false, defaultValue="") String password,
				@RequestParam(value="userType", required=false, defaultValue="9") int userType,
				@RequestParam(value="userName", required=false, defaultValue="") String userName,
				@RequestParam(value="email", required=false, defaultValue="") String email,
				@RequestParam(value="countryCode", required=false, defaultValue="") String countryCode,
				@RequestParam(value="phoneNumber", required=false, defaultValue="") String phoneNumber,
				@RequestParam(value="imgFilename", required=false, defaultValue="") String imgFilename,
				@RequestParam(value="thumbImgFilename", required=false, defaultValue="") String thumbImgFilename,
				@RequestParam(value="imgRegDate", required=false, defaultValue="") String imgRegDate,
				@RequestParam(value="imgPath", required=false, defaultValue="") String imgPath,
				@RequestParam(value="thumbImgPath", required=false, defaultValue="") String thumbImgPath,
				@RequestParam(value="intro", required=false, defaultValue="") String intro,
				@RequestParam(value="address", required=false, defaultValue="") String address,
				@RequestParam(value="latitude", required=false, defaultValue="0") float latitude,
				@RequestParam(value="longitude", required=false, defaultValue="0") float longitude,
				@RequestParam(value="sex", required=false, defaultValue="1") int sex,
				@RequestParam(value="birthday", required=false, defaultValue="") String birthday,
				@RequestParam(value="regDate", required=false, defaultValue="") String regDate,
				@RequestParam(value="lastLogindate", required=false, defaultValue="") String lastLogindate,
				@RequestParam(value="loginFacebook", required=false, defaultValue="0") int loginFacebook,
				@RequestParam(value="loginTwitter", required=false, defaultValue="0") int loginTwitter,
				@RequestParam(value="osType", required=false, defaultValue="") String osType,
				@RequestParam(value="osVersion", required=false, defaultValue="") String osVersion,
				@RequestParam(value="appVersion", required=false, defaultValue="") String appVersion,
				@RequestParam(value="deviceName", required=false, defaultValue="") String deviceName,
				@RequestParam(value="deviceId", required=false, defaultValue="") String deviceId,
				@RequestParam(value="pushkey", required=false, defaultValue="") String pushkey,
				@RequestParam(value="usePushservice", required=false, defaultValue="0") int usePushservice,
				@RequestParam(value="status", required=false, defaultValue="0") int status,
				@RequestParam(value="loginStatus", required=false, defaultValue="0") int loginStatus,
				@RequestParam(value="access_tocken", required=false, defaultValue="") String access_tocken,
				
				HttpSession session, 
				HttpServletResponse res
			) {

			Map<String, Object> map = new HashMap<String, Object>();	
			
			//세션 확인.
			boolean isAuth = false;
			try {			
				final String access_token = "192175840946630|DbwTHTke72U7_g29CwjFcQ6JQPo";
				final String urlStr = "https://graph.facebook.com/debug_token?"
						+ "input_token=" + URLEncoder.encode(access_tocken, "UTF-8")
						+ "&access_token=" + access_token;
		
				HttpURLConnection conn;
				BufferedReader rd;
				String line;
				String result = "";
				try {
					URL url = new URL(urlStr);
				    conn = (HttpURLConnection) url.openConnection();
				    conn.setRequestMethod("GET");
				    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				    while ((line = rd.readLine()) != null) {
				    	result += line;
				    }
				    rd.close();
				    
				    int index = result.indexOf("is_valid");
	                if (index > 0)
	                {
	                    String resultTxt = result.substring(index + 10, index + 14);
	                    if (resultTxt.equals("true"))
	                    {
	                        isAuth = true;
	                    }
	                }
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			if(isAuth) {
				boolean isUsedId = userDao.correctId(userId);
				User user = new User();
		
				if (isUsedId == false) {
					
					password = Sha256Enc.getRandomKey(6);
					
					user.setUserId(userId);
					user.setPassword(password);
					user.setUserType(userType);
					user.setUserName(userName);
					user.setEmail(email);
					user.setCountryCode(countryCode);
					user.setPhoneNumber(phoneNumber);
					user.setImgFilename(imgFilename);
					user.setThumbImgFilename(thumbImgFilename);
					if(imgRegDate.equals("")) {
						user.setImgRegDate(null);
					} else {
						user.setImgRegDate(imgRegDate);	
					}
					
					user.setImgPath(imgPath);
					user.setThumbImgPath(thumbImgPath);
					user.setIntro(intro);
					user.setAddress(address);
					user.setLatitude(latitude);
					user.setLongitude(longitude);
					user.setSex(sex);
					if(imgRegDate.equals("")) {
						user.setBirthday(null);
					} else {
						user.setBirthday(birthday);
					}
					
					user.setRegDate(regDate);
					user.setLastLogindate(lastLogindate);
					user.setLoginFacebook(loginFacebook);
					user.setLoginTwitter(loginTwitter);
					user.setOsType(osType);
					user.setOsVersion(osVersion);
					user.setAppVersion(appVersion);
					user.setDeviceName(deviceName);
					user.setDeviceId(deviceId);
					user.setPushkey(pushkey);
					user.setUsePushservice(usePushservice);
					user.setStatus(status);
					user.setLoginStatus(loginStatus);
					userDao.addUser(user);
			
					map.put("password", password);
					map.put("result", true);
					map.put("message", "회원가입되었습니다.");
				} else {
					
					password = Sha256Enc.getRandomKey(6);
					
					userDao.updatePassword(userId, password);
					userDao.updateUserPush(userId, pushkey, osType, osVersion
							, deviceName, deviceId, appVersion);
					
					map.put("password", password);
					map.put("result", true);
					map.put("message", "회원가입되었습니다.");
				}
				map.put("user", user);
			}
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		}
		
			*/
		
		
	////////////////////////////////	
	
	//로그인
	@RequestMapping("/m/login.go")
	public String LoginController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="token", required=false, defaultValue="") String token,
			@RequestParam(value="osType", required=false, defaultValue="") String osType,
			@RequestParam(value="phoneNumber", required=false, defaultValue="") String phoneNumber,
			@RequestParam(value="deviceName", required=false, defaultValue="") String deviceName,
			@RequestParam(value="deviceId", required=false, defaultValue="") String deviceId,
			@RequestParam(value="pushKey", required=false, defaultValue="") String pushKey,
		
			
			HttpServletResponse res
		) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		String id = "";
		//세션 확인.
		boolean isAuth = false;
		try {			
			
			final String urlStr = "https://graph.facebook.com/me?"
					//+ "input_token=" + URLEncoder.encode(phoneNumber, "UTF-8")
					+ "access_token="+token ;
	
			HttpURLConnection conn;
			BufferedReader rd;
			String line;
			String result = "";
			
			try {
				URL url = new URL(urlStr);
			    conn = (HttpURLConnection) url.openConnection();
			    conn.setRequestMethod("GET");
			    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    while ((line = rd.readLine()) != null) {
			    	result += line;
			    }
			    rd.close();
			 
			    JSONObject jsonObj = JSONObject.fromObject(result);
				
				id=jsonObj.get("id").toString();
				  int cnt =0;
			if(id.equals(phoneNumber)){
				 cnt = userDao.getUserId(id);
			}
			 if(cnt>0){
				  isAuth=true;
			 }
			    
			  
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		if(isAuth){
			User uu = new User();
			uu.setOsType(osType);
			uu.setDeviceName(deviceName);
			uu.setDeviceId(deviceId);
			uu.setPushkey(pushKey);
			uu.setUserId(userId);
			uu.setPhoneNumber(id);
			uu.setToken(token);
			userDao.updateUserLogin(uu,1);
			map.put("result", true);
			map.put("User", userDao.getUser(userId));
			map.put("message", "complete");
			
		}else{
			map.put("result", false);
			map.put("message", "A non-registered users ");
		}
		
		


		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	//로그아웃
	@RequestMapping("/m/logout.go")
	public String LogoutController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
		
			
			HttpServletResponse res
		) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int chk = userDao.getUserchk(userId,true);
		
		if(chk>0){
			User uu = new User();
			uu.setOsType("");
			uu.setDeviceName("");
			uu.setDeviceId("");
			uu.setPushkey("");
			uu.setUserId(userId);
			userDao.updateUserLogin(uu,0);
			map.put("result", true);
			map.put("message", "complete");
		}else{
			
			map.put("result", false);
			map.put("message", "A non-registered users ");
		}


		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}	

	// 탈퇴
	@RequestMapping("/m/drop.go")
	public String DropController(
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			int chk = userDao.getUserchk(userId, true);
	
			if (chk > 0) {
		
				
				
				 List<Bbs>list = bbsDao.getBbsMyList(userId);
				 for(Bbs bbs : list){
					 if(!bbs.getFiles().equals("")){
							filedelete(bbs.getFiles());
					}
					int bbsSeq= bbs.getBbsSeq();
					List<BbsComment>commentlist = bbsCommentDao.getBbsCommentListAll(bbsSeq);
					for(BbsComment bc : commentlist){
						if(!bc.getFiles().equals("")){
							filedelete(bc.getFiles());
						}
						
					}
					bbsFncDao.deleteBbsGood(bbsSeq);
					bbsCommentDao.deleteBbsCommentAll(bbsSeq);
					bbsDao.deleteBbs(bbsSeq);
				 }
				 
			   List<BbsComment>commentlist = bbsCommentDao.getBbsCommentListAllUser(userId);
				 for(BbsComment bc : commentlist){
					if(!bc.getFiles().equals("")){
						filedelete(bc.getFiles());
					}
					List<BbsComment>commentlist2 = bbsCommentDao.getBbsCommentListComment(bc.getBbsCommentSeq());
					 for(BbsComment bc2 : commentlist2){
							if(!bc2.getFiles().equals("")){
								filedelete(bc2.getFiles());
							}
					 }
					 bbsCommentDao.deleteRBbsComment(bc.getBbsCommentSeq());
					 bbsCommentDao.deleteBbsComment(bc.getBbsCommentSeq());
						
				}
				 bbsCommentDao.deleteBbsCommentId(userId);
				 
				 List<MissionComment>missionlist =missionCommentDao.getMissionCommentListMy(userId);
				 for(MissionComment mc : missionlist){
						if(!mc.getFiles().equals("")){
							filedelete(mc.getFiles());
						}
						List<MissionComment>commentlist2 = missionCommentDao.getMissionRCommentListComment(mc.getMissionCommentSeq());
						 for(MissionComment mc2 : commentlist2){
								if(!mc2.getFiles().equals("")){
									filedelete(mc2.getFiles());
								}
						 }
						missionCommentDao.deleteMissionComment(mc.getMissionCommentSeq());
						missionCommentDao.deleteRMissionComment(mc.getMissionCommentSeq());	
				}
				 missionCommentDao.deleteMissionCommentId(userId);
				 missionUserDao.deleteMissiondropUser(userId);
				 
			
				 
				 //문진정보  
				userAllergyDao.deleteUserAllergy(userId);
					
				userDao.deleteUser(userId);
				
				map.put("result", true);
				map.put("message", "complete");
			} else {
	
				map.put("result", false);
				map.put("message", "A non-registered users ");
			}
		}catch(Exception e){
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	// 회원상세보기
	@RequestMapping("/m/user_view.go")
	public String userViewController(
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
		HttpSession session, Model model,HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int chk = userDao.getUserchk(userId, true);
		if(chk>0){
			User user = userDao.getUser(userId);
			map.put("user", user);
			map.put("result", true);
			map.put("message", "complete");
		}else{
			map.put("result", false);
			map.put("message", "A non-registered users ");
		}
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}



	// 프로필 이미지등록
	@RequestMapping("/m/photo_upload.go")
	public String proUploadController(
			HttpServletRequest req, HttpServletResponse res, Model model
	) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		

		String FILE_PATH = "/files/temp/";
		String FILE_LOCAL_PATH = FILE_ROOT + FILE_PATH;
		//String userId = "";
		String photo = "";
		String fileName = "";
		String photoPre = "";
		String path = "";
		String repath ="";
		String org = "";
		
		int fileSize = FILE_MAX_SIZE * 1024 * 1024;

		try {
			req.setCharacterEncoding("utf-8");

			File file = null;
			try {
				MultipartRequest multi = new MultipartRequest(req, FILE_LOCAL_PATH, fileSize, "UTF-8", new UniqFileRenamePolicy());

				// 폼에서 입력한 값을 가져옴
				String userId="";
				path = F.nullCheck(multi.getParameter("path"), "");
				int isThumb = Integer.parseInt(F.nullCheck(multi.getParameter("isThumb"), "0"));
				int addThumb = Integer.parseInt(F.nullCheck(multi.getParameter("addThumb"), "0"));
				if(path.equals("profile")){
					userId = F.nullCheck(multi.getParameter("userId"), "");
				}
				Enumeration files = multi.getFileNames();
				while (files.hasMoreElements()) {
					String elementName = (String) files.nextElement();
					file = multi.getFile(elementName);
					if (file != null) {
						org = file.getName();
						photo =org;
						if(path.equals("profile")){
							String hwak = photo.substring(photo.lastIndexOf("."));
							photo = userId+hwak;
							
							File oriFile = new File(FILE_LOCAL_PATH + org);
							File profileFile = new File(FILE_LOCAL_PATH + photo);
							oriFile.renameTo(profileFile);
						}
					}
				}
				
				fileName = photo;
				String fullpath ="";
				
				if (isThumb == 1) {
					if(path.equals("profile")){
						fullpath=FILE_ROOT +"/files/"+ path +"/thumb";
						repath =  "/files/"+ path+"/thumb/" ;
					}else{
						String yearMonth = T.getTodayYear()+T.getTodayMonth();
						
						photoPre = yearMonth;
						fullpath =FILE_ROOT+"/files/"+ path +"/"+photoPre +"/thumb";
						repath = "/files/"+ path +"/"+photoPre +"/thumb/";
					}
				} else {
					if(path.equals("profile")){
						fullpath=FILE_ROOT +"/files/"+  path;
						repath =  "/files/"+ path+"/" ;
					}else{
						String yearMonth = T.getTodayYear()+T.getTodayMonth();
						
						photoPre = yearMonth;
						fullpath =FILE_ROOT +"/files/"+ path +"/"+photoPre;
						repath = "/files/"+ path +"/"+photoPre+"/";
					}
				}
				
				String FILE_RESIZE_PATH = FILE_ROOT +repath;
			
				if(!path.equals("profile")){
					File copyFolder = new File(fullpath);
					if (!copyFolder.exists()) {
						copyFolder.mkdirs();
					}
				}
				
				// 파일 복사
				FileInputStream fis = new FileInputStream(FILE_LOCAL_PATH + fileName);
				FileOutputStream fos = new FileOutputStream(fullpath+"/"+fileName);
				int data = 0;
				while((data=fis.read())!=-1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
				
				if (addThumb == 1) {
					// 축소이미지 저장
					File newFile = new File(FILE_LOCAL_PATH + fileName);
					File nomalFile = new File(FILE_RESIZE_PATH+fileName);
					if (!nomalFile.exists()) {
						nomalFile.mkdirs();
					}
					try {
						ImageUtil.resize(newFile, nomalFile, 720, 0, 0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// 썸네일이미지 저장
					File thumbFile = new File(FILE_RESIZE_PATH+"thumb/"+fileName);
					if (!thumbFile.exists()) {
						thumbFile.mkdirs();
					}
					try {
						ImageUtil.resize(newFile, thumbFile, 200, 0, 0);
						result = true;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				//복사한뒤 원본파일을 삭제함
				file.delete();
				map.put("photo", fileName);
				map.put("path", repath);
				map.put("result", true);
				map.put("message", "사진이 등록되었습니다.");
			} catch (Exception e) {
				e.getMessage();
			}
			
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", "fail! \n"+e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);

		Gson gson = new Gson();
		String outputstr = gson.toJson(jsonObject);
		Response.responseWrite(res, outputstr);

		return null;

	}
	// 파일삭제
		@RequestMapping("/m/file_delete.go")
		public String file_deleteController(
				@RequestParam(value = "file", required = false, defaultValue = "") String file,
				Model model, HttpServletResponse res) {

			Map<String, Object> map = new HashMap<String, Object>();
			
			filedelNow(file);
			
			map.put("result", true);
			
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);
			
			return null;
		}
	
	//다이어리 
	@RequestMapping("/m/bbs_list.go")
	public String BbsListController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="goodCount", required=false, defaultValue="1") int goodCount,
			HttpServletResponse res
		) {
		
		List<Bbs> bbsList = null;
		int bbsCount = 0;
    	

		bbsList = bbsDao.getBbsList("","","","",0,goodCount,page, ITEM_COUNT_PER_PAGE);
		bbsCount = bbsDao.getBbsCount("","","","",0,goodCount);
		for(int i=0;i<bbsList.size();i++){
			Bbs bbs= bbsList.get(i);
			int chk= bbsFncDao.getBBsCount(bbs.getBbsSeq(),userId,BbsFnc.FNC_TYPE_LIKE);
			if(chk>0){//좋아요 상태면
				bbs.setGoodStatus(1);
			}
		
			bbsList.set(i, bbs);
			
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("bbsList", bbsList);
		map.put("currentPage", page);
		map.put("itemCount", bbsCount);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	//다이어리 상세
	@RequestMapping("/m/bbs_view.go")
	public String BbsViewController(
			@RequestParam(value="userId", required=false, defaultValue="") String userId,
			@RequestParam(value="page", required=false, defaultValue="1") int page,
			@RequestParam(value="bbsSeq", required=false, defaultValue="0") int bbsSeq,
			HttpServletResponse res
		) {
		
		
		Bbs bbs= bbsDao.getBbs(bbsSeq);
		List commentList = bbsCommentDao.getBbsCommentList(bbs.getBbsSeq(),0,page,ITEM_COUNT_PER_PAGE);
		int count = bbsCommentDao.getBbsCommentCount(bbsSeq,0);
		bbs.setCommentList(commentList);
	
		int chk= bbsFncDao.getBBsCount(bbs.getBbsSeq(),userId,BbsFnc.FNC_TYPE_LIKE);
		if(chk>0){//좋아요 상태면
			bbs.setGoodStatus(1);
		}
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("bbs", bbs);
		map.put("currentPage", page);
		map.put("itemCount", count);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 다이어리 댓글 상세
	@RequestMapping("/m/bbs_comment_view.go")
	public String BbsCommentController(
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			//@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq, 
			@RequestParam(value="bbsCommentSeq", required=false, defaultValue="0") int bbsCommentSeq,
			HttpServletResponse res) {

		BbsComment bc = bbsCommentDao.getBbsComment(bbsCommentSeq);
		List commentList = bbsCommentDao.getBbsCommentList(bc.getBbsSeq(),bbsCommentSeq, page, ITEM_COUNT_PER_PAGE);
		int count = bbsCommentDao.getBbsCommentCount(bc.getBbsSeq(), bbsCommentSeq);
		bc.setCommentList(commentList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("bc", bc);
		map.put("currentPage", page);
		map.put("itemCount", count);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	// 다이어리 쓰기 수정
	@RequestMapping("/m/bbs_edit.go")
	public String BbsEditController(
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq, 
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId, 
			@RequestParam(value="bbsHeader", required=false, defaultValue="") String bbsHeader,
			@RequestParam(value="bbsTitle", required=false, defaultValue="") String bbsTitle,
			@RequestParam(value="contents", required=false, defaultValue="") String contents,
			@RequestParam(value="photo", required=false, defaultValue="") String photo,
			@RequestParam(value="thumb", required=false, defaultValue="") String thumb,
			@RequestParam(value="linkUrl", required=false, defaultValue="1") String linkUrl,
	
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result =false;
		String message ="";
		try{
			FILE_LOCAL_PATH = FILE_ROOT ;
			String [] photoArr = photo.split(",");
			String photoString="";
			if(photoArr.length>0){
				photoString = fileJson(photo, thumb);
			}
			
			Bbs bbs=null;
			String beforefile="";
			int beforecount =0;
			if(bbsSeq <1){//등록
				bbs = new Bbs();
				bbs.setUserId(userId);
				bbs.setBbsContents(contents);
				bbs.setBbsTitle(bbsTitle);
				bbs.setLinkUrl(linkUrl);
				bbs.setBbsHeader(bbsHeader);
				bbs.setFiles(photoString);
				int count=0;
				if(!photoString.equals("")){
					count=photoArr.length-1;
				}
				bbs.setFileCount(count);
				bbsDao.addBbs(bbs);
				result=true;
			
			}else{
				bbs=bbsDao.getBbs(bbsSeq);
				beforefile =bbs.getFiles();
				beforecount=bbs.getFileCount();
				bbs.setUserId(userId);
				bbs.setBbsContents(contents);
				bbs.setBbsTitle(bbsTitle);
				bbs.setLinkUrl(linkUrl);
				bbs.setBbsHeader(bbsHeader);
			/*	if(!beforefile.equals(photoString)){
					 String beforefiles = beforefile;
					 String newfiles = photoString;
					 JSONObject fileObj = JSONObject.fromObject(beforefiles);
					 
					
					if(!beforefile.equals("")){
						bbsfiledelete(beforefile,photoString);
					}
				}*/
				bbs.setFiles(photoString);
				int count=0;
				if(!photoString.equals("")){
					count=photoArr.length-1;
				}
				bbs.setFileCount(count);
				bbsDao.updateBbs(bbs);
				result=true;
			
			}
			
	
			map.put("result", result);
			map.put("message", "complete");
		}catch(Exception e){
			map.put("result", result);
			map.put("message", e.getMessage());
		}
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	// 다이어리 댓글 쓰기 수정
		@RequestMapping("/m/bbs_comment_edit.go")
		public String BbsCommentEditController(
				@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq, 
				@RequestParam(value = "bbsCommentSeq", required = false, defaultValue = "0") int bbsCommentSeq, 
				@RequestParam(value = "userId", required = false, defaultValue = "") String userId, 
				@RequestParam(value="bbsHeader", required=false, defaultValue="") String bbsHeader,
				@RequestParam(value="bbsTitle", required=false, defaultValue="") String bbsTitle,
				@RequestParam(value="contents", required=false, defaultValue="") String bbsContents,
				@RequestParam(value="photo", required=false, defaultValue="") String photo,
				@RequestParam(value="thumb", required=false, defaultValue="") String thumb,
				@RequestParam(value="linkUrl", required=false, defaultValue="") String linkUrl,
				@RequestParam(value="rlevel", required=false, defaultValue="0") int rlevel,//댓글 0 댓글의댓글 1 이상
				@RequestParam(value="rCommentSeq", required=false, defaultValue="0") int rCommentSeq,
				HttpServletResponse res) {

			Map<String, Object> map = new HashMap<String, Object>();
			boolean result =false;
			String message ="";
			try{
				FILE_LOCAL_PATH = FILE_ROOT ;
				
				String [] photoArr = photo.split(",");
				String photoString="";
				if(photoArr.length>0){
					photoString = fileJson(photo, thumb);
				}
				Bbs bbs = bbsDao.getBbs(bbsSeq);
				if(bbs!=null){
					BbsComment bc=null;
					String beforefile="";
					int beforecount =0;
					if(bbsCommentSeq <1){//등록
						bc = new BbsComment();
						bc.setUserId(userId);
						bc.setBbsContents(bbsContents);
						bc.setBbsTitle(bbsTitle);
						bc.setLinkUrl(linkUrl);
						bc.setBbsHeader(bbsHeader);
						bc.setFiles(photoString);
						int count=0;
						if(!photoString.equals("")){
							count=photoArr.length-1;
						}
						bc.setFileCount(count);
						bc.setRLevel(rlevel);
						bc.setBbsSeq(bbsSeq);
						bc.setRCommentSeq(rCommentSeq);
						bbsCommentDao.addBbsComment(bc);
						result=true;
						String txt=txtLength(bbs.getBbsContents());
						List<User>list = userDao.getBbsPushList(bbsSeq);
						for(User user:list){
							String userdd=user.getUserId(); 
							if(!user.getUserId().equals(userId)){
								push(user.getUserId(), bbsSeq, "New Comment Diary :"+txt, Push.MSG_TYPE_SEND_DIARY_COMMENT, userId);
						
							}
						}
					
					
					}else{
						bc=bbsCommentDao.getBbsComment(bbsCommentSeq);
						beforefile =bc.getFiles();
						beforecount=bc.getFileCount();
						bc.setUserId(userId);
						bc.setBbsContents(bbsContents);
						bc.setBbsTitle(bbsTitle);
						bc.setLinkUrl(linkUrl);
						bc.setBbsHeader(bbsHeader);
						bc.setFiles(photoString);
						int count=0;
						if(!photoString.equals("")){
							count=photoArr.length-1;
						}
						bc.setFileCount(count);
						bc.setRLevel(rlevel);
						bc.setBbsSeq(bbsSeq);
						bc.setRCommentSeq(rCommentSeq);
						bbsCommentDao.updateBbsComment(bc);
						result=true;
				
					}
					if(!beforefile.equals(photoString)){
						if(!beforefile.equals("")){
							filedelete(beforefile);
						}
					}
					map.put("result", result);
					map.put("message", "complete");
				}else{
					map.put("result", result);
					map.put("message", "Can not search Diary!");
				}
			}catch(Exception e){
				map.put("result", result);
				map.put("message", e.getMessage());
			}
		
			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, jsonObject);

			return null;
		}
		
	// 다이어리 삭제
	@RequestMapping("/m/bbs_delete.go")
	public String bbsdelController(Model model, HttpServletResponse res, HttpSession session,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq,
			@RequestParam(value = "bbsCommentSeq", required = false, defaultValue = "0") int bbsCommentSeq
			) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (bbsCommentSeq == 0) { // 본글 삭제

				Bbs bbs = bbsDao.getBbs(bbsSeq);
				if (bbs.getFileCount() != 0) {
					String files = bbs.getFiles();
					filedelete(files);
				}
				// 댓글 전체 가져오기
				List<BbsComment> list = bbsCommentDao.getBbsCommentListAll(bbsSeq);

				for (int i = 0; i < list.size(); i++) {
					BbsComment bc = list.get(i);
					if (bc.getFileCount() != 0) {
						filedelete(bc.getFiles());
					}

				}
				bbsDao.deleteBbs(bbsSeq);
				bbsCommentDao.deleteBbsCommentAll(bbsSeq);
				map.put("type", 1);
			} else {// 댓글 삭제
				BbsComment bbsc = bbsCommentDao.getBbsComment(bbsCommentSeq);
				if (bbsc.getFileCount() != 0) {
					filedelete(bbsc.getFiles());
				}
				List<BbsComment> comment = bbsCommentDao.getBbsCommentListComment(bbsCommentSeq);
				for (int i = 0; i < comment.size(); i++) {
					BbsComment bcomment = comment.get(i);
					if (bcomment.getFileCount() != 0) {
						filedelete(bcomment.getFiles());
					}
				}

				bbsCommentDao.deleteBbsComment(bbsCommentSeq);
				bbsCommentDao.deleteRBbsComment(bbsCommentSeq);
				map.put("type", 2);

			}

			map.put("message", "complete");
			map.put("result", true);

		} catch (Exception e) {

			map.put("result", false);
			map.put("message", e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}

	// 좋아요
	@RequestMapping("/m/bbs_good.go")
	public String bbsdelController(Model model, HttpServletResponse res, HttpSession session,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "bbsSeq", required = false, defaultValue = "0") int bbsSeq
		) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			int chk=bbsFncDao.getBBsCount(bbsSeq, userId, BbsFnc.FNC_TYPE_LIKE);
			if(chk==0){
				BbsFnc bf = new BbsFnc();
				bf.setBbsSeq(bbsSeq);
				bf.setFncType(BbsFnc.FNC_TYPE_LIKE);
				bf.setUserId(userId);
				bbsFncDao.addBbsFnc(bf);
				map.put("message", "complete");
				map.put("result", true);
			}else{
				map.put("message", "You are already registered");
				map.put("result", false);
			}
		
			

		} catch (Exception e) {

			map.put("result", false);
			map.put("message", e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
	
	

	// 혈액형 정보화면
	@RequestMapping("/m/main_list.go")
	public String MainListController(
			@RequestParam(value = "benefitpage", required = false, defaultValue = "1") int benefitpage,
			@RequestParam(value = "neutralpage", required = false, defaultValue = "1") int neutralpage,
			@RequestParam(value = "toxicpage", required = false, defaultValue = "1") int toxicpage,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Mission> missionList = new ArrayList<>();
		List<Food> benefitList=new ArrayList<>();
		List<Food> neutralList=new ArrayList<>();
		List<Food> toxicList=new ArrayList<>();
		Data data = new Data();
	
		int benefitCount=0;
		int neutralCount=0;
		int toxicCount=0;
		
		try{
			User user= userDao.getUser(userId);
			String bloodtype=user.getBloodGroups();
			missionList = missionDao.getMissionListAll();
			
			if(!bloodtype.equals("")){
				
				benefitList= foodDao.getFoodList(bloodtype,1,benefitpage, ITEM_COUNT_PER_PAGE);
				benefitCount=foodDao.getFoodCount(bloodtype, 1);
				neutralList= foodDao.getFoodList(bloodtype,2,neutralpage, ITEM_COUNT_PER_PAGE);
				neutralCount=foodDao.getFoodCount(bloodtype, 2);
				toxicList= foodDao.getFoodList(bloodtype,3,toxicpage, ITEM_COUNT_PER_PAGE);
				toxicCount=foodDao.getFoodCount(bloodtype, 3);
				data = dataDao.getData(bloodtype);
				map.put("result",true);
			}else{
				map.put("result",false);
				map.put("message","We do not have your basic information. Please enter a basic information.");
			}
		}catch(Exception e){
			map.put("result",false);
			map.put("message",e.getMessage());
		}
	
		map.put("data", data);
		map.put("missionList", missionList);
		map.put("benefitList", benefitList);
		map.put("neutralList", neutralList);
		map.put("toxicList", toxicList);
		
		map.put("benefitpage", benefitpage);
		map.put("neutralpage", neutralpage);
		map.put("toxicpage", toxicpage);
	
		map.put("benefitCount", benefitCount);
		map.put("neutralCount", neutralCount);
		map.put("toxicCount", toxicCount);
		
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;

	}
	
	// 음식 검색 화면 
	@RequestMapping("/m/search_food.go")
	public String MainListController(

			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "foodName", required = false, defaultValue = "") String foodName,
			HttpServletResponse res) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Vfood> List=new ArrayList<>();
		int count=0;
		try{
			User user= userDao.getUser(userId);
			String bloodtype=user.getBloodGroups();
		
			
			if(!bloodtype.equals("")){
				
				List= foodDao.getFoodSearchList(bloodtype,foodName);
				//count = foodDao.getFoodSearchCount(bloodtype,foodName);
				map.put("result",true);
			}else{
				map.put("result",false);
				map.put("message","We do not have your basic information. Please enter a basic information.");
			}
		}catch(Exception e){
			map.put("result",false);
			map.put("message",e.getMessage());
		}
	
	
		map.put("List", List);
	/*	map.put("currentPage", page);
		map.put("itemCount", count);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);*/

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;

	}
	
	// 음식 검색 상세 화면 
		@RequestMapping("/m/search_food_view.go")
		public String searchViewController(

				@RequestParam(value = "foodSeq", required = false, defaultValue = "") int foodSeq,
				@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
				HttpServletResponse res) {
			Map<String, Object> map = new HashMap<String, Object>();

			Vfood vf=null;
			int count=0;
			try{
				User user= userDao.getUser(userId);
				String bloodtype=user.getBloodGroups();
			
				if(!bloodtype.equals("")){
					
					vf = foodDao.getFoodV(foodSeq,bloodtype);
					map.put("result",true);
				}else{
					map.put("result",false);
					map.put("message","We do not have your basic information. Please enter a basic information.");
				}
			}catch(Exception e){
				map.put("result",false);
				map.put("message",e.getMessage());
			}
		
		
			map.put("food", vf);
		/*	map.put("currentPage", page);
			map.put("itemCount", count);
			map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
			map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);*/

			JSONObject jsonObject = JSONObject.fromObject(map);
			Response.responseWrite(res, map);
			return null;

		}
		
	// 미션
	@RequestMapping("/m/mission_list.go")
	public String MissionListController(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		List<Mission> missionList = null;
		double missionCount = 0;

		if (userId.equals("")) {
			missionList = missionDao.getMissionList("App","","","","","",page, ITEM_COUNT_PER_PAGE);
			missionCount = missionDao.getMissionCount("App","","","");
		} else {
			missionList = missionDao.getMissionMyList(userId, page, ITEM_COUNT_PER_PAGE);
			missionCount = missionDao.getMissionMyCount(userId);

		}
		
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("missionList", missionList);
		map.put("currentPage", page);
		map.put("itemCount", missionCount);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, map);
		return null;

	}

	// 미션 참여하기
	@RequestMapping("/m/mission_join.go")
	public String MissionJoinController(
			@RequestParam(value = "missionSeq", required = false, defaultValue = "0") int missionSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		int count = 0;

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			count = missionUserDao.getMissionUserChk(missionSeq, userId);

			if (count == 0) {
				MissionUser mu = new MissionUser();
				mu.setUserId(userId);
				mu.setMissionSeq(missionSeq);

				missionUserDao.addMissionUser(mu);

				map.put("result", true);
				map.put("message", "complete");
			} else {
				map.put("result", false);
				map.put("message", "you are already registered");
			}
		} catch (Exception e) {
			map.put("result", false);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 미션 상세보기
	@RequestMapping("/m/mission_view.go")
	public String MissionViewController(
			@RequestParam(value = "missionSeq", required = false, defaultValue = "0") int missionSeq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			HttpServletResponse res) {

		Mission ms = missionDao.getMission(missionSeq);
		
		
		int status=missionUserDao.getMissionUserChk(missionSeq, userId);
		
		List commentList = missionCommentDao.getMissionCommentList(missionSeq, 0, page, ITEM_COUNT_PER_PAGE);
		int count = missionCommentDao.getMissionCommentCount(missionSeq, 0);
		ms.setCommentList(commentList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("ms", ms);
		map.put("status", status);
		map.put("currentPage", page);
		map.put("itemCount", count);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 미션 댓글상세
	@RequestMapping("/m/mission_comment_view.go")
	public String MissionCommentViewController(
			@RequestParam(value = "missionCommentSeq", required = false, defaultValue = "0") int missionCommentSeq,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, HttpServletResponse res) {

		MissionComment mc = missionCommentDao.getMissionComment(missionCommentSeq);
		List commentList = missionCommentDao.getMissionCommentList(mc.getMissionSeq(), missionCommentSeq, page,
				ITEM_COUNT_PER_PAGE);
		int count = missionCommentDao.getMissionCommentCount(mc.getMissionSeq(), missionCommentSeq);
		mc.setCommentList(commentList);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("mc", mc);
		map.put("currentPage", page);
		map.put("itemCount", count);
		map.put("ITEM_COUNT_PER_PAGE", ITEM_COUNT_PER_PAGE);
		map.put("PAGE_COUNT_PER_PAGING", PAGE_COUNT_PER_PAGING);

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 미션 댓글 쓰기 수정
	@RequestMapping("/m/mission_comment_edit.go")
	public String MissionCommentEditController(
			@RequestParam(value = "missionSeq", required = false, defaultValue = "0") int missionSeq,
			@RequestParam(value = "missionCommentSeq", required = false, defaultValue = "0") int missionCommentSeq,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "bbsHeader", required = false, defaultValue = "") String bbsHeader,
			@RequestParam(value = "missionTitle", required = false, defaultValue = "") String missionTitle,
			@RequestParam(value = "missionContents", required = false, defaultValue = "") String missionContents,
			@RequestParam(value = "photo", required = false, defaultValue = "") String photo,
			@RequestParam(value = "thumb", required = false, defaultValue = "") String thumb,
			@RequestParam(value = "linkUrl", required = false, defaultValue = "") String linkUrl,
			@RequestParam(value = "rlevel", required = false, defaultValue = "0") int rlevel, // 댓글
																								// 0
																								// 댓글의댓글
																								// 1
																								// 이상
			@RequestParam(value = "rCommentSeq", required = false, defaultValue = "0") int rCommentSeq,
			HttpServletResponse res) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = false;
		String message = "";
		try {
			FILE_LOCAL_PATH = FILE_ROOT;

			String[] photoArr = photo.split(",");
			String photoString = "";

			MissionComment mc = null;
			String beforefile = "";
			int beforecount = 0;
			int chk = 0;

			chk = missionUserDao.getMissionUserChk(missionSeq, userId);
			Mission mission = missionDao.getMission(missionSeq);
			if(mission.getMissionSeq()!=0){
				if (chk > 0) {
					if (photoArr.length > 0) {
						photoString = fileJson(photo, thumb);
					}
					if (missionCommentSeq < 1) {// 등록
						mc = new MissionComment();
						mc.setUserId(userId);
						mc.setMissionContents(missionContents);
						mc.setMissionTitle(missionTitle);
						mc.setLinkUrl(linkUrl);
						mc.setBbsHeader(bbsHeader);
						mc.setFiles(photoString);
						int count=0;
						if(!photoString.equals("")){
							count=photoArr.length-1;
						}
						mc.setFileCount(count);
						mc.setRLevel(rlevel);
						mc.setMissionSeq(missionSeq);
						mc.setRCommentSeq(rCommentSeq);
						missionCommentDao.addMissionComment(mc);
						result = true;
						
						String txt=txtLength(mission.getTitle());
						List<User>list = missionUserDao.getMissionUserPushList(missionSeq);
						
						for(User user : list){
							if(!user.getUserId().equals(userId)){
								push(user.getUserId(), missionSeq, "New Mission Comment : "+txt, Push.MSG_TYPE_SEND_MISSION_COMMENT, userId);
							}
							
						}
						
	
					} else {
						mc = missionCommentDao.getMissionComment(missionCommentSeq);
						beforefile = mc.getFiles();
						beforecount = mc.getFileCount();
						mc.setUserId(userId);
						mc.setMissionContents(missionContents);
						mc.setMissionTitle(missionTitle);
						mc.setLinkUrl(linkUrl);
						mc.setBbsHeader(bbsHeader);
						mc.setFiles(photoString);
						int count=0;
						if(!photoString.equals("")){
							count=photoArr.length-1;
						}
						mc.setFileCount(count);
						mc.setRLevel(rlevel);
						mc.setMissionSeq(missionSeq);
						mc.setRCommentSeq(rCommentSeq);
						missionCommentDao.updateMissionComment(mc);
						result = true;
				
	
					}
					if(!beforefile.equals(photoString)){
						if(!beforefile.equals("")){
							filedelete(beforefile);
						}
					}
					map.put("result", result);
					map.put("message", "complete");
				} else {
					map.put("result", result);
					map.put("message", "A non-registered users");
				}
			}else{
				map.put("result", result);
				map.put("message", "Can not search Mission!");
			}

		} catch (Exception e) {
			map.put("result", result);
			map.put("message", e.getMessage());
		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}

	// 미션 삭제
	@RequestMapping("/m/mission_delete.go")
	public String missiondelController(Model model, HttpServletResponse res, HttpSession session,
			@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
			@RequestParam(value = "missionSeq", required = false, defaultValue = "0") int missionSeq,
			@RequestParam(value = "missionCommentSeq", required = false, defaultValue = "0") int missionCommentSeq) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {

			if (missionCommentSeq == 0) { // 본글 삭제

				Mission ms = missionDao.getMission(missionSeq);
				if (!ms.getFileName().equals("")) {
					String files = ms.getFileName();
					filedelete(files);
				}
				// 댓글 전체 가져오기
				List<MissionComment> list = missionCommentDao.getMissionCommentListAll(missionSeq);

				for (int i = 0; i < list.size(); i++) {
					MissionComment mc = list.get(i);
					if (mc.getFileCount() != 0) {
						filedelete(mc.getFiles());
					}

				}
				missionDao.deleteMission(missionSeq);
				missionCommentDao.deleteMissionCommentAll(missionSeq);
				missionUserDao.deleteMissionUser(missionSeq);
				map.put("type", 1);
			} else {// 댓글 삭제
				MissionComment mc = missionCommentDao.getMissionComment(missionCommentSeq);
				if (mc.getFileCount() != 0) {
					filedelete(mc.getFiles());
				}
				List<MissionComment> comment = missionCommentDao.getMissionCommentListComment(missionCommentSeq);
				for (int i = 0; i < comment.size(); i++) {
					MissionComment mcomment = comment.get(i);
					if (mcomment.getFileCount() != 0) {
						filedelete(mcomment.getFiles());
					}
				}

				missionCommentDao.deleteMissionComment(missionCommentSeq);
				missionCommentDao.deleteRMissionComment(missionCommentSeq);
				map.put("type", 2);
			}

			map.put("message", "complete");
			map.put("result", true);

		} catch (Exception e) {

			map.put("result", false);
			map.put("message", e.getMessage());

		}

		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);
		return null;
	}
		
		
		
		////////////////////////////////////////////////////////////////
	
	////////////test
	// 미션 등록/수정
	@RequestMapping("/m/mission_edit_do.go")
	public String MissionEditController(
		@RequestParam(value = "missionSeq", required = false, defaultValue = "0") int missionSeq,
		@RequestParam(value = "sendPush", required = false, defaultValue = "0") int sendPush,
		@RequestParam(value = "title", required = false, defaultValue = "") String title,
		@RequestParam(value = "contentsText", required = false, defaultValue = "") String contentsText,
		@RequestParam(value = "visible", required = false, defaultValue = "0") int visible,
		@RequestParam(value = "fileName", required = false, defaultValue = "") String fileName,
		@RequestParam(value = "thumb", required = false, defaultValue = "") String thumb,
		@RequestParam(value = "missionStartDate", required = false, defaultValue = "") String missionStartDate,
		@RequestParam(value = "missionEndDate", required = false, defaultValue = "") String missionEndDate,
		@RequestParam(value = "missionPushTime", required = false, defaultValue = "") String missionPushTime,
		HttpServletResponse res,HttpSession hs) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result =false;
		String message ="";
		try{
			String userId = (String)hs.getAttribute("USER_ID");
			FILE_LOCAL_PATH = FILE_ROOT ;
			String [] photoArr = fileName.split(",");
			String photoString="";
			if(photoArr.length>1){
				photoString = fileJson(fileName, thumb);
			}
			
			Mission ms=null;
			String beforefile="";
			int beforecount =0;
			if(missionSeq <1){//등록
				ms = new Mission();
				ms.setUserId(userId);
				ms.setSendPush(sendPush);
				ms.setTitle(title);
				ms.setContentsText(contentsText);
				ms.setVisible(visible);
				ms.setFileName(photoString);
				ms.setMissionStartDate(missionStartDate);
				ms.setMissionEndDate(missionEndDate);
				ms.setMissionPushTime(missionPushTime);
				missionDao.addMission(ms);
				result=true;
			
			}else{
				ms=missionDao.getMission(missionSeq);
				beforefile =ms.getFileName();
		
				ms.setUserId(userId);
				ms.setSendPush(sendPush);
				ms.setTitle(title);
				ms.setContentsText(contentsText);
				ms.setVisible(visible);
				ms.setFileName(photoString);
				ms.setMissionStartDate(missionStartDate);
				ms.setMissionEndDate(missionEndDate);
				ms.setMissionPushTime(missionPushTime);
				missionDao.updateMission(ms);
				result=true;
			
			}
			
			if(!beforefile.equals(photoString)){
				if(!beforefile.equals("")){
					filedelete(beforefile);
				}
			}
			map.put("result", result);
			map.put("message", "complete");
		}catch(Exception e){
			map.put("result", result);
			map.put("message", e.getMessage());
		}
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
	
	////////////test
	// 미션 등록/수정
	@RequestMapping("/m/push.go")
	public String MissionEditController(
	
		@RequestParam(value = "userId", required = false, defaultValue = "") String userId,
	
		HttpServletResponse res,HttpSession hs) {

		Map<String, Object> map = new HashMap<String, Object>();
		boolean result =false;
		String message ="";
		try{
			User uu = userDao.getUser(userId);
			
			if (uu.getUsePushservice()==1&&!uu.getPushkey().equals("")&&uu.getStatus()==1) {
			
				Push push = new Push();
				push.setBadge(1);
				push.setOs(uu.getOsType());
				push.setPushKey(uu.getPushkey());
				push.setMsgType("1");
				push.setUserid(userId);
				push.setStatus(0);
				push.setServiceId("EFOODY");
				push.setPushType(1);							
				push.setMsg("테스트");
				push.setMsgKey("11");
				pushDao.addPush(push);
				result=true;
			}
		
			map.put("result", result);
			map.put("message", "complete");
		}catch(Exception e){
			map.put("result", result);
			map.put("message", e.getMessage());
		}
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		Response.responseWrite(res, jsonObject);

		return null;
	}
		
		///////////////////////////////////////////////////////////////////
		
		String fileJson(String photo,String thumb) throws IOException{
		
			JSONObject jObject = new JSONObject();
			JSONArray jsArray = new JSONArray();
			
			String[] photoArr = photo.split(",");
			String[] thumbArr = thumb.split(",");
			String photoString="";
			if(!photo.equals("")){
				for (int i=0; i<photoArr.length; i++) {
					if(photoArr[i]!=""&&thumbArr[i]!=""){
						File file = new File(FILE_ROOT + photoArr[i]);
						BufferedImage bi = ImageIO.read(file);
						int width=bi.getWidth();
						int height=bi.getHeight();
						
						JSONObject jItem = new JSONObject();
						jItem.put("type", "img");
						jItem.put("width", width);
						jItem.put("height", height);
						jItem.put("thumb", thumbArr[i]);
						jItem.put("file", photoArr[i]);	
						jsArray.add(jItem);
					}
				}
				jObject.put("items", jsArray);
				photoString = jObject.toString();
			}
			
			return photoString;
			
		}
		
		void bbsfiledelete(String beforefile,String newfile)throws IOException{
			
			JSONObject fileObj = JSONObject.fromObject(beforefile);
			JSONObject newfileObj = JSONObject.fromObject(newfile);
			
			JSONArray fileList = fileObj.getJSONArray("items");
			JSONArray newfileList = newfileObj.getJSONArray("items");
			
			for(int i=0;i<fileList.size();i++){
					JSONObject fileNames=(JSONObject)fileList.get(i);
					String fileOrg = fileNames.getString("file");
					String fileThumb = fileNames.getString("thumb");
					
					for(int k=0;k<newfileList.size();k++){
						JSONObject fileNames2=(JSONObject)newfileList.get(i);
						String fileOrg2 = fileNames2.getString("file");
						String fileThumb2 = fileNames2.getString("thumb");
						if(!fileOrg2.equals(fileOrg)){
							FILE_LOCAL_PATH = FILE_ROOT;
							File file = new File(FILE_LOCAL_PATH + fileOrg);
							file.delete();
							
						}
						if(!fileThumb2.equals(fileThumb)){
							File file2 = new File(FILE_LOCAL_PATH + fileThumb);
							file2.delete();
						}
						
					}
			}
		}
		
		
		
		void filedelete(String beforefile)throws IOException{
			
			JSONObject fileObj = JSONObject.fromObject(beforefile);
			JSONArray fileList = fileObj.getJSONArray("items");
			for(int i=0;i<fileList.size();i++){
					JSONObject fileNames=(JSONObject)fileList.get(i);
					String fileOrg = fileNames.getString("file");
					String fileThumb = fileNames.getString("thumb");
					
					FILE_LOCAL_PATH = FILE_ROOT;
					File file = new File(FILE_LOCAL_PATH + fileOrg);
					file.delete();
	
					File file2 = new File(FILE_LOCAL_PATH + fileThumb);
					file2.delete();
					
		
			}
		}
		
		//파일삭제
		public void filedelNow(String files){
			String [] arr = files.split(",");
			for(int i=0; i<arr.length ; i++){
				String fileName = arr[i];
				//본파일
				FILE_LOCAL_PATH = FILE_ROOT + fileName ;
				File file = new File(FILE_LOCAL_PATH);
				file.delete();
				//썸네일
				String  thumpath = fileName.substring(0,fileName.lastIndexOf("/"))+"/thumb"; //경로
				String tumbName = fileName.substring(fileName.lastIndexOf("/")); //사진이름
				String thumb = FILE_ROOT +thumpath + tumbName;
				File file2 = new File(thumb);
				file2.delete();
				
			}
			
			
		}
		//내용 자르기
		public String txtLength(String contents){
			
			
			if(contents.length()>8){
				contents = contents.substring(0, 8)+"...";
			}
			return contents;
		}
		
		//푸시키알림전송
		public void push(String userId,int seq,String pushmsg,String type,String writeId){
			//boolean res = false;
			
			User uu = userDao.getUser(userId);
			
			if (uu.getUsePushservice()==1&&!uu.getPushkey().equals("")&&uu.getStatus()==1) {
			
				Push push = new Push();
				push.setBadge(1);
				push.setOs(uu.getOsType());
				push.setPushKey(uu.getPushkey());
				push.setMsgType(type);
				push.setUserid(userId);
				push.setStatus(0);
				push.setServiceId("EFOODY");
				push.setPushType(1);							
				push.setMsg(pushmsg);
				push.setMsgKey(String.valueOf(seq));
				pushDao.addPush(push);
			}
		}
		
	
}
