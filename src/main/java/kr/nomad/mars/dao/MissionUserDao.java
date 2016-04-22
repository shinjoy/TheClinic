package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.MissionUser;
import kr.nomad.mars.dto.User;
public class MissionUserDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper missionuserMapper = new RowMapper() {
		public MissionUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			MissionUser missionuser = new MissionUser();
			missionuser.setMissionSeq(rs.getInt("mission_seq"));
			missionuser.setUserId(rs.getString("user_id"));
			missionuser.setRegDate(rs.getString("reg_date"));
			return missionuser;
		}
	};

	private RowMapper VmissionuserMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setMissionSeq(rs.getInt("mission_seq"));
			user.setUserId(rs.getString("user_id"));
			user.setRegDate(rs.getString("reg_date"));
			user.setUserId(rs.getString("user_id"));
		
			user.setUserType(rs.getString("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setPhoneNumber(rs.getString("phone_number"));

			user.setBirthday(rs.getString("birthday"));
			user.setGender(rs.getInt("gender"));
			user.setRegDate(rs.getString("reg_date"));
	
			user.setPhoto(rs.getString("photo"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
		
			return user;
		}
	};
	
	public void addMissionUser(final MissionUser missionuser) {
		String query = "" +
				"INSERT INTO T_NF_MISSION_USER " +
				"	(mission_seq, user_id, reg_date) " +
				"VALUES " +
				"	(?, ?, getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
			missionuser.getMissionSeq(),
			missionuser.getUserId()
		});
	}

	public void deleteMissionUser(int mission_seq) {
		String query = "DELETE FROM T_NF_MISSION_USER WHERE mission_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { mission_seq });
	}
	
	public void deleteMissiondropUser(String userId) {
		String query = "DELETE FROM T_NF_MISSION_USER WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updateMissionUser(MissionUser missionuser) { 
		String query = "" + 
				"UPDATE T_NF_MISSION_USER SET " +
				"	mission_seq = ?, " +
				"	user_id = ?, " +
				"	reg_date = ? " +
				"WHERE mission_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			missionuser.getMissionSeq(),
			missionuser.getUserId(),
			missionuser.getRegDate()
		});
	}

	public MissionUser getMissionUser(String mission_seq) {
		String query = "" + 
				"SELECT mission_seq, user_id, reg_date " +
				"FROM T_NF_MISSION_USER " +
				"WHERE mission_seq = ? ";
		return (MissionUser)this.jdbcTemplate.queryForObject(query, new Object[] { mission_seq }, this.missionuserMapper);
	}


	public int getMissionUserChk(int mission_seq,String userId) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_MISSION_USER " +
				"WHERE mission_seq = ? and user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { mission_seq,userId });
	}
	
	public List<User> getMissionUserList(int missionSeq,int page, int itemCountPerPage) {
		String query = "" +
				"select * from ("
				+ " select "
				+ " ROW_NUMBER() OVER(order by reg_date desc) as row_seq,  "
				+ " A.* "
				+ "  from V_NF_MISSION_USER AS A where mission_seq = ? "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		try{
			return (List<User>)this.jdbcTemplate.query(query, new Object[] { missionSeq }, this.VmissionuserMapper);
		}catch(Exception e){
			return new ArrayList<>();
		}
	}
	
	public List<User> getMissionUserPushList(int missionSeq) {
		String query = "" +
				"select * from "
				
				+ "   V_NF_MISSION_USER where mission_seq = ? ";
				
		try{
			return (List<User>)this.jdbcTemplate.query(query, new Object[] { missionSeq }, this.VmissionuserMapper);
		}catch(Exception e){
			return new ArrayList<>();
		}
	}
	public int getMissionUserCount(int missionSeq) {
		String query = "" +
				"select count(*) from "
				
				+ "   V_NF_MISSION_USER  where mission_seq = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { missionSeq });
	}
	

}