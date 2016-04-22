package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Mission;

public class MissionDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper missionMapper = new RowMapper() {
		public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mission mission = new Mission();
			mission.setMissionSeq(rs.getInt("mission_seq"));
			mission.setUserId(rs.getString("user_id"));
			mission.setSendPush(rs.getInt("send_push"));
			mission.setTitle(rs.getString("title"));
			mission.setContentsHtml(rs.getString("contents_html"));
			mission.setContentsText(rs.getString("contents_text"));
			mission.setLinkUrl(rs.getString("link_url"));
			mission.setVisible(rs.getInt("visible"));
			mission.setFileName(rs.getString("file_name"));
			mission.setViewCount(rs.getInt("view_count"));
			mission.setRegDate(rs.getString("reg_date"));
			mission.setMissionStartDate(rs.getString("mission_start_date"));
			mission.setMissionEndDate(rs.getString("mission_end_date"));
			mission.setMissionPushTime(rs.getString("mission_push_time"));
			mission.setCountUser(rs.getInt("countUser"));
			mission.setMissionPushDate1(rs.getInt("mission_push_date1"));
			mission.setMissionPushDate2(rs.getInt("mission_push_date2"));
			mission.setMissionPushDate3(rs.getInt("mission_push_date3"));
			mission.setMissionPushDate4(rs.getInt("mission_push_date4"));
			mission.setMissionPushDate5(rs.getInt("mission_push_date5"));
			mission.setMissionPushDate6(rs.getInt("mission_push_date6"));
			mission.setMissionPushDate7(rs.getInt("mission_push_date7"));
			return mission;
		}
	};
	
	
	private RowMapper VmissionPushMapper = new RowMapper() {
		public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mission mission = new Mission();
			mission.setMissionSeq(rs.getInt("mission_seq"));
			mission.setUserId(rs.getString("user_id"));
			mission.setSendPush(rs.getInt("send_push"));
			mission.setTitle(rs.getString("title"));
			mission.setContentsHtml(rs.getString("contents_html"));
			mission.setContentsText(rs.getString("contents_text"));
			mission.setLinkUrl(rs.getString("link_url"));
			mission.setVisible(rs.getInt("visible"));
			mission.setFileName(rs.getString("file_name"));
			mission.setViewCount(rs.getInt("view_count"));
			mission.setRegDate(rs.getString("reg_date"));
			mission.setMissionStartDate(rs.getString("mission_start_date"));
			mission.setMissionEndDate(rs.getString("mission_end_date"));
			mission.setMissionPushTime(rs.getString("mission_push_time"));
			mission.setUserName(rs.getString("user_name"));
		
			mission.setOsType(rs.getString("os_type"));
			mission.setOsVersion(rs.getString("os_version"));
			mission.setAppVersion(rs.getString("app_version"));
			mission.setDeviceName(rs.getString("device_name"));
			mission.setDeviceId(rs.getString("device_id"));
			mission.setPushkey(rs.getString("pushkey"));
			mission.setUsePushservice(rs.getInt("use_pushservice"));

			mission.setMissionPushDate1(rs.getInt("mission_push_date1"));
			mission.setMissionPushDate2(rs.getInt("mission_push_date2"));
			mission.setMissionPushDate3(rs.getInt("mission_push_date3"));
			mission.setMissionPushDate4(rs.getInt("mission_push_date4"));
			mission.setMissionPushDate5(rs.getInt("mission_push_date5"));
			mission.setMissionPushDate6(rs.getInt("mission_push_date6"));
			mission.setMissionPushDate7(rs.getInt("mission_push_date7"));
			return mission;
		}
	};
	
	/*String userName = "";
	
	String birthday = "";
	int gender = 0;


	String osType = "";
	String osVersion = "";
	String appVersion = "";
	String deviceName = "";
	String deviceId = "";
	String pushkey = "";
	int usePushservice = 0;*/
	
	private RowMapper VmissionUserMapper = new RowMapper() {
		public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Mission mission = new Mission();
			mission.setMissionSeq(rs.getInt("mission_seq"));
			mission.setUserId(rs.getString("user_id"));
			mission.setSendPush(rs.getInt("send_push"));
			mission.setTitle(rs.getString("title"));
			mission.setContentsHtml(rs.getString("contents_html"));
			mission.setContentsText(rs.getString("contents_text"));
			mission.setLinkUrl(rs.getString("link_url"));
			mission.setVisible(rs.getInt("visible"));
			mission.setFileName(rs.getString("file_name"));
			mission.setViewCount(rs.getInt("view_count"));
			mission.setRegDate(rs.getString("reg_date"));
			mission.setMissionStartDate(rs.getString("mission_start_date"));
			mission.setMissionEndDate(rs.getString("mission_end_date"));
			mission.setMissionPushTime(rs.getString("mission_push_time"));
			mission.setUserName(rs.getString("user_name"));
			mission.setBirthday(rs.getString("birthday"));
			mission.setGender(rs.getInt("gender"));
			mission.setOsType(rs.getString("os_type"));
			mission.setOsVersion(rs.getString("os_version"));
			mission.setAppVersion(rs.getString("app_version"));
			mission.setDeviceName(rs.getString("device_name"));
			mission.setDeviceId(rs.getString("device_id"));
			mission.setPushkey(rs.getString("pushkey"));
			mission.setUsePushservice(rs.getInt("use_pushservice"));
			mission.setCountUser(rs.getInt("countUser"));
			mission.setMissionPushDate1(rs.getInt("mission_push_date1"));
			mission.setMissionPushDate2(rs.getInt("mission_push_date2"));
			mission.setMissionPushDate3(rs.getInt("mission_push_date3"));
			mission.setMissionPushDate4(rs.getInt("mission_push_date4"));
			mission.setMissionPushDate5(rs.getInt("mission_push_date5"));
			mission.setMissionPushDate6(rs.getInt("mission_push_date6"));
			mission.setMissionPushDate7(rs.getInt("mission_push_date7"));
			return mission;
		}
	};

	public void addMission(final Mission mission) {
		String query = "" +
				"INSERT INTO T_NF_MISSION " +
				"	( user_id, send_push, title, "
				+ "contents_html, contents_text, link_url,"
				+ " visible, file_name, view_count,"
				+ " reg_date, mission_start_date, mission_end_date,"
				+ " mission_push_time,"
				+ "mission_push_date1,mission_push_date2,mission_push_date3,mission_push_date4,mission_push_date5,mission_push_date6,mission_push_date7) " +
				"VALUES " +
				"	( ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " getDate(), ?, ?,"
				+ " ?,"
				+ " ?,?,?,?,?,?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
		
			mission.getUserId(),
			mission.getSendPush(),
			mission.getTitle(),
			mission.getContentsHtml(),
			mission.getContentsText(),
			mission.getLinkUrl(),
			mission.getVisible(),
			mission.getFileName(),
			mission.getViewCount(),
			mission.getMissionStartDate(),
			mission.getMissionEndDate(),
			mission.getMissionPushTime(),
			mission.getMissionPushDate1(),
			mission.getMissionPushDate2(),
			mission.getMissionPushDate3(),
			mission.getMissionPushDate4(),
			mission.getMissionPushDate5(),
			mission.getMissionPushDate6(),
			mission.getMissionPushDate7()
		});
	}

	public void deleteMission(int mission_seq) {
		String query = "DELETE FROM T_NF_MISSION WHERE mission_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { mission_seq });
	}

	public void updateMission(Mission mission) { 
		String query = "" + 
				"UPDATE T_NF_MISSION SET " +
			
				"	user_id = ?, " +
				"	send_push = ?, " +
				"	title = ?, " +
				"	contents_html = ?, " +
				"	contents_text = ?, " +
				"	link_url = ?, " +
				"	visible = ?, " +
				"	file_name = ?, " +
				"	view_count = ?, " +
			
				"	mission_start_date = ?, " +
				"	mission_end_date = ?, " +
				"	mission_push_time = ?, " +
				" mission_push_date1 = ?, " +
				" mission_push_date2 = ?, " +
				" mission_push_date3 = ?, " +
				" mission_push_date4 = ?, " +
				" mission_push_date5 = ?, " +
				" mission_push_date6 = ?, " +
				" mission_push_date7 = ? " +
				"WHERE mission_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {

			mission.getUserId(),
			mission.getSendPush(),
			mission.getTitle(),
			mission.getContentsHtml(),
			mission.getContentsText(),
			mission.getLinkUrl(),
			mission.getVisible(),
			mission.getFileName(),
			mission.getViewCount(),

			mission.getMissionStartDate(),
			mission.getMissionEndDate(),
			mission.getMissionPushTime(),
			mission.getMissionPushDate1(),
			mission.getMissionPushDate2(),
			mission.getMissionPushDate3(),
			mission.getMissionPushDate4(),
			mission.getMissionPushDate5(),
			mission.getMissionPushDate6(),
			mission.getMissionPushDate7(),
			mission.getMissionSeq()
		});
	}

	public Mission getMission(int mission_seq) {
		String query = "" + 
				"SELECT * "
		
				+" FROM V_NF_MISSION  " +
				" WHERE mission_seq = ? ";
		try{
			return (Mission)this.jdbcTemplate.queryForObject(query, new Object[] { mission_seq }, this.missionMapper);
		}catch(Exception e){
			return new Mission();
		}
	}

	public List<Mission> getMissionList(String type,String keyword,String startDate,String endDate,String colname,String sort,int page, int itemCountPerPage) {
		String con=" where 1=1 ";
		if(!keyword.equals("")){
			con+=" and contents_html like '%"+keyword+"%' or title like '%"+keyword+"%'" ;
		}
		if(!startDate.equals("")&&!endDate.equals("")){
			con+=" and (reg_date > '"+ startDate +"' and reg_date < '" + endDate +"')" ;
		}
		if(type.equals("App")){
			con += "and mission_start_date < getDate() and mission_end_date > getDate() ";
		}
		
		
		
		String order =" mission_seq";
		String order2=" desc ";
		if(!colname.equals("")){
			order=colname+" ";
		}
		if(!sort.equals("")){
			order2=sort+" ";
		}
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by "+order+" "+order2+") as row_seq, "
				+ "			A.*"
			
				+ "		FROM V_NF_MISSION AS A "+con
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		try{
			return (List<Mission>)this.jdbcTemplate.query(query, this.missionMapper);
		}catch(Exception e){
			return new ArrayList<>();
		}
	}
	
	public int getMissionCount(String type,String keyword,String startDate,String endDate) {
		String con=" where 1=1 ";
		if(!keyword.equals("")){
			con+=" and contents_html like '%"+keyword+"%' or title like '%"+keyword+"%'" ;
		}
		if(!startDate.equals("")&&!endDate.equals("")){
			con+=" and (reg_date > '"+ startDate +"' and reg_date < '" + endDate +"')" ;
		}
		if(type.equals("App")){
			con += "and mission_start_date < getDate() and mission_end_date > getDate() ";
		}
		
		
		String query = "" 
				+ "	SELECT count(*) FROM  "
				+ "		 V_NF_MISSION " +con;
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public int getActiveCount() {
		
		String query = "" 
				+ "	SELECT count(*) FROM  "
				+ "		 T_NF_MISSION where mission_start_date < getDate() and mission_end_date > getDate() ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<Mission> getMissionMyList(String userId,int page, int itemCountPerPage) {
		
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by reg_date desc) as row_seq, "
				+ "			A.*, "
				+ "		(select count(*) from t_nf_mission_user where a.mission_seq = mission_seq)as countUser "
				+ "		FROM V_NF_MISSION_USER AS A where user_id= ? "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		try{
			return (List<Mission>)this.jdbcTemplate.query(query,new Object[] { userId }, this.VmissionUserMapper);
		}catch(Exception e){
			return new ArrayList();
		}
	}
	

	
	
	public int getMissionMyCount(String userId) {
		
		String query = "" 
				+ "	SELECT count(*) FROM  "
				+ "		 V_NF_MISSION_USER where user_id = ? ";
		return this.jdbcTemplate.queryForInt(query,new Object[] { userId });
	}
	
	public List<Mission> getMissionListTop(int count) {
		
		String query = "" 
				+ "	SELECT top "+count+" *, "
				+ "		(select count(*) from t_nf_mission_user where a.mission_seq = mission_seq)as countUser "
				+ " 	FROM 	 T_NF_MISSION as a order by reg_date desc";
				
		return (List<Mission>)this.jdbcTemplate.query(query, this.missionMapper);
	}
	
	public List<Mission> getMissionListAll() {
		
		String query = "" 
				+ "	SELECT  *, "
				+ "		(select count(*) from t_nf_mission_user where a.mission_seq = mission_seq)as countUser "
				+ " 	FROM  T_NF_MISSION as a where mission_start_date < getDate() and mission_end_date > getDate()   order by reg_date desc";
				
		return (List<Mission>)this.jdbcTemplate.query(query, this.missionMapper);
	}
	
	public int getLastSeq() {
		String query = " SELECT MAX(mission_seq) FROM T_NF_MISSION ";
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public List<Mission> getMissionPushList(String time, String week) {
		String con="";
		if(week.equals("일")){ //일요일
			con ="and mission_push_date7 =1";
		}else if(week.equals("월")){ //월요일
			con ="and mission_push_date1 =1";
		}else if(week.equals("화")){ //화요일
			con ="and mission_push_date2 =1";
		}else if(week.equals("수")){ //수요일
			con ="and mission_push_date3 =1";
		}else if(week.equals("목")){ //목요일
			con ="and mission_push_date4 =1";
		}else if(week.equals("금")){ //금요일
			con ="and mission_push_date5 =1";
		}else if(week.equals("토")){ //토요일
			con ="and mission_push_date6 =1";
		}
		String query = "" 
				+ "	SELECT  * "
				+ " FROM  V_NF_MISSION_PUSH where mission_push_time = '"+time+"' and send_push=1 and use_pushservice =1 and pushkey !='' " + con +"";
				
		return (List<Mission>)this.jdbcTemplate.query(query, this.VmissionPushMapper);
	}

}
