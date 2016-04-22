package kr.nomad.mars.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Bbs;
import kr.nomad.mars.dto.User;

public class UserDao {
	
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getString("user_type"));
			user.setUserName(rs.getString("user_name"));
			user.setPhoneNumber(rs.getString("phone_number"));
			user.setLatitude(rs.getInt("latitude"));
			user.setLongitude(rs.getInt("longitude"));
			user.setBirthday(rs.getString("birthday"));
			user.setGender(rs.getInt("gender"));
			user.setRegDate(rs.getString("reg_date"));
			user.setLastLogindate(rs.getString("last_logindate"));
			user.setLoginFacebook(rs.getInt("login_facebook"));
			user.setLoginGoogle(rs.getInt("login_google"));
			user.setOsType(rs.getString("os_type"));
			user.setOsVersion(rs.getString("os_version"));
			user.setAppVersion(rs.getString("app_version"));
			user.setDeviceName(rs.getString("device_name"));
			user.setDeviceId(rs.getString("device_id"));
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setStatus(rs.getInt("status"));
			user.setLoginStatus(rs.getInt("login_status"));
			user.setUserMed(rs.getInt("user_med"));
			user.setPhoto(rs.getString("photo"));
			user.setArea(rs.getString("area"));
			user.setHeight(rs.getDouble("height"));
			user.setWeight(rs.getDouble("weight"));
			user.setBloodGroups(rs.getString("blood_groups"));
			return user;
		}
	};

	private RowMapper VpushuserMapper = new RowMapper() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUserId(rs.getString("user_id"));
		
			user.setOsType(rs.getString("os_type"));
	
			user.setPushkey(rs.getString("pushkey"));
			user.setUsePushservice(rs.getInt("use_pushservice"));
			user.setBbsSeq(rs.getInt("bbs_seq"));
			return user;
		}
	};
	public void addUser(final User user) {
		String query = "" +
				"INSERT INTO T_NF_USER " +
				"	(user_id, password, user_type,"
				+ " user_name, phone_number, latitude,"
				+ " longitude, birthday, gender,"
				+ " reg_date, last_logindate, login_facebook,"
				+ " login_google, os_type, os_version,"
				+ " app_version, device_name, device_id,"
				+ " pushkey, use_pushservice, status,"
				+ " login_status, user_med,photo,"
				+ "	area,height,weight,"
				+ "	blood_groups,token) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " getDate(), ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ? , ? ) ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getUserId(),
			user.getPassword(),
			user.getUserType(),
			user.getUserName(),
			user.getPhoneNumber(),
			user.getLatitude(),
			user.getLongitude(),
			user.getBirthday(),
			user.getGender(),
			user.getLastLogindate(),
			user.getLoginFacebook(),
			user.getLoginGoogle(),
			user.getOsType(),
			user.getOsVersion(),
			user.getAppVersion(),
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			user.getUsePushservice(),
			user.getStatus(),
			user.getLoginStatus(),
			user.getUserMed(),
			user.getPhoto(),
			user.getArea(),
			user.getHeight(),
			user.getWeight(),
			user.getBloodGroups(),
			user.getToken()
		});
	}

	public void deleteUser(String user_id) {
		String query = "DELETE FROM T_NF_USER WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { user_id });
	}
	
	public void updateUserphoto(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				
				"	photo = ? " +
		
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getPhoto(),
			user.getUserId()
		});
	}

	public void updateUser(User user) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				 "	area = ? ,"
				+ " height = ?, "
				+ " weight = ?,"
	
				+ "	blood_groups = ?," +
		
			
				"	user_name = ?, " +
				
				"	birthday = ?, " +
				"	gender = ?, " +
				"   phone_number = ?, "+
				"   token = ?, "+
				"	use_pushservice = ? " +
		
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			user.getArea(),
			user.getHeight(),
			user.getWeight(),
			user.getBloodGroups(),	
		
			user.getUserName(),
			
			user.getBirthday(),
			user.getGender(),
			user.getPhoneNumber(),
			user.getToken(),
			user.getUsePushservice(),
			user.getUserId()
		});
	}

	public void updateUserLogin(User user,int Loginstatus) { 
		String query = "" + 
				"UPDATE T_NF_USER SET " +
				"	phone_number = ?, " +
				"	token = ?, " +
				"	os_type = ?, " +
		
				"	device_name = ?, " +
				"	device_id = ?, " +
				"	pushkey = ?, " +
				" login_status = ? "+
	
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			user.getPhoneNumber(),
			user.getToken(),
			user.getOsType(),
		
			user.getDeviceName(),
			user.getDeviceId(),
			user.getPushkey(),
			Loginstatus,
			user.getUserId()
		});
	}
	
	public int getUserLoginchk(String user_id,String key) {
		
		
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? and phone_number = ?";
		return this.jdbcTemplate.queryForInt(query, new Object[] { user_id ,key});
	}
	
	public int getUserchk(String user_id,boolean isActive) {
		String con="";
		
		if(isActive){
			con = " and status = 1 ";  
		}
		
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? "+con;
		return this.jdbcTemplate.queryForInt(query, new Object[] { user_id });
	}
	public User getUserorg(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
		return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper);
	}
	//페이스북 아이디확인
	public int getUserId(String phoneNumber) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_USER " +
				"WHERE phone_number = ? ";
	
			try{
				return this.jdbcTemplate.queryForInt(query, new Object[] { phoneNumber });
			}catch(Exception e){
				return 0;
			}
		
	}
	
	
	public User getUser(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER " +
				"WHERE user_id = ? ";
	
			try{
				return (User)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userMapper);
			}catch(Exception e){
				return new User();
			}
		
	}

	public List<User> getUserList(String keyword,int gender,String sort,String colname,int page, int itemCountPerPage) {
		
		String con=" where user_type=3 ";
		
		if(!keyword.equals("")){
			con+=" and user_name like '%"+keyword+"%' or user_id like '%"+keyword+"%' ";
		}
		if(gender!=0){
			con+=" and gender = "+gender;
		}
		
		String order = " reg_date ";
		String order2=" desc ";
		if(!colname.equals("")){
			order= colname +" ";
		}
		if(!sort.equals("")){
			order2= sort +" ";
		}
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by "+order+order2+") as row_seq, "
				+ "			A.* "
				+ "		FROM T_NF_USER AS A "
				+ con 
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	
	public int getUserCount(String keyword,int gender) {
		
		String con=" where  user_type=3 ";
		
		if(!keyword.equals("")){
			con+=" and user_name like '%"+keyword+"%' or user_id like '%"+keyword+"%' ";
		}
		if(gender!=0){
			con+=" and gender = "+gender;
		}
		
		String query = "" 
				+ "	SELECT count(*)   "
				+ "		FROM T_NF_USER "
				+ con ;
				
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public int getUserAccessCount() {
		
	
		
		String query = "" 
				+ "	SELECT count(*)   "
				+ "		FROM T_NF_USER where user_type =3 and login_status =1  ";
			
				
		return this.jdbcTemplate.queryForInt(query);
	}
	
	public boolean correctId(String userId) {
		String query = "SELECT COUNT(*) FROM T_NF_USER WHERE user_id = ? ";
		return (this.jdbcTemplate.queryForInt(query, new Object[] { userId }) == 1);
	}
	
	/**
	 * 아이디 패스워드 일치 확인
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean correctPw(String userId, String password) {		
	    String query = "SELECT count(*) AS id_cnt FROM T_NF_USER WHERE user_id = ? and password = ?";
	    try {
		    return this.jdbcTemplate.queryForInt(query, new Object[] { userId, password}) > 0;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 비밀번호 변경
	 * @param userId
	 * @param userPassword
	 */
	public boolean updateUserPassword(String userId, String password) { 
		boolean res= false;
		String query = "" + 
				"	UPDATE T_NF_USER  " +
				"	SET password = ? " +
				"	WHERE user_id = ? ";
		try {
			this.jdbcTemplate.update(query, new Object[] { password, userId });
			res=true;
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			return res;
		}
		
	}
	
	public List<User> getUserListTop(int count) {
		
		String con=" where user_type=3 ";
		
		
		String query = "" 
				+ "	SELECT top "+count+" * FROM  "
				+ "	 T_NF_USER "
				+ con +" order by reg_date desc";
				
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	
	public List<User> getPushUserList() {
		
	
		String query = "" 
				+ "	SELECT * FROM  "
				+ "		 T_NF_USER where use_pushservice =1 and pushkey !='' ";
				
		return (List<User>)this.jdbcTemplate.query(query, this.userMapper);
	}
	
	public List<User> getBbsPushList(int bbsSeq) {
		String query = "" 
				+ "	SELECT * FROM  "
				+ "		 V_NF_BBSNCOMMENT where bbs_seq = ?  ";
		return (List<User>)this.jdbcTemplate.query(query,  new Object[] { bbsSeq }, this.VpushuserMapper);
	}
}
